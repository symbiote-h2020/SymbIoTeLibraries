package eu.h2020.symbiote.core.model;


import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Represents the Interworking Service of the platform.
 *
 * Created by Szymon Mueller on 15/03/2017.
 */
public class InterworkingService {

    /**
     * Symbiote id of the interworking service.
     */
    private String id;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return ReflectionToStringBuilder.toString(this);
    }

}
