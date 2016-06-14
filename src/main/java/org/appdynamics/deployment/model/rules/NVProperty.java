package org.appdynamics.deployment.model.rules;

public class NVProperty {
	private String name;
	private String value;

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return "NVProperty [name=" + name + ", value=" + value + "]";
	}

}
