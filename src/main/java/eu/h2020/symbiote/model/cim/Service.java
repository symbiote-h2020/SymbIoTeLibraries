package eu.h2020.symbiote.model.cim;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryEntity;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Represents CIM-defined Service class.
 *
 * Created by Szymon Mueller on 28/03/2017.
 */
@QueryEntity
@Document
public class Service extends Resource {

    @JsonProperty("resultType")
    private Datatype resultType;
    @JsonProperty("parameters")
    private List<Parameter> parameters;

    public Service() {
    }

    public Datatype getResultType() {
        return resultType;
    }

    public void setResultType(Datatype resultType) {
        this.resultType = resultType;
    }

    public List<Parameter> getParameters() {
        return parameters;
    }

    public void setParameters(List<Parameter> parameters) {
        this.parameters = parameters;
    }
}
