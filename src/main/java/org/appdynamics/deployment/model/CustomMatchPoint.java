package org.appdynamics.deployment.model;

import org.appdynamics.deployment.model.rules.MatchRule;
import org.appdynamics.deployment.utils.StringUtils;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName ="custom-match-point")
public class CustomMatchPoint {
	private String name;
    @JacksonXmlProperty(localName = "business-transaction-name")
	private String businessTransactionName;
    @JacksonXmlProperty(localName = "entry-point")
	private String entryPoint;
	private boolean background;
	private boolean enabled;
    @JacksonXmlProperty(localName = "match-rule")
	private MatchRule matchRule;

	public String getName() {
		return name;
	}

	public String getBusinessTransactionName() {
		return businessTransactionName;
	}

	public String getEntryPoint() {
		return entryPoint;
	}

	public boolean isBackground() {
		return background;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public MatchRule getMatchRule() {
		return matchRule;
	}

	public boolean isDefault() {

		// Pojo Rules
		if(matchRule.getPojoRule()!=null) {
			if(StringUtils.isInList(this.name, "Cron4J", "JavaTimer", "JCronTab", "Quartz")) {
				return true;
			}
		}
			
		return false;
	}
	
	public boolean isUserModified() {
		return !isDefault() || isEnabled(); 
	}

	@Override
	public String toString() {
		return "CustomMatchPoint [name=" + name + ", businessTransactionName=" + businessTransactionName + ", entryPoint=" + entryPoint + ", background=" + background + ", enabled=" + enabled + ", matchRule=" + matchRule + "]";
	}
}
