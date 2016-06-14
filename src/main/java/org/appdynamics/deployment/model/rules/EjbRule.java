package org.appdynamics.deployment.model.rules;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class EjbRule {
	private boolean enabled;
	private boolean excluded;
	private int priority;
	private String type;
	@JacksonXmlProperty(localName = "ejb-name")
	private MatchClassName ejbName;
	@JacksonXmlProperty(localName = "implements-business-interface")
	private MatchClassName implementsBusinessInterface;
	@JacksonXmlProperty(localName = "extends-from-class")
	private MatchClassName extendsFromClass;
	@JacksonXmlProperty(localName = "class-name")
	private MatchClassName className;
	@JacksonXmlProperty(localName = "match-method")
	public MatchMethod matchMethod;

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isExcluded() {
		return excluded;
	}

	public int getPriority() {
		return priority;
	}

	public MatchClassName getEjbName() {
		return ejbName;
	}

	public MatchClassName getImplementsBusinessInterface() {
		return implementsBusinessInterface;
	}

	public MatchClassName getExtendsFromClass() {
		return extendsFromClass;
	}

	public MatchClassName getClassName() {
		return className;
	}

	public MatchMethod getMatchMethod() {
		return matchMethod;
	}

	public String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "EjbRule [enabled=" + enabled + ", excluded=" + excluded + ", priority=" + priority + ", ejbName=" + ejbName + ", implementsBusinessInterface=" + implementsBusinessInterface + ", extendsFromClass=" + extendsFromClass + ", className=" + className + ", matchMethod=" + matchMethod + "]";
	}

}
