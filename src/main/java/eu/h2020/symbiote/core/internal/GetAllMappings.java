package eu.h2020.symbiote.core.internal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetAllMappings extends AbstractMappingRequest {

    @JsonCreator
    public GetAllMappings(@JsonProperty("getDefinition") boolean getDefinition) {
        setGetDefinition(getDefinition);
    }
}
