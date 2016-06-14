package org.appdynamics.deployment.model;


public class BusinessTransaction {
	private int id;
    private String name;
    private String entryPointType;
    private String internalName;
    private boolean background;
    private long tierId;
    private String tierName;
    
    private Tier tier;
    
	private Long calls;

	private BusinessTransaction() {}
	
	public Long getCalls() {
		return calls;
	}

	public void setCalls(Long calls) {
		this.calls = calls;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEntryPointType() {
		return entryPointType;
	}

	public String getInternalName() {
		return internalName;
	}

	public boolean isBackground() {
		return background;
	}

	public boolean isOverflow() {
		return (name!=null && name.equals("_APPDYNAMICS_DEFAULT_TX_"));
	}

	public long getTierId() {
		return tierId;
	}

	public String getTierName() {
		return tierName;
	}
	
	public Tier getTier() {
		return tier;
	}

	public void setTier(Tier tier) {
		this.tier = tier;
	}

	@Override
	public String toString() {
		return "_BusinessTransaction [id=" + id + ", name=" + name + ", entryPointType=" + entryPointType + ", internalName=" + internalName + ", background=" + background + ", tierId=" + tierId + ", tierName=" + tierName + ", calls=" + calls + "]";
	}

	
}
