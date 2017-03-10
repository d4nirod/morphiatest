/*
 *
 * Copyright Farmers WIFE S.L. All Rights Reserved.
 * 
 */

package com.farmerswife.cloudwife.entities;

import java.io.Serializable;
import java.util.Date;

public class CWUserDevice implements Serializable {
	private static final long serialVersionUID = 1560424530869569548L;
	private String platformName;
	private String deviceToken;
	private String deviceName;
	private Date lastUpdated;
	private String apnsRejectionReason;
	private Date invalidationTimestamp;
	
	public enum PlatformType { IOS, MAC }
	private PlatformType platformType;
	
	public CWUserDevice() {
	}
	
	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public String getDeviceToken() {
		return deviceToken;
	}

	public void setDeviceToken(String deviceToken) {
		this.deviceToken = deviceToken;
	}
	
	@Override
	public String toString() {
		String result = platformName + " | " + deviceToken;
		return result;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public PlatformType getPlatformType() {
		return platformType;
	}

	public void setPlatformType(PlatformType platformType) {
		this.platformType = platformType;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}
	
	public String getApnsRejectionReason() {
		return apnsRejectionReason;
	}

	public void setApnsRejectionReason(String apnsRejectionReason) {
		this.apnsRejectionReason = apnsRejectionReason;
	}

	public Date getInvalidationTimestamp() {
		return invalidationTimestamp;
	}

	public void setInvalidationTimestamp(Date invalidationTimestamp) {
		this.invalidationTimestamp = invalidationTimestamp;
	}
}
