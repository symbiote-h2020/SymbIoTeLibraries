package eu.h2020.symbiote.cloud.monitoring.model;

public class CloudMonitoringPlatform {

	//platformID
	private String internalId;

//	private Token coreToken;

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
}
