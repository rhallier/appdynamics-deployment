package org.appdynamics.deployment.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.appdynamics.deployment.model.Application;
import org.appdynamics.deployment.model.Audit;
import org.appdynamics.deployment.model.AutoDiscoveryConfig;
import org.appdynamics.deployment.model.BusinessTransaction;
import org.appdynamics.deployment.model.Controller;
import org.appdynamics.deployment.model.CustomMatchPoints;
import org.appdynamics.deployment.model.Event;
import org.appdynamics.deployment.model.Excludes;
import org.appdynamics.deployment.model.HealthRuleViolation;
import org.appdynamics.deployment.model.MetricData;
import org.appdynamics.deployment.model.Node;
import org.appdynamics.deployment.model.RequestSegmentData;
import org.appdynamics.deployment.model.Tier;
import org.appdynamics.deployment.model.Timerange;
import org.appdynamics.deployment.utils.StringUtils;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.mashape.unirest.http.HttpResponse;

@Service
public class RestService {

	final Logger logger = LoggerFactory.getLogger(RestService.class);
	final DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

	public Application[] getApplications(Controller controller) throws RestException {
		HttpResponse<Application[]> applicationsRep = RestClient.of(controller).get("rest/applications").toJson().asObject(Application[].class);
		Application[] apps = applicationsRep.getBody();
		return apps;
	}

	public Tier[] getTiers(Controller controller, int applicationId) throws RestException {
		HttpResponse<Tier[]> tiersRep = RestClient.of(controller).get("rest/applications/{appId}/tiers").routeParam("appId", String.valueOf(applicationId)).toJson().asObject(Tier[].class);
		Tier[] tiers = tiersRep.getBody();
		return tiers;
	}
	
	public Node[] getNodes(Controller controller, int applicationId) throws RestException {
		HttpResponse<Node[]> nodesRep = RestClient.of(controller).get("rest/applications/{appId}/nodes").routeParam("appId", String.valueOf(applicationId)).toJson().asObject(Node[].class);
		Node[] nodes = nodesRep.getBody();
		return nodes;
	}
	
	public BusinessTransaction[] getBusinessTransactions(Controller controller, int applicationId, Boolean exclude) throws RestException {
		RestHttpRequest request = RestClient.of(controller).get("rest/applications/{appId}/business-transactions").routeParam("appId", String.valueOf(applicationId));
		
		if(exclude!=null)
			request.queryString("exclude", exclude);
		
		HttpResponse<BusinessTransaction[]> btRep = request.toJson().asObject(BusinessTransaction[].class);
		
		BusinessTransaction[] bts = btRep.getBody();
		return bts;
	}
	
	public HealthRuleViolation[] getHealthRuleViolations(Controller controller, int applicationId, Timerange timerange) throws RestException {
		HttpResponse<HealthRuleViolation[]> hrRep = RestClient.of(controller)
				.get("rest/applications/{appId}/problems/healthrule-violations")
				.routeParam("appId", String.valueOf(applicationId))
				.between(timerange)
				.toJson()
				.asObject(HealthRuleViolation[].class);
		HealthRuleViolation[] hrs = hrRep.getBody();
		return hrs;
	}
	
	public Event[] getEventData(Controller controller, int applicationId, String[] eventTypes, String[] severities, Timerange timerange) throws RestException {
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
	 * Audit
	 */
	public Audit[] getAudit(Controller controller, Date start, Date end) throws RestException {
		List<Audit> result = new ArrayList<Audit>();
		
		if(start==null)
			throw new IllegalArgumentException("Start date is mandatory");
		
		LocalDate _start = new LocalDate(start);
		LocalDate _end = new LocalDate(end);
		
		if(_start.isAfter(_end))
			throw new IllegalArgumentException("Start date must be less than End date");
		
		// TODO : Check 403 http response code : response.getStatusLine().getStatusCode()
		
		for(LocalDate current = _start; !current.isAfter(_end); current = current.plusDays(1) ) {
			
			HttpResponse<Audit[]> eRep = RestClient.of(controller)
					.get("ControllerAuditHistory")
					.queryString("startTime", current.toDateTimeAtStartOfDay().toString(dtf))
					.queryString("endTime", current.toDateTimeAtStartOfDay().withTime(23, 59, 59, 999).toString(dtf))
					.asObject(Audit[].class);

			Audit[] resultDay = eRep.getBody();
			
			if(resultDay.length!=0)
				result.addAll(Arrays.asList(resultDay));
		}
		
		return result.toArray(new Audit[] {});
	}
	
	/*
	 * Snapshots
	 */
	public RequestSegmentData[] getSnapshots(Controller controller, int applicationId, boolean includeProperties, boolean includeExitCalls, Timerange timerange) throws RestException {
		HttpResponse<RequestSegmentData[]> eRep = RestClient.of(controller)
				.get("rest/applications/{appId}/request-snapshots")
				.routeParam("appId", String.valueOf(applicationId))
				.between(timerange)
				.queryString("need-props", includeProperties)
				.queryString("need-exit-calls", includeExitCalls)
				.toJson()
				.asObject(RequestSegmentData[].class);

		RequestSegmentData[] result = eRep.getBody();
		return result;
	}
	
	/*
	 * Transaction Detection
	 */
	public AutoDiscoveryConfig getAutoDiscoveryConfig(Controller controller, int applicationId, String tierName) throws RestException {
		AutoDiscoveryConfig result=null;
		
		if(tierName==null)
			result = RestClient.of(controller).get("transactiondetection/{appId}/auto").routeParam("appId", String.valueOf(applicationId)).asXml(AutoDiscoveryConfig.class);
		else
			result = RestClient.of(controller).get("transactiondetection/{appId}/{tierName}/auto").routeParam("appId", String.valueOf(applicationId)).routeParam("tierName", tierName).asXml(AutoDiscoveryConfig.class);
		
		return result;
	}

	public CustomMatchPoints getCustomMatchPoints(Controller controller, int applicationId, String tierName) throws RestException {
		CustomMatchPoints result=null;
		
		if(tierName==null)
			result = RestClient.of(controller).get("transactiondetection/{appId}/custom").routeParam("appId", String.valueOf(applicationId)).asXml(CustomMatchPoints.class);
		else
			result = RestClient.of(controller).get("transactiondetection/{appId}/{tierName}/custom").routeParam("appId", String.valueOf(applicationId)).routeParam("tierName", tierName).asXml(CustomMatchPoints.class);
		
		return result;
	}

	public Excludes getExcludes(Controller controller, int applicationId, String tierName) throws RestException {
		Excludes result=null;
		
		if(tierName==null)
			result = RestClient.of(controller).get("transactiondetection/{appId}/exclude").routeParam("appId", String.valueOf(applicationId)).asXml(Excludes.class);
		else
			result = RestClient.of(controller).get("transactiondetection/{appId}/{tierName}/exclude").routeParam("appId", String.valueOf(applicationId)).routeParam("tierName", tierName).asXml(Excludes.class);
		
		return result;
	}

	
	public boolean isActiveNode(Controller controller, long applicationId, String tierName, String nodeName, Timerange timerange) throws RestException {
		HttpResponse<MetricData[]> mdRep = RestClient.of(controller)
			 .get("rest/applications/{appId}/metric-data")
			.routeParam("appId", String.valueOf(applicationId))
			.queryString("metric-path", "Application Infrastructure Performance|"+tierName+"|Individual Nodes|"+nodeName+"|Agent|App|Availability")
			.between(timerange)
			.toJson()
			.asObject(MetricData[].class);
		
		MetricData[] data = mdRep.getBody();
		
		if(data!=null && data.length>0 && data[0].getMetricValues()!=null && !data[0].getMetricValues().isEmpty())
			return data[0].getMetricValues().get(0).getSum()>0;
		else
			return false;
	}
	
	public long getBTNbOfCalls(Controller controller, long applicationId, BusinessTransaction bt, Timerange timerange) throws RestException {
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


	public List<Application> buildGraph(Controller controller, ModelFilter filter, Timerange timerange) throws RestException {
		
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
