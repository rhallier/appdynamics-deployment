package org.appdynamics.deployment.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.appdynamics.deployment.model.Audit;
import org.appdynamics.deployment.service.RestService;
import org.appdynamics.deployment.utils.ReportBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AuditController {

	final Logger logger = LoggerFactory.getLogger(AuditController.class);

	@Autowired
	private RestService service;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private ApplicationContext applicationContext;

    @Autowired
    private ConversionService conversionService;
    
    @ModelAttribute("reportFormats")
	public ReportFormat[] getReportFormats() {
		return ReportFormat.values();
	}

	@RequestMapping(path = "/audit", method = RequestMethod.GET)
	public String index(Model model) {
		AuditForm auditForm = applicationContext.getBean(AuditForm.class);
		model.addAttribute("auditForm", auditForm);
		return "audit";
	}

	@RequestMapping(path = "/audit", method = RequestMethod.POST)
	public ModelAndView audit(@Valid AuditForm auditForm, final ModelMap modelMap, final BindingResult bindingResult, final Model model) {

		ApplicationsHolder applicationsHolder = applicationContext.getBean(ApplicationsHolder.class);

		if (!applicationsHolder.isControllerSet()) {
			MessageHelper.addError(model, messageSource.getMessage("controller.connection.notset", null, null));
			return new ModelAndView("audit");
		}

		if (!bindingResult.hasErrors()) {
			try {
				Audit[] audits = service.getAudit(applicationsHolder.getController(), auditForm.getStart(), auditForm.getEndOrDefault());

				return new ReportBuilder("reportAudit", modelMap)
						.setDatasource(Arrays.asList(audits))
						.setFormat(auditForm.getReportFormat())
						.addParameter("controller", applicationsHolder.getController().getUrl())
						.addParameter("timerange", auditForm.getStart()+" to "+auditForm.getEndOrDefault())
						.toModelAndView();
			} catch (Exception e) {
				logger.error("Audit report generation failed", e);
				MessageHelper.addError(model, messageSource.getMessage("audit.build.failure", null, null));
			}
		}

		return new ModelAndView("audit");
	}

	@Component
	@Scope(scopeName = "session")
	public static class AuditForm implements Serializable {
		@DateTimeFormat(pattern = "MM/dd/yyyy")
		private Date start;
		@DateTimeFormat(pattern = "MM/dd/yyyy")
		private Date end;
		@NotNull
		private ReportFormat reportFormat;

		public Date getStart() {
			return start;
		}

		public Date getEndOrDefault() {
			return end!=null ? end : new Date();
		}

		public void setStart(Date start) {
			this.start = start;
		}

		public Date getEnd() {
			return end;
		}

		public void setEnd(Date end) {
			this.end = end;
		}

		public ReportFormat getReportFormat() {
			return reportFormat;
		}

		public void setReportFormat(ReportFormat reportFormat) {
			this.reportFormat = reportFormat;
		}
		

	}

}
