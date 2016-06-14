package org.appdynamics.deployment.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName ="excludes")
public class Excludes {
	
	@JacksonXmlProperty(localName = "controller-version", isAttribute = true)
    private String controllerVersion;
    
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "exclude")
    private List<Exclude> excludes=new ArrayList<Exclude>();

	public String getControllerVersion() {
		return controllerVersion;
	}

	public List<Exclude> getExcludes() {
		return excludes;
	}
	
	public int getNbOfUserExcludes() {
		int result = 0;
		
		if(excludes!=null)
			for(Exclude e : excludes) {
				result=result+(e.isUserModified()?1:0);
			}
		
		return result;
	}

}
