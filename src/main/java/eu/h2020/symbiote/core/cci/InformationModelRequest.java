package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.model.mim.InformationModel;

/**
 * Created by mateuszl on 11.08.2017.
 */
public class InformationModelRequest extends AbstractRequest<InformationModel> {

    public InformationModelRequest(InformationModel body) {
        super(body);
    }

    public InformationModelRequest() {
        // Needed for Jackson serialization
    }
}
