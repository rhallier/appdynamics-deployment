package org.appdynamics.deployment.model.rules;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/*




-<message-content message-type="TEXT">


-<text-message>

<text filter-value="dgfdfg" filter-type="STARTSWITH"/>

</text-message>

</message-content>

</incoming-message-rule>
*/
public class JmsRule {

	private boolean enabled;
	private boolean excluded;
	private int priority;
	@JacksonXmlProperty(localName = "class-name")
	private MatchClassName className;
	@JacksonXmlProperty(localName = "message-properties")
	private MessageProperties messageProperties;
	@JacksonXmlProperty(localName = "message-destination")
	private MessageDestination messageDestination;
	@JacksonXmlProperty(localName = "message-content")
	private MessageContent messageContent;

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

	public MessageProperties getMessageProperties() {
		return messageProperties;
	}

	public MessageDestination getMessageDestination() {
		return messageDestination;
	}

	public MessageContent getMessageContent() {
		return messageContent;
	}

	@Override
	public String toString() {
		return "JmsRule [enabled=" + enabled + ", excluded=" + excluded + ", priority=" + priority + ", className=" + className + ", messageProperties=" + messageProperties + ", messageDestination=" + messageDestination + ", messageContent=" + messageContent + "]";
	}

}
