package eu.h2020.symbiote.cloud.trust.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author RuggenthalerC
 *
 *         Object to store platform and resource trust/reputation values.
 */
public class TrustEntry {

	public TrustEntry() {
		// left intentionally empty
	}

	/**
	 * Create new trust entry object.
	 * 
	 * @param type
	 * @param platformId
	 * @param resourceId
	 * @param value
	 */
	public TrustEntry(Type type, String platformId, String resourceId, Double value) {
		this.type = type;
		this.platformId = platformId;
		this.resourceId = resourceId;
		this.value = value;
		this.lastUpdate = new Date();

		// set unique ID for entry
		String identifier = this.resourceId != null ? this.resourceId : this.platformId;
		this.id = UUID.nameUUIDFromBytes((identifier + "-" + this.type).getBytes()).toString();
	}

	public TrustEntry(String platformId, Double value) {
		this(Type.PLATFORM_REPUTATION, platformId, null, value);
	}

	public enum Type {
		PLATFORM_REPUTATION, RESOURCE_TRUST, ADAPTIVE_RESOURCE_TRUST
	}

	@Id
	@JsonProperty("id")
	private String id;

	@JsonProperty("type")
	private Type type;

	@JsonProperty("last_update")
	private Date lastUpdate;

	@JsonProperty("platform_id")
	private String platformId;

	@JsonProperty("resource_id")
	private String resourceId;

	@JsonProperty("value")
	private Double value;

	public Type getType() {
		return this.type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Date getLastUpdate() {
		return this.lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public String getPlatformId() {
		return this.platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getResourceId() {
		return this.resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public Double getValue() {
		return this.value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
