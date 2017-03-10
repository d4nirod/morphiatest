package morphiatest;

import org.apache.log4j.Logger;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.farmerswife.cloudwife.entities.CWUser;
import com.mongodb.BasicDBObject;

public class RemoveUserDeviceTester extends Tester {
	static Logger logger = Logger.getLogger(RemoveUserDeviceTester.class.getName());

	public static void main(String[] args) {
		logger.info("********** STARTING " + DeviceTokenAggregationTester.class.getName() + " *************");
		configure();
		Query<CWUser> q = ds.createQuery(CWUser.class).field("devices.apnsRejectionReason").exists();
		BasicDBObject condition = new BasicDBObject("apnsRejectionReason", new BasicDBObject("$exists", true));
		UpdateOperations<CWUser> uop = ds.createUpdateOperations(CWUser.class)
				.removeAll("devices", condition);

		UpdateResults results = ds.update(q,uop);
		logger.info("Updated docs: " + results.getUpdatedCount());
	}

}
