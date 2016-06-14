package org.appdynamics.deployment.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Event {
	private int id;
	private String type;
	@JacksonXmlProperty(localName = "event-time")
	private long eventTime;
	private String severity;
	private String summary;
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "affected-entities")
	private AffectedEntities affectedEntities;
	@JacksonXmlProperty(localName = "mark-as-read")
	private boolean markedAsRead;
	@JacksonXmlProperty(localName = "mark-as-resolved")
	private boolean markedAsResolved;
	private boolean archived;
	@JacksonXmlProperty(localName = "deep-URL")
	private String deepURL;

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public long getEventTime() {
		return eventTime;
	}

	public String getSeverity() {
		return severity;
	}

	public String getSummary() {
		return summary;
	}

	public AffectedEntities getAffectedEntities() {
		return affectedEntities;
	}

	public boolean isMarkedAsRead() {
		return markedAsRead;
	}

	public boolean isMarkedAsResolved() {
		return markedAsResolved;
	}

	public boolean isArchived() {
		return archived;
	}

	public String getDeepURL() {
		return deepURL;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", type=" + type + ", eventTime=" + eventTime + ", severity=" + severity + ", summary=" + summary + ", affectedEntities=" + affectedEntities + ", markedAsRead=" + markedAsRead + ", markedAsResolved=" + markedAsResolved + ", archived=" + archived + ", deepURL=" + deepURL + "]";
	}

}
