package eu.h2020.symbiote.core.ci;

import eu.h2020.symbiote.model.cim.Capability;
import eu.h2020.symbiote.model.cim.Parameter;
import eu.h2020.symbiote.model.cim.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * Message describing basic resource object for getResourceUrls results.
 */
public class QueryResourceResult {
    private String platformId;
    private String platformName;
    private String owner;
    private String name;
    private String id;
    private String description;
    private String locationName;
    private Double locationLatitude;
    private Double locationLongitude;
    private Double locationAltitude;
    private List<Property> observedProperties;
    private List<String> resourceType;
    private List<Parameter> inputParameters;
    private List<Capability> capabilities;
    private Float ranking;

    /**
     * Default empty constructor.
     */
    public QueryResourceResult() {
        //Needed for Jackson serialization
    }

    public QueryResourceResult(QueryResourceResult queryResourceResult) {
        setPlatformId(queryResourceResult.getPlatformId());
        setPlatformName(queryResourceResult.getPlatformName());
        setOwner(queryResourceResult.getOwner());
        setName(queryResourceResult.getName());
        setId(queryResourceResult.getId());
        setDescription(queryResourceResult.getDescription());
        setLocationName(queryResourceResult.getLocationName());
        setLocationLatitude(queryResourceResult.getLocationLatitude());
        setLocationLongitude(queryResourceResult.getLocationLongitude());
        setLocationAltitude(queryResourceResult.getLocationAltitude());
        setObservedProperties(queryResourceResult.getObservedProperties());
        setResourceType(queryResourceResult.getResourceType());
        setInputParameters(queryResourceResult.getInputParameters());
        setCapabilities(queryResourceResult.getCapabilities());
        setRanking(queryResourceResult.getRanking());
    }

    public String getPlatformId() {
        return platformId;
    }

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getPlatformName() {
        return platformName;
    }

    public void setPlatformName(String platformName) {
        this.platformName = platformName;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Double getLocationLatitude() {
        return locationLatitude;
    }

    public void setLocationLatitude(Double locationLatitude) {
        this.locationLatitude = locationLatitude;
    }

    public Double getLocationLongitude() {
        return locationLongitude;
    }

    public void setLocationLongitude(Double locationLongitude) {
        this.locationLongitude = locationLongitude;
    }

    public Double getLocationAltitude() {
        return locationAltitude;
    }

    public void setLocationAltitude(Double locationAltitude) {
        this.locationAltitude = locationAltitude;
    }

    public List<Property> getObservedProperties() {
        return observedProperties;
    }

    public void setObservedProperties(List<Property> observedProperties) {
        if (observedProperties == null)
            this.observedProperties = null;
        else
            this.observedProperties = new ArrayList<>(observedProperties);
    }

    public List<String> getResourceType() {
        return resourceType;
    }

    public void setResourceType(List<String> resourceType) {
        if (resourceType == null)
            this.resourceType = null;
        else
            this.resourceType = new ArrayList<>(resourceType);
    }

    public List<Parameter> getInputParameters() {
        return inputParameters;
    }

    public void setInputParameters(List<Parameter> inputParameters) {
        this.inputParameters = inputParameters;
    }

    public List<Capability> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<Capability> capabilities) {
        this.capabilities = capabilities;
    }

    public Float getRanking() { return ranking; }

    public void setRanking(Float ranking) { this.ranking = ranking; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QueryResourceResult)) return false;

        QueryResourceResult result = (QueryResourceResult) o;

        if (getPlatformId() != null ? !getPlatformId().equals(result.getPlatformId()) : result.getPlatformId() != null)
            return false;
        if (getPlatformName() != null ? !getPlatformName().equals(result.getPlatformName()) : result.getPlatformName() != null)
            return false;
        if (getOwner() != null ? !getOwner().equals(result.getOwner()) : result.getOwner() != null) return false;
        if (getName() != null ? !getName().equals(result.getName()) : result.getName() != null) return false;
        if (getId() != null ? !getId().equals(result.getId()) : result.getId() != null) return false;
        if (getDescription() != null ? !getDescription().equals(result.getDescription()) : result.getDescription() != null)
            return false;
        if (getLocationName() != null ? !getLocationName().equals(result.getLocationName()) : result.getLocationName() != null)
            return false;
        if (getLocationLatitude() != null ? !getLocationLatitude().equals(result.getLocationLatitude()) : result.getLocationLatitude() != null)
            return false;
        if (getLocationLongitude() != null ? !getLocationLongitude().equals(result.getLocationLongitude()) : result.getLocationLongitude() != null)
            return false;
        if (getLocationAltitude() != null ? !getLocationAltitude().equals(result.getLocationAltitude()) : result.getLocationAltitude() != null)
            return false;
        if (getObservedProperties() != null ? !getObservedProperties().equals(result.getObservedProperties()) : result.getObservedProperties() != null)
            return false;
        if (getResourceType() != null ? !getResourceType().equals(result.getResourceType()) : result.getResourceType() != null)
            return false;
        if (getInputParameters() !=null ? !getInputParameters().equals(result.getInputParameters()) : result.getInputParameters() != null )
            return false;
        if (getCapabilities() != null ? !getCapabilities().equals(result.getCapabilities()):result.getCapabilities() != null)
            return false;
        return getRanking() != null ? getRanking().equals(result.getRanking()) : result.getRanking() == null;
    }

    @Override
    public int hashCode() {
        int result = getPlatformId() != null ? getPlatformId().hashCode() : 0;
        result = 31 * result + (getPlatformName() != null ? getPlatformName().hashCode() : 0);
        result = 31 * result + (getOwner() != null ? getOwner().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getLocationName() != null ? getLocationName().hashCode() : 0);
        result = 31 * result + (getLocationLatitude() != null ? getLocationLatitude().hashCode() : 0);
        result = 31 * result + (getLocationLongitude() != null ? getLocationLongitude().hashCode() : 0);
        result = 31 * result + (getLocationAltitude() != null ? getLocationAltitude().hashCode() : 0);
        result = 31 * result + (getObservedProperties() != null ? getObservedProperties().hashCode() : 0);
        result = 31 * result + (getResourceType() != null ? getResourceType().hashCode() : 0);
        result = 31 * result + (getInputParameters() != null ? getInputParameters().hashCode() : 0);
        result = 31 * result + (getCapabilities() != null ? getCapabilities().hashCode() : 0);
        result = 31 * result + (getRanking() != null ? getRanking().hashCode() : 0);
        return result;
    }
}
