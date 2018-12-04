package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.model.mim.OntologyMapping;

/**
 * Created by mateuszl on 11.08.2017.
 */
public class InfoModelMappingRequest extends AbstractRequest<OntologyMapping> {

    public InfoModelMappingRequest(OntologyMapping body) {
        super(body);
    }

    public InfoModelMappingRequest() {
        // Needed for Jackson serialization
    }
}
