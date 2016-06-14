package org.appdynamics.deployment.model.rules;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class WebRule {
	private boolean enabled;
	private boolean excluded;
	private int priority;
	private NVProperties properties;
	private MatchClassName uri;
    @JacksonXmlProperty(localName = "http-method")
	private String httpMethod;

	public boolean isEnabled() {
		return enabled;
	}

	public int getPriority() {
		return priority;
	}

	public NVProperties getProperties() {
		return properties;
	}

	public MatchClassName getUri() {
		return uri;
	}

	public boolean isExcluded() {
		return excluded;
	}

	@Override
	public String toString() {
		return "WebRule [enabled=" + enabled + ", priority=" + priority + ", properties=" + properties + ", uri=" + uri + "]";
	}

}
