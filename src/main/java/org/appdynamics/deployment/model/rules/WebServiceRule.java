package org.appdynamics.deployment.model.rules;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class WebServiceRule {

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

	public boolean isExcluded() {
		return excluded;
	}

	public int getPriority() {
		return priority;
	}

	public MatchClassName getWebServiceName() {
		return webServiceName;
	}

	public MatchClassName getOperationName() {
		return operationName;
	}

	@Override
	public String toString() {
		return "WebServiceRule [enabled=" + enabled + ", excluded=" + excluded + ", priority=" + priority + ", webServiceName=" + webServiceName + ", operationName=" + operationName + "]";
	}

}
