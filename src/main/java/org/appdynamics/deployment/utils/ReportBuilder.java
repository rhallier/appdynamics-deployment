package org.appdynamics.deployment.utils;

import java.util.Collection;

import org.appdynamics.deployment.controller.ReportFormat;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

public class ReportBuilder {
	private static final String FILE_FORMAT = "format";
	private static final String DATASOURCE = "datasource";

	private final ModelMap modelMap;
	private final String reportFilename;
	
	public ReportBuilder(String reportFilename, ModelMap modelMap) {
		this.modelMap=modelMap;
		this.reportFilename=reportFilename;
	}
	
	public ReportBuilder setDatasource(Collection<?> input) {
		modelMap.put(DATASOURCE, input);
		return this;
	}
	
	public ReportBuilder setFormat(ReportFormat format) {
		ReportFormat target = format!=null ? format : ReportFormat.PDF;
		
		modelMap.put(FILE_FORMAT, target.toString().toLowerCase());
		
		if (target == ReportFormat.PDF)
		    modelMap.put("IS_IGNORE_PAGINATION", false);
		else
		    modelMap.put("IS_IGNORE_PAGINATION", true);

		return this;
	}
	
	public ReportBuilder addParameter(String key, Object value) {
		modelMap.put(key, value);
		return this;
	}
	
	public ModelAndView toModelAndView() {
		return new ModelAndView(this.reportFilename, modelMap);
	}
}
