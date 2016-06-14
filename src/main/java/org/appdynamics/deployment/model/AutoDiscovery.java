package org.appdynamics.deployment.model;

import org.appdynamics.deployment.utils.StringUtils;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName="auto-discovery")
public class AutoDiscovery {

    private String name;
    private String properties;
    @JacksonXmlProperty(localName="discovery-resolution")
    private String discoveryResolution;
    @JacksonXmlProperty(localName="transaction-detection-enabled")
    private String transactionDetectionEnabled;
    private String enabled;
    
    public AutoDiscovery(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public String getDiscoveryResolution() {
        return discoveryResolution;
    }

    public void setDiscoveryResolution(String discoveryResolution) {
        this.discoveryResolution = discoveryResolution;
    }

    public String getTransactionDetectionEnabled() {
        return transactionDetectionEnabled;
    }

    public void setTransactionDetectionEnabled(String transactionDetectionEnabled) {
        this.transactionDetectionEnabled = transactionDetectionEnabled;
    }

    public String isEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }
    
	public boolean isChanged() {

		// properties
		if(StringUtils.isInList(name, "WEB", "PHP_WEB", "SERVLET", "RUBY_WEB", "NATIVE", "ASP_DOTNET","PYTHON_WEB", "NODEJS_WEB")) {
			if(!properties.equalsIgnoreCase("uri-length;first-n-segments;segment-length;2;"))
				return true;
		}
		
		
		// transaction-detection-enabled
		if(transactionDetectionEnabled.equalsIgnoreCase("false")) {
			if(!StringUtils.isInList(name, "EJB", "POJO", "SPRING_BEAN", "DOTNET_REMOTING", "POCO"))
				return true;
		}

		// enabled
		if(enabled.equalsIgnoreCase("false")) {
			return true;
		}
		
		return false;
	}

	@Override
	public String toString() {
		return "_AutoDiscovery [name=" + name + ", properties=" + properties + ", discoveryResolution=" + discoveryResolution + ", transactionDetectionEnabled=" + transactionDetectionEnabled + ", enabled=" + enabled + "]";
	}


}
