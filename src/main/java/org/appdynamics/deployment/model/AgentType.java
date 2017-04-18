package org.appdynamics.deployment.model;

public enum AgentType {

	APP_AGENT("Java"),
	NATIVE_WEB_SERVER("Web Server"),
	DOT_NET_APP_AGENT(".Net"),
	NODEJS_APP_AGENT("Node.js"),
	PHP_APP_AGENT("PHP"),
	PYTHON_APP_AGENT("Python"),
    RUBY_APP_AGENT("Ruby"),
	NATIVE_SDK("Native SDK"),
	NATIVE_DYNAMICS("Native Dynamic"),
	MACHINE_AGENT("Machine agent"),
	DB_COLLECTOR("DB"),
	DB_AGENT("DB"),
    SIM_MACHINE_AGENT("Server Visibility"),
    APM_MACHINE_AGENT("APM Machine Agent"),
    SERVICE_AVAIL_MACHINE_AGENT("Service Availability"),
    APM_APP_AGENT("APM Agent"),
    ANALYTICS_AGENT("Analytics");

	private String label;

	private AgentType(String label) {
		this.label = label;
	}

	private String getLabel() {
		return label;
	}

	public static boolean isJava(String agentType) {
		return APP_AGENT.name().equalsIgnoreCase(agentType);
	}
	
	public static String translate(String agentType) {
		String result = agentType;
		if(agentType!=null) 
			for(AgentType at : AgentType.values()) {
				if(at.name().equalsIgnoreCase(agentType)) {
					result = at.getLabel();
					break;
				}
			}

		return result;
	}
}
