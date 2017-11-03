package eu.h2020.symbiote.cloud.model;

import java.util.Date;

public class CloudResourceFederation {
	
	/*
	 * Is the Id of Federation
	 */
	private String idFederation;

	/*
	 * Is the date since the resource must be monitored for that federation
	 */
	private Date sinceDate;

	
	public String getIdFederation() {
		return idFederation;
	}
	public void setIdFederation(String idFederation) {
		this.idFederation = idFederation;
	}
	public Date getSinceDate() {
		return sinceDate;
	}
	public void setSinceDate(Date sinceDate) {
		this.sinceDate = sinceDate;
	}

}
