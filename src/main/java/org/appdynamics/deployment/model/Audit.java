package org.appdynamics.deployment.model;

import java.util.Date;

public class Audit {
	private long timeStamp;
	private Date auditDateTime;
	private String accountName;
	private String securityProviderType;
	private String userName;
	private String action;
	private String objectType;
	private String objectName;

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Date getAuditDateTime() {
		return auditDateTime;
	}

	public void setAuditDateTime(Date auditDateTime) {
		this.auditDateTime = auditDateTime;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getSecurityProviderType() {
		return securityProviderType;
	}

	public void setSecurityProviderType(String securityProviderType) {
		this.securityProviderType = securityProviderType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String acton) {
		this.action = acton;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	@Override
	public String toString() {
		return "Audit [timeStamp=" + timeStamp + ", auditDateTime=" + auditDateTime + ", accountName=" + accountName + ", securityProviderType=" + securityProviderType + ", userName=" + userName + ", action=" + action + ", objectType=" + objectType + ", objectName=" + objectName + "]";
	}
}
