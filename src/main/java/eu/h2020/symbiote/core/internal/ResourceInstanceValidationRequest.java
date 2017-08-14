package eu.h2020.symbiote.core.internal;

import eu.h2020.symbiote.core.model.RDFInfo;
import eu.h2020.symbiote.core.model.internal.CoreResource;

import java.util.Map;

/**
 * Resource Instance validation request for RDFs
 *
 * Created by Mael on 16/03/2017.
 */
public class ResourceInstanceValidationRequest extends RDFInfo {

    private String informationModelId;

    public ResourceInstanceValidationRequest() {

    }

    public String getInformationModelId() {
        return informationModelId;
    }

    public void setInformationModelId(String informationModelId) {
        this.informationModelId = informationModelId;
    }
}
