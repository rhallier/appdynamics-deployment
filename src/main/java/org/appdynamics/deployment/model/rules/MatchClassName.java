package org.appdynamics.deployment.model.rules;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class MatchClassName {
    @JacksonXmlProperty(localName="filter-type")
    private String filterType;
    @JacksonXmlProperty(localName="filter-value")
    private String filterValue;
    @JacksonXmlProperty(localName="inverse")
    private String inverse;
    
	public String getFilterType() {
		return filterType;
	}
	
	public String getFilterValue() {
		return filterValue;
	}
	
	public String getInverse() {
		return inverse;
	}

	@Override
	public String toString() {
		return "MatchClassName [filterType=" + filterType + ", filterValue=" + filterValue + "]";
	}
}
