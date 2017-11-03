package eu.h2020.symbiote.cloud.model;

public class CloudResourceParams {

	/**
	 * Is the type of Device
	 */
	private String type;

	/**
	 * Is the Resource is included in Core.
	 */
	private boolean isCore;

	/**
	 * Is the list of Federations where Device is included and the date since the device activity
	 * must be monitored
	 */
	private CloudResourceFederation[] listFederations;
	
	public CloudResourceParams(){
		
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CloudResourceFederation[] getListFederations() {
		return listFederations;
	}

	public void setListFederations(CloudResourceFederation[] listFederations) {
		this.listFederations = listFederations;
	}

	public boolean isCore() {
		return isCore;
	}

	public void setCore(boolean isCore) {
		this.isCore = isCore;
	}
	
}
