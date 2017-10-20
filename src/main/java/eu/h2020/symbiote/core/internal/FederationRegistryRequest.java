package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.cci.AbstractRequest;
import eu.h2020.symbiote.model.mim.Federation;

/**
 * Payload model used in Federations CRUD requests to Registry.
 * <p>
 * Created by mateuszl on 22.08.2017.
 */
public class FederationRegistryRequest extends AbstractRequest<Federation> {

    public FederationRegistryRequest() {
        // Needed for Jackson serialization
    }

    public FederationRegistryRequest(Federation body) {
        super(body);
    }
}