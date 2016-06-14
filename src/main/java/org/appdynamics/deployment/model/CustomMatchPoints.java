package org.appdynamics.deployment.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName ="custom-match-points")
public class CustomMatchPoints {

	@JacksonXmlProperty(localName = "controller-version", isAttribute = true)
    private String controllerVersion;
    
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "custom-match-point")
    private List<CustomMatchPoint> customMatchPoints=new ArrayList<CustomMatchPoint>();

	public String getControllerVersion() {
		return controllerVersion;
	}

	public List<CustomMatchPoint> getCustomMatchPoints() {
		return customMatchPoints;
	}
	
	public int getNbOfUserCustomMatchPoints() {
		int result = 0;
		
		if(customMatchPoints!=null)
			for(CustomMatchPoint cmp : customMatchPoints) {
				result=result+(cmp.isUserModified()?1:0);
			}
		
		return result;
	}

	@Override
	public String toString() {
		return "CustomMatchPoints [controllerVersion=" + controllerVersion + ", customMatchPoints=" + customMatchPoints + "]";
	}
}
