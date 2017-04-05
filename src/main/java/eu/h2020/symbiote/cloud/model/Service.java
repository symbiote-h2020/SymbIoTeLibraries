package eu.h2020.symbiote.cloud.model;

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
    private String name;
    private Parameter hasOutputParameter;
    private List<InputParameter> hasInputParameter;

    public Service() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Parameter getHasOutputParameter() {
        return hasOutputParameter;
    }

    public void setHasOutputParameter(Parameter hasOutputParameter) {
        this.hasOutputParameter = hasOutputParameter;
    }

    public List<InputParameter> getHasInputParameter() {
        return hasInputParameter;
    }

    public void setHasInputParameter(List<InputParameter> hasInputParameter) {
        this.hasInputParameter = hasInputParameter;
    }
}
