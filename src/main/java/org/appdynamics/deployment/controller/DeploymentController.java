package org.appdynamics.deployment.controller;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.appdynamics.deployment.model.Application;
import org.appdynamics.deployment.model.Timerange;
import org.appdynamics.deployment.service.ModelFilter;
import org.appdynamics.deployment.service.RestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DeploymentController {
	
	final Logger logger = LoggerFactory.getLogger(DeploymentController.class);

	@Autowired
	private RestService service;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ApplicationContext applicationContext;

	@ModelAttribute("reports")
	public Report[] getReports() {
		return Report.values();
	}
	
	@ModelAttribute("reportFormats")
	public ReportFormat[] getReportFormats() {
		return ReportFormat.values();
	}
	
    @RequestMapping(path="/deploymentStatus", method=RequestMethod.GET)
    public String index(Model model) {
		GraphForm graphForm = applicationContext.getBean(GraphForm.class);
    	model.addAttribute("graphForm", graphForm);
        return "deploymentStatus";
    }

    @RequestMapping(path="/deploymentStatus", method=RequestMethod.POST)
	public String buildGraph(@Valid GraphForm graphForm, final BindingResult bindingResult, final Model model) {

		ApplicationsHolder applicationsHolder = applicationContext.getBean(ApplicationsHolder.class);
		
		if(!applicationsHolder.isControllerSet()) {
			MessageHelper.addError(model, messageSource.getMessage("controller.connection.notset", null, null));
			return "deploymentStatus";
		}
		
		Timerange timerange = Timerange.beforeNow(graphForm.timerangeMinsBeforeNow);
		ModelFilter filter = new ModelFilter();
		
		if(graphForm.notFilter)
			filter.excludeApplication(graphForm.applicationFilter);
		else
			filter.includeApplication(graphForm.applicationFilter);
		
		if (!bindingResult.hasErrors()) {
			try {
				List<Application> applications = service.buildGraph(applicationsHolder.getController(), filter, timerange);
				
				applicationsHolder.setTimerange(timerange);
				applicationsHolder.setApplications(applications);
				
				MessageHelper.addSuccess(model, messageSource.getMessage("deploymentStatus.build.success", null, null));
			} catch (Exception e) {
				logger.error("Connection failed", e);
				MessageHelper.addError(model, messageSource.getMessage("deploymentStatus.build.failure", null, null));
			}
		}

		return "deploymentStatus";

	}

    @Component
    @Scope(scopeName = "session")
	public static class GraphForm implements Serializable {
		private boolean notFilter;
		private String applicationFilter;
		@Min(1)
		private int timerangeMinsBeforeNow;
		
		public String getApplicationFilter() {
			return applicationFilter;
		}

		public void setApplicationFilter(String applicationFilter) {
			this.applicationFilter = applicationFilter;
		}

		public boolean isNotFilter() {
			return notFilter;
		}

		public void setNotFilter(boolean notFilter) {
			this.notFilter = notFilter;
		}

		public int getTimerangeMinsBeforeNow() {
			return timerangeMinsBeforeNow;
		}

		public void setTimerangeMinsBeforeNow(int timerangeMinsBeforeNow) {
			this.timerangeMinsBeforeNow = timerangeMinsBeforeNow;
		}
	}

}
