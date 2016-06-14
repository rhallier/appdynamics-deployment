package org.appdynamics.deployment.model.rules;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class MessageProperty {
	private String type;
	private String name;
	@JacksonXmlProperty(localName = "match-type")
	private String matchType;
	private MatchClassName value;

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getMatchType() {
		return matchType;
	}

	public MatchClassName getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "MessageProperty [type=" + type + ", name=" + name + ", matchType=" + matchType + ", value=" + value + "]";
	}

}
