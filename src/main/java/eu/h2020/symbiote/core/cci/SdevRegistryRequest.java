package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.cloud.model.ssp.SspRegInfo;

/**
 * Class representing SDEV registry request.
 *
 * Created by mateuszl on 25.05.2018.
 */
public class SdevRegistryRequest extends AbstractRequest<SspRegInfo> {
    public SdevRegistryRequest(SspRegInfo body) {
        super(body);
    }

    public SdevRegistryRequest() {
        // Needed for Jackson serialization
    }
}
