package eu.h2020.symbiote.core.model.resources;

import java.util.List;

/**
 * Represents CIM-defined Service class.
 *
 * Created by Mael on 28/03/2017.
 */
public class Service extends Resource {

    private String name;
    private Output hasOutputParamter;
    private List<Input> hasInputParameter;

    public Service() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Output getHasOutputParamter() {
        return hasOutputParamter;
    }

    public void setHasOutputParamter(Output hasOutputParamter) {
        this.hasOutputParamter = hasOutputParamter;
    }

    public List<Input> getHasInputParameter() {
        return hasInputParameter;
    }

    public void setHasInputParameter(List<Input> hasInputParameter) {
        this.hasInputParameter = hasInputParameter;
    }
}
