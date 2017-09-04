package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.core.model.AbstractRequest;
import eu.h2020.symbiote.core.model.InformationModel;

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

    public InformationModel getInformationModel() {
        return super.getBody();
    }

    public void setInformationModel(InformationModel informationModel) {
        super.setBody(informationModel);
    }
}
