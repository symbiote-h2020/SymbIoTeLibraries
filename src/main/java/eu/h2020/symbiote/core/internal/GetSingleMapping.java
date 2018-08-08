package eu.h2020.symbiote.core.internal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GetSingleMapping extends AbstractMappingRequest {

    private String mappingId;

    @JsonCreator
    public GetSingleMapping(@JsonProperty("getDefinition") boolean getDefinition,
                            @JsonProperty("mappingId") String mappingId) {
        setGetDefinition(getDefinition);
        setMappingId(mappingId);
    }

    public String getMappingId() { return mappingId; }
    public void setMappingId(String mappingId) { this.mappingId = mappingId; }
}
