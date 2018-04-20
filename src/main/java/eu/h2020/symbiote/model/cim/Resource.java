package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.querydsl.core.annotations.QueryEntity;
import io.swagger.annotations.ApiModel;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Objects;

/**
 * This is the most generic resource representation. Each specific resource type from CIM (StationarySensor, Actuator etc) should extend this class.
 * <p>
 * Each resource, regardless of the type (CIM-defined or even PIM-defined), when stored in the Core is represented by this object.
 * <p>
 * Created by Mael on 28/03/2017.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Actuator.class, name = "Actuator"),
        @JsonSubTypes.Type(value = Service.class, name = "Service"),
        @JsonSubTypes.Type(value = Device.class, name = "Device"),
        @JsonSubTypes.Type(value = StationarySensor.class, name = "StationarySensor"),
        @JsonSubTypes.Type(value = MobileSensor.class, name = "MobileSensor")
})
@ApiModel(description = "Description of a Resource. " +
        "Can be one of following subclasses: Actuator, Service, ActuatingService, StationarySensor, StationaryDevice, MobileSensor, MobileDevice " +
        "(consult SymbIoTeLibraries documentation for API).")
@QueryEntity
@Document
public class Resource {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private List<String> description;

    @JsonProperty("interworkingServiceURL")
    private String interworkingServiceURL;

    public Resource() {
        // Empty constructor used for jackson serialization/deserialization
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public List<String> getDescription() {
        return description;
    }
    public void setDescription(List<String> description) {
        this.description = description;
    }

    public String getInterworkingServiceURL() {
        return interworkingServiceURL;
    }
    public void setInterworkingServiceURL(String interworkingServiceURL) {
        this.interworkingServiceURL = interworkingServiceURL;
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o)
            return true;

        // null check
        if (o == null)
            return false;

        // type check and cast
        if (!(o instanceof Resource))
            return false;

        Resource resource = (Resource) o;

        // field comparison
        return Objects.equals(this.id, resource.getId())
                && Objects.equals(this.name, resource.getName())
                && Objects.equals(this.description, resource.getDescription())
                && Objects.equals(this.interworkingServiceURL, resource.getInterworkingServiceURL());
    }
}