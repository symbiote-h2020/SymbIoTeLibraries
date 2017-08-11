package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.core.model.InformationModel;

/**
 * Created by mateuszl on 11.08.2017.
 */
public class InformationModelRequest {

    private String token;
    private InformationModel informationModel;

    public InformationModelRequest() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public InformationModel getInformationModel() {
        return informationModel;
    }

    public void setInformationModel(InformationModel informationModel) {
        this.informationModel = informationModel;
    }
}
