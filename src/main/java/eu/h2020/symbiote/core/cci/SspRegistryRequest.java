package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.model.mim.SmartSpace;

/**
 * Created by mateuszl on 25.05.2018.
 */
public class SspRegistryRequest extends AbstractRequest<SmartSpace> {
    public SspRegistryRequest(SmartSpace body) {
        super(body);
    }

    public SspRegistryRequest() {
        // Needed for Jackson serialization
    }
}