package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.cloud.model.ssp.SspRegInfo;

/**
 * Created by mateuszl on 25.05.2018.
 */
public class SdevRegistryResponse extends AbstractResponse<SspRegInfo> {
    public SdevRegistryResponse() {
        // Needed for Jackson serialization
    }

    public SdevRegistryResponse(int status, String message, SspRegInfo body) {
        super(status, message, body);
    }
}
