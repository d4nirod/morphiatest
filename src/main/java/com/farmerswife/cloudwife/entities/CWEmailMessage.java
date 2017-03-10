/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package com.farmerswife.cloudwife.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity(value = "cw_email")
public class CWEmailMessage extends AbsctractEmailMessage {

	@Id ObjectId id;
			
	// Getters and setters
	
	public ObjectId getId() {
		return id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}
}
