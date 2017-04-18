package org.appdynamics.deployment.controller;

public enum ReportFormat {
	PDF("PDF"), XLSX("Excel");
	
	private String label;

	private ReportFormat(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
