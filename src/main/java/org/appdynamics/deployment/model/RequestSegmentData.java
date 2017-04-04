package org.appdynamics.deployment.model;

import java.util.ArrayList;
import java.util.List;

public class RequestSegmentData {
	private int id;
	private boolean archived;
	private String requestGUID;
	private int businessTransactionId;
	private int applicationId;
	private int applicationComponentId;
	private int applicationComponentNodeId;
	private boolean async;
	private String threadID;
	private String threadName;
	private long localStartTime;
	private long serverStartTime;
	private boolean firstInChain;
	private String callChain;
	private String localID;
	private boolean errorOccured;
	private boolean hasDeepDiveData;
	private UserExperience userExperience;
	private long timeTakenInMilliSecs;
	private long cpuTimeTakenInMilliSecs;
	private String warningThreshold;
	private String criticalThreshold;
	private String summary;
	private String errorSummary;
	private String diagnosticSessionGUID;
	private DeepDivePolicy deepDivePolicy;
	private boolean delayedDeepDive;
	private int delayedDeepDiveOffSet;
	private String URL;
	private String httpSessionID;
	private List<Long> errorIDs = new ArrayList<Long>();
	private List<NameValue> errorDetails = new ArrayList<NameValue>();
	private List<NameValue> httpParameters = new ArrayList<NameValue>();
	private List<NameValue> businessData = new ArrayList<NameValue>();
	private List<NameValue> cookies = new ArrayList<NameValue>();
	private List<NameValue> httpHeaders = new ArrayList<NameValue>();
	private List<NameValue> sessionKeys = new ArrayList<NameValue>();
	private List<NameValue> responseHeaders = new ArrayList<NameValue>();
	private List<String> logMessages = new ArrayList<String>();
	private List<NameValue> transactionProperties = new ArrayList<NameValue>();
	private List<NameValue> transactionEvents = new ArrayList<NameValue>();
	private boolean unresolvedCallInCallChain;
	private List<NameValue> dotnetProperty = new ArrayList<NameValue>();
	private long endToEndLatency;
	private List<SnapshotExitCall> snapshotExitCalls = new ArrayList<SnapshotExitCall>();
	private boolean exitCallsDataTruncated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}

	public String getRequestGUID() {
		return requestGUID;
	}

	public void setRequestGUID(String requestGUID) {
		this.requestGUID = requestGUID;
	}

	public int getBusinessTransactionId() {
		return businessTransactionId;
	}

	public void setBusinessTransactionId(int businessTransactionId) {
		this.businessTransactionId = businessTransactionId;
	}

	public int getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}

	public int getApplicationComponentId() {
		return applicationComponentId;
	}

	public void setApplicationComponentId(int applicationComponentId) {
		this.applicationComponentId = applicationComponentId;
	}

	public int getApplicationComponentNodeId() {
		return applicationComponentNodeId;
	}

	public void setApplicationComponentNodeId(int applicationComponentNodeId) {
		this.applicationComponentNodeId = applicationComponentNodeId;
	}

	public boolean isAsync() {
		return async;
	}

	public void setAsync(boolean async) {
		this.async = async;
	}

	public String getThreadID() {
		return threadID;
	}

	public void setThreadID(String threadID) {
		this.threadID = threadID;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public long getLocalStartTime() {
		return localStartTime;
	}

	public void setLocalStartTime(long localStartTime) {
		this.localStartTime = localStartTime;
	}

	public long getServerStartTime() {
		return serverStartTime;
	}

	public void setServerStartTime(long serverStartTime) {
		this.serverStartTime = serverStartTime;
	}

	public boolean isFirstInChain() {
		return firstInChain;
	}

	public void setFirstInChain(boolean firstInChain) {
		this.firstInChain = firstInChain;
	}

	public String getCallChain() {
		return callChain;
	}

	public void setCallChain(String callChain) {
		this.callChain = callChain;
	}

	public String getLocalID() {
		return localID;
	}

	public void setLocalID(String localID) {
		this.localID = localID;
	}

	public boolean isErrorOccured() {
		return errorOccured;
	}

	public void setErrorOccured(boolean errorOccured) {
		this.errorOccured = errorOccured;
	}

	public boolean isHasDeepDiveData() {
		return hasDeepDiveData;
	}

	public void setHasDeepDiveData(boolean hasDeepDiveData) {
		this.hasDeepDiveData = hasDeepDiveData;
	}

	public UserExperience getUserExperience() {
		return userExperience;
	}

	public void setUserExperience(UserExperience userExperience) {
		this.userExperience = userExperience;
	}

	public long getTimeTakenInMilliSecs() {
		return timeTakenInMilliSecs;
	}

	public void setTimeTakenInMilliSecs(long timeTakenInMilliSecs) {
		this.timeTakenInMilliSecs = timeTakenInMilliSecs;
	}

	public long getCpuTimeTakenInMilliSecs() {
		return cpuTimeTakenInMilliSecs;
	}

	public void setCpuTimeTakenInMilliSecs(long cpuTimeTakenInMilliSecs) {
		this.cpuTimeTakenInMilliSecs = cpuTimeTakenInMilliSecs;
	}

	public String getWarningThreshold() {
		return warningThreshold;
	}

	public void setWarningThreshold(String warningThreshold) {
		this.warningThreshold = warningThreshold;
	}

	public String getCriticalThreshold() {
		return criticalThreshold;
	}

	public void setCriticalThreshold(String criticalThreshold) {
		this.criticalThreshold = criticalThreshold;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getErrorSummary() {
		return errorSummary;
	}

	public void setErrorSummary(String errorSummary) {
		this.errorSummary = errorSummary;
	}

	public String getDiagnosticSessionGUID() {
		return diagnosticSessionGUID;
	}

	public void setDiagnosticSessionGUID(String diagnosticSessionGUID) {
		this.diagnosticSessionGUID = diagnosticSessionGUID;
	}

	public DeepDivePolicy getDeepDivePolicy() {
		return deepDivePolicy;
	}

	public void setDeepDivePolicy(DeepDivePolicy deepDivePolicy) {
		this.deepDivePolicy = deepDivePolicy;
	}

	public boolean isDelayedDeepDive() {
		return delayedDeepDive;
	}

	public void setDelayedDeepDive(boolean delayedDeepDive) {
		this.delayedDeepDive = delayedDeepDive;
	}

	public int getDelayedDeepDiveOffSet() {
		return delayedDeepDiveOffSet;
	}

	public void setDelayedDeepDiveOffSet(int delayedDeepDiveOffSet) {
		this.delayedDeepDiveOffSet = delayedDeepDiveOffSet;
	}

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getHttpSessionID() {
		return httpSessionID;
	}

	public void setHttpSessionID(String httpSessionID) {
		this.httpSessionID = httpSessionID;
	}

	public List<Long> getErrorIDs() {
		return errorIDs;
	}

	public void setErrorIDs(List<Long> errorIDs) {
		this.errorIDs = errorIDs;
	}

	public List<NameValue> getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(List<NameValue> errorDetails) {
		this.errorDetails = errorDetails;
	}

	public List<NameValue> getHttpParameters() {
		return httpParameters;
	}

	public void setHttpParameters(List<NameValue> httpParameters) {
		this.httpParameters = httpParameters;
	}

	public List<NameValue> getBusinessData() {
		return businessData;
	}

	public void setBusinessData(List<NameValue> businessData) {
		this.businessData = businessData;
	}

	public List<NameValue> getCookies() {
		return cookies;
	}

	public void setCookies(List<NameValue> cookies) {
		this.cookies = cookies;
	}

	public List<NameValue> getHttpHeaders() {
		return httpHeaders;
	}

	public void setHttpHeaders(List<NameValue> httpHeaders) {
		this.httpHeaders = httpHeaders;
	}

	public List<NameValue> getSessionKeys() {
		return sessionKeys;
	}

	public void setSessionKeys(List<NameValue> sessionKeys) {
		this.sessionKeys = sessionKeys;
	}

	public List<NameValue> getResponseHeaders() {
		return responseHeaders;
	}

	public void setResponseHeaders(List<NameValue> responseHeaders) {
		this.responseHeaders = responseHeaders;
	}

	public List<String> getLogMessages() {
		return logMessages;
	}

	public void setLogMessages(List<String> logMessages) {
		this.logMessages = logMessages;
	}

	public List<NameValue> getTransactionProperties() {
		return transactionProperties;
	}

	public void setTransactionProperties(List<NameValue> transactionProperties) {
		this.transactionProperties = transactionProperties;
	}

	public List<NameValue> getTransactionEvents() {
		return transactionEvents;
	}

	public void setTransactionEvents(List<NameValue> transactionEvents) {
		this.transactionEvents = transactionEvents;
	}

	public boolean isUnresolvedCallInCallChain() {
		return unresolvedCallInCallChain;
	}

	public void setUnresolvedCallInCallChain(boolean unresolvedCallInCallChain) {
		this.unresolvedCallInCallChain = unresolvedCallInCallChain;
	}

	public List<NameValue> getDotnetProperty() {
		return dotnetProperty;
	}

	public void setDotnetProperty(List<NameValue> dotnetProperty) {
		this.dotnetProperty = dotnetProperty;
	}

	public long getEndToEndLatency() {
		return endToEndLatency;
	}

	public void setEndToEndLatency(long endToEndLatency) {
		this.endToEndLatency = endToEndLatency;
	}

	public List<SnapshotExitCall> getSnapshotExitCalls() {
		return snapshotExitCalls;
	}

	public void setSnapshotExitCalls(List<SnapshotExitCall> snapshotExitCalls) {
		this.snapshotExitCalls = snapshotExitCalls;
	}

	public boolean isExitCallsDataTruncated() {
		return exitCallsDataTruncated;
	}

	public void setExitCallsDataTruncated(boolean exitCallsDataTruncated) {
		this.exitCallsDataTruncated = exitCallsDataTruncated;
	}

	@Override
	public String toString() {
		return "RequestSegmentData [id=" + id + ", archived=" + archived + ", requestGUID=" + requestGUID
				+ ", businessTransactionId=" + businessTransactionId + ", applicationId=" + applicationId
				+ ", applicationComponentId=" + applicationComponentId + ", applicationComponentNodeId="
				+ applicationComponentNodeId + ", async=" + async + ", threadID=" + threadID + ", threadName="
				+ threadName + ", localStartTime=" + localStartTime + ", serverStartTime=" + serverStartTime
				+ ", firstInChain=" + firstInChain + ", callChain=" + callChain + ", localID=" + localID
				+ ", errorOccured=" + errorOccured + ", hasDeepDiveData=" + hasDeepDiveData + ", userExperience="
				+ userExperience + ", timeTakenInMilliSecs=" + timeTakenInMilliSecs + ", cpuTimeTakenInMilliSecs="
				+ cpuTimeTakenInMilliSecs + ", warningThreshold=" + warningThreshold + ", criticalThreshold="
				+ criticalThreshold + ", summary=" + summary + ", errorSummary=" + errorSummary
				+ ", diagnosticSessionGUID=" + diagnosticSessionGUID + ", deepDivePolicy=" + deepDivePolicy
				+ ", delayedDeepDive=" + delayedDeepDive + ", delayedDeepDiveOffSet=" + delayedDeepDiveOffSet + ", URL="
				+ URL + ", httpSessionID=" + httpSessionID + ", errorIDs=" + errorIDs + ", errorDetails=" + errorDetails
				+ ", httpParameters=" + httpParameters + ", businessData=" + businessData + ", cookies=" + cookies
				+ ", httpHeaders=" + httpHeaders + ", sessionKeys=" + sessionKeys + ", responseHeaders="
				+ responseHeaders + ", logMessages=" + logMessages + ", transactionProperties=" + transactionProperties
				+ ", transactionEvents=" + transactionEvents + ", unresolvedCallInCallChain="
				+ unresolvedCallInCallChain + ", dotnetProperty=" + dotnetProperty + ", endToEndLatency="
				+ endToEndLatency + ", snapshotExitCalls=" + snapshotExitCalls + ", exitCallsDataTruncated="
				+ exitCallsDataTruncated + "]";
	}

	
}
