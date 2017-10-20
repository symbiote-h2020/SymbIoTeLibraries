package eu.h2020.symbiote.core.internal.cram;

import eu.h2020.symbiote.core.cci.AbstractRequestSecured;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

import java.util.List;

/**
 * POJO describing a request for resources' Interworking Interface URLs.
 * @author Vasileios Glykantzis (ICOM)
 */
public class ResourceUrlsRequest extends AbstractRequestSecured<List<String>> {

    public ResourceUrlsRequest(SecurityRequest securityRequest, List<String> body) {
        super(securityRequest, body);
    }

    public ResourceUrlsRequest() {
        // Needed for Jackson serialization
    }
}