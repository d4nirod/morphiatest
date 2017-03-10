package morphiatest;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.mongodb.morphia.AdvancedDatastore;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;

import com.farmerswife.cloudwife.entities.CWEmailMessage;
import com.farmerswife.cloudwife.entities.CWFailedEmailMessage;

public class AlternateEmailTester extends Tester {
	static Logger logger = Logger.getLogger(AlternateEmailTester.class.getName());

	public static void main(String[] args) {
		logger.info("********** STARTING " + AlternateEmailTester.class.getName() + " *************");

		configure();
		Query<CWEmailMessage> q = ds.createQuery(CWEmailMessage.class).field("_id").equal(new ObjectId("58b5ad35162c3f01e72e5a54"));
		CWEmailMessage msg = q.get();
//		msg.setId(null);
//		AdvancedDatastore ads = (AdvancedDatastore) ds;
//		Key<CWEmailMessage> key = ads.save("tmp_email", msg);
//		logger.info("results: " + key.getId());
		CWFailedEmailMessage failMsg = CWFailedEmailMessage.copyFrom(msg);
		Key<CWFailedEmailMessage> expKey = ds.save(failMsg);
		logger.info("saved id: " + expKey.getId());
		CWFailedEmailMessage cwFailedEmailMessage = ds.createQuery(CWFailedEmailMessage.class).field("_id").equal(expKey.getId()).get();
		logger.info("fetched id: " + cwFailedEmailMessage.getId());
		
	}

}
