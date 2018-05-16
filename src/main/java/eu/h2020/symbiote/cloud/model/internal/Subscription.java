package eu.h2020.symbiote.cloud.model.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Petar Krivic 16/05/2018.
 * 
 * Subscription model class.
 */
public class Subscription {
	
	//federationMemberID
	@JsonProperty("platformId")
    @Pattern(regexp="^[\\w-]{4,}$")
    @Size(max=30)
	private String platformId;
	
	//turn flag of wanted resourceType to true
	@JsonProperty("resourceType")
	private Map<String, Boolean> resourceType;

	//names of preferred locations (OPTIONAL)
	@JsonProperty("locations")
	private List<String> locations;
	
	//names of preferred observedProperties for sensors (OPTIONAL)
	@JsonProperty
	private List<String> observedProperties;
	
	//names of preferred capabilities for actuators (OPTIONAL)
	@JsonProperty
	private List<String> capabilities;
	
	public Subscription(){
		this.resourceType = new HashMap<>();
		this.resourceType.put("service", false);
		this.resourceType.put("device", false);
		this.resourceType.put("actuator", false);
		this.resourceType.put("sensor", false);
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
	
}
