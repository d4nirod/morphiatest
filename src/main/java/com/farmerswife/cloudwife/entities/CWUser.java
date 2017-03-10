/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package com.farmerswife.cloudwife.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;


@Entity(value = "cw_user")
public class CWUser implements Serializable {
	private static final long serialVersionUID = -1191745713627010198L;
	
	public static final boolean DEFAULT_EMAIL_NOTIFICATIONS = true;
	public static final boolean DEFAULT_PUSH_NOTIFICATIONS = true;
	public static final boolean DEFAULT_OPEN_LINKS_IN_APP = false; 

	@Id  
	private ObjectId id;
	
	private boolean active;
	private String firstName;
	private String lastName;
	private String password;
	private String timezone;
	private boolean hasThumbnail = false;
	private Date inboxReadTimestamp;
	
	private boolean emailNotificationsEnabled = DEFAULT_EMAIL_NOTIFICATIONS;
	private boolean pushNotificationsEnabled = DEFAULT_PUSH_NOTIFICATIONS;
	private boolean emailNotificationsOpenLinksInApp = DEFAULT_OPEN_LINKS_IN_APP;
	
	@Embedded
	private List<CWUserEmail> emails = new ArrayList<>();
	
	@Embedded
	private List<CWUserDevice> devices = new ArrayList<>();
	
	public CWUser() {
		this.active = false;
	}
	
	// Getters and setter
	
	public List<CWUserEmail> getEmails() {
		return emails;
	}

	public void setEmails(List<CWUserEmail> emails) {
		this.emails = emails;
	}

	public List<CWUserDevice> getDevices() {
		return devices;
	}

	public void setDevices(List<CWUserDevice> devices) {
		this.devices = devices;
	}

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

	public boolean isPushNotificationsEnabled() {
		return pushNotificationsEnabled;
	}

	public void setPushNotificationsEnabled(boolean pushNotificationsEnabled) {
		this.pushNotificationsEnabled = pushNotificationsEnabled;
	}

	public boolean isEmailNotificationsEnabled() {
		return emailNotificationsEnabled;
	}

	public void setEmailNotificationsEnabled(boolean emailNotificationsEnabled) {
		this.emailNotificationsEnabled = emailNotificationsEnabled;
	}

	public boolean isEmailNotificationsOpenLinksInApp() {
		return emailNotificationsOpenLinksInApp;
	}

	public void setEmailNotificationsOpenLinksInApp(boolean emailNotificationsOpenLinksInApp) {
		this.emailNotificationsOpenLinksInApp = emailNotificationsOpenLinksInApp;
	}
}
