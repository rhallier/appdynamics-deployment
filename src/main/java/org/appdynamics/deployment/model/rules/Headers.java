package org.appdynamics.deployment.model.rules;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Headers {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "header")
	private List<Header> headers = new ArrayList<Header>();

	public List<Header> getHeaders() {
		return headers;
	}

	@Override
	public String toString() {
		return "Headers [headers=" + headers + "]";
	}
}
