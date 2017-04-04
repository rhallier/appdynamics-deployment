package org.appdynamics.deployment.model;

import java.util.ArrayList;
import java.util.Collection;

/*
 *     
 *
    HTTP Exit Point
	    <customExitPointId>-1</customExitPointId>
	    <detailString>http://ws:8080/cart/orderService?wsdl</detailString>
	    <properties/>
	    <toComponentId>185</toComponentId>
	    <timeTakenInMillis>2</timeTakenInMillis>
	    <count>1</count>
	    <errorCount>0</errorCount>
	    <errorDetails>\N</errorDetails>
	    <snapshotSequenceCounter>7</snapshotSequenceCounter>
	    <exitPointName>HTTP</exitPointName>
	    <timestamp>0</timestamp>
    
    JDBC Exit Point
	  <customExitPointId>-1</customExitPointId>
	  <detailString>DELETE FROM cart_item WHERE (Cart_ID = ?)</detailString>
	  <properties>
	    <name-value>
	      <id>0</id>
	      <name>Query Type</name>
	      <value>Delete</value>
	    </name-value>
	    <name-value>
	      <id>0</id>
	      <name>Statement Type</name>
	      <value>Prepared Statement</value>
	    </name-value>
	  </properties>
	  <toComponentId>{[UNRESOLVED][798]}</toComponentId>
	  <timeTakenInMillis>1</timeTakenInMillis>
	  <count>1</count>
	  <errorCount>0</errorCount>
	  <errorDetails>\N</errorDetails>
	  <snapshotSequenceCounter>51</snapshotSequenceCounter>
	  <exitPointName>JDBC</exitPointName>
	  <timestamp>0</timestamp>

 */
public class SnapshotExitCall {
	private int customExitPointId;
	private String toComponentId;
	private String timestamp;
	private String exitPointName;
	private int count;
	private String detailString;
	private String errorDetails;
	private String snapshotSequenceCounter;
	private int errorCount;
	private Collection<NameValue> properties = new ArrayList<NameValue>();
	private long timeTakenInMillis;

	public int getCustomExitPointId() {
		return customExitPointId;
	}

	public void setCustomExitPointId(int customExitPointId) {
		this.customExitPointId = customExitPointId;
	}

	public String getToComponentId() {
		return toComponentId;
	}

	public void setToComponentId(String toComponentId) {
		this.toComponentId = toComponentId;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getExitPointName() {
		return exitPointName;
	}

	public void setExitPointName(String exitPointName) {
		this.exitPointName = exitPointName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDetailString() {
		return detailString;
	}

	public void setDetailString(String detailString) {
		this.detailString = detailString;
	}

	public String getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

	public String getSnapshotSequenceCounter() {
		return snapshotSequenceCounter;
	}

	public void setSnapshotSequenceCounter(String snapshotSequenceCounter) {
		this.snapshotSequenceCounter = snapshotSequenceCounter;
	}

	public int getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}

	public Collection<NameValue> getProperties() {
		return properties;
	}

	public void setProperties(Collection<NameValue> properties) {
		this.properties = properties;
	}

	public long getTimeTakenInMillis() {
		return timeTakenInMillis;
	}

	public void setTimeTakenInMillis(long timeTakenInMillis) {
		this.timeTakenInMillis = timeTakenInMillis;
	}

	@Override
	public String toString() {
		return "SnapshotExitCall [customExitPointId=" + customExitPointId + ", toComponentId=" + toComponentId
				+ ", timestamp=" + timestamp + ", exitPointName=" + exitPointName + ", count=" + count
				+ ", detailString=" + detailString + ", errorDetails=" + errorDetails + ", snapshotSequenceCounter="
				+ snapshotSequenceCounter + ", errorCount=" + errorCount + ", properties=" + properties
				+ ", timeTakenInMillis=" + timeTakenInMillis + "]";
	}

}
