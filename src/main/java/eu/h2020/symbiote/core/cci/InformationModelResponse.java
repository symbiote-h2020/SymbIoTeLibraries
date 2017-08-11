package eu.h2020.symbiote.core.cci;

import eu.h2020.symbiote.core.model.InformationModel;

/**
 * Created by mateuszl on 11.08.2017.
 */
public class InformationModelResponse {

    private int status;
    private String message;
    private InformationModel informationModel;

    public InformationModelResponse() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public InformationModel getInformationModel() {
        return informationModel;
    }

    public void setInformationModel(InformationModel informationModel) {
        this.informationModel = informationModel;
    }
}
