package eu.h2020.symbiote.cloud.model.internal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.model.cim.Actuator;
import eu.h2020.symbiote.model.cim.Resource;
import eu.h2020.symbiote.model.cim.Service;
import org.springframework.data.annotation.PersistenceConstructor;

/**
 * This class is used for storing and retrieving information of federated platform resources from the Platform Registry.
 *
 * @author Vasileios Glykantzis (ICOM)
 * @since 2/22/2018.
 */
public class FederatedResource {
    private String id;
    private Resource resource;
    private String oDataUrl;
    private String restUrl;
    private String federationId;
    private Boolean bartered;

    public FederatedResource(Resource resource, String id, String federationId, Boolean bartered) {
        this.id = id;
        this.resource = resource;
        this.federationId = federationId;
        this.bartered = bartered;

        // Todo: Consider actual Resource validation here
        if (resource.getInterworkingServiceURL() != null) {
            this.oDataUrl = createUrl(UrlType.ODATA, id);
            this.restUrl = createUrl(UrlType.REST, id);
        }
    }

    /**
     * Construct an instance using the provided arguments.
     *
     * @param id the id identifying a resource inside a federation
     * @param resource the resource description
     * @param oDataUrl the OData resource url
     * @param restUrl the Rest resource url
     * @param federationId the federation id of the resource
     * @param bartered indicated if the resource is bartered or not
     */
    @PersistenceConstructor
    @JsonCreator
    public FederatedResource(@JsonProperty("id") String id,
                             @JsonProperty("resource") Resource resource,
                             @JsonProperty("oDataUrl") String oDataUrl,
                             @JsonProperty("restUrl") String restUrl,
                             @JsonProperty("federationId") String federationId,
                             @JsonProperty("bartered") Boolean bartered) {
        this.id = id;
        this.resource = resource;
        this.oDataUrl = oDataUrl;
        this.restUrl = restUrl;
        this.federationId = federationId;
        this.bartered = bartered;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Resource getResource() { return resource; }
    public void setResource(Resource resource) { this.resource = resource; }

    public String getoDataUrl() { return oDataUrl; }
    public void setoDataUrl(String oDataUrl) { this.oDataUrl = oDataUrl; }

    public String getRestUrl() { return restUrl; }
    public void setRestUrl(String restUrl) { this.restUrl = restUrl; }

    public String getFederationId() { return federationId; }
    public void setFederationId(String federationId) { this.federationId = federationId; }

    public Boolean getBartered() { return bartered; }
    public void setBartered(Boolean bartered) { this.bartered = bartered; }

    private String createUrl(UrlType urlType, String id) {
        if (resource instanceof Actuator)
            return createUrl(urlType, "Actuator", id);
        else if (resource instanceof Service)
            return createUrl(urlType, "Service", id);
        else
            return createUrl(urlType, "Sensor", id);
    }

    private String createUrl(UrlType urlType, String resourceTypeName, String id) {
        return urlType == UrlType.ODATA ?
                resource.getInterworkingServiceURL().replaceAll("(/rap)?/*$", "")
                        +  "/rap/" + resourceTypeName + "s('" + id + "')" :
                resource.getInterworkingServiceURL().replaceAll("(/rap)?/*$", "")
                        +  "/rap/" + resourceTypeName + "/" + id;
    }

    private enum UrlType {
        ODATA, REST
    }
}
