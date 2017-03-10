/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package morphiatest;

import java.util.Map.Entry;

import org.apache.log4j.Logger;
import com.mongodb.BasicDBObject;
import com.mongodb.CommandResult;
import com.mongodb.DB;
import com.mongodb.DBObject;

public class StoredFunctionTest extends Tester {
	
	static Logger logger = Logger.getLogger(StoredFunctionTest.class.getName());
	
	public static void main(String[] args) {
		logger.info("********** STARTING " + StoredFunctionTest.class.getName() + " *************");
		configure();
		
DB db = ds.getDB();
final DBObject loadScripts = new BasicDBObject();
loadScripts.put("eval", "db.loadServerScripts();");
CommandResult result = db.command(loadScripts);
System.out.println(result.toString());

DBObject echoFunctionCall = new BasicDBObject();
echoFunctionCall.put("eval", "echoFunction('Fooooooooo');");
result = db.command(echoFunctionCall);
System.out.println(result.toString());

echoFunctionCall.put("eval", "echoFunction('Baaaaaar');");
result = db.command(echoFunctionCall);
System.out.println(result.toString());
	}
}
