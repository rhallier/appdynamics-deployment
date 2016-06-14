package org.appdynamics.deployment.model;

import java.util.ArrayList;
import java.util.List;

public class MetricData {
    private long metricId;
    private String metricPath;
    private String metricName;
    private String frequency;
    private List<MetricValue> metricValues=new ArrayList<MetricValue>();

    public MetricData(){}

	public long getMetricId() {
		return metricId;
	}

	public String getMetricPath() {
		return metricPath;
	}

	public String getMetricName() {
		return metricName;
	}

	public String getFrequency() {
		return frequency;
	}

	public List<MetricValue> getMetricValues() {
		return metricValues;
	}

	@Override
	public String toString() {
		return "_MetricData [metricId=" + metricId + ", metricPath=" + metricPath + ", metricName=" + metricName + ", frequency=" + frequency + "]";
	}

	
    
}
