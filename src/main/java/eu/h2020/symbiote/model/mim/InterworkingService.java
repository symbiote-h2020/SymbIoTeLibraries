package eu.h2020.symbiote.model.mim;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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
    @NotNull
    @Pattern(regexp="^(https:\\/\\/www\\.|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$")
    private String url;

    /**
     * SymbioteId of this service's information model.
     */
    @NotNull
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
