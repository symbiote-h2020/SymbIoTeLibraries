package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.model.cim.Resource;

import java.util.Map;

/**
 * Created by mateuszl on 25.05.2018.
 */
public class SspResourceReqistryResponse extends AbstractResponse<Map<String,Resource>> {
    public SspResourceReqistryResponse() {
        // Needed for Jackson serialization
    }

    public SspResourceReqistryResponse(int status, String message, Map<String,Resource> body) {
        super(status, message, body);
    }
}
