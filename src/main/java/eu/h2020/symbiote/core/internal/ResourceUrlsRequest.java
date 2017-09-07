package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.model.AbstractRequestSecured;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

import java.util.List;

/**
 * POJO describing a request for resources' Interworking Interface URLs.
 */
public class ResourceUrlsRequest extends AbstractRequestSecured<List<String>> {

    public ResourceUrlsRequest(SecurityRequest securityRequest, List<String> body) {
        super(securityRequest, body);
    }

    public ResourceUrlsRequest() {
        // Needed for Jackson serialization
    }
}