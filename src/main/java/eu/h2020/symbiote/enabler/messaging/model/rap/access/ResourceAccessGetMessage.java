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
 * @author <a href="mailto:m.pardi@nextworks.it">Matteo Pardi</a>
 * 
 * @deprecated use {@link eu.h2020.symbiote.cloud.model.rap.access.ResourceAccessGetMessage} instead.  
 */
@Deprecated
public class ResourceAccessGetMessage extends ResourceAccessMessage {
    
    @JsonProperty("resourceInfo")
    List<ResourceInfo> resInfo;
   
    
    /**
     * JSON Constructor
     * @param resInfo               the resource data information  
     */
    @JsonCreator
    public ResourceAccessGetMessage(@JsonProperty("resourceInfo") List<ResourceInfo> resInfo){
        this.accessType = AccessType.GET;
        this.resInfo = resInfo;
    }
    
    @JsonProperty("resourceInfo")
    public List<ResourceInfo> getResourceInfo(){
        return this.resInfo;
    }
}
