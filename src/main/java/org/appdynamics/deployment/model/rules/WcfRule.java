package org.appdynamics.deployment.model.rules;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class WcfRule {
	private boolean enabled;
	private boolean excluded;
	private int priority;
	@JacksonXmlProperty(localName = "web-service-name")
	private MatchClassName webServiceName;
	@JacksonXmlProperty(localName = "operation-name")
	private MatchClassName operationName;

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isExcluded() {
		return excluded;
	}

	public void setExcluded(boolean excluded) {
		this.excluded = excluded;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public MatchClassName getWebServiceName() {
		return webServiceName;
	}

	public void setWebServiceName(MatchClassName webServiceName) {
		this.webServiceName = webServiceName;
	}

	public MatchClassName getOperationName() {
		return operationName;
	}

	public void setOperationName(MatchClassName operationName) {
		this.operationName = operationName;
	}

}
