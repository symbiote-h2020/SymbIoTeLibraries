package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.model.mim.Platform;

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
}
