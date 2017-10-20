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

	
}
