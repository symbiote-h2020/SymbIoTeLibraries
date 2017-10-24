package eu.h2020.symbiote.core.ci;

/**
 * Enum containing possible resource types
 *
 * Created by Szymon Mueller on 11/05/2017.
 */
public enum ResourceType {

    ACTUATOR("Actuator","http://www.symbiote-h2020.eu/ontology/core#Actuator"),
    STATIONARY_SENSOR("Stationary Sensor","http://www.symbiote-h2020.eu/ontology/core#StationarySensor"),
    ACTUATING_SERVICE("Actuating Service","http://www.symbiote-h2020.eu/ontology/core#ActuatingService"),
    SERVICE("Service","http://www.symbiote-h2020.eu/ontology/core#Service"),
    MOBILE_SENSOR("Mobile Sensor","http://www.symbiote-h2020.eu/ontology/core#MobileSensor");

    private final String name;
    private final String uri;

    ResourceType(String name, String uri) {
        this.name = name;
        this.uri = uri;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getUri() {
        return this.uri;
    }

    public String getName() {
        return this.name;
    }

    public static ResourceType getTypeForName(String name) {
        switch (name) {
            case "Actuator":
                return ResourceType.ACTUATOR;
            case "StationarySensor":
            case "Stationary Sensor":
                return ResourceType.STATIONARY_SENSOR;
            case "ActuatingService":
            case "Actuating Service":
                return ResourceType.ACTUATING_SERVICE;
            case "Service":
                return ResourceType.SERVICE;
            case "MobileSensor":
            case "Mobile Sensor":
                return ResourceType.MOBILE_SENSOR;
            default:
                throw new IllegalArgumentException("unknown name '" + name + "'");
        }
    }


}
