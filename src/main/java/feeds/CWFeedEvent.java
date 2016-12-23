/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package feeds;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Index;
import org.mongodb.morphia.annotations.Indexes;



/**
 * Master feed 
 */
@Entity(value = "my_cw_feed_events")
//@Indexes(@Index("feedSourceType, feedSourceId"))
public class CWFeedEvent implements Serializable {
	private static final long serialVersionUID = -500275205658710615L;

	public static final String CONTEXT_INBOX = "inbox";

	@Id 
	private ObjectId id;
	private Date timestamp;
	private String eventType;
	private CWFeedObject eventObject;

	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	
	
	public String getEventType() {
		return eventType;
	}
	
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public CWFeedObject getEventObject() {
		return eventObject;
	}
	
	public void setEventObject(CWFeedObject eventObject) {
		this.eventObject = eventObject;
	}
}
