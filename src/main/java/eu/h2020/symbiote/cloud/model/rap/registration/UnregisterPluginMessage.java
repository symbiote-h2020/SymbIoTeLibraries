/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.cloud.model.rap.registration;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 *
 * @author <a href="mailto:m.pardi@nextworks.it">Matteo Pardi</a>
 */
public class UnregisterPluginMessage extends PluginRegistrationMessage {
    
    @JsonCreator
    public UnregisterPluginMessage(String platformId) {
        this.actionType = RegistrationAction.UNREGISTER_PLUGIN;
        this.platformId = platformId;
    }
}
