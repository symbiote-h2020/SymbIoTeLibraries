/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.cloud.model.rap.registration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.model.cim.Resource;

/**
 *
 * @author <a href="mailto:m.pardi@nextworks.it">Matteo Pardi</a>
 */
public class UpdateResourceMessage extends ResourceRegistrationMessage {
    @JsonProperty("host")
    private final String host;
    @JsonProperty("resource")
    private final Resource resource;
    
    @JsonCreator
    public UpdateResourceMessage(@JsonProperty("host") String host,
                                   @JsonProperty("resource") Resource resource) {
        this.actionType = RegistrationAction.REGISTER_RESOURCE;
        this.host = host;
        this.resource = resource;
    }
    
    @JsonProperty("host")
    public String getHost() {
        return host;
    }
    
    @JsonProperty("resource")
    public Resource getResource() {
        return resource;
    }    
}
