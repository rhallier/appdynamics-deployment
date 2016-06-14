package org.appdynamics.deployment.model.rules;

public class SplitConfig {
	private String type;
	private String operation;

	public String getType() {
		return type;
	}

	public String getOperation() {
		return operation;
	}

	@Override
	public String toString() {
		return "SplitConfig [type=" + type + ", operation=" + operation + "]";
	}

}
