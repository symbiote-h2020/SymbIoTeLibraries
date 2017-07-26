package eu.h2020.symbiote.core.model.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Represents CIM-defined rdfs:Datatype
 *
 * Created by Szymon Mueller on 26/07/2017.
 */
public class RdfsDatatype {

    @JsonProperty("datatypeName")
    private String datatypeName;

    public String getDatatypeName() {
        return datatypeName;
    }

    public void setDatatypeName(String datatypeName) {
        this.datatypeName = datatypeName;
    }
}
