package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.cloud.model.ssp.SspSDEVInfo;

/**
 * Created by mateuszl on 25.05.2018.
 */
public class SdevRegistryResponse extends AbstractResponse<SspSDEVInfo> {
    public SdevRegistryResponse() {
        // Needed for Jackson serialization
    }

    public SdevRegistryResponse(int status, String message, SspSDEVInfo body) {
        super(status, message, body);
    }
}
