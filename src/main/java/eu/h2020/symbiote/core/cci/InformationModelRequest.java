package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.core.model.AbstractRequestSecured;
import eu.h2020.symbiote.core.model.InformationModel;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;

/**
 * Created by mateuszl on 11.08.2017.
 */
public class InformationModelRequest extends AbstractRequestSecured<InformationModel> {

    public InformationModelRequest(SecurityRequest securityRequest, InformationModel body) {
        super(securityRequest, body);
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
