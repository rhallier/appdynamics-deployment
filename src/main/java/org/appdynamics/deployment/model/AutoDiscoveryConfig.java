package org.appdynamics.deployment.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class AutoDiscoveryConfig {
	@JacksonXmlProperty(localName = "controller-version", isAttribute = true)
    private String controllerVersion;
    
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "auto-discovery")
    private List<AutoDiscovery> autoDiscovery=new ArrayList<AutoDiscovery>();
    
    public AutoDiscoveryConfig(){}

	public String getControllerVersion() {
		return controllerVersion;
	}

	public List<AutoDiscovery> getAutoDiscovery() {
		return autoDiscovery;
	}
	
	public int getModifiedAutoConfig() {
		int result = 0;
		
		if(autoDiscovery!=null)
			for(AutoDiscovery ad : autoDiscovery) {
				result=result+(ad.isChanged()?1:0);
			}
		
		return result;
	}

	@Override
	public String toString() {
		return "_AutoDiscoveryConfig [controllerVersion=" + controllerVersion + ", autoDiscovery=" + autoDiscovery + "]";
	}
}
