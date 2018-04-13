package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Class representing device object from Core Information Model.
 *
 * Created by Szymon Mueller on 26/07/2017.
 */
@QueryEntity
@Document
public class Device extends Resource {

    @JsonProperty("locatedAt")
    private Location locatedAt;

    @JsonProperty("services")
    private List<Service> services;

    public Location getLocatedAt() {
        return locatedAt;
    }

    public void setLocatedAt(Location locatedAt) {
        this.locatedAt = locatedAt;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
}
