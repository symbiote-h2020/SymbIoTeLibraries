package eu.h2020.symbiote.cloud.model.internal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.PersistenceConstructor;

/**
 * @author Petar Krivic 16/05/2018.
 *
 * Subscription model class.
 */
public class Subscription {

    @Id
    private String id;

    //federationMemberID
    private String platformId;

    private String federationId;

    //flags defining wanted resourceTypes
    private Map<String, Boolean> resourceType;

    //names of preferred device locations (OPTIONAL)
    private List<String> locations;

    //names of preferred observedProperties for sensors (OPTIONAL)
    private List<String> observedProperties;

    //names of preferred capabilities for actuators (OPTIONAL)
    private List<String> capabilities;

    public Subscription(String platformId, String federationId) {
        setId(platformId, federationId);
        this.platformId = platformId;
        this.federationId = federationId;
        this.resourceType = new HashMap<>();
        this.resourceType.put("service", true);
        this.resourceType.put("device", true);
        this.resourceType.put("actuator", true);
        this.resourceType.put("sensor", true);
        this.locations = new ArrayList<>();
        this.observedProperties = new ArrayList<>();
        this.capabilities = new ArrayList<>();
    }

    @PersistenceConstructor
    @JsonCreator
    public Subscription(@JsonProperty("platformId") String platformId,
                        @JsonProperty("federationId") String federationId,
                        @JsonProperty("resourceType") Map<String, Boolean> resourceType,
                        @JsonProperty("locations") List<String> locations,
                        @JsonProperty("observedProperties") List<String> observedProperties,
                        @JsonProperty("capabilities") List<String> capabilities) {
        setId(platformId, federationId);
        this.platformId = platformId;
        this.federationId = federationId;
        this.resourceType = resourceType;
        this.locations = locations;
        this.observedProperties = observedProperties;
        this.capabilities = capabilities;
    }

    public String getId() { return id; }

    public void setId(String platformId, String federationId) { this.id = platformId + "@" + federationId; }

    public String getFederationId() { return federationId; }

    public void setFederationId(String federationId) { this.federationId = federationId; }

    public String getPlatformId() { return platformId; }

    public void setPlatformId(String platformId) { this.platformId = platformId; }

    public Map<String, Boolean> getResourceType() { return resourceType; }

    public void setResourceType(Map<String, Boolean> resourceType) { this.resourceType = resourceType; }

    public List<String> getLocations() { return locations; }

    public void setLocations(List<String> locations) { this.locations = locations; }

    public List<String> getObservedProperties() { return observedProperties; }

    public void setObservedProperties(List<String> observedProperties) { this.observedProperties = observedProperties; }

    public List<String> getCapabilities() { return capabilities; }

    public void setCapabilities(List<String> capabilities) { this.capabilities = capabilities; }

}
