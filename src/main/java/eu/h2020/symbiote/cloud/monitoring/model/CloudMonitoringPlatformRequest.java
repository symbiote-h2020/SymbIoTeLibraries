/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.cloud.monitoring.model;

import eu.h2020.symbiote.core.model.AbstractRequestSecured;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

/**
 *
 * @author Matteo Pardi <m.pardi@nextworks.it>
 */
public class CloudMonitoringPlatformRequest extends AbstractRequestSecured<CloudMonitoringPlatform> {

    public CloudMonitoringPlatformRequest(SecurityRequest securityRequest, CloudMonitoringPlatform body) {
        super(securityRequest, body);
    }

    public CloudMonitoringPlatformRequest() {
        // Needed for Jackson serialization
    }
}
