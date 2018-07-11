package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.cloud.model.ssp.SspRegInfo;
import eu.h2020.symbiote.core.cci.AbstractResponseSecured;

/**
 * Created by Szymon Mueller on 28/05/2018.
 */
public class CoreSdevRegistryResponse extends AbstractResponseSecured<SspRegInfo> {

    public CoreSdevRegistryResponse() {
        //Empty constructor
    }

    public CoreSdevRegistryResponse(int status, String message, SspRegInfo sdev) {
        super(status, message, sdev);
    }

}
