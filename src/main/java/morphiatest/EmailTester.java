package morphiatest;

import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.mongodb.morphia.query.Query;
import org.mongodb.morphia.query.UpdateOperations;
import org.mongodb.morphia.query.UpdateResults;

import com.farmerswife.cloudwife.entities.CWEmailMessage;

public class EmailTester extends Tester {
	static Logger logger = Logger.getLogger(EmailTester.class.getName());

	public static void main(String[] args) {
		logger.info("********** STARTING " + EmailTester.class.getName() + " *************");

		configure();
		Query<CWEmailMessage> q = ds.createQuery(CWEmailMessage.class).field("_id").equal(new ObjectId("58b5ad35162c3f01e72e5a54"));
		UpdateOperations<CWEmailMessage> uop = ds.createUpdateOperations(CWEmailMessage.class)
				.set("type", "blah")
				.set("retryCount", 6)
				.set("lastRetry", new Date())
				.set("nextRetry", DateUtils.addMinutes(new Date(), 10))
				.set("errorMessage", "blah Error msg");
				
		UpdateResults res = ds.update(q, uop);
		logger.info("results: " + res.getUpdatedCount());

	}

}
