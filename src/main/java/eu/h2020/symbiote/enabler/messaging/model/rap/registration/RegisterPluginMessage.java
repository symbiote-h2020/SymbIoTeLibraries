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
 * @deprecated use {@link eu.h2020.symbiote.cloud.model.rap.registration.RegisterPluginMessage} instead.  
 */
public class RegisterPluginMessage extends PluginRegistrationMessage {
    @JsonProperty("hasFilters")
    private final boolean hasFilters;
       
    @JsonProperty("hasNotifications")
    private final boolean hasNotifications;
    
    @JsonCreator
    public RegisterPluginMessage(@JsonProperty("platformId") String platformId,
            @JsonProperty("hasFilters") boolean hasFilters,
            @JsonProperty("hasNotifications") boolean hasNotifications) {
        this.actionType = RegistrationMessage.RegistrationAction.REGISTER_PLUGIN;
        this.platformId = platformId;
        this.hasFilters = hasFilters;
        this.hasNotifications = hasNotifications;
    }
        
    @JsonProperty("hasFilters")
    public boolean getHasFilters() {
        return hasFilters;
    }

    @JsonProperty("hasNotifications")
    public boolean getHasNotifications() {
        return hasNotifications;
    }
}
