package org.appdynamics.deployment.model;

import org.appdynamics.deployment.model.rules.AspDotNetRule;
import org.appdynamics.deployment.model.rules.EjbRule;
import org.appdynamics.deployment.model.rules.JmsRule;
import org.appdynamics.deployment.model.rules.PojoRule;
import org.appdynamics.deployment.model.rules.ServletRule;
import org.appdynamics.deployment.model.rules.SpringRule;
import org.appdynamics.deployment.model.rules.StrutsRule;
import org.appdynamics.deployment.model.rules.WcfRule;
import org.appdynamics.deployment.model.rules.WebRule;
import org.appdynamics.deployment.model.rules.WebServiceRule;
import org.appdynamics.deployment.utils.StringUtils;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Exclude {
	private String name;
	@JacksonXmlProperty(localName = "servlet-rule")
	private ServletRule servletRule;
	@JacksonXmlProperty(localName = "asp-dotnet-rule")
	private AspDotNetRule aspDotNetRule;
	@JacksonXmlProperty(localName = "web-rule")
	private WebRule webRule;
    @JacksonXmlProperty(localName = "ejb-rule")
	private EjbRule ejbRule;
    @JacksonXmlProperty(localName = "incoming-message-rule")
	private JmsRule jmsRule;
    @JacksonXmlProperty(localName = "pojo-rule")
	private PojoRule pojoRule;
    @JacksonXmlProperty(localName = "spring-bean-rule")
	private SpringRule springRule;
    @JacksonXmlProperty(localName = "struts-action-rule")
	private StrutsRule strutsRule;
    @JacksonXmlProperty(localName = "web-service-rule")
	private WebServiceRule webServiceRule;
    @JacksonXmlProperty(localName = "wcf-rule")
	private WcfRule wcfRule;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ServletRule getServletRule() {
		return servletRule;
	}

	public AspDotNetRule getAspDotNetRule() {
		return aspDotNetRule;
	}
	
	public WebRule getWebRule() {
		return webRule;
	}
	

	public EjbRule getEjbRule() {
		return ejbRule;
	}

	public JmsRule getJmsRule() {
		return jmsRule;
	}

	public PojoRule getPojoRule() {
		return pojoRule;
	}

	public SpringRule getSpringRule() {
		return springRule;
	}

	public StrutsRule getStrutsRule() {
		return strutsRule;
	}

	public WebServiceRule getWebServiceRule() {
		return webServiceRule;
	}

	public WcfRule getWcfRule() {
		return wcfRule;
	}

	public boolean isDefault() {
		return StringUtils.isInList(name, 
				"NodeJS Static Content Filter",
				"Apache Axis Servlet",
				"Apache Axis2 Admin Servlet",
				"Apache Axis2 Servlet",
				"CometD Annotation Servlet",
				"CometD Servlet",
				"JAX WS RI Dispatcher Servlet",
				"JBoss 6.x web-services Servlet",
				"JBoss web-services servlet",
				"Jersey 2.x Servlet",
				"Jersey Servlet",
				"Spring WS - Base servlet for Spring's web framework",
				"Spring WS - dispatching of Web service messages",
				"Struts Action Servlet",
				"Weblogic JAX RPC Servlets",
				"Weblogic JAX WS Servlet",
				"Weblogic JAX WS Webservice Servlet",
				"Websphere web-services axis Servlet",
				"Websphere web-services Servlet",
				"XFire web-services servlet",
				"ASP.NET MVC5 Resource Handler",
				"ASP.NET WCF Activation Handler",
				"ASP.NET WebService Script Handler",
				"ASP.NET WebService Session Handler",
				"Python Static Content Filter"								
				);
	}
	
	public boolean isEnabled() {
		if (servletRule!=null)
			return servletRule.isEnabled();

		if (aspDotNetRule!=null)
			return aspDotNetRule.isEnabled();
		
		if (webRule!=null)
			return webRule.isEnabled();
		
		if (wcfRule!=null)
			return wcfRule.isEnabled();
		
		return false;
	}
	
	public boolean isUserModified() {
		return !isDefault() || !isEnabled(); 
	}

	@Override
	public String toString() {
		return "Exclude [name=" + name + ", servletRule=" + servletRule + ", aspDotNetRule=" + aspDotNetRule + ", webRule=" + webRule + ", ejbRule=" + ejbRule + ", jmsRule=" + jmsRule + ", pojoRule=" + pojoRule + ", springRule=" + springRule + ", strutsRule=" + strutsRule + ", webServiceRule=" + webServiceRule + "]";
	}
}
