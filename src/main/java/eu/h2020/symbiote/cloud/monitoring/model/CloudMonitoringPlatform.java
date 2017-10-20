package eu.h2020.symbiote.cloud.monitoring.model;

import java.util.Date;

public class CloudMonitoringPlatform {

	//platformID
	private String internalId;
	
	private Date timePlatform;
	
	private Date timeRegister;
	
	private int avaiPlatform;

	private int loadPlatform;

	private CloudMonitoringDevice[] devices;
	
	public CloudMonitoringPlatform(){
		
	}

	public String getInternalId() {
		return internalId;
	}

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
