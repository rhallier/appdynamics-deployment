package org.appdynamics.deployment.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "deepLinkUrl" })
public class HealthRuleViolation {
	private int id;
	private String name;
	private long startTimeInMillis;
	private long detectedTimeInMillis;
	private long endTimeInMillis;
	private String incidentStatus;
	private String severity;
	private EntityDefinition triggeredEntityDefinition;
	private EntityDefinition affectedEntityDefinition;
	private String description;

	private HealthRuleViolation() {}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public long getStartTimeInMillis() {
		return startTimeInMillis;
	}

	public long getDetectedTimeInMillis() {
		return detectedTimeInMillis;
	}

	public long getEndTimeInMillis() {
		return endTimeInMillis;
	}

	public String getIncidentStatus() {
		return incidentStatus;
	}

	public String getSeverity() {
		return severity;
	}

	public EntityDefinition getTriggeredEntityDefinition() {
		return triggeredEntityDefinition;
	}

	public EntityDefinition getAffectedEntityDefinition() {
		return affectedEntityDefinition;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "_HealthRuleViolation [id=" + id + ", name=" + name + ", startTimeInMillis=" + startTimeInMillis + ", detectedTimeInMillis=" + detectedTimeInMillis + ", endTimeInMillis=" + endTimeInMillis + ", incidentStatus=" + incidentStatus + ", severity=" + severity + ", triggeredEntityDefinition=" + triggeredEntityDefinition + ", affectedEntityDefinition=" + affectedEntityDefinition + ", description=" + description + "]";
	}
}
