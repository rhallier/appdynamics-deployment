package org.appdynamics.deployment.model.rules;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class AspDotNetRule {

	private boolean enabled;
	private boolean excluded;
	private int priority;
	@JacksonXmlProperty(localName = "class-name")
	private MatchClassName className;
	private MatchClassName uri;
	private Parameters parameters;
	private NVProperties properties;

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isExcluded() {
		return excluded;
	}

	public int getPriority() {
		return priority;
	}

	public MatchClassName getClassName() {
		return className;
	}

	public MatchClassName getUri() {
		return uri;
	}

	public Parameters getParameters() {
		return parameters;
	}

	public NVProperties getProperties() {
		return properties;
	}

	@Override
	public String toString() {
		return "AspDotNetRule [enabled=" + enabled + ", priority=" + priority + ", className=" + className + "]";
	}
}
