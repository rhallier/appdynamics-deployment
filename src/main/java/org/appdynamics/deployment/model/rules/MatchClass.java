package org.appdynamics.deployment.model.rules;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class MatchClass {
    private String type;
    @JacksonXmlProperty(localName="name")
    private MatchClassName matchClassName;
	
    public String getType() {
		return type;
	}
	
    public MatchClassName getMatchClassName() {
		return matchClassName;
	}
	
	@Override
	public String toString() {
		return "MatchClass [type=" + type + ", matchClassName=" + matchClassName + "]";
	}
}
