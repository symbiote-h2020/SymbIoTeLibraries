package eu.h2020.symbiote.cloud.model;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import eu.h2020.symbiote.core.model.resources.Resource;
@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
public class CloudResource  {

    @Id
    @JsonProperty("internalId")
    private String internalId;
    @JsonProperty("hoost")
    private String host;
    
    @JsonProperty("resource")
    Resource resource;


    public CloudResource() {
    }

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }
   
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Resource getResource() {
		return resource;
	}

	public void setResource(Resource resource) {
		this.resource = resource;
	}  

	
    
}
