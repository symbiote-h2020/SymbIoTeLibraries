package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.core.model.AbstractRequest;
import eu.h2020.symbiote.core.model.Platform;

/**
 * Created by mateuszl on 07.08.2017.
 */
public class PlatformRegistryRequest extends AbstractRequest<Platform> {

    public PlatformRegistryRequest(Platform body) {
        super(body);
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
