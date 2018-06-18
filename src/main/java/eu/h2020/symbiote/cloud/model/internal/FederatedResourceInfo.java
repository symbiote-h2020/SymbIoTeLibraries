package eu.h2020.symbiote.cloud.model.internal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryEntity;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@QueryEntity
@Document
public class FederatedResourceInfo {

    private String symbioteId;
    private String oDataUrl;
    private String restUrl;


    /**
     * Construct an instance using the provided arguments.
     *
     * @param symbioteId the symbioteId identifying a cloudResource inside a federation
     * @param oDataUrl the OData cloudResource url
     * @param restUrl the Rest cloudResource url
     */
    @PersistenceConstructor
    @JsonCreator
    public FederatedResourceInfo(@JsonProperty("symbioteId") String symbioteId,
                                 @JsonProperty("oDataUrl") String oDataUrl,
                                 @JsonProperty("restUrl") String restUrl)
            throws IllegalArgumentException {

        Pattern p = Pattern.compile("^([\\w-]+)@([\\w-]+)@([\\w-]+)$");
        Matcher m = p.matcher(symbioteId);

        if (!m.find())
            throw new IllegalArgumentException("The symbioteId is malformed");

        this.symbioteId = symbioteId;
        this.oDataUrl = oDataUrl;
        this.restUrl = restUrl;

    }

    public String getSymbioteId() { return symbioteId; }
    public void setSymbioteId(String symbioteId) { this.symbioteId = symbioteId; }

    public String getoDataUrl() { return oDataUrl; }
    public void setoDataUrl(String oDataUrl) { this.oDataUrl = oDataUrl; }

    public String getRestUrl() { return restUrl; }
    public void setRestUrl(String restUrl) { this.restUrl = restUrl; }
}
