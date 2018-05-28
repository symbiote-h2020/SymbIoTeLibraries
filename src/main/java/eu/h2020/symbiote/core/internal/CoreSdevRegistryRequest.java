package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.cloud.model.ssp.SspSDEVInfo;
import eu.h2020.symbiote.core.cci.AbstractRequestSecured;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

/**
 * Created by Szymon Mueller on 28/05/2018.
 */
public class CoreSdevRegistryRequest extends AbstractRequestSecured<SspSDEVInfo> {

    private String sspId;

    public CoreSdevRegistryRequest() {
        //Empty constructor
    }

    public CoreSdevRegistryRequest(SecurityRequest securityRequest, SspSDEVInfo sdev, String sspId ) {
        super(securityRequest, sdev);
        this.sspId = sspId;
    }

    public String getSspId() {
        return sspId;
    }

    public void setSspId(String sspId) {
        this.sspId = sspId;
    }
}
