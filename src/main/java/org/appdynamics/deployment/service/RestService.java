package org.appdynamics.deployment.service;

import java.util.ArrayList;
import java.util.List;

import org.appdynamics.deployment.model.Application;
import org.appdynamics.deployment.model.AutoDiscoveryConfig;
import org.appdynamics.deployment.model.BusinessTransaction;
import org.appdynamics.deployment.model.Controller;
import org.appdynamics.deployment.model.CustomMatchPoints;
import org.appdynamics.deployment.model.Event;
import org.appdynamics.deployment.model.Excludes;
import org.appdynamics.deployment.model.HealthRuleViolation;
import org.appdynamics.deployment.model.MetricData;
import org.appdynamics.deployment.model.Node;
import org.appdynamics.deployment.model.Tier;
import org.appdynamics.deployment.model.Timerange;
import org.appdynamics.deployment.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.exceptions.UnirestException;

@Service
public class RestService {

	final Logger logger = LoggerFactory.getLogger(RestService.class);

	public Application[] getApplications(Controller controller) throws UnirestException {
		HttpResponse<Application[]> applicationsRep = RestClient.of(controller).get("rest/applications").toJson().asObject(Application[].class);
		Application[] apps = applicationsRep.getBody();
		return apps;
	}

	public Tier[] getTiers(Controller controller, int applicationId) throws UnirestException {
		HttpResponse<Tier[]> tiersRep = RestClient.of(controller).get("rest/applications/{appId}/tiers").routeParam("appId", String.valueOf(applicationId)).toJson().asObject(Tier[].class);
		Tier[] tiers = tiersRep.getBody();
		return tiers;
	}
	
	public Node[] getNodes(Controller controller, int applicationId) throws UnirestException {
		HttpResponse<Node[]> nodesRep = RestClient.of(controller).get("rest/applications/{appId}/nodes").routeParam("appId", String.valueOf(applicationId)).toJson().asObject(Node[].class);
		Node[] nodes = nodesRep.getBody();
		return nodes;
	}
	
	public BusinessTransaction[] getBusinessTransactions(Controller controller, int applicationId, Boolean exclude) throws UnirestException {
		RestHttpRequest request = RestClient.of(controller).get("rest/applications/{appId}/business-transactions").routeParam("appId", String.valueOf(applicationId));
		
		if(exclude!=null)
			request.queryString("exclude", exclude);
		
		HttpResponse<BusinessTransaction[]> btRep = request.toJson().asObject(BusinessTransaction[].class);
		
		BusinessTransaction[] bts = btRep.getBody();
		return bts;
	}
	
	public HealthRuleViolation[] getHealthRuleViolations(Controller controller, int applicationId, Timerange timerange) throws UnirestException {
		HttpResponse<HealthRuleViolation[]> hrRep = RestClient.of(controller)
				.get("rest/applications/{appId}/problems/healthrule-violations")
				.routeParam("appId", String.valueOf(applicationId))
				.between(timerange)
				.toJson()
				.asObject(HealthRuleViolation[].class);
		HealthRuleViolation[] hrs = hrRep.getBody();
		return hrs;
	}
	
	public Event[] getEventData(Controller controller, int applicationId, String[] eventTypes, String[] severities, Timerange timerange) throws UnirestException {
		//curl --user user1@customer1:secret http://demo.appdynamics.com//controller/rest/applications/6/events\?time-range-type=BEFORE_NOW\&duration-in-mins=30\&event-types=%20APPLICATION_ERROR,DIAGNOSTIC_SESSION\&severities=INFO,WARN,ERROR
		HttpResponse<Event[]> eRep = RestClient.of(controller)
				.get("rest/applications/{appId}/events")
				.routeParam("appId", String.valueOf(applicationId))
				.between(timerange)
				.queryString("event-types", StringUtils.join(",",eventTypes))
				.queryString("severities", StringUtils.join(",", severities))
				.toJson()
				.asObject(Event[].class);
		Event[] result = eRep.getBody();
		return result;
	}

	/*
	 * Transaction Detection
	 */
	public AutoDiscoveryConfig getAutoDiscoveryConfig(Controller controller, int applicationId, String tierName) throws UnirestException {
		AutoDiscoveryConfig result=null;
		
		if(tierName==null)
			result = RestClient.of(controller).get("transactiondetection/{appId}/auto").routeParam("appId", String.valueOf(applicationId)).asXml(AutoDiscoveryConfig.class);
		else
			result = RestClient.of(controller).get("transactiondetection/{appId}/{tierName}/auto").routeParam("appId", String.valueOf(applicationId)).routeParam("tierName", tierName).asXml(AutoDiscoveryConfig.class);
		
		return result;
	}

	public CustomMatchPoints getCustomMatchPoints(Controller controller, int applicationId, String tierName) throws UnirestException {
		CustomMatchPoints result=null;
		
		if(tierName==null)
			result = RestClient.of(controller).get("transactiondetection/{appId}/custom").routeParam("appId", String.valueOf(applicationId)).asXml(CustomMatchPoints.class);
		else
			result = RestClient.of(controller).get("transactiondetection/{appId}/{tierName}/custom").routeParam("appId", String.valueOf(applicationId)).routeParam("tierName", tierName).asXml(CustomMatchPoints.class);
		
		return result;
	}

	public Excludes getExcludes(Controller controller, int applicationId, String tierName) throws UnirestException {
		Excludes result=null;
		
		if(tierName==null)
			result = RestClient.of(controller).get("transactiondetection/{appId}/exclude").routeParam("appId", String.valueOf(applicationId)).asXml(Excludes.class);
		else
			result = RestClient.of(controller).get("transactiondetection/{appId}/{tierName}/exclude").routeParam("appId", String.valueOf(applicationId)).routeParam("tierName", tierName).asXml(Excludes.class);
		
		return result;
	}

	
	public boolean isActiveNode(Controller controller, long applicationId, String tierName, String nodeName, Timerange timerange) throws UnirestException {
		HttpResponse<MetricData[]> mdRep = RestClient.of(controller)
			 .get("rest/applications/{appId}/metric-data")
			.routeParam("appId", String.valueOf(applicationId))
			.queryString("metric-path", "Application Infrastructure Performance|"+tierName+"|Individual Nodes|"+nodeName+"|Agent|App|Availability")
			.between(timerange)
			.toJson()
			.asObject(MetricData[].class);
		
		MetricData[] data = mdRep.getBody();
		
		if(data!=null && data.length>0)
			return data[0].getMetricValues().get(0).getSum()>0;
		else
			return false;
	}
	
	public long getBTNbOfCalls(Controller controller, long applicationId, BusinessTransaction bt, Timerange timerange) throws UnirestException {
		HttpResponse<MetricData[]> mdRep = RestClient.of(controller)
			 .get("rest/applications/{appId}/metric-data")
			.routeParam("appId", String.valueOf(applicationId))
			.queryString("metric-path", "Business Transaction Performance|Business Transactions|"+bt.getTierName()+"|"+bt.getName()+"|Calls per Minute")
			.between(timerange)
			.toJson()
			.asObject(MetricData[].class);
		
		MetricData[] data = mdRep.getBody();
		if(data!=null && data.length>0 && data[0].getMetricValues()!=null && data[0].getMetricValues().size()!=0)
			return data[0].getMetricValues().get(0).getSum();
		else
			return 0;
	}


	public List<Application> buildGraph(Controller controller, ModelFilter filter, Timerange timerange) throws UnirestException {
		
		int nbapps=0;
		List<Application> result = new ArrayList<Application>();
		
		Application[] apps = getApplications(controller);
		
		if (apps != null)
			for (Application application : apps) {
				nbapps++;
				logger.debug("Processing application {} ({}/{})", application.getName(), nbapps, apps.length);
				
				if(!filter.acceptApplication(application.getName()))
					continue;
				else
					result.add(application);

				Tier[] tiers = getTiers(controller,application.getId());
				Node[] nodes = getNodes(controller,application.getId());
				BusinessTransaction[] transactions = getBusinessTransactions(controller,application.getId(), false);
				BusinessTransaction[] excludedTransactions = getBusinessTransactions(controller,application.getId(), true);
				HealthRuleViolation[] alerts = getHealthRuleViolations(controller,application.getId(), timerange);
				
				// Transaction detection
				AutoDiscoveryConfig autoDiscoveryConfigApplication = getAutoDiscoveryConfig(controller,application.getId(), null);
				CustomMatchPoints customMatchPointsApplication = getCustomMatchPoints(controller, application.getId(), null);
				Excludes excludesApplication = getExcludes(controller, application.getId(), null);

				// Application
				application.setModifiedAutoDiscovery(autoDiscoveryConfigApplication.getModifiedAutoConfig());
				application.setNbOfUserModifiedCustomMatchPoints(customMatchPointsApplication.getNbOfUserCustomMatchPoints());
				application.setNbOfUserModifiedExcludeMatchPoints(excludesApplication.getNbOfUserExcludes());
				

				// Tiers
				if(tiers!=null) {
					logger.debug("   Tiers : ");

					for(Tier tier : tiers) {
						logger.debug("          {}", tier.getName());

						application.addTier(tier);

						AutoDiscoveryConfig autoDiscoveryConfigTier = getAutoDiscoveryConfig(controller,application.getId(), tier.getName());
						CustomMatchPoints customMatchPointsTier = getCustomMatchPoints(controller, application.getId(), tier.getName());
						Excludes excludesTier = getExcludes(controller, application.getId(), tier.getName());
						
						tier.setModifiedAutoDiscovery(autoDiscoveryConfigTier.getModifiedAutoConfig());
						tier.setNbOfUserModifiedCustomMatchPoints(customMatchPointsTier.getNbOfUserCustomMatchPoints());
						tier.setNbOfUserModifiedExcludeMatchPoints(excludesTier.getNbOfUserExcludes());
					}
				}
				
				// Nodes
				if (nodes != null) {
					logger.debug("   Nodes : ");

					for (Node node : nodes) {
						logger.debug("          {}", node.getName());

						node.setActive(isActiveNode(controller,application.getId(), node.getTierName(), node.getName(), timerange));

						Tier parent = application.getTier(node.getTierId());

						if (parent != null) {
							parent.addNode(node);
						} else
							logger.error("Node {} doesnt belong to a tier", node);
					}
				}
				
				// Business Transactions
				if (transactions != null) {
					logger.debug("   Business Transactions : ");
					
					for (BusinessTransaction bt : transactions) {
						logger.debug("          {}", bt.getName());

						bt.setCalls(getBTNbOfCalls(controller,application.getId(), bt, timerange));

						Tier parent = application.getTier(bt.getTierId());

						if (parent != null) {
							parent.addBusinessTransaction(bt);
						} else
							logger.error("Business Transaction {} doesnt belong to a tier", bt);
					}
				}

				if (excludedTransactions != null) {
					logger.debug("   Excluded Business Transactions : ");
					
					for (BusinessTransaction bt : excludedTransactions) {
						logger.debug("          {}", bt.getName());

						Tier parent = application.getTier(bt.getTierId());

						if (parent != null) {
							parent.addExcludedBusinessTransaction(bt);
						} else
							logger.error("Business Transaction {} doesnt belong to a tier", bt);
					}
				}

				
				// Health Rules
				if (alerts != null) {
					logger.debug("   Health Rule Violations : ");

					for(HealthRuleViolation hr : alerts) {
						logger.debug("          {}", hr.getName());
						application.addHealthRuleViolation(hr);
					}
				}
				
			}
		return result;
	}
}
