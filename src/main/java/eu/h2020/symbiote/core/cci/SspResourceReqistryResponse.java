package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.cloud.model.ssp.SspResource;

/**
 * Created by mateuszl on 25.05.2018.
 */
public class SspResourceReqistryResponse extends AbstractResponseSecured<SspResource> {
    public SspResourceReqistryResponse() {
        // Needed for Jackson serialization
    }

    public SspResourceReqistryResponse(int status, String message, SspResource body) {
        super(status, message, body);
    }
}
