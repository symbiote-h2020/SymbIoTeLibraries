/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.enabler.messaging.model.rap.access;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import eu.h2020.symbiote.enabler.messaging.model.rap.db.ResourceInfo;

import java.util.List;

/**
 *
* @author <a href="mailto:l.tomaselli@nextworks.it">Luca Tomaselli</a>
 * 
 * @deprecated use {@link eu.h2020.symbiote.cloud.model.rap.access.ResourceAccessSetMessage} instead.  
 */
public class ResourceAccessSetMessage extends ResourceAccessMessage{
    @JsonProperty("resourceInfo")
    private final List<ResourceInfo> resInfo;
    
    @JsonProperty("body")
    private final String body;
    
    /**
     * JSON Constructor
     * @param resInfo               the resource data information
     * @param body                  the body of request
     */
    @JsonCreator
    public ResourceAccessSetMessage(@JsonProperty("resourceInfo") List<ResourceInfo> resInfo, 
                                    @JsonProperty("body") String body) {
        this.accessType = ResourceAccessMessage.AccessType.SET;
        this.resInfo = resInfo;
        this.body = body;
    }
    
    @JsonProperty("body")
    public String getBody() {
        return body;
    }
    
    @JsonProperty("resourceInfo")
    public List<ResourceInfo> getResourceInfo(){
        return this.resInfo;
    }
}
