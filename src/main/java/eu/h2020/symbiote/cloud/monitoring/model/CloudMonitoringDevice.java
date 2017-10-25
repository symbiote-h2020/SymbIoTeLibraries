package eu.h2020.symbiote.cloud.monitoring.model;

import java.util.Date;
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
	 * Unused in r4, is the availability value to device. 
	 * @deprecated 
	 */
	private int availability;

	/**
	 * Unused in r4, is the load value to device. 
	 * @deprecated 
	 */
	private int load;

	/**
	 * Unused in r4, is the date and time of the device metric in string format 
	 * @deprecated 
	 */
	private String timestamp;
		
	/**
	 * Is the date and time of the device metric.
	 */
	private Date timemetric;
	
	/**
	 * Array Data, is the class that includes metrics data details, in a metric list in a generic and flexible format (Tag, Value)
	 */
	private CloudMonitoringMetrics[] metrics;
	
	public CloudMonitoringDevice(){
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAvailability() {
		return availability;
	}

	public void setAvailability(int availability) {
		this.availability = availability;
	}

	public int getLoad() {
		return load;
	}

	public void setLoad(int load) {
		this.load = load;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public CloudMonitoringMetrics[] getMetrics() {
		return metrics;
	}

	public void setMetrics(CloudMonitoringMetrics[] metrics) {
		this.metrics = metrics;
	}

	public Date getTimemetric() {
		return timemetric;
	}

	public void setTimemetric(Date timemetric) {
		this.timemetric = timemetric;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}



}
