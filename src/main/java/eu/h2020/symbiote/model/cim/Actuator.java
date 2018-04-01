package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Represents CIM-defined Actuator class. Actuator specifies location it is located at (using unique location identifier
 * defined by platform the actuator belongs to) as well as a list of capabilities it is using (by specifying list
 * of symbIoTe Ids of the services).
 *
 * Created by Mael on 28/03/2017.
 */
@QueryEntity
@Document
public class Actuator extends Device {

    @JsonProperty("capabilities")
    private List<Capability> capabilities;

    public Actuator() {
    }

    public List<Capability> getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(List<Capability> capabilities) {
        this.capabilities = capabilities;
    }
}
