package eu.h2020.symbiote.model.cim;

/**
 * Class representing a Data Property defined in CIM.
 *
 * Created by Szymon Mueller on 26/07/2017.
 */
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS)
@JsonSubTypes({
    @JsonSubTypes.Type(value = PrimitiveProperty.class, name = "PrimitiveProperty") ,
    @JsonSubTypes.Type(value = ComplexProperty.class, name = "ComplexProperty")
})
public class DataProperty {

    @JsonProperty("name")
    private String name;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
