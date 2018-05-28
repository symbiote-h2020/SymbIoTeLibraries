package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.cloud.model.ssp.SspSDEVInfo;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

/**
 * Class representing SDEV registry request.
 *
 * Created by mateuszl on 25.05.2018.
 */
public class SdevRegistryRequest extends AbstractRequest<SspSDEVInfo> {
    public SdevRegistryRequest(SspSDEVInfo body) {
        super(body);
    }

    public SdevRegistryRequest() {
        // Needed for Jackson serialization
    }
}
