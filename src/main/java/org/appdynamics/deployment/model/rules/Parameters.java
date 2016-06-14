package org.appdynamics.deployment.model.rules;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Parameters {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "parameter")
	private List<Parameter> parameters = new ArrayList<Parameter>();

	public List<Parameter> getParameters() {
		return parameters;
	}

	@Override
	public String toString() {
		return "Parameters [parameters=" + parameters + "]";
	}
}
