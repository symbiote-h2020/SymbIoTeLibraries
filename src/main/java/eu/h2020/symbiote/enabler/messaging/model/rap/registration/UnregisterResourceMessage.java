/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.enabler.messaging.model.rap.registration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author <a href="mailto:m.pardi@nextworks.it">Matteo Pardi</a>
 * 
 * @deprecated use {@link eu.h2020.symbiote.cloud.model.rap.registration.UnregisterResourceMessage} instead.  
 */
public class UnregisterResourceMessage extends ResourceRegistrationMessage {
    @JsonProperty("id")
    String internalId;
    
    @JsonProperty("id")
    public String getInternalId() {
        return internalId;
    }
    
    @JsonCreator
    public UnregisterResourceMessage(@JsonProperty("id") String resourceId) {
        this.actionType = RegistrationAction.UNREGISTER_RESOURCE;
        this.internalId = resourceId;
    }
}
