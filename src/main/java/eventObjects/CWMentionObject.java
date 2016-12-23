/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package eventObjects;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Embedded;


@Embedded
public class CWMentionObject implements Serializable {
	private static final long serialVersionUID = 7679180733138684688L;

	public static final String TYPE_USER = "user";
	
	private String entityId;
	private String type;
	private String mappedValue;
	
	public CWMentionObject() {
	}
	
	public CWMentionObject(String entityId, String type, String mappedValue) {
		super();
		this.entityId = entityId;
		this.type = type;
		this.mappedValue = mappedValue;
	}

	public String getEntityId() {
		return entityId;
	}
	
	public void setEntityId(String entityId) {
		this.entityId = entityId;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getMappedValue() {
		return mappedValue;
	}
	
	public void setMappedValue(String mappedValue) {
		this.mappedValue = mappedValue;
	}

}
