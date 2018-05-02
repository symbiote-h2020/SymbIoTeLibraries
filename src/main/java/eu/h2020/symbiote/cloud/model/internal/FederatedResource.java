package eu.h2020.symbiote.cloud.model.internal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryEntity;
import eu.h2020.symbiote.model.cim.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This class is used for storing and retrieving information of federated platform resources from the Platform Registry.
 *
 * @author Vasileios Glykantzis (ICOM)
 * @since 2/22/2018.
 */

@QueryEntity
@Document
public class FederatedResource {

    @Id
    private String symbioteId;
    private CloudResource cloudResource;
    private String oDataUrl;
    private String restUrl;
    private String resourceType;

    // Todo: Remove this field since this information is available in cloudResource.getFederationInfo.getSharingInformation
    // This field is just use for conveniently getting the federations where the resource is exposed
    private Set<String> federations;

    //This field is used by the searchService to be able to perform repository queries for resources of type Device.
    private Location locatedAt;
    private String locationName;
    private double[] locationCoords;

    public FederatedResource(CloudResource cloudResource) {
        this(cloudResource.getFederationInfo().getSymbioteId(), cloudResource);
    }

    public FederatedResource(String symbioteId, CloudResource cloudResource)
            throws IllegalArgumentException {

        Pattern p = Pattern.compile("^([\\w-]+)@([\\w-]+)$");
        Matcher m = p.matcher(symbioteId);

        if (!m.find())
            throw new IllegalArgumentException("The symbioteId is malformed");

        if (cloudResource.getResource().getInterworkingServiceURL() == null)
            throw new IllegalArgumentException("cloudResource.getResource().getInterworkingServiceURL() == null");

        this.symbioteId = symbioteId;
        this.cloudResource = cloudResource;
        if (cloudResource.getResource().getInterworkingServiceURL() != null) {
            this.oDataUrl = this.createUrl(UrlType.ODATA, symbioteId);
            this.restUrl = this.createUrl(UrlType.REST, symbioteId);
        }

        if (cloudResource.getFederationInfo() == null || cloudResource.getFederationInfo().getSharingInformation() == null) {
            this.federations = new HashSet<>();
        } else
            this.federations = cloudResource.getFederationInfo().getSharingInformation().keySet();


        resourceType = cloudResource.getResource().getClass().getSimpleName();//getCanonicalName()


        if(cloudResource.getResource() instanceof Device) {
            this.locatedAt = ((Device) cloudResource.getResource()).getLocatedAt();
            this.locationName = ((Device) cloudResource.getResource()).getLocatedAt().getName();
        }
       else {
            this.locatedAt = null;
            this.locationName = null;
        }

       if(locatedAt!=null)
       {
           if( locatedAt instanceof WGS84Location)
               locationCoords = new double[]{((WGS84Location) locatedAt).getLongitude(), ((WGS84Location) locatedAt).getLatitude()};
           else locationCoords = null;
       }

    }


    /**
     * Construct an instance using the provided arguments.
     *
     * @param symbioteId the symbioteId identifying a cloudResource inside a federation
     * @param cloudResource the cloudResource description
     * @param oDataUrl the OData cloudResource url
     * @param restUrl the Rest cloudResource url
     * @param federations the list of federations where the resource is currently exposed
     */
    @PersistenceConstructor
    @JsonCreator
    public FederatedResource(@JsonProperty("symbioteId") String symbioteId,
                             @JsonProperty("cloudResource") CloudResource cloudResource,
                             @JsonProperty("oDataUrl") String oDataUrl,
                             @JsonProperty("restUrl") String restUrl,
                             @JsonProperty("federations") Set<String> federations,
                             @JsonProperty("locatedAt") Location locatedAt)
    throws IllegalArgumentException {

        Pattern p = Pattern.compile("^([\\w-]+)@([\\w-]+)$");
        Matcher m = p.matcher(symbioteId);

        if (!m.find())
            throw new IllegalArgumentException("The symbioteId is malformed");

        this.symbioteId = symbioteId;
        this.cloudResource = cloudResource;
        this.oDataUrl = oDataUrl;
        this.restUrl = restUrl;
        this.federations = federations;
        this.locatedAt = locatedAt;
        this.resourceType = cloudResource.getResource().getClass().getSimpleName();
    }

    public void clearPrivateInfo() {
        cloudResource.setInternalId(null);
        cloudResource.setPluginId(null);
        FederationInfoBean federationInfoBean = new FederationInfoBean();
        federationInfoBean.setSharingInformation(new HashMap<>());
        cloudResource.setFederationInfo(federationInfoBean);
        federations.clear();
    }

    public void shareToNewFederation(String federationId, Boolean barteringStatus) {
        ResourceSharingInformation resourceSharingInformation = new ResourceSharingInformation();
        resourceSharingInformation.setBartering(barteringStatus);
        cloudResource.getFederationInfo().getSharingInformation().put(federationId, resourceSharingInformation);

        federations.add(federationId);
    }

    public void unshareFromFederation(String federationId) {
        cloudResource.getFederationInfo().getSharingInformation().remove(federationId);
        federations.remove(federationId);
    }

    public String getSymbioteId() { return this.symbioteId; }
    public void setSymbioteId(String symbioteId) throws IllegalArgumentException {
        Pattern p = Pattern.compile("^([\\w-]+)@([\\w-]+)$");
        Matcher m = p.matcher(symbioteId);

        if (!m.find())
            throw new IllegalArgumentException("The symbioteId is malformed");

        this.symbioteId = symbioteId;
    }

    public CloudResource getCloudResource() { return this.cloudResource; }
    public void setCloudResource(CloudResource cloudResource) { this.cloudResource = cloudResource; }

    public String getoDataUrl() { return this.oDataUrl; }
    public void setoDataUrl(String oDataUrl) { this.oDataUrl = oDataUrl; }

    public String getRestUrl() { return this.restUrl; }
    public void setRestUrl(String restUrl) { this.restUrl = restUrl; }

    public Set<String> getFederations() { return federations; }
    public void setFederations(Set<String> federations) { this.federations = federations; }


    public Location getLocatedAt() { return this.locatedAt; }
    public void setLocatedAt(Location locatedAt) { this.locatedAt = locatedAt; }

    public String getLocationName() { return this.locationName; }
    public void setLocatedAt(String locationName) { this.locationName = locationName; }

    public double[] getlocationCoords() { return this.locationCoords; }
    public void setlocationCoords(double[] locationCoords) { this.locationCoords = locationCoords; }

    public String getResourceType() { return this.resourceType; }
    public void setResourceType(String resourceType) { this.resourceType = resourceType; }


    @JsonIgnore
    public String getPlatformId() throws IllegalArgumentException {

        Pattern p = Pattern.compile("^([\\w-]+)@([\\w-]+)$");
        Matcher m = p.matcher(symbioteId);

        if (m.find())
            return m.group(2);
        throw new IllegalArgumentException("The symbioteId is malformed");
    }

    private String createUrl(UrlType urlType, String id) {
        Resource resource = cloudResource.getResource();
        if (resource instanceof Actuator) {
            return createUrl(urlType, "Actuator", id);
        } else {
            return resource instanceof Service ?
                    createUrl(urlType, "Service", id) :
                    createUrl(urlType, "Sensor", id);
        }
    }

    private String createUrl(UrlType urlType, String resourceTypeName, String id) {
        String interworkingServiceUrl = cloudResource.getResource().getInterworkingServiceURL();
        return urlType == UrlType.ODATA ?
                interworkingServiceUrl.replaceAll("(/rap)?/*$", "") + "/rap/"
                        + resourceTypeName + "s('" + id + "')" :
                interworkingServiceUrl.replaceAll("(/rap)?/*$", "") + "/rap/"
                        + resourceTypeName + "/" + id;
    }

    private enum UrlType {
        ODATA,
        REST
    }
}