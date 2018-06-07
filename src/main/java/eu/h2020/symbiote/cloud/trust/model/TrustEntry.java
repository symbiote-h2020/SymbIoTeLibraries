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
	public TrustEntry(Type type, String platformId, String resourceId) {
		this.type = type;
		this.platformId = platformId;
		this.resourceId = resourceId;
		this.lastUpdate = new Date();

		// set unique ID for entry
		if (Type.PLATFORM_REPUTATION.equals(type)) {
			this.id = UUID.nameUUIDFromBytes((this.platformId + "-" + this.type).getBytes()).toString();
		} else {
			this.id = UUID.nameUUIDFromBytes((this.resourceId + "-" + this.type).getBytes()).toString();
		}
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

	/**
	 * Sets the new value and lastUpdate to current time.
	 * 
	 * @param value
	 */
	public void updateEntry(Double value) {
		this.value = value;
		this.lastUpdate = new Date();
	}
}
