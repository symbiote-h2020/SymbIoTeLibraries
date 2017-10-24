package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.model.mim.InformationModel;

/**
 * Created by mateuszl on 11.08.2017.
 */
public class InformationModelResponse extends AbstractResponse<InformationModel> {

    public InformationModelResponse() {
        // Needed for Jackson serialization
    }

    public InformationModelResponse(int status, String message, InformationModel body) {
        super(status, message, body);
    }
}
