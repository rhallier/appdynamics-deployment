package org.appdynamics.deployment.model.rules;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Cookies {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "cookie")
	private List<Cookie> cookies = new ArrayList<Cookie>();

	public List<Cookie> getCookies() {
		return cookies;
	}

	@Override
	public String toString() {
		return "Cookies [cookies=" + cookies + "]";
	}
}
