package org.appdynamics.deployment.model;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class AutoDiscoveryConfig {
	private final static Logger logger = LoggerFactory.getLogger(AutoDiscoveryConfig.class);

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
				if(logger.isDebugEnabled())
					logger.debug("Evaluating if the autoDiscovery panel has changed for "+ad);
				result=result+(ad.isChanged()?1:0);
			}
		
		return result;
	}

	@Override
	public String toString() {
		return "_AutoDiscoveryConfig [controllerVersion=" + controllerVersion + ", autoDiscovery=" + autoDiscovery + "]";
	}
}
