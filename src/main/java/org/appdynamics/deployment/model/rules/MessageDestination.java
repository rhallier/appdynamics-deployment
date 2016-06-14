package org.appdynamics.deployment.model.rules;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class MessageDestination {
	private String type;
	@JacksonXmlProperty(localName = "destination-name")
	private MatchClassName destinationName;

	public String getType() {
		return type;
	}

	public MatchClassName getDestinationName() {
		return destinationName;
	}

	@Override
	public String toString() {
		return "MessageDestination [type=" + type + ", destinationName=" + destinationName + "]";
	}

}
