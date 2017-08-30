package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.model.RDFInfo;

/**
 * Resource Instance validation request for RDFs
 *
 * Created by Mael on 16/03/2017.
 */
public class ResourceInstanceValidationRequest extends RDFInfo {

    private String informationModelId;

    public ResourceInstanceValidationRequest() {
        // Needed for Jackson serialization
    }

    public String getInformationModelId() {
        return informationModelId;
    }

    public void setInformationModelId(String informationModelId) {
        this.informationModelId = informationModelId;
    }
}
