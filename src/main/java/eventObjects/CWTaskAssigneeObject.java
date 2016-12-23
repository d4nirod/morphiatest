/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package eventObjects;

import java.io.Serializable;

import org.mongodb.morphia.Key;
import org.mongodb.morphia.annotations.Embedded;

import entities.CWUser;


@Embedded
public class CWTaskAssigneeObject implements Serializable {
	private static final long serialVersionUID = 5865101712789190169L;

	private Key<CWUser> entityKey;
	private String userFirstName;
	private String userLastName;
	private String userEmail;
	
	private String roleName;
	
	public Key<CWUser> getEntityKey() {
		return entityKey;
	}

	public void setEntityKey(Key<CWUser> entityKey) {
		this.entityKey = entityKey;
	}

	public String getUserFirstName() {
		return userFirstName;
	}
	
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	
	public String getUserLastName() {
		return userLastName;
	}
	
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	
	public String getUserEmail() {
		return userEmail;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
