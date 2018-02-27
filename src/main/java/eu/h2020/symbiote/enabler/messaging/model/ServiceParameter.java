package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class describing input parameter value. Values will be interpreted on the platform side.
 *
 * Created by Szymon Mueller on 26/02/2018.
 */
public class ServiceParameter {

    @JsonProperty("name")
    private String name;

    @JsonProperty("value")
    private String value;

    /**
     * Constructor of the parameter with specified name and it's value.
     * @param name Name of the parameter, should be equal to the name of the input parameter of the resource.
     * @param value String representing a value that will be forwarded to the service as input parameter.
     */
    public ServiceParameter( String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
