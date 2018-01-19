package eu.h2020.symbiote.cloud.federation.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author RuggenthalerC
 *
 *         Federation history items.
 */
public class FederationHistory {
	@JsonProperty("federation_id")
	private String federationId;

	@JsonProperty("date_federation_created")
	private Date dateFederationCreated;

	@JsonProperty("date_federation_removed")
	private Date dateFederationRemoved;

	@JsonProperty("date_platform_joined")
	private Date datePlatformJoined;

	@JsonProperty("date_platform_left")
	private Date datePlatformLeft;

	public FederationHistory() {
		// left empty intentionally
	}

	public FederationHistory(String federationId) {
		this.federationId = federationId;
	}

	public String getFederationId() {
		return this.federationId;
	}

	public void setFederationId(String federationId) {
		this.federationId = federationId;
	}

	public Date getDateFederationCreated() {
		return this.dateFederationCreated;
	}

	public void setDateFederationCreated(Date dateFederationCreated) {
		this.dateFederationCreated = dateFederationCreated;
	}

	public Date getDateFederationRemoved() {
		return this.dateFederationRemoved;
	}

	public void setDateFederationRemoved(Date dateFederationRemoved) {
		this.dateFederationRemoved = dateFederationRemoved;
	}

	public Date getDatePlatformJoined() {
		return this.datePlatformJoined;
	}

	public void setDatePlatformJoined(Date datePlatformJoined) {
		this.datePlatformJoined = datePlatformJoined;
	}

	public Date getDatePlatformLeft() {
		return this.datePlatformLeft;
	}

	public void setDatePlatformLeft(Date datePlatformLeft) {
		this.datePlatformLeft = datePlatformLeft;
	}
}
