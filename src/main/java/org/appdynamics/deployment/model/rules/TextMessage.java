package org.appdynamics.deployment.model.rules;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class TextMessage {
	@JacksonXmlProperty(localName = "text")
	private MatchClassName text;

	public MatchClassName getText() {
		return text;
	}

	@Override
	public String toString() {
		return "TextMessage [text=" + text + "]";
	}

}
