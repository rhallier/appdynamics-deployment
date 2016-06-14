package org.appdynamics.deployment.model.rules;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class MessageProperties {
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "property")
	private List<MessageProperty> properties = new ArrayList<MessageProperty>();

	public List<MessageProperty> getProperties() {
		return properties;
	}

	@Override
	public String toString() {
		return "MessageProperties [properties=" + properties + "]";
	}

}
