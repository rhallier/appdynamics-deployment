package org.appdynamics.deployment.model;

import java.util.ArrayList;
import java.util.Collection;

public class Tier {

	private int id;
	private String name;
	private String description;
    private String type;
    private String agentType;
    private int numberOfNodes;
    
    private int modifiedAutoDiscovery;
    private int nbOfUserModifiedCustomMatchPoints;
    private int nbOfUserModifiedExcludeMatchPoints;
    
	private Application application;
    private Collection<Node> nodes = new ArrayList<Node>();
	private Collection<BusinessTransaction> transactions = new ArrayList<BusinessTransaction>();
	private Collection<BusinessTransaction> excludedTransactions = new ArrayList<BusinessTransaction>();

	private Tier() {}
	
	public Tier addBusinessTransaction(BusinessTransaction businessTransaction) {
		businessTransaction.setTier(this);
		this.transactions.add(businessTransaction);
		return this;
	}

	public Tier addExcludedBusinessTransaction(BusinessTransaction businessTransaction) {
		this.excludedTransactions.add(businessTransaction);
		return this;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
	
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getAgentType() {
		return agentType;
	}

	public Application getApplication() {
		return application;
	}

	void setApplication(Application application) {
		this.application = application;
	}

	// NODES
	public Tier addNode(Node node) {
		node.setTier(this);
		this.nodes.add(node);
		return this;
	}
	
	public int getNumberOfNodes() {
		return numberOfNodes;
	}

	public Collection<Node> getNodes() {
		return nodes;
	}

	public int getNbOfActiveNodes() {
		int result = 0;
		if(nodes!=null)
			for(Node node : nodes)
				result+=(node.isActive() ? 1 : 0);
		return result;
	}


	// BUSINESS TRANSACTIONS
	public Collection<BusinessTransaction> getTransactions() {
		return transactions;
	}

	public Collection<BusinessTransaction> getExcludedTransactions() {
		return excludedTransactions;
	}

	public int getNbOfActiveBusinessTransactions() {
		int result = 0;
		if(transactions!=null) {
			for(BusinessTransaction bt : transactions)
				if(bt.getCalls()!=0)
					result++;
		}
		return result;
	}

	public int getNbOfBusinessTransactions() {
		return transactions!=null ? transactions.size() : 0;
	}

	public boolean isOverflown() {
		boolean result=false;
		if(transactions!=null) {
			for(BusinessTransaction bt : transactions)
				if(bt.isOverflow() && bt.getCalls()!=0) {
					result = true;
					break;
				}
		}
		return result;
	}

	public int getNbOfExcludedBusinessTransactions() {
		return excludedTransactions!=null ? excludedTransactions.size() : 0;	
	}

	// TRANSACTION DETECTION
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


	@Override
	public String toString() {
		return "_Tier [id=" + id + ", name=" + name + ", description=" + description + ", type=" + type + ", agentType=" + agentType + ", numberOfNodes=" + numberOfNodes + ", modifiedAutoDiscovery=" + modifiedAutoDiscovery + "]";
	}

	
}
