package org.appdynamics.deployment.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.appdynamics.deployment.model.Application;
import org.appdynamics.deployment.model.Timerange;
import org.appdynamics.deployment.service.ModelFilter;
import org.appdynamics.deployment.service.RestService;
import org.hibernate.validator.constraints.NotBlank;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
    @RequestMapping("/")
    String index(Model model) {
    	model.addAttribute("controllerForm", new ControllerForm());
        return "home";
    }

	@RequestMapping(value = "/testConnection")
	@ResponseBody
	public String testConnection(@RequestBody ControllerForm controllerForm) {
		try {
			org.appdynamics.deployment.model.Controller controller = controllerForm.toController();
			Application[] applications = service.getApplications(controller);
			if(applications==null)
				logger.warn("No applications found");
			return "{\"result\":\"ok\"}";
		}
		catch(Exception e) {
			logger.error("Connection failed", e);
			return "{\"result\":\"ko\", \"error\":\"Connection failed\"}";
		}
		
	}

	@RequestMapping(value = "/buildGraph", method = RequestMethod.POST)
	public String buildGraph(@Valid ControllerForm controllerForm, final BindingResult bindingResult, final Model model) {
		
		Timerange timerange = Timerange.beforeNow(controllerForm.timerangeMinsBeforeNow);
		ModelFilter filter = new ModelFilter().application(controllerForm.applicationFilter);
		
		if (!bindingResult.hasErrors()) {
			try {
				org.appdynamics.deployment.model.Controller controller = controllerForm.toController();
				List<Application> applications = service.buildGraph(controller, filter, timerange);
				
				ApplicationsHolder applicationsHolder = applicationContext.getBean(ApplicationsHolder.class);
				applicationsHolder.setController(controller);
				applicationsHolder.setTimerange(timerange);
				applicationsHolder.setApplications(applications);
				
				MessageHelper.addSuccess(model, messageSource.getMessage("controller.connection.success", null, null));
			} catch (Exception e) {
				logger.error("Connection failed", e);
				MessageHelper.addError(model, messageSource.getMessage("controller.connection.failure", null, null));
			}
		}

		return "home";

	}

	public static class ControllerForm {
		@NotBlank
		private String url;
		private String account;
		@NotBlank
		private String username;
		@NotBlank
		private String password;

		private String applicationFilter;
		@Min(1)
		private int timerangeMinsBeforeNow;
		
		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getAccount() {
			return account;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getApplicationFilter() {
			return applicationFilter;
		}

		public void setApplicationFilter(String applicationFilter) {
			this.applicationFilter = applicationFilter;
		}

		public int getTimerangeMinsBeforeNow() {
			return timerangeMinsBeforeNow;
		}

		public void setTimerangeMinsBeforeNow(int timerangeMinsBeforeNow) {
			this.timerangeMinsBeforeNow = timerangeMinsBeforeNow;
		}

		public org.appdynamics.deployment.model.Controller toController() {
			return new org.appdynamics.deployment.model.Controller(url, account, username, password);
		}
	}

}
