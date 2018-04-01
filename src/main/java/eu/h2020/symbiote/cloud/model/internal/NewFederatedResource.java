package eu.h2020.symbiote.cloud.model.internal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryEntity;
import eu.h2020.symbiote.model.cim.Actuator;
import eu.h2020.symbiote.model.cim.Resource;
import eu.h2020.symbiote.model.cim.Service;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This class is used for storing and retrieving information of federated platform resources from the Platform Registry.
 *
 * @author Vasileios Glykantzis (ICOM)
 * @since 2/22/2018.
 */

@QueryEntity
@Document
public class NewFederatedResource {

    @Id
    private String symbioteId;
    private String oDataUrl;
    private String restUrl;

    /**
     * Construct an instance using the provided arguments.
     *
     * @param symbioteId the symbioteId identifying a cloudResource inside a federation
     * @param cloudResource the cloudResource description
     * @param oDataUrl the OData cloudResource url
     * @param restUrl the Rest cloudResource url
     * @param federations the list of federations where the resource is currently exposed
     */
    @PersistenceConstructor
    @JsonCreator
    public NewFederatedResource(@JsonProperty("symbioteId") String symbioteId,
                                @JsonProperty("oDataUrl") String oDataUrl,
                                @JsonProperty("restUrl") String restUrl)
    throws IllegalArgumentException {

        Pattern p = Pattern.compile("^([\\w-]+)@([\\w-]+)$");
        Matcher m = p.matcher(symbioteId);

        if (!m.find())
            throw new IllegalArgumentException("The symbioteId is malformed");

        this.symbioteId = symbioteId;
        this.oDataUrl = oDataUrl;
        this.restUrl = restUrl;
    }


    public String getoDataUrl() { return this.oDataUrl; }
    public void setoDataUrl(String oDataUrl) { this.oDataUrl = oDataUrl; }

    public String getRestUrl() { return this.restUrl; }
    public void setRestUrl(String restUrl) { this.restUrl = restUrl; }
}