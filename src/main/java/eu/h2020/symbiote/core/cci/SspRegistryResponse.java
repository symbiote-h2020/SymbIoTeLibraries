package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.model.mim.SmartSpace;

/**
 * Created by mateuszl on 25.05.2018.
 */
public class SspRegistryResponse extends AbstractResponse<SmartSpace> {
    public SspRegistryResponse() {
        // Needed for Jackson serialization
    }

    public SspRegistryResponse(int status, String message, SmartSpace body) {
        super(status, message, body);
    }
}
