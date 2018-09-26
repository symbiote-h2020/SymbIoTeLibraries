package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.cci.AbstractRequestSecured;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

/**
 * Core internal payload of clear data message.
 *
 * Created by Szymon Mueller on 09/11/2017.
 */
public class ClearDataRequest extends AbstractRequestSecured<String> {

    public ClearDataRequest() {

    }

    public ClearDataRequest(SecurityRequest securityRequest, String platformId ) {
        super(securityRequest,platformId);
    }

}
