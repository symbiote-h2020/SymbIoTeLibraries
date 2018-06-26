package eu.h2020.symbiote.cloud.model.internal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryEntity;
import eu.h2020.symbiote.model.cim.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;
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
    private String aggregationId;
    private CloudResource cloudResource;
    private String resourceType;

    // This map has as key the federationId and as value the federated resource information
    private Map<String, FederatedResourceInfo> federatedResourceInfoMap = new HashMap<>();

    // Todo: Remove these fields if an easier way is found for location related queries.
    // This field is used by the searchService to be able to perform location related queries for resource
    // type Device and/or location type WGS84Location.
    private Location locatedAt;
    private double[] locationCoords;
    private Double adaptiveTrust;

    public FederatedResource(CloudResource cloudResource) {
        this(cloudResource.getFederationInfo().getAggregationId(), cloudResource, null);
    }


    public FederatedResource(String aggregationId, CloudResource cloudResource, Double adaptiveTrust)
            throws IllegalArgumentException {

        Pattern p = Pattern.compile("^([\\w-]+)@([\\w-]+)$");
        Matcher m = p.matcher(aggregationId);

        if (!m.find())
            throw new IllegalArgumentException("The aggregationId is malformed");

        if (cloudResource.getResource().getInterworkingServiceURL() == null)
            throw new IllegalArgumentException("cloudResource.getResource().getInterworkingServiceURL() == null");

        this.aggregationId = aggregationId;
        this.cloudResource = cloudResource;

        // Add resource to federations
        if (cloudResource.getFederationInfo() != null && cloudResource.getFederationInfo().getSharingInformation() != null) {
             for (Map.Entry<String, ResourceSharingInformation> entry : cloudResource.getFederationInfo().getSharingInformation().entrySet()) {
                String symbioteId = createSymbioteId(entry.getKey());

                federatedResourceInfoMap.put(
                        entry.getKey(),
                        new FederatedResourceInfo(
                                symbioteId,
                                createUrl(UrlType.ODATA, symbioteId),
                                createUrl(UrlType.REST, symbioteId)
                                )
                );
            }
        }


        resourceType = cloudResource.getResource().getClass().getSimpleName();

        if(cloudResource.getResource() instanceof Device)
            this.locatedAt = ((Device) cloudResource.getResource()).getLocatedAt();
        else
            this.locatedAt = null;


       if(locatedAt != null) {
           if(locatedAt instanceof WGS84Location)
               locationCoords = new double[]{((WGS84Location) locatedAt).getLongitude(), ((WGS84Location) locatedAt).getLatitude()};
           else locationCoords = null;
       }

       this.adaptiveTrust = adaptiveTrust;
    }


    /**
     * Construct an instance using the provided arguments.
     *
     * @param aggregationId the aggregationId identifying a cloudResource inside a federation
     * @param cloudResource the cloudResource description
     * @param resourceType the type of the CloudResource
     * @param federatedResourceInfoMap map containing resource info for each federation the resource is shared to
     * @param locatedAt the location of the resource
     * @param locationCoords the location coordinated of the resource
     */
    @PersistenceConstructor
    @JsonCreator
    public FederatedResource(@JsonProperty("aggregationId") String aggregationId,
                             @JsonProperty("cloudResource") CloudResource cloudResource,
                             @JsonProperty("resourceType") String resourceType,
                             @JsonProperty("federatedResourceInfoMap") Map<String, FederatedResourceInfo> federatedResourceInfoMap,
                             @JsonProperty("locatedAt") Location locatedAt,
                             @JsonProperty("locationCoords") double[] locationCoords,
                             @JsonProperty("adaptiveTrust") Double adaptiveTrust)
    throws IllegalArgumentException {

        Pattern p = Pattern.compile("^([\\w-]+)@([\\w-]+)$");
        Matcher m = p.matcher(aggregationId);

        if (!m.find())
            throw new IllegalArgumentException("The aggregationId is malformed");

        this.aggregationId = aggregationId;
        this.cloudResource = cloudResource;
        this.resourceType = resourceType;
        this.federatedResourceInfoMap = federatedResourceInfoMap;
        this.locatedAt = locatedAt;
        this.locationCoords = locationCoords;
        this.adaptiveTrust = adaptiveTrust;
    }

    public void clearPrivateInfo() {
        cloudResource.setInternalId(null);
        cloudResource.setPluginId(null);
        FederationInfoBean federationInfoBean = new FederationInfoBean();
        federationInfoBean.setSharingInformation(new HashMap<>());
        cloudResource.setFederationInfo(federationInfoBean);
        federatedResourceInfoMap.clear();
        adaptiveTrust = null;
    }

    public void shareToNewFederation(String federationId, Boolean barteringStatus) {
        if (cloudResource.getFederationInfo().getSharingInformation().get(federationId) != null &&
                cloudResource.getFederationInfo().getSharingInformation().get(federationId).getSharingDate() != null)
            shareToNewFederation(federationId, barteringStatus,
                    cloudResource.getFederationInfo().getSharingInformation().get(federationId).getSharingDate());
        else
            shareToNewFederation(federationId, barteringStatus, new Date());

    }

    public void shareToNewFederation(String federationId, Boolean barteringStatus, Date sharingDate) {
        String symbioteId = createSymbioteId(federationId);
        ResourceSharingInformation resourceSharingInformation = new ResourceSharingInformation();
        resourceSharingInformation.setBartering(barteringStatus);
        resourceSharingInformation.setSharingDate(sharingDate);
        resourceSharingInformation.setSymbioteId(symbioteId);

        cloudResource.getFederationInfo().getSharingInformation().put(federationId, resourceSharingInformation);
        federatedResourceInfoMap.put(
                federationId,
                new FederatedResourceInfo(
                        symbioteId,
                        createUrl(UrlType.ODATA, symbioteId),
                        createUrl(UrlType.REST, symbioteId)
                        )
        );
    }

    public void unshareFromFederation(String federationId) {
        cloudResource.getFederationInfo().getSharingInformation().remove(federationId);
        federatedResourceInfoMap.remove(federationId);
     }

    public String getAggregationId() { return this.aggregationId; }
    public void setAggregationId(String aggregationId) throws IllegalArgumentException {
        Pattern p = Pattern.compile("^([\\w-]+)@([\\w-]+)$");
        Matcher m = p.matcher(aggregationId);

        if (!m.find())
            throw new IllegalArgumentException("The aggregationId is malformed");

        this.aggregationId = aggregationId;
    }

    public CloudResource getCloudResource() { return this.cloudResource; }
    public void setCloudResource(CloudResource cloudResource) { this.cloudResource = cloudResource; }

    public Map<String, FederatedResourceInfo> getFederatedResourceInfoMap() {
        return federatedResourceInfoMap;
    }

    public void setFederatedResourceInfoMap(Map<String, FederatedResourceInfo> federatedResourceInfoMap) {
        this.federatedResourceInfoMap = federatedResourceInfoMap;
    }

    public Location getLocatedAt() { return this.locatedAt; }
    public void setLocatedAt(Location locatedAt) { this.locatedAt = locatedAt; }

    public double[] getLocationCoords() { return locationCoords; }
    public void setLocationCoords(double[] locationCoords) { this.locationCoords = locationCoords; }

    public String getResourceType() { return this.resourceType; }
    public void setResourceType(String resourceType) { this.resourceType = resourceType; }

    public Double getAdaptiveTrust() { return adaptiveTrust; }
    public void setAdaptiveTrust(Double adaptiveTrust) { this.adaptiveTrust = adaptiveTrust; }

    @JsonIgnore
    public Set<String> getFederations() { return this.federatedResourceInfoMap.keySet(); }

    @JsonIgnore
    public String getPlatformId() throws IllegalArgumentException {

        Pattern p = Pattern.compile("^([\\w-]+)@([\\w-]+)$");
        Matcher m = p.matcher(aggregationId);

        if (m.find())
            return m.group(2);
        throw new IllegalArgumentException("The aggregationId is malformed");
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

    private String createSymbioteId(String federationId) {
        return aggregationId + "@" + federationId;
    }

    private enum UrlType {
        ODATA,
        REST
    }
}