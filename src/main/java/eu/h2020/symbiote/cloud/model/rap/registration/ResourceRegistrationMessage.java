/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.cloud.model.rap.registration;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import eu.h2020.symbiote.cloud.model.internal.CloudResource;

/**
 *
 * @author <a href="mailto:m.pardi@nextworks.it">Matteo Pardi</a>
 */

@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
@JsonSubTypes({
	@JsonSubTypes.Type(value = CloudResource.class,   name = "REGISTER_RESOURCE"),
        @JsonSubTypes.Type(value = UnregisterResourceMessage.class, name = "UNREGISTER_RESOURCE"),
        @JsonSubTypes.Type(value = CloudResource.class, name = "UPDATE_RESOURCE")
})
abstract public class ResourceRegistrationMessage extends RegistrationMessage {
    
}
