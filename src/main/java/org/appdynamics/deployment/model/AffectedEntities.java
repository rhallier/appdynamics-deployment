package org.appdynamics.deployment.model;

import java.util.ArrayList;

public class AffectedEntities {
    private ArrayList<EntityDefinition> entityDefinition=new ArrayList<EntityDefinition>();

	public ArrayList<EntityDefinition> getEntityDefinition() {
		return entityDefinition;
	}

	@Override
	public String toString() {
		return "AffectedEntities [entityDefinition=" + entityDefinition + "]";
	}
    
    

}
