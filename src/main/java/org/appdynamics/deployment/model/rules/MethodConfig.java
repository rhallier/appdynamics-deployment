package org.appdynamics.deployment.model.rules;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class MethodConfig {
	private String returnType;
	private String name;
	@JacksonXmlProperty(localName = "param-length")
	private int paramLength;
	@JacksonXmlProperty(localName = "param-index")
	private int paramIndex;
	@JacksonXmlProperty(localName = "param-getter")
	private String paramGetter;

	public String getReturnType() {
		return returnType;
	}

	public String getName() {
		return name;
	}

	public int getParamLength() {
		return paramLength;
	}

	public int getParamIndex() {
		return paramIndex;
	}

	public String getParamGetter() {
		return paramGetter;
	}

	@Override
	public String toString() {
		return "MethodConfig [returnType=" + returnType + ", name=" + name + ", paramLength=" + paramLength + ", paramIndex=" + paramIndex + ", paramGetter=" + paramGetter + "]";
	}
	
}
