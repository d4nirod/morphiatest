/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package morphiatest;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.mongodb.morphia.Key;
import org.mongodb.morphia.query.Query;

import entities.CWTask;

public class MapperIssueTest extends Tester {
	
	static Logger logger = Logger.getLogger(MapperIssueTest.class.getName());
	
	public static void main(String[] args) {
		logger.info("********** STARTING " + MapperIssueTest.class.getName() + " *************");
		configure();
		Object taskId = ds.save(new CWTask("Testing Mapper " + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS").format(new Date()))).getId();
		CWTask task = ds.get(CWTask.class, taskId);
		logger.info("'" + task.name + "' saved with ID: " + task.id);
		
		task.assignees = new ArrayList<Key<CWTask>>();
		task.assignees.add(new Key<>(CWTask.class, "cw_task", task.id));
		
		Query<CWTask> q = ds.createQuery(CWTask.class).field("_id").equal(task.id);
        ds.update(q, ds.createUpdateOperations(CWTask.class).set("assignees", task.assignees));
	}
}
