package eu.h2020.symbiote.core.model.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.core.model.Location;

import java.util.List;

/**
 * Class representing device object from Core Information Model.
 *
 * Created by Szymon Mueller on 26/07/2017.
 */
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
