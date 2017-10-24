package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.model.mim.Platform;

/**
 * Created by mateuszl on 07.08.2017.
 */
public class PlatformRegistryResponse extends AbstractResponse<Platform> {

    public PlatformRegistryResponse() {
        // Needed for Jackson serialization
    }

    public PlatformRegistryResponse(int status, String message, Platform body) {
        super(status, message, body);
    }
}
