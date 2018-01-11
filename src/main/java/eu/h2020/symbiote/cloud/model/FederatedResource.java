package eu.h2020.symbiote.cloud.model;

import eu.h2020.symbiote.cloud.model.internal.CloudResource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FederatedResource {
	
	/*
	 * Is the Id of Federation
	 */
	private String idFederation;

	/*
	 * Is the date in which the resources was shared with the federation
	 */
	private Date sharingDate;
	
	private List<CloudResource> resources = new ArrayList<>();

	
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
	
	public List<CloudResource> getResources() {
		return resources;
	}
	
	public void setResources(List<CloudResource> resources) {
		this.resources = resources;
	}
}
