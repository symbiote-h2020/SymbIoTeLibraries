package eu.h2020.symbiote.cloud.model.internal;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FederationInfoBean {

    /**
     * This field is used by Platform Registry to aggregate the metadata of the same resource shared to different federations
     */
	@JsonProperty("aggregationId")
	private String aggregationId;

	/**
	 * This map contains information about the federations in which this resource has been shared.
     * The key is the federation ID The value is the information of
	 * this resource in that federation such as: date in which it was shared, access policies, etc.
	 */
	@JsonProperty("sharingInformation")
	private Map<String, ResourceSharingInformation> sharingInformation = new HashMap<>();

	/**
	 * This value is calculated by Trust Manager of the platform owing the resource and it is propagated to the other platform members
	 */
	@JsonProperty("resourceTrust")
	private Double resourceTrust;

	public String getAggregationId() {
		return aggregationId;
	}

	public void setAggregationId(String aggregationId) {
		this.aggregationId = aggregationId;
	}

	public Map<String, ResourceSharingInformation> getSharingInformation() {
		return sharingInformation;
	}

	public void setSharingInformation(Map<String, ResourceSharingInformation> sharingInformation) {
		this.sharingInformation = sharingInformation;
	}

	public Double getResourceTrust() {
		return resourceTrust;
	}

	public void setResourceTrust(Double resourceTrust) {
		this.resourceTrust = resourceTrust;
	}
}
