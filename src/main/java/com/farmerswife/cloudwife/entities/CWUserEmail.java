/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package com.farmerswife.cloudwife.entities;

import java.io.Serializable;

import org.mongodb.morphia.annotations.Embedded;

@Embedded
public class CWUserEmail implements Serializable {
	private static final long serialVersionUID = 4676868927267850358L;

	private String email;
	private boolean primary = false;
	private boolean verified = false;
	private String verificationKey = null;
	
	public CWUserEmail() {
	}

	public CWUserEmail(String email, String verificationKey) {
		this(email, false, verificationKey);
	}
	
	public CWUserEmail(String email, boolean verified, String verificationKey) {
		this.email = email;
		this.verified = verified;
		this.verificationKey = verificationKey;
	}

	// Getters and setter

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public boolean isPrimary() {
		return primary;
	}
	
	public void setPrimary(boolean primary) {
		this.primary = primary;
	}
	
	public boolean isVerified() {
		return verified;
	}
	
	public void setVerified(boolean verified) {
		this.verified = verified;
	}
	
	public String getVerificationKey() {
		return verificationKey;
	}
	
	public void setVerificationKey(String verificationKey) {
		this.verificationKey = verificationKey;
	}
	
	@Override
	public String toString() {
		String result = email;
		if (!verified) {
			result += " (not verified)";
		}
		return result;
	}
}
