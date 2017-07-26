package eu.h2020.symbiote.cloud.model.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.cloud.model.data.parameter.Parameter;
import eu.h2020.symbiote.core.model.resources.Datatype;
import eu.h2020.symbiote.core.model.resources.Resource;
import java.util.List;
import org.springframework.data.annotation.Id;

/**
 * Represents CIM-defined Service class.
 *
 * Created by Mael on 28/03/2017.
 */
public class Service extends Resource {

    @Id
    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonIgnore
    private Datatype outputDatatype;
    @JsonProperty("inputParameters")
    private List<Parameter> inputParameters;

    public Service() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Datatype getOutputDatatype() {
        return outputDatatype;
    }

    public void setOutputDatatype(Datatype outputDatatype) {
        this.outputDatatype = outputDatatype;
    }

    public List<Parameter> getInputParameters() {
        return inputParameters;
    }

    public void setInputParameters(List<Parameter> inputParameters) {
        this.inputParameters = inputParameters;
    }
}
