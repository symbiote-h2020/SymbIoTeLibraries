/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.enabler.messaging.model.rap.access;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

/**
 *
 * @author <a href="mailto:m.pardi@nextworks.it">Matteo Pardi</a>
 * 
 * Class modeling a message exchanged on the queue from the RAP to the plugin-RAP
 * in order to access to a resource through the platform layer
 * 
 * @deprecated use {@link eu.h2020.symbiote.cloud.model.rap.access.ResourceAccessMessage} instead.  
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type")
@JsonSubTypes({
	@Type(value = ResourceAccessGetMessage.class,       name = "GET"),
        @Type(value = ResourceAccessHistoryMessage.class,   name = "HISTORY"),
        @Type(value = ResourceAccessSetMessage.class,       name = "SET"),
        @Type(value = ResourceAccessSubscribeMessage.class,   name = "SUBSCRIBE"),
        @Type(value = ResourceAccessUnSubscribeMessage.class,   name = "UNSUBSCRIBE"),
})
abstract public class ResourceAccessMessage {
    
    public enum AccessType {
        GET, HISTORY, SET, SUBSCRIBE, UNSUBSCRIBE
    }
    
    @JsonProperty("type")
    AccessType accessType;
    

    @JsonProperty("type")
    public AccessType getAccessType() {
        return accessType;
    }
}
