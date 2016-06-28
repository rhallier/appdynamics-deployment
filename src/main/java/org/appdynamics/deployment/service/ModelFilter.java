package org.appdynamics.deployment.service;

import java.util.ArrayList;
import java.util.List;

import org.appdynamics.deployment.utils.StringUtils;

public class ModelFilter {
	private List<String> includeApplications = new ArrayList<String>();
	private List<String> excludeApplications = new ArrayList<String>();

	public ModelFilter() {
	}

	public ModelFilter includeApplication(String application) {
		if(StringUtils.isNotBlank(application))
			includeApplications.add(application);
		return this;
	}

	public ModelFilter excludeApplication(String application) {
		if(StringUtils.isNotBlank(application))
			excludeApplications.add(application);
		return this;
	}

	public List<String> getIncludeApplications() {
		return includeApplications;
	}

	public void setIncludeApplications(List<String> applications) {
		this.includeApplications = applications;
	}
	
	public boolean acceptApplication(String applicationName) {
		boolean result = true;
		
		// Exclude
		if(excludeApplications!=null && !excludeApplications.isEmpty()) {
			for(String app : excludeApplications) {
				if( applicationName.toLowerCase().contains(app.toLowerCase())) {
					result =false;
					break;
				}
			}
		}
		
		// Include
		if(result) {
			if(includeApplications!=null && !includeApplications.isEmpty()) {
				result = false;
	
				for(String app : includeApplications) {
					if( applicationName.toLowerCase().contains(app.toLowerCase())) {
						result =true;
						break;
					}
				}
			}
		}
		return result;
	}
}
