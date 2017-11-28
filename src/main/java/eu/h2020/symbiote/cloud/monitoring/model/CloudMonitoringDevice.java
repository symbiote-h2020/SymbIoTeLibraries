package eu.h2020.symbiote.cloud.monitoring.model;

import java.util.List;

/**
 * 
 * This class contained the device data to be monitored by the platform, contains Metrics  
 *  
 * @author: Fernando Campos
 * @version: 20/10/2017
 * 
 * @see eu.h2020.symbiote.cloud.monitoring.model.CloudMonitoringPlatform 
 * @see eu.h2020.symbiote.cloud.monitoring.model.CloudMonitoringMetrics
 */
public class CloudMonitoringDevice {

	//symbioteID
	/**
	 * is the Device Id
	 */
	private String id; 
	/**
	 * is the Device Id
	 */
	private String type;
	
	/**
	 * Array Data, is the class that includes metrics data details, in a metric list in a generic and flexible format (Tag, Value)
	 */
	private List<Metric> metrics;
	
	public CloudMonitoringDevice(){
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	

	public List<Metric> getMetrics() {
		return metrics;
	}

	public void setMetrics(List<Metric> metrics) {
		this.metrics = metrics;
	}

}
