package org.appdynamics.deployment.model.rules;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Header {
    @JacksonXmlProperty(localName = "match-type")
	private String matchType;
	private MatchClassName name;
	private MatchClassName value;

	public String getMatchType() {
		return matchType;
	}

	public MatchClassName getName() {
		return name;
	}

	public MatchClassName getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "Header [matchType=" + matchType + ", name=" + name + ", value=" + value + "]";
	}

}
