package eu.h2020.symbiote.core.model.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.annotations.ApiModel;

import java.util.List;

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
//        @JsonSubTypes.Type(value = ActuatingService.class, name = "ActuatingService"),
        @JsonSubTypes.Type(value = StationarySensor.class, name = "StationarySensor"),
        @JsonSubTypes.Type(value = MobileSensor.class, name = "MobileSensor"),
//        @JsonSubTypes.Type(value = MobileDevice.class, name = "MobileDevice"),
//        @JsonSubTypes.Type(value = StationaryDevice.class, name = "StationaryDevice")
})
@ApiModel(description = "Description of a Resource. " +
        "Can be one of following subclasses: Actuator, Service, ActuatingService, StationarySensor, StationaryDevice, MobileSensor, MobileDevice " +
        "(consult SymbIoTeLibraries documentation for API).")
public class Resource {
    @JsonProperty("id")
    private String id;
    @JsonProperty("labels")
    private List<String> labels;
    @JsonProperty("comments")
    private List<String> comments;
    @JsonProperty("interworkingServiceURL")
    private String interworkingServiceURL;

    public Resource() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<String> getComments() {
        return comments;
    }

    public void setComments(List<String> comments) {
        this.comments = comments;
    }

    public String getInterworkingServiceURL() {
        return interworkingServiceURL;
    }

    public void setInterworkingServiceURL(String interworkingServiceURL) {
        this.interworkingServiceURL = interworkingServiceURL;
    }
}
