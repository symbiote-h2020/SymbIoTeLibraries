package eu.h2020.symbiote.core.internal;

/**
 * Resource Instance validation request for RDFs
 * <p>
 * Created by Mael on 16/03/2017.
 */
public class ResourceInstanceValidationRequest extends RDFInfo {

    private String informationModelId;

    private String interworkingServiceURL;

    public ResourceInstanceValidationRequest() {
        // Needed for Jackson serialization
    }

    public ResourceInstanceValidationRequest(String informationModelId) {
        this.informationModelId = informationModelId;
    }

    public String getInformationModelId() {
        return informationModelId;
    }

    public void setInformationModelId(String informationModelId) {
        this.informationModelId = informationModelId;
    }

    public String getInterworkingServiceURL() {
        return interworkingServiceURL;
    }

    public void setInterworkingServiceURL(String interworkingServiceURL) {
        this.interworkingServiceURL = interworkingServiceURL;
    }
}
