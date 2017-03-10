package morphiatest;

import org.apache.log4j.Logger;
import org.jongo.Find;
import org.jongo.Jongo;
import org.jongo.MongoCollection;
import org.jongo.MongoCursor;
import org.jongo.ResultHandler;

import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class JongoTest {

	public static void main(String[] args) {
		String dbURI = "mongodb://127.0.0.1:27017";
		String dbName = "cloudwife";
		MongoClient mongo = new MongoClient(new MongoClientURI(dbURI));
		DB db = mongo.getDB(dbName);
		Jongo jongo = new Jongo(db);
		
		
		MongoCollection tasks = jongo.getCollection("cw_task");
		/*		
 		db.cw_task.find( { $and: [ 
		                          { $or: [ { "assignees": {$exists: false} }, { "assignees": null }, { "assignees": { $size: 1 } } ] },
		                          { "doneBehavior": "ALL" } ]
		                      });
		*/
		Find found = tasks.find("{ $and: [ " +
				"{ $or: [ { 'assignees': {$exists: false} }, { 'assignees': null }, { 'assignees': { $size: 1 } } ] }," +
				"{ 'doneBehavior': 'ALL' }" +
		"]}");
		
		
		MongoCursor<DBObject> map = found.map(new ResultHandler<DBObject>() {
			@Override
			public DBObject map(DBObject result) {
				return result;
			}
	    });
		
		while(map.hasNext()) {
			DBObject dbo = map.next();
			System.out.println(dbo.toString());
		}
	}

}
