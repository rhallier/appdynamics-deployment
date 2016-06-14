package org.appdynamics.deployment.model;

public class EntityDefinition {
	private long entityId;
    private String entityType;
    private String name;
	
    public long getEntityId() {
		return entityId;
	}
	public String getEntityType() {
		return entityType;
	}
	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return "_EntityDefinition [entityId=" + entityId + ", entityType=" + entityType + ", name=" + name + "]";
	}
}
