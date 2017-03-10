package com.farmerswife.cloudwife.entities;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;

public abstract class AbsctractEmailMessage {

	private String subject;
	private String senderName;
	private String recipientName;
	private String recipientEmail;
	private String htmlContent;
	private Date created;
	private String type;
	private String errorMessage;
	private int retryCount;
	private Date lastRetry;
	private Date nextRetry;


	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientEmail() {
		return recipientEmail;
	}

	public void setRecipientEmail(String recipientEmail) {
		this.recipientEmail = recipientEmail;
	}

	public String getHtmlContent() {
		return htmlContent;
	}

	public void setHtmlContent(String htmlContent) {
		this.htmlContent = htmlContent;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(int retryCount) {
		this.retryCount = retryCount;
	}

	public Date getLastRetry() {
		return lastRetry;
	}

	public void setLastRetry(Date lastRetry) {
		this.lastRetry = lastRetry;
	}

	public Date getNextRetry() {
		return nextRetry;
	}

	public void setNextRetry(Date nextRetry) {
		this.nextRetry = nextRetry;
	}

}