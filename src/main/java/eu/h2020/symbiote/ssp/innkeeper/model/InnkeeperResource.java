package eu.h2020.symbiote.ssp.innkeeper.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vasgl on 8/24/2017.
 */
public class InnkeeperResource {

    @Id
    @JsonProperty("id")
    private String id;

    @JsonProperty("hash")
    private String hash;

    @JsonProperty("deviceDescriptor")
    private DeviceDescriptor deviceDescriptor;

    @JsonProperty("observesProperty")
    private List<String> observesProperty;

    public InnkeeperResource() {
        // empty constructor
    }

    public InnkeeperResource(String id, String hash, DeviceDescriptor deviceDescriptor, List<String> observesProperty) {
        setId(id);
        setHash(hash);
        setDeviceDescriptor(deviceDescriptor);
        setObservesProperty(new ArrayList<>(observesProperty));
    }

    public InnkeeperResource(InnkeeperResource resource) {
        setId(resource.getId());
        setHash(resource.getHash());
        setDeviceDescriptor(resource.getDeviceDescriptor());
        setObservesProperty(resource.getObservesProperty());
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getHash() { return hash; }
    public void setHash(String hash) { this.hash = hash; }

    public DeviceDescriptor getDeviceDescriptor() { return deviceDescriptor; }
    public void setDeviceDescriptor(DeviceDescriptor deviceDescriptor) { this.deviceDescriptor = deviceDescriptor; }

    public List<String> getObservesProperty() { return observesProperty; }
    public void setObservesProperty(List<String> observesProperty) { this.observesProperty = observesProperty; }
}
