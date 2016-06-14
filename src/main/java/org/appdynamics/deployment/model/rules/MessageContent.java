package org.appdynamics.deployment.model.rules;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class MessageContent {

	@JacksonXmlProperty(localName = "message-type")
	private String messageType;
	@JacksonXmlProperty(localName = "text-message")
	private TextMessage textMessage;

	public String getMessageType() {
		return messageType;
	}

	public TextMessage getTextMessage() {
		return textMessage;
	}

	@Override
	public String toString() {
		return "MessageContent [messageType=" + messageType + ", textMessage=" + textMessage + "]";
	}

}
