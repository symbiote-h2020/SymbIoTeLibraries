package eu.h2020.symbiote.core.model;


/**
 * Represents the Interworking Service of the platform.
 *
 * Created by Szymon Mueller on 15/03/2017.
 */
public class InterworkingService {

    /**
     * URL where Interworking Service (Interworking Interface or other component on platform side that will answer
     * calls from the Core).
     */
    private String url;

    /**
     * SymbioteId of this service's information model.
     */
    private String informationModelId;

    public InterworkingService() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getInformationModelId() {
        return informationModelId;
    }

    public void setInformationModelId(String informationModelId) {
        this.informationModelId = informationModelId;
    }

    @Override
    public String toString() {
        return "{url: " + getUrl() + ", id: " + getInformationModelId() + "}";
    }
}
