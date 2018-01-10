package eu.h2020.symbiote.cloud.model;

import eu.h2020.symbiote.cloud.model.internal.CloudResource;

import java.util.Date;

public class FederatedResource {
	
	/*
	 * Is the Id of Federation
	 */
	private String idFederation;

	/*
	 * Is the date in which the resource was shared with the federation
	 */
	private Date sharingDate;
	
	private CloudResource resource;

	
	public String getIdFederation() {
		return idFederation;
	}
	public void setIdFederation(String idFederation) {
		this.idFederation = idFederation;
	}
	public Date getSharingDate() {
		return sharingDate;
	}
	public void setSharingDate(Date sharingDate) {
		this.sharingDate = sharingDate;
	}
	
	public CloudResource getResource() {
		return resource;
	}
	
	public void setResource(CloudResource resource) {
		this.resource = resource;
	}
}
