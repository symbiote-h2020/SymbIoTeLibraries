package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.core.model.AbstractResponseSecured;
import eu.h2020.symbiote.core.model.InformationModel;

/**
 * Created by mateuszl on 11.08.2017.
 */
public class InformationModelResponse extends AbstractResponseSecured<InformationModel> {

    public InformationModelResponse() {
        // Needed for Jackson serialization
    }

    public InformationModelResponse(int status, String message, InformationModel body) {
        super(status, message, body);
    }

    public InformationModel getInformationModel() {
        return super.getBody();
    }

    public void setInformationModel(InformationModel informationModel) {
        super.setBody(informationModel);
    }
}
