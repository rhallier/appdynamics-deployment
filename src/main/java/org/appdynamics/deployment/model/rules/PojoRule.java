package org.appdynamics.deployment.model.rules;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class PojoRule {
	private boolean enabled;
	private boolean excluded;
	private int priority;
    @JacksonXmlProperty(localName="display-name")
	private String displayName;
	private boolean background;
    @JacksonXmlProperty(localName="match-class")
	private MatchClass matchClass;
    @JacksonXmlProperty(localName="split-config")
	private SplitConfig splitConfig;
    @JacksonXmlProperty(localName="match-method")
	private MatchMethod matchMethod;
    @JacksonXmlProperty(localName="split-exclude")
	private MatchClassName splitExclude;

	public boolean isEnabled() {
		return enabled;
	}

	public int getPriority() {
		return priority;
	}

	public String getDisplayName() {
		return displayName;
	}

	public boolean isBackground() {
		return background;
	}

	public MatchClass getMatchClass() {
		return matchClass;
	}

	public SplitConfig getSplitConfig() {
		return splitConfig;
	}

	public MatchMethod getMatchMethod() {
		return matchMethod;
	}

	public boolean isExcluded() {
		return excluded;
	}

	public MatchClassName getSplitExclude() {
		return splitExclude;
	}

	@Override
	public String toString() {
		return "PojoRule [enabled=" + enabled + ", priority=" + priority + ", displayName=" + displayName + ", background=" + background + ", matchClass=" + matchClass + ", splitConfig=" + splitConfig + ", matchMethod=" + matchMethod + "]";
	}

}
