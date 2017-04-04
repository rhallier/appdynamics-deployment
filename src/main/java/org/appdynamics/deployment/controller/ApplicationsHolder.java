package org.appdynamics.deployment.controller;

import java.util.List;

import org.appdynamics.deployment.model.Application;
import org.appdynamics.deployment.model.Controller;
import org.appdynamics.deployment.model.Timerange;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(scopeName = "session")
public class ApplicationsHolder {

	private Controller controller;

	private Timerange timerange;
	private List<Application> applications = null;

	public List<Application> getApplications() {
		return applications;
	}

	public Controller getController() {
		return controller;
	}

	public void resetWithController(Controller controller) {
		this.controller = controller;
		this.timerange=null;
		this.applications=null;
	}

	public void setApplications(List<Application> applications) {
		this.applications = applications;
	}

	public Timerange getTimerange() {
		return timerange;
	}

	public void setTimerange(Timerange timerange) {
		this.timerange = timerange;
	}

	public boolean isControllerSet() {
		return this.controller!=null;
	}
	
	public boolean isInitialized() {
		return applications != null;
	}

	public int getNbApplications() {
		return applications != null ? applications.size() : 0;
	}

}
