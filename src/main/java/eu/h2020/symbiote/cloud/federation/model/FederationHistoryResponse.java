package eu.h2020.symbiote.cloud.federation.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author RuggenthalerC
 *
 *         Federation history event for given platform.
 */
public class FederationHistoryResponse {
	@JsonProperty("platform_id")
	private String platformId;

	@JsonProperty("history")
	private final List<FederationHistory> history = new ArrayList<>();

	public FederationHistoryResponse() {
		// left empty intentionally
	}

	public FederationHistoryResponse(String platformId) {
		this.platformId = platformId;
	}

	public String getPlatformId() {
		return this.platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public List<FederationHistory> getEvents() {
		return this.history;
	}
}
