package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.cloud.model.ssp.SspSDEVInfo;
import eu.h2020.symbiote.core.cci.AbstractRequestSecured;
import eu.h2020.symbiote.core.cci.AbstractResponseSecured;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

/**
 * Created by Szymon Mueller on 28/05/2018.
 */
public class CoreSdevRegistryResponse extends AbstractResponseSecured<SspSDEVInfo> {

    public CoreSdevRegistryResponse() {
        //Empty constructor
    }

    public CoreSdevRegistryResponse(int status, String message, SspSDEVInfo sdev) {
        super(status, message, sdev);
    }

}
