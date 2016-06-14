package org.appdynamics.deployment.model.rules;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class NVProperties {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "property")
	private List<NVProperty> properties = new ArrayList<NVProperty>();

	public List<NVProperty> getProperties() {
		return properties;
	}

	@Override
	public String toString() {
		return "NVProperties [properties=" + properties + "]";
	}
}
