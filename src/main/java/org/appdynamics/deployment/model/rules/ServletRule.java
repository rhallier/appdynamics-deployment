package org.appdynamics.deployment.model.rules;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class ServletRule {
	private boolean enabled;
	private boolean excluded;
	private int priority;
    @JacksonXmlProperty(localName = "class-name")
	private MatchClassName className;
	private MatchClassName uri;
    @JacksonXmlProperty(localName = "servlet-name")
	private MatchClassName servletName;
	private MatchClassName host;
	private MatchClassName port;
	private Parameters parameters;
	private Headers headers;
	private Cookies cookies;
	private NVProperties properties;
    @JacksonXmlProperty(localName = "generic-method-config")
	private GenericMethodConfig genericMethodConfig;
    @JacksonXmlProperty(localName = "http-method")
	private String httpMethod;

	public boolean isEnabled() {
		return enabled;
	}

	public boolean isExcluded() {
		return excluded;
	}

	public int getPriority() {
		return priority;
	}

	public MatchClassName getClassName() {
		return className;
	}

	public MatchClassName getUri() {
		return uri;
	}

	public MatchClassName getServletName() {
		return servletName;
	}

	public MatchClassName getHost() {
		return host;
	}

	public MatchClassName getPort() {
		return port;
	}

	public Parameters getParameters() {
		return parameters;
	}

	public Headers getHeaders() {
		return headers;
	}

	public Cookies getCookies() {
		return cookies;
	}

	public NVProperties getProperties() {
		return properties;
	}

	public GenericMethodConfig getGenericMethodConfig() {
		return genericMethodConfig;
	}

	public String getHttpMethod() {
		return httpMethod;
	}

	@Override
	public String toString() {
		return "ServletRule [enabled=" + enabled + ", excluded=" + excluded + ", priority=" + priority + ", className=" + className + ", uri=" + uri + ", servletName=" + servletName + ", host=" + host + ", port=" + port + ", parameters=" + parameters + ", headers=" + headers + ", cookies=" + cookies + ", properties=" + properties + ", genericMethodConfig=" + genericMethodConfig + ", httpMethod=" + httpMethod + "]";
	}

}
