package org.appdynamics.deployment.model.rules;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class StrutsRule {
	private boolean enabled;
	private boolean excluded;
	private int priority;
	@JacksonXmlProperty(localName = "action-name")
	private MatchClassName actionName;
	@JacksonXmlProperty(localName = "action-class-name")
	private MatchClassName actionClassName;
	@JacksonXmlProperty(localName = "action-method-name")
	private MatchClassName actionMethodName;

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isExcluded() {
		return excluded;
	}

	public int getPriority() {
		return priority;
	}

	public MatchClassName getActionName() {
		return actionName;
	}

	public MatchClassName getActionClassName() {
		return actionClassName;
	}

	public MatchClassName getActionMethodName() {
		return actionMethodName;
	}

	@Override
	public String toString() {
		return "StrutsRule [enabled=" + enabled + ", excluded=" + excluded + ", priority=" + priority + ", actionName=" + actionName + ", actionClassName=" + actionClassName + ", actionMethodName=" + actionMethodName + "]";
	}

}
