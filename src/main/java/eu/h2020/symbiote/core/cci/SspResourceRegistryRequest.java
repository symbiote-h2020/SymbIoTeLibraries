package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.cloud.model.ssp.SspResource;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

/**
 * Created by mateuszl on 25.05.2018.
 */
public class SspResourceRegistryRequest extends AbstractRequestSecured<SspResource> {
    public SspResourceRegistryRequest(SecurityRequest securityRequest, SspResource body) {
        super(securityRequest, body);
    }

    public SspResourceRegistryRequest() {
        // Needed for Jackson serialization
    }
}
