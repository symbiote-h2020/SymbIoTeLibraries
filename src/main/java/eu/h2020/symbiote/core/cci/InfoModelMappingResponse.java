package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.model.mim.OntologyMapping;

/**
 * Created by mateuszl on 11.08.2017.
 */
public class InfoModelMappingResponse extends AbstractResponse<OntologyMapping> {

    public InfoModelMappingResponse() {
        // Needed for Jackson serialization
    }

    public InfoModelMappingResponse(int status, String message, OntologyMapping body) {
        super(status, message, body);
    }
}
