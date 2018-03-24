package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Represents CIM-defined InputDatatype parameter class
 *
 * Created by Mael on 28/03/2017.
 */
public class Parameter {

    @JsonProperty("name")
    private String name;
    @JsonProperty("mandatory")
    private boolean mandatory;
    @JsonProperty("restrictions")
    private List<Restriction> restrictions;
    @JsonProperty("datatype")
    private Datatype datatype;

    public Parameter() {}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isMandatory() {
        return mandatory;
    }

    public void setMandatory(boolean mandatory) {
        this.mandatory = mandatory;
    }

    public List<Restriction> getRestrictions() {
        return restrictions;
    }

    public void setRestrictions(List<Restriction> restrictions) {
        this.restrictions = restrictions;
    }

    public Datatype getDatatype() {
        return datatype;
    }

    public void setDatatype(Datatype datatype) {
        this.datatype = datatype;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Parameter parameter = (Parameter) o;

        if (mandatory != parameter.mandatory) return false;
        if (name != null ? !name.equals(parameter.name) : parameter.name != null) return false;
        if (restrictions != null ? !restrictions.equals(parameter.restrictions) : parameter.restrictions != null)
            return false;
        return datatype != null ? datatype.equals(parameter.datatype) : parameter.datatype == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (mandatory ? 1 : 0);
        result = 31 * result + (restrictions != null ? restrictions.hashCode() : 0);
        result = 31 * result + (datatype != null ? datatype.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Parameter{" +
                "name='" + name + '\'' +
                ", mandatory=" + mandatory +
                ", restrictions=" + restrictions +
                ", datatype=" + datatype +
                '}';
    }
}
