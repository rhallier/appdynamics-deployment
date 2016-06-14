package org.appdynamics.deployment.model.rules;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class MatchMethod {
    @JacksonXmlProperty(localName="name")
	private MatchClassName matchClassName;

	public MatchClassName getMatchClassName() {
		return matchClassName;
	}

	@Override
	public String toString() {
		return "MatchMethod [matchClassName=" + matchClassName + "]";
	}
}
