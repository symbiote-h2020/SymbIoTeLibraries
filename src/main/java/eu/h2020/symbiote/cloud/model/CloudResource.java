package eu.h2020.symbiote.cloud.model;

import java.util.List;

import org.springframework.data.annotation.Id;

import eu.h2020.symbiote.core.model.Location;
public class CloudResource  implements Cloneable{

    @Id
    private String internalId;
    private String id;
    private String name;
    private String owner;
    private String description;
    private List<String> observedProperties;
    private String resourceURL;
    private Location location;
    private String featureOfInterest = null;
    private String platformId;
    private String host;


    public CloudResource() {
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getObservedProperties() {
        return observedProperties;
    }

    public void setObservedProperties(List<String> observedProperties) {
        this.observedProperties = observedProperties;
    }

    public String getResourceURL() {
        return resourceURL;
    }

    public void setResourceURL(String resourceURL) {
        this.resourceURL = resourceURL;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getFeatureOfInterest() {
        return featureOfInterest;
    }

    public void setFeatureOfInterest(String featureOfInterest) {
        this.featureOfInterest = featureOfInterest;
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }
    
    public CloudResource clone()throws CloneNotSupportedException{  
    	return (CloudResource)super.clone();  
    }

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}  
    
    
}
