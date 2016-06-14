package org.appdynamics.deployment.model.rules;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class MatchRule {
    @JacksonXmlProperty(localName = "pojo-rule")
	private PojoRule pojoRule;
    @JacksonXmlProperty(localName = "servlet-rule")
	private ServletRule servletRule;
    @JacksonXmlProperty(localName = "web-rule")
	private WebRule webRule;
    @JacksonXmlProperty(localName = "ejb-rule")
	private EjbRule ejbRule;
    @JacksonXmlProperty(localName = "spring-bean-rule")
	private SpringRule springRule;
    @JacksonXmlProperty(localName = "struts-action-rule")
	private StrutsRule strutsRule;
    @JacksonXmlProperty(localName = "web-service-rule")
	private WebServiceRule webServiceRule;
    @JacksonXmlProperty(localName = "incoming-message-rule")
	private JmsRule jmsRule;

    @JacksonXmlProperty(localName = "poco-rule")
	private PocoRule pocoRule;
    @JacksonXmlProperty(localName = "asp-dotnet-rule")
	private AspDotNetRule aspDotNetRule;

	public MatchRule() {
	}

	public PojoRule getPojoRule() {
		return pojoRule;
	}

	public PocoRule getPocoRule() {
		return pocoRule;
	}

	public ServletRule getServletRule() {
		return servletRule;
	}

	public WebRule getWebRule() {
		return webRule;
	}

	public EjbRule getEjbRule() {
		return ejbRule;
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

	public JmsRule getJmsRule() {
		return jmsRule;
	}

	public AspDotNetRule getAspDotNetRule() {
		return aspDotNetRule;
	}

}
