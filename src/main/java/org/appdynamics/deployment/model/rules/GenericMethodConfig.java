package org.appdynamics.deployment.model.rules;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class GenericMethodConfig {
    @JacksonXmlProperty(localName = "class-name")
	private String className;
    @JacksonXmlProperty(localName = "method-config")
	private MethodConfig methodConfig;

	public String getClassName() {
		return className;
	}

	public MethodConfig getMethodConfig() {
		return methodConfig;
	}

	@Override
	public String toString() {
		return "GenericMethodConfig [className=" + className + ", methodConfig=" + methodConfig + "]";
	}

}
