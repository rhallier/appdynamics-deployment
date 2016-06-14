package org.appdynamics.deployment.controller;

public enum Report {
	
	APPLICATIONS("reportApplications","Application level"),
	TIERS("reportTiers","Tier level"),
	NODES("reportNodes","Node level"),
	BUSINESS_TRANSACTIONS("reportBusinessTransactions","Business Transactions")
	;
	
	private String filename;
	private String label;
	
	private Report(String filename, String label) {
		this.label=label;
		this.filename=filename;
	}

	public String getFilename() {
		return filename;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
