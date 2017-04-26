package org.appdynamics.deployment.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

public class XmlObjectMapper extends XmlMapper {

	private static final long serialVersionUID = 1L;

	public XmlObjectMapper() {
		super();
		configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

}
