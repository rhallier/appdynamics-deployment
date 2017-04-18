package org.appdynamics.deployment.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.appdynamics.deployment.model.AgentType;
import org.appdynamics.deployment.model.Application;
import org.appdynamics.deployment.model.BusinessTransaction;
import org.appdynamics.deployment.model.Node;
import org.appdynamics.deployment.model.Tier;
import org.appdynamics.deployment.utils.ReportBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportsController {

	@Autowired
	private ApplicationContext applicationContext;

	@RequestMapping(value = "/reports")
	public ModelAndView getRptByParam(final ModelMap modelMap, ModelAndView modelAndView, @RequestParam("report") final Report report, @RequestParam(name="format", required=false) final ReportFormat format) {

		ApplicationsHolder applicationsHolder = applicationContext.getBean(ApplicationsHolder.class);
		List<Application> applications = applicationsHolder.getApplications();
		List<?> output = null;

		if(Report.APPLICATIONS==report) {
			output = applications;
		}
		else if (Report.TIERS==report) {
			List<Tier> tiers = new ArrayList<Tier>();
			for(Application app : applications)
				if(app.getTiers()!=null)
					for(Tier tier : app.getTiers())
						tiers.add(tier);
			
			 output=tiers;
		}
		else if (Report.NODES==report) {
			List<Node> nodes = new ArrayList<Node>();
			for(Application app : applications)
				if(app.getTiers()!=null)
					for(Tier tier : app.getTiers()) {
						if(tier.getNodes()!=null)
							for(Node node : tier.getNodes())
								nodes.add(node);
					}
						
			
			 output = nodes;
		}
		else if (Report.BUSINESS_TRANSACTIONS==report) {
			List<BusinessTransaction> bts = new ArrayList<BusinessTransaction>();
			for(Application app : applications)
				if(app.getTiers()!=null)
					for(Tier tier : app.getTiers()) {
						if(tier.getTransactions()!=null)
							for(BusinessTransaction bt : tier.getTransactions())
								bts.add(bt);
					}
						
			
			 output = bts;
		}
		else if (Report.LICENSES==report) {
			Map<String, Integer> licenses = applicationsHolder.getConsumedLicenses();
			List<LicenseItem> lic = new ArrayList<LicenseItem>();
			
			for(Map.Entry<String, Integer> e : licenses.entrySet())
				lic.add(new LicenseItem(e.getKey(), e.getValue()));
			
			output=lic;
		}
		
		return new ReportBuilder(report.getFilename(), modelMap)
				.setDatasource(output)
				.setFormat(format)
				.addParameter("controller", applicationsHolder.getController().getUrl())
				.addParameter("timerange", applicationsHolder.getTimerange().toString())
				.toModelAndView();
	}

	public static class LicenseItem {
		private String agentType;
		private int amount;
		
		
		public LicenseItem(String agentType, int amount) {
			super();
			
			this.agentType = AgentType.translate(agentType);
			this.amount = amount;
		}
		
		public String getAgentType() {
			return agentType;
		}
		public int getAmount() {
			return amount;
		}
	}
}
