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
 * @deprecated use {@link eu.h2020.symbiote.cloud.model.rap.access.ResourceAccessSubscribeMessage} instead.  
 */
public class ResourceAccessSubscribeMessage extends ResourceAccessMessage {
    
    @JsonProperty("resourceInfoList")
    List<ResourceInfo> resInfoList;
    /**
     * JSON Constructor
     * @param resInfoList               the list of resource data information     
     */
    @JsonCreator
    public ResourceAccessSubscribeMessage(@JsonProperty("resourceInfo") List<ResourceInfo> resInfoList){
        this.accessType = AccessType.SUBSCRIBE;
        this.resInfoList = resInfoList;
    }
    
    @JsonProperty("resourceInfoList")
    public List<ResourceInfo> getResourceInfoList() {
        return resInfoList;
    }
}
