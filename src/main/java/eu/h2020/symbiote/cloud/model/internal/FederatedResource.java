package eu.h2020.symbiote.cloud.model.internal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.model.cim.Actuator;
import eu.h2020.symbiote.model.cim.Resource;
import eu.h2020.symbiote.model.cim.Service;
import org.springframework.data.annotation.PersistenceConstructor;

import java.util.Map;

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
    private Map<String, Boolean> barteringInfo;


    public FederatedResource(String id, Resource resource, Map<String, Boolean> barteringInfo) {
        this.id = id;
        this.resource = resource;
        this.barteringInfo = barteringInfo;
        if (resource.getInterworkingServiceURL() != null) {
            this.oDataUrl = this.createUrl(UrlType.ODATA, id);
            this.restUrl = this.createUrl(UrlType.REST, id);
        }

    }

    /**
     * Construct an instance using the provided arguments.
     *
     * @param id the id identifying a resource inside a federation
     * @param resource the resource description
     * @param oDataUrl the OData resource url
     * @param restUrl the Rest resource url
     * @param barteringInfo the key is the federation id and the value shows if the resource is bartered in the
     *                      respective federation
     */
    @PersistenceConstructor
    @JsonCreator
    public FederatedResource(@JsonProperty("id") String id,
                             @JsonProperty("resource") Resource resource,
                             @JsonProperty("oDataUrl") String oDataUrl,
                             @JsonProperty("restUrl") String restUrl,
                             @JsonProperty("barteringInfo") Map<String, Boolean> barteringInfo) {
        this.id = id;
        this.resource = resource;
        this.oDataUrl = oDataUrl;
        this.restUrl = restUrl;
        this.barteringInfo = barteringInfo;
    }

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Resource getResource() {
        return this.resource;
    }
    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public String getoDataUrl() {
        return this.oDataUrl;
    }
    public void setoDataUrl(String oDataUrl) {
        this.oDataUrl = oDataUrl;
    }

    public String getRestUrl() {
        return this.restUrl;
    }
    public void setRestUrl(String restUrl) {
        this.restUrl = restUrl;
    }

    public Map<String, Boolean> getBarteringInfo() { return barteringInfo; }
    public void setBarteringInfo(Map<String, Boolean> barteringInfo) { this.barteringInfo = barteringInfo; }


    private String createUrl(UrlType urlType, String id) {
        if (this.resource instanceof Actuator) {
            return this.createUrl(urlType, "Actuator", id);
        } else {
            return this.resource instanceof Service ? this.createUrl(urlType, "Service", id) : this.createUrl(urlType, "Sensor", id);
        }
    }

    private String createUrl(UrlType urlType, String resourceTypeName, String id) {
        return urlType == UrlType.ODATA ?
                this.resource.getInterworkingServiceURL().replaceAll("(/rap)?/*$", "") + "/rap/"
                        + resourceTypeName + "s('" + id + "')" :
                this.resource.getInterworkingServiceURL().replaceAll("(/rap)?/*$", "") + "/rap/"
                        + resourceTypeName + "/" + id;
    }

    private static enum UrlType {
        ODATA,
        REST;

        private UrlType() {
        }
    }
}