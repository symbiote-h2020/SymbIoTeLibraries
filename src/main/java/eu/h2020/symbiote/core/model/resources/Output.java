package eu.h2020.symbiote.core.model.resources;

/**
 * Represents CIM-defined Output class (named Parameter in CIM v0.5).
 *
 * Created by Mael on 28/03/2017.
 */
public class Output {

    private boolean isArray;
    private String datatype;

    public Output() {
    }

    public boolean isArray() {
        return isArray;
    }

    public void setArray(boolean array) {
        isArray = array;
    }

    public String getDatatype() {
        return datatype;
    }

    public void setDatatype(String datatype) {
        this.datatype = datatype;
    }
}
