package org.appdynamics.deployment.controller;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.appdynamics.deployment.model.Application;
import org.appdynamics.deployment.model.BusinessTransaction;
import org.appdynamics.deployment.model.Node;
import org.appdynamics.deployment.model.Tier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ReportsController {
	private static final String FILE_FORMAT = "format";
	private static final String DATASOURCE = "datasource";

	@Autowired
	private ApplicationContext applicationContext;

	@RequestMapping(value = "/reports")
	public ModelAndView getRptByParam(final ModelMap modelMap, ModelAndView modelAndView, @RequestParam("report") final Report report, @RequestParam(name=FILE_FORMAT, required=false) final String format) {

		ApplicationsHolder applicationsHolder = applicationContext.getBean(ApplicationsHolder.class);
		List<Application> applications = applicationsHolder.getApplications();
		JRBeanCollectionDataSource datasource = null;

		if(Report.APPLICATIONS==report) {
			datasource = new JRBeanCollectionDataSource(applications, true);
		}
		else if (Report.TIERS==report) {
			List<Tier> tiers = new ArrayList<Tier>();
			for(Application app : applications)
				if(app.getTiers()!=null)
					for(Tier tier : app.getTiers())
						tiers.add(tier);
			
			 datasource = new JRBeanCollectionDataSource(tiers, true);
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
						
			
			 datasource = new JRBeanCollectionDataSource(nodes, true);
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
						
			
			 datasource = new JRBeanCollectionDataSource(bts, true);
		}
		
		modelMap.put(DATASOURCE, datasource);
		modelMap.put(FILE_FORMAT, format!=null ? format : "pdf");
		
		if (format==null || format.equals("pdf"))
		    modelMap.put("IS_IGNORE_PAGINATION", false);
		else
		    modelMap.put("IS_IGNORE_PAGINATION", true);
		
		modelMap.put("controller", applicationsHolder.getController().getUrl());
		modelMap.put("timerange", applicationsHolder.getTimerange().toString());
		modelAndView = new ModelAndView(report.getFilename(), modelMap);
		return modelAndView;
	}
}
