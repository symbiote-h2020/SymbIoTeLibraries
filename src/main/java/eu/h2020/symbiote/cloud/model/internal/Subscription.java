package eu.h2020.symbiote.cloud.model.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Petar Krivic 16/05/2018.
 * 
 * Subscription model class.
 */
public class Subscription {
	
	//federationMemberID
	@Id
	@JsonProperty("platformId")
	private String platformId;
	
	//flags defining wanted resourceTypes
	@JsonProperty("resourceType")
	private Map<String, Boolean> resourceType;

	//names of preferred device locations (OPTIONAL)
	@JsonProperty("locations")
	private List<String> locations;
	
	//names of preferred observedProperties for sensors (OPTIONAL)
	@JsonProperty("observedProperties")
	private List<String> observedProperties;
	
	//names of preferred capabilities for actuators (OPTIONAL)
	@JsonProperty("capabilities")
	private List<String> capabilities;
	
	public Subscription(){
		this.resourceType = new HashMap<>();
		this.resourceType.put("service", true);
		this.resourceType.put("device", true);
		this.resourceType.put("actuator", true);
		this.resourceType.put("sensor", true);
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public Map<String, Boolean> getResourceType() {
		return resourceType;
	}

	public void setResourceType(Map<String, Boolean> resourceType) {
		this.resourceType = resourceType;
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}

	public List<String> getObservedProperties() {
		return observedProperties;
	}

	public void setObservedProperties(List<String> observedProperties) {
		this.observedProperties = observedProperties;
	}

	public List<String> getCapabilities() {
		return capabilities;
	}

	public void setCapabilities(List<String> capabilities) {
		this.capabilities = capabilities;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Subscription other = (Subscription) obj;
		if (platformId == null) {
			if (other.platformId != null)
				return false;
		} else if (!platformId.equals(other.platformId))
			return false;
		return true;
	}
	
	
}
