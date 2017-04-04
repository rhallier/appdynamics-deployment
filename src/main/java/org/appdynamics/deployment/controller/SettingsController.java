package org.appdynamics.deployment.controller;

import javax.validation.Valid;

import org.appdynamics.deployment.model.Application;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SettingsController {
	
	final Logger logger = LoggerFactory.getLogger(SettingsController.class);

	@Autowired
	private RestService service;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private MessageSource messageSource;

    @RequestMapping(path={ "", "/", "/controller" }, method=RequestMethod.GET)
    public String index(Model model) {
		ApplicationsHolder applicationsHolder = applicationContext.getBean(ApplicationsHolder.class);
		org.appdynamics.deployment.model.Controller controller = applicationsHolder.getController();
		
		ControllerForm form = ControllerForm.of(controller);
		model.addAttribute("controllerForm", form);
        return "controller";
    }

    @RequestMapping(path={ "/controller" }, method=RequestMethod.POST)
	public String save(@Valid ControllerForm controllerForm, final BindingResult bindingResult, final Model model) {
    	
		if (!bindingResult.hasErrors()) {
			try {
				org.appdynamics.deployment.model.Controller controller = controllerForm.toController();
				Application[] applications = service.getApplications(controller);

				if(applications==null)
					logger.warn("No applications found");
				
				ApplicationsHolder applicationsHolder = applicationContext.getBean(ApplicationsHolder.class);
				applicationsHolder.resetWithController(controller);
				
				MessageHelper.addSuccess(model, messageSource.getMessage("controller.connection.success", null, null));
			} catch (Exception e) {
				logger.error("Connection failed", e);
				MessageHelper.addError(model, messageSource.getMessage("controller.connection.failure", null, null));
			}
		}

		return "controller";
	}

	public static class ControllerForm {
		@NotBlank
		private String url;
		private String account;
		@NotBlank
		private String username;
		@NotBlank
		private String password;
		
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

		public org.appdynamics.deployment.model.Controller toController() {
			return new org.appdynamics.deployment.model.Controller(url, account, username, password);
		}
		
		public static ControllerForm of(org.appdynamics.deployment.model.Controller controller) {
			ControllerForm form = new ControllerForm();
			if(controller!=null) {
				form.setUrl(controller.getUrl());
				form.setAccount(controller.getAccount());
				form.setUsername(controller.getUsername());
			}
			return form;
		}
	}
}
