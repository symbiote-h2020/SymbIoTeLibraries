package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.cloud.model.ssp.SspSDEVInfo;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

/**
 * Created by mateuszl on 25.05.2018.
 */
public class SdevRegistryRequest extends AbstractRequestSecured<SspSDEVInfo> {
    public SdevRegistryRequest(SecurityRequest securityRequest, SspSDEVInfo body) {
        super(securityRequest, body);
    }

    public SdevRegistryRequest() {
        // Needed for Jackson serialization
    }
}
