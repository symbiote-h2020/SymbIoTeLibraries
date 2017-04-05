package eu.h2020.symbiote.cloud.model.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import eu.h2020.symbiote.cloud.model.data.parameter.InputParameter;
import eu.h2020.symbiote.core.model.resources.Parameter;
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
    private String name;
    @JsonIgnore
    private Parameter outputParameter;
    @JsonProperty("inputParameters")
    private List<InputParameter> inputParameters;

    public Service() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parameter getOutputParameter() {
        return outputParameter;
    }

    public void setOutputParameter(Parameter outputParameter) {
        this.outputParameter = outputParameter;
    }

    public List<InputParameter> getInputParameters() {
        return inputParameters;
    }

    public void setInputParameters(List<InputParameter> inputParameters) {
        this.inputParameters = inputParameters;
    }
}
