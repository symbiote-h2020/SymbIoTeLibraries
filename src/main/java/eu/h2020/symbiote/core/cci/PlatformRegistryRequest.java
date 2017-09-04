package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.core.model.AbstractRequestSecured;
import eu.h2020.symbiote.core.model.ISecurityRequestContent;
import eu.h2020.symbiote.core.model.Platform;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

/**
 * Created by mateuszl on 07.08.2017.
 */
public class PlatformRegistryRequest extends AbstractRequestSecured<Platform> implements ISecurityRequestContent {

    public PlatformRegistryRequest(SecurityRequest token, Platform body) {
        super(token, body);
    }

    public PlatformRegistryRequest() {
        // Needed for Jackson serialization
    }

    public Platform getPlatform() {
        return super.getBody();
    }

    public void setPlatform(Platform platform) {
        super.setBody(platform);
    }
}
