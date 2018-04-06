package eu.h2020.symbiote.enabler.messaging.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.PersistenceConstructor;

/**
 * Class describing input parameter value. Values will be interpreted into JSON values and send to the platform.
 *
 * Created by Szymon Mueller on 26/02/2018.
 */
public class ServiceParameter {


    private String name;


    private Object value;

    /**
     * Constructor of the parameter with specified name and it's value.
     * @param name Name of the parameter, should be equal to the name of the input parameter of the resource.
     * @param value Object representing a value that will be forwarded to the service as input parameter.
     */
    @JsonCreator
    @PersistenceConstructor
    public ServiceParameter( @JsonProperty("name") String name,
                             @JsonProperty("value") Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceParameter that = (ServiceParameter) o;

        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return value != null ? value.equals(that.value) : that.value == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceParameter{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
