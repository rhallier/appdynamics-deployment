package org.appdynamics.deployment.model;

public class Node {
    private int id;
    private String name;
    private String type;
    private int machineId;
    private String machineName;
    private String machineOSType;
    private boolean machineAgentPresent;
    private String machineAgentVersion;
    private boolean appAgentPresent;
    private String appAgentVersion;
	private IPAddresses ipAddresses;
	private int tierId;
	private String tierName;
	// Added for 4.2
	private String agentType;
	private String nodeUniqueLocalId;
	
	private boolean active;

	private Tier tier;
	
	private Node() {}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public int getMachineId() {
		return machineId;
	}

	public String getMachineName() {
		return machineName;
	}

	public String getMachineOSType() {
		return machineOSType;
	}

	public boolean isMachineAgentPresent() {
		return machineAgentPresent;
	}

	public String getMachineAgentVersion() {
		return machineAgentVersion;
	}

	public boolean isAppAgentPresent() {
		return appAgentPresent;
	}

	public String getAppAgentVersion() {
		return appAgentVersion;
	}

	public IPAddresses getIpAddresses() {
		return ipAddresses;
	}

	public String getAgentType() {
		return agentType;
	}

	public String getNodeUniqueLocalId() {
		return nodeUniqueLocalId;
	}

	public int getTierId() {
		return tierId;
	}

	public String getTierName() {
		return tierName;
	}

	public Tier getTier() {
		return tier;
	}

	void setTier(Tier tier) {
		this.tier = tier;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * 		
	    NATIVE_WEB_SERVER			WEB SERVER
		APP_AGENT					JAVA
		DOT_NET_APP_AGENT			.NET
		NODEJS_APP_AGENT			NODE.js
		...

	 * @return
	 */
	public boolean isJavaAgent() {
		return AgentType.isJava(agentType);
	}
	
	@Override
	public String toString() {
		return "_Node [id=" + id + ", name=" + name + ", type=" + type + ", machineId=" + machineId + ", machineName=" + machineName + ", machineOSType=" + machineOSType + ", machineAgentPresent=" + machineAgentPresent + ", machineAgentVersion=" + machineAgentVersion + ", appAgentPresent=" + appAgentPresent + ", appAgentVersion=" + appAgentVersion + ", ipAddresses=" + ipAddresses + ", tierId=" + tierId + ", tierName=" + tierName + ", agentType=" + agentType + ", nodeUniqueLocalId=" + nodeUniqueLocalId + ", active=" + active + "]";
	}

	
	
}
