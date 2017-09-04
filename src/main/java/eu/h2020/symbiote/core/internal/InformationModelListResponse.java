package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.model.InformationModel;
import eu.h2020.symbiote.core.model.AbstractResponseSecured;

import java.util.List;

/**
 * Response used in communication regarding to request for Resources of a given Platform.
 *
 * Created by mateuszl on 09.08.2017.
 */
public class InformationModelListResponse extends AbstractResponseSecured<List<InformationModel>> {

    public InformationModelListResponse() {
        // Needed for Jackson serialization
    }

    public InformationModelListResponse(int status, String message, List<InformationModel> body) {
        super(status, message, body);
    }

    public void setInformationModels(List<InformationModel> informationModels){
        super.setBody(informationModels);
    }

    public List<InformationModel> getInformationModels(){
        return super.getBody();
    }
}
