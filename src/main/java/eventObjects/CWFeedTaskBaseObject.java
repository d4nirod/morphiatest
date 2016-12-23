/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package eventObjects;

import org.bson.types.ObjectId;

import feeds.CWFeedObject;

public class CWFeedTaskBaseObject extends CWFeedObject {
	private static final long serialVersionUID = 937638923920359199L;

	protected ObjectId taskId;
	protected String taskName;
	

	protected CWTaskCommentObject taskCommentObject;
	
	
	public ObjectId getTaskId() {
		return taskId;
	}

	public void setTaskId(ObjectId taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}
	
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	
	public CWTaskCommentObject getTaskCommentObject() {
		return taskCommentObject;
	}
	
	public void setTaskCommentObject(CWTaskCommentObject taskCommentObject) {
		this.taskCommentObject = taskCommentObject;
	}
	
}