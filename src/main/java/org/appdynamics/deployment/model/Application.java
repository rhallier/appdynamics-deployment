package org.appdynamics.deployment.model;

import java.util.ArrayList;
import java.util.Collection;

public class Application {
    private int id;
    private String name;
    private String description;
    
    // Tied to Application scope (not including the tier level)
    private int modifiedAutoDiscovery;
    private int nbOfUserModifiedCustomMatchPoints;
    private int nbOfUserModifiedExcludeMatchPoints;
    
	private Collection<Tier> tiers = new ArrayList<Tier>();
	private Collection<HealthRuleViolation> healthRuleViolations = new ArrayList<HealthRuleViolation>();

	private Application() {
		super();
	}

	public Application addTier(Tier tier) {
		tier.setApplication(this);
		this.tiers.add(tier);
		return this;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

	public int getModifiedAutoDiscovery() {
		return modifiedAutoDiscovery;
	}

	public void setModifiedAutoDiscovery(int modifiedAutoDiscovery) {
		this.modifiedAutoDiscovery = modifiedAutoDiscovery;
	}

	public int getNbOfUserModifiedCustomMatchPoints() {
		return nbOfUserModifiedCustomMatchPoints;
	}

	public void setNbOfUserModifiedCustomMatchPoints(int nbOfUserModifiedCustomMatchPoints) {
		this.nbOfUserModifiedCustomMatchPoints = nbOfUserModifiedCustomMatchPoints;
	}

	public int getNbOfUserModifiedExcludeMatchPoints() {
		return nbOfUserModifiedExcludeMatchPoints;
	}

	public void setNbOfUserModifiedExcludeMatchPoints(int nbOfUserModifiedExcludeMatchPoints) {
		this.nbOfUserModifiedExcludeMatchPoints = nbOfUserModifiedExcludeMatchPoints;
	}

	public Collection<Tier> getTiers() {
		return tiers;
	}
	
	public int getNbOfTiers() {
		return tiers!=null ? tiers.size() : 0;
	}
	
	public int getNbOfNodes() {
		int result = 0;
		if(tiers!=null)
			for(Tier tier : tiers) {
				result+=tier.getNumberOfNodes();
			}
		return result;
	}
	
	public int getNbOfActiveNodes() {
		int result = 0;
		if(tiers!=null)
			for(Tier tier : tiers) {
				result+=tier.getNbOfActiveNodes();
			}
		return result;
	}
	
	public Tier getTier(long l) {
		Tier result = null;
		if(tiers!=null)
			for(Tier tier : tiers)
				if(tier.getId()==l) {
					result = tier;
					break;
				}
					
		return result;
	}
	
	public Collection<HealthRuleViolation> getHealthRuleViolations() {
		return healthRuleViolations;
	}

	public void addHealthRuleViolation(HealthRuleViolation alert) {
		this.healthRuleViolations.add(alert);
	}
	
	// Scoring
	
	public float getScore() {
		// Business Transactions
		float bt_score = 0;

		if(tiers!=null && !tiers.isEmpty()) {

			for(Tier tier : tiers) {
				if(tier.getModifiedAutoDiscovery()>0)
					bt_score++;

				if(tier.getNbOfUserModifiedCustomMatchPoints()>0)
					bt_score+=3;
					
				if(tier.getNbOfUserModifiedExcludeMatchPoints()>0)
					bt_score+=1;
				
				if(tier.isOverflown())
					bt_score-=5;
				else if(tier.getNbOfBusinessTransactions()>50)
					bt_score--;
				
				if(tier.getNbOfExcludedBusinessTransactions()>0)
					bt_score+=1;
			}
			
			bt_score=bt_score/tiers.size();
		}
		else
			bt_score=-1;

		if(getModifiedAutoDiscovery()>0)
			bt_score++;

		if(getNbOfUserModifiedCustomMatchPoints()>0)
			bt_score+=3;

		if(getTotalNbOfUserModifiedExcludedMatchPoints()>0)
			bt_score+=1;

		return bt_score;
	}

	// Business Transactions
	public int getNbOfBusinessTransactions() {
		int result = 0;
		if(tiers!=null) {
			for(Tier tier : tiers)
				result+=tier.getNbOfBusinessTransactions();
		}
		return result;
	}

	public int getNbOfActiveBusinessTransactions() {
		int result = 0;
		if(tiers!=null) {
			for(Tier tier : tiers)
				result+=tier.getNbOfActiveBusinessTransactions();
		}
		return result;
	}

	public boolean isOverflown() {
		boolean result=false;
		if(tiers!=null) {
			for(Tier tier : tiers)
				if(tier.isOverflown()) {
					result=true;
					break;
				}
		}
		return result;
	}

	public int getNbOfExcludedBusinessTransactions() {
		int result = 0;
		if(tiers!=null) {
			for(Tier tier : tiers)
				result+=tier.getNbOfExcludedBusinessTransactions();
		}
		return result;
	}
	
	// Health Rules Violations
	
	public int getNbOfHealthRuleViolations() {
		return healthRuleViolations!=null ? healthRuleViolations.size() : 0;
	}

	// Transaction Detection
	public int getTotalNbOfModifiedAutoDiscovery() {
		int result = modifiedAutoDiscovery;
		
		if(tiers!=null)
			for(Tier tier : tiers)
				result+=tier.getModifiedAutoDiscovery();
		
		return result;
	}

	public int getTotalNbOfUserModifiedCustomMatchPoints() {
		int result = nbOfUserModifiedCustomMatchPoints;
		
		if(tiers!=null)
			for(Tier tier : tiers)
				result+=tier.getNbOfUserModifiedCustomMatchPoints();
		
		return result;
	}

	public int getTotalNbOfUserModifiedExcludedMatchPoints() {
		int result = nbOfUserModifiedExcludeMatchPoints;
		
		if(tiers!=null)
			for(Tier tier : tiers)
				result+=tier.getNbOfUserModifiedExcludeMatchPoints();
		
		return result;
	}

	@Override
	public String toString() {
		return "_Application [id=" + id + ", name=" + name + ", description=" + description + ", modifiedAutoDiscovery=" + modifiedAutoDiscovery + "]";
	}


	
}

