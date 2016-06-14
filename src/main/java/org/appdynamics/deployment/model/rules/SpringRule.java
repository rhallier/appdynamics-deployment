package org.appdynamics.deployment.model.rules;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class SpringRule {
	private boolean enabled;
	private boolean excluded;
	private int priority;
	@JacksonXmlProperty(localName = "bean-id")
	private MatchClassName beanId;
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

	public MatchClassName getBeanId() {
		return beanId;
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

	@Override
	public String toString() {
		return "SpringRule [enabled=" + enabled + ", excluded=" + excluded + ", priority=" + priority + ", beanId=" + beanId + ", implementsBusinessInterface=" + implementsBusinessInterface + ", extendsFromClass=" + extendsFromClass + ", className=" + className + ", matchMethod=" + matchMethod + "]";
	}

}
