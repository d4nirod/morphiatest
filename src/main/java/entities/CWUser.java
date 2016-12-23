/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package entities;

import java.io.Serializable;
import java.util.Date;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value = "cw_user")
public class CWUser implements Serializable {
	private static final long serialVersionUID = -1191745713627010198L;

	@Id  
	private ObjectId id;
	
	private boolean active;
	private String firstName;
	private String lastName;
	private String password;
	private String timezone;
	private boolean hasThumbnail = false;
	private Date inboxReadTimestamp;
	
	public CWUser() {
		this.active = false;
	}
	
	// Getters and setter
	
	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}

	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getTimezone() {
		return timezone;
	}
	
	public void setTimezone(String timezone) {
		this.timezone = timezone;
	}

	public boolean getHasThumbnail() {
		return hasThumbnail;
	}
	
	public void setHasThumbnail(boolean hasThumbnail) {
		this.hasThumbnail = hasThumbnail;
	}
	

	public Date getInboxReadTimestamp() {
		return inboxReadTimestamp;
	}
	
	public void setInboxReadTimestamp(Date inboxReadTimestamp) {
		this.inboxReadTimestamp = inboxReadTimestamp;
	}
}
