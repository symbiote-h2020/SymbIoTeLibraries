package eu.h2020.symbiote.cloud.model;

import eu.h2020.symbiote.core.model.Resource;


public class CloudResource extends Resource {

    private String internalId;

    public String getInternalId() {
        return internalId;
    }

    public void setInternalId(String internalId) {
        this.internalId = internalId;
    }

}