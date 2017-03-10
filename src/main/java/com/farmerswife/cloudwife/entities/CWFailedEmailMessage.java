/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package com.farmerswife.cloudwife.entities;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;


import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtils;


@Entity(value = "cw_failed_email")
public class CWFailedEmailMessage extends AbsctractEmailMessage {

	@Id  
	private ObjectId id;


	public ObjectId getId() {
		return this.id;
	}
	
	public void setId(ObjectId id) {
		this.id = id;
	}

	public static CWFailedEmailMessage copyFrom(CWEmailMessage msg) {
		CWFailedEmailMessage obj = new CWFailedEmailMessage();
		
		try {
			BeanUtils.copyProperties(obj, msg);
			obj.setId(null);
			return obj;
		} catch (IllegalAccessException | InvocationTargetException e) {
			return null;
		}
	}

}
