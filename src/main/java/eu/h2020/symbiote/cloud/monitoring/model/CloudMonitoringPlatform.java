package eu.h2020.symbiote.cloud.monitoring.model;

import eu.h2020.symbiote.commons.security.token.SymbIoTeToken;

public class CloudMonitoringPlatform {

	//platformID
	private String internalId;
	
	private SymbIoTeToken coreToken;
	
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

	public SymbIoTeToken getCoreToken() {
		return coreToken;
	}

	public void setCoreToken(SymbIoTeToken coreToken) {
		this.coreToken = coreToken;
	}
	
	
	
	
}
