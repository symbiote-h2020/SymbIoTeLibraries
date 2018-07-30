package eu.h2020.symbiote.cloud.monitoring.model;

import java.util.List;

/**
 * 
 * This class contained all data to be monitored by the platform, its the main class contains Devices and Metrics 
 * and accumulated data, according to defined metric types.
 * 
 * @author: Fernando Campos
 * @version: 20/10/2017
 * 
 * @see eu.h2020.symbiote.cloud.monitoring.model.CloudMonitoringDevice
 */
public class CloudMonitoringPlatform {

	/**
	 * Simple Data, is the Platform constants Id
	 */
	private String platformId;
	
	/**
	 * Array Data, is the class that includes device data details, in a device list
	 */
	private List<CloudMonitoringDevice> metrics;
	
	
	/**
	 * Simple Data, is the Platform constants Id
	 * @return Platform platformId
	 */
	public String getPlatformId() {
		return platformId;
	}


	/**
	 * {@link CloudMonitoringPlatform#getPlatformId}
	 * @param platformId platformId send from the Platform
	 */
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	
	public List<CloudMonitoringDevice> getMetrics() {
		return metrics;
	}

	public void setMetrics(List<CloudMonitoringDevice> metrics) {
		this.metrics = metrics;
	}

	
}	


