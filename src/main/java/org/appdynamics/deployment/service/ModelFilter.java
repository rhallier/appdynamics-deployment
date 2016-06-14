package org.appdynamics.deployment.service;

import java.util.ArrayList;
import java.util.List;

import org.appdynamics.deployment.utils.StringUtils;

public class ModelFilter {
	private List<String> applications = new ArrayList<String>();

	public ModelFilter() {
	}

	public ModelFilter application(String application) {
		if(StringUtils.isNotBlank(application))
			applications.add(application);
		return this;
	}
	
	public List<String> getApplications() {
		return applications;
	}

	public void setApplications(List<String> applications) {
		this.applications = applications;
	}
	
	public boolean acceptApplication(String applicationName) {
		boolean result = true;
		
		if(applications!=null && !applications.isEmpty()) {
			result = false;

			for(String app : applications) {
				if(applicationName.toLowerCase().contains(app.toLowerCase())) {
					result =true;
					break;
				}
			}
		}
		return result;
	}
}
