package eu.h2020.symbiote.cloud.model;

import org.springframework.data.annotation.Id;

import eu.h2020.symbiote.core.model.resources.Resource;
public class CloudResource  {

    @Id
    private String internalId;
    private String host;
    
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
