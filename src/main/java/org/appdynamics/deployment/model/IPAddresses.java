package org.appdynamics.deployment.model;

import java.util.ArrayList;
import java.util.List;

import org.appdynamics.deployment.utils.StringUtils;

public class IPAddresses {
    private List<String> ipAddresses=new ArrayList<String>();

    public List<String> getipAddresses() {
        return ipAddresses;
    }

    public String getLabel() {
    	return StringUtils.join(", ", ipAddresses);
    }
    
	@Override
	public String toString() {
		return "_IPAddresses [ipAddresses=" + ipAddresses + "]";
	}
    
    
}
