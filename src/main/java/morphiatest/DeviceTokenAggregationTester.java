package morphiatest;

import static org.mongodb.morphia.aggregation.Projection.expression;
import static org.mongodb.morphia.aggregation.Projection.projection;

import java.util.Arrays;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.mongodb.morphia.aggregation.AggregationPipeline;
import org.mongodb.morphia.query.Query;

import com.farmerswife.cloudwife.entities.CWUser;
import com.farmerswife.cloudwife.entities.CWUserDevice;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.BulkWriteOperation;
import com.mongodb.BulkWriteResult;

public class DeviceTokenAggregationTester extends Tester {
	static Logger logger = Logger.getLogger(DeviceTokenAggregationTester.class.getName());

	public static void main(String[] args) {
		logger.info("********** STARTING " + DeviceTokenAggregationTester.class.getName() + " *************");
		configure();
		Query<CWUser> qRejection = ds.createQuery(CWUser.class).field("devices.apnsRejectionReason").exists();

		BasicDBList compareFields = new BasicDBList();
		compareFields.addAll(Arrays.asList("$devices.lastUpdated", "$devices.invalidationTimestamp"));

		Query<CWUser> qDeviceExists = ds.createQuery(CWUser.class).field("devices").notEqual(null);
		
		// Get users whose device token has been unregistered after the last activity
		// TODO: check if need to use AggregationOptions to use cursor mode instead of inline
		AggregationPipeline aggregation = ds.createAggregation(CWUser.class)
				.match(qRejection)
				.unwind("devices")
				.project(projection("devices", 
						expression("$cond", new BasicDBObject()
								.append("if", new BasicDBObject("$lt", compareFields))
								.append("then", "$devices")
								.append("else", null))))
				.match(qDeviceExists);
		
		Iterator<CWUser> results = aggregation.aggregate(CWUser.class);
		
		BulkWriteOperation bulkOp = ds.getCollection(CWUser.class).initializeUnorderedBulkOperation();
		while(results.hasNext()) {
			CWUser user = results.next();
			if (user.getDevices() == null || user.getDevices().isEmpty()) continue;
			CWUserDevice device = user.getDevices().get(0);
			
			BasicDBObject condition = new BasicDBObject("deviceToken", device.getDeviceToken());
			BasicDBObject update = new BasicDBObject("$pull", new BasicDBObject("devices", condition));
			bulkOp.find(new BasicDBObject("_id", user.getId())).updateOne(update);
		}
		
		BulkWriteResult result = bulkOp.execute();
		logger.info("Matched count: " + result.getMatchedCount() + ", modified count: " + result.getModifiedCount());
	}

}
