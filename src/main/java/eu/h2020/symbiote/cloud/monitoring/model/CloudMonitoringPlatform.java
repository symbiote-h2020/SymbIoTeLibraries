package eu.h2020.symbiote.cloud.monitoring.model;

import java.util.Date;

/**
 * 
 * This class contained all data to be monitored by the platform, its the main class contains Devices and Metrics 
 * and accumulated data, according to defined metric types.
 * 
 * @author: Fernando Campos
 * @version: 20/10/2017
 * 
 * @see eu.h2020.symbiote.cloud.monitoring.model.CloudMonitoringDevice 
 * @see eu.h2020.symbiote.cloud.monitoring.model.CloudMonitoringMetrics
 */
public class CloudMonitoringPlatform {

	/**
	 * Simple Data, is the Platform internal Id
	 */
	private String internalId;
	
	/**
	 * Simple Data, is date time send by the platform
	 */
	private Date timePlatform;

	/**
	 * Simple Data, is date time when monitoring component registered the object
	 */
	private Date timeRegister;
	/**
	 * Simple Data, is the Federation internal Id
	 */
	private String federationId;
	
	/**
	 * Simple Data, is date time send by the platform
	 */
	private Date dateFederation;
	
	
	/**
	 * Accumulated Data, is the percentage value of available devices (availability=1) contained in this class
	 */
	private int avaiPlatform;
	
	/**
	 * Accumulated Data, is the load media value of all devices (load=%value) contained in this class
	 */
	private int loadPlatform;

	/**
	 * Array Data, is the class that includes device data details, in a device list
	 */
	private CloudMonitoringDevice[] devices;
	
	/**
	 * Array Data, is the class that includes metrics data details, in a metric list in a generic and flexible format (Tag, Value)
	 * Including group segment in tag. group.metric.x, where group=group of devices.
	 */
	private CloudMonitoringMetrics[] metrics;
	
	public CloudMonitoringPlatform(){
		
	}
	
	/**
	 * Simple Data, is the Platform internal Id
	 * @return Platform internalId
	 */
	public String getInternalId() {
		return internalId;
	}


	/**
	 * {@link CloudMonitoringPlatform#getInternalId}
	 * @param internalId internalId send from the Platform
	 */
	public void setInternalId(String internalId) {
		this.internalId = internalId;
	}

	public CloudMonitoringDevice[] getDevices() {
		return devices;
	}

	public void setDevices(CloudMonitoringDevice[] devices) {
		this.devices = devices;
	}

	public Date getTimePlatform() {
		return timePlatform;
	}

	public void setTimePlatform(Date timePlatform) {
		this.timePlatform = timePlatform;
	}

	public Date getTimeRegister() {
		return timeRegister;
	}

	public void setTimeRegister(Date timeRegister) {
		this.timeRegister = timeRegister;
	}

	public int getAvaiPlatform() {
		return avaiPlatform;
	}

	public void setAvaiPlatform(int avaiPlatform) {
		this.avaiPlatform = avaiPlatform;
	}

	public int getLoadPlatform() {
		return loadPlatform;
	}

	public void setLoadPlatform(int loadPlatform) {
		this.loadPlatform = loadPlatform;
	}

	public String getFederationId() {
		return federationId;
	}

	public void setFederationId(String federationId) {
		this.federationId = federationId;
	}

	public Date getDateFederation() {
		return dateFederation;
	}

	public void setDateFederation(Date dateFederation) {
		this.dateFederation = dateFederation;
	}

	public CloudMonitoringMetrics[] getMetrics() {
		return metrics;
	}

	public void setMetrics(CloudMonitoringMetrics[] metrics) {
		this.metrics = metrics;
	}

	
}	


