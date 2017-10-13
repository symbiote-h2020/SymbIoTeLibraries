/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.cloud.monitoring.model;

import eu.h2020.symbiote.core.model.AbstractResponseSecured;

/**
 *
 * @author Matteo Pardi <m.pardi@nextworks.it>
 */
public class CloudMonitoringPlatformResponse extends AbstractResponseSecured<CloudMonitoringPlatform> {

    public CloudMonitoringPlatformResponse(int status, String message, CloudMonitoringPlatform body) {
        super(status, message, body);
    }
    
    public CloudMonitoringPlatformResponse() {
        // Needed for Jackson serialization
    }
    
}