package eu.h2020.symbiote.barteringAndTrading;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

//TODO ~ refactpr > move to > security.communication.payloads
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterRequest {

	public Long beginTimestamp;
	public Long endTimestamp;
	public String platform;
	public Long federationId;

	public FilterRequest()
	{}

	@JsonCreator
	public FilterRequest(
			@JsonProperty("beginTimestamp") Long beginTimestamp,
			@JsonProperty("endTimestamp") Long endTimestamp,
			@JsonProperty("platform") String platform,
			@JsonProperty("federationId") Long federationId) {
		this.beginTimestamp = beginTimestamp;
		this.endTimestamp = endTimestamp;
		this.platform = platform;
		this.federationId = federationId;
	}

	public Long getBeginTimestamp() {
		return beginTimestamp;
	}

	public void setBeginTimestamp(Long beginTimestamp) {
		this.beginTimestamp = beginTimestamp;
	}

	public Long getEndTimestamp() {
		return endTimestamp;
	}

	public void setEndTimestamp(Long endTimestamp) {
		this.endTimestamp = endTimestamp;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public Long getFederationId() {
		return federationId;
	}

	public void setFederationId(Long federationId) {
		this.federationId = federationId;
	}

	public boolean isValidRequest() {
		if (platform == null)
			return false;

		if (beginTimestamp != null && endTimestamp == null ||
			endTimestamp != null && beginTimestamp == null){
			return false;
		}
		return true;
	}
}