package org.appdynamics.deployment.model.rules;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Cookie {
	private String matchType;
	private MatchClassName name;
	private MatchClassName value;

	@JacksonXmlProperty(localName = "match-type")
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
		return "Cookie [matchType=" + matchType + ", name=" + name + ", value=" + value + "]";
	}

}
