package org.appdynamics.deployment.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.appdynamics.deployment.model.AgentType;
import org.appdynamics.deployment.model.Application;
import org.appdynamics.deployment.model.Controller;
import org.appdynamics.deployment.model.Node;
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

	public Map<String, Integer> getConsumedLicenses() {
		Map<String, List<Integer>> agents = new HashMap<String, List<Integer>>();
		
		// Build map of agents / machines
		if(applications!=null)
			for(Application app : applications) {
				for(Node node : app.getNodes()) {
					if(!node.isActive())
						continue;
					
					List<Integer> machines = agents.get(node.getAgentType());
					if(machines==null) {
						machines = new ArrayList<Integer>();
						agents.put(node.getAgentType(), machines);
					}
					
					machines.add(node.getMachineId());
				}
			}
				
		// Build consumes licenses
		Map<String, Integer> result = new HashMap<String, Integer>();

		for(String agentType : agents.keySet()) {
			int licenses = 0;
			// Count per agent instance
			if(AgentType.isJava(agentType)) {
				licenses = agents.get(agentType).size();
			}
			// Count per machine instance
			else {
				Set<Integer> distinctMachines = new HashSet<Integer>(agents.get(agentType));
				licenses=distinctMachines.size();
			}
			
			result.put(agentType, licenses);
		}
		
		return result;
	}
}
