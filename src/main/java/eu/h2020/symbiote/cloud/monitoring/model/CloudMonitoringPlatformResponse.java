/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.cloud.monitoring.model;

import eu.h2020.symbiote.core.cci.AbstractResponseSecured;

/**
 *
 * @author <a href="mailto:m.pardi@nextworks.it">Matteo Pardi</a>
 */
public class CloudMonitoringPlatformResponse extends AbstractResponseSecured<CloudMonitoringPlatform> {

    public CloudMonitoringPlatformResponse(int status, String message, CloudMonitoringPlatform body) {
        super(status, message, body);
    }
    
    public CloudMonitoringPlatformResponse() {
        // Needed for Jackson serialization
    }
    
}
