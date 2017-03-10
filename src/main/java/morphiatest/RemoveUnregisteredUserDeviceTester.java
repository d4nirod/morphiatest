package morphiatest;

import static org.mongodb.morphia.aggregation.Projection.expression;
import static org.mongodb.morphia.aggregation.Projection.projection;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.mongodb.morphia.aggregation.AggregationPipeline;
import org.mongodb.morphia.query.Query;

import com.farmerswife.cloudwife.entities.CWUser;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;

public class RemoveUnregisteredUserDeviceTester extends Tester {
	static Logger logger = Logger.getLogger(RemoveUnregisteredUserDeviceTester.class.getName());

	public static void main(String[] args) {
		logger.info("********** STARTING " + DeviceTokenAggregationTester.class.getName() + " *************");
		configure();
		Set<String> deviceTokens = new HashSet<>();
		Set<ObjectId> userIds = new HashSet<>();
		Query<CWUser> qRejectedDevices = ds.createQuery(CWUser.class).field("devices.apnsRejectionReason").exists();
		BasicDBList compareFields = new BasicDBList();
		compareFields.addAll(Arrays.asList("$devices.lastUpdated", "$devices.invalidationTimestamp"));
		Query<CWUser> qDeviceNotNull = ds.createQuery(CWUser.class).field("devices").notEqual(null);

		// Get users whose device token has been unregistered after the last activity
		// TODO: check if need to use AggregationOptions to use cursor mode instead of inline
		AggregationPipeline aggregation = ds.createAggregation(CWUser.class)
				.match(qRejectedDevices)
				.unwind("devices")
				.project(projection("devices", 
						expression("$cond", new BasicDBObject()
								.append("if", new BasicDBObject("$lt", compareFields))
								.append("then", "$devices")
								.append("else", null))))
				.match(qDeviceNotNull);
		Iterator<CWUser> results = aggregation.aggregate(CWUser.class);
		
		BulkWriteOperation bulkOp = ds.getCollection(CWUser.class).initializeUnorderedBulkOperation();
		while(results.hasNext()) {
			CWUser user = results.next();
			if (user.getDevices() == null || user.getDevices().isEmpty()) continue;
			String deviceToken = user.getDevices().get(0).getDeviceToken();
			deviceTokens.add(deviceToken);
			userIds.add(user.getId());
			BasicDBObject condition = new BasicDBObject("deviceToken", deviceToken);
			BasicDBObject update = new BasicDBObject("$pull", new BasicDBObject("devices", condition));
			bulkOp.find(new BasicDBObject("_id", user.getId())).updateOne(update);
		}
		
		BulkWriteResult res = bulkOp.execute();
		log.info("Updated docs: " + res.getModifiedCount());
		log.info("Removed unregistered device tokens [" + 
				StringUtils.join(deviceTokens, ", ") + "] from users [" +
				StringUtils.join(userIds, ", ") + "]");
	}

}
