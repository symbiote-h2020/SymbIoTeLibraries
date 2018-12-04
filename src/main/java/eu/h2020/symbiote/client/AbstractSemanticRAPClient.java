/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.h2020.symbiote.client;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import eu.h2020.symbiote.client.interfaces.CRAMClient;
import eu.h2020.symbiote.client.interfaces.RAPClient;
import eu.h2020.symbiote.client.interfaces.SearchClient;
import eu.h2020.symbiote.client.interfaces.SemanticRAPClient;
import eu.h2020.symbiote.core.ci.SparqlQueryOutputFormat;
import eu.h2020.symbiote.core.ci.SparqlQueryRequest;
import eu.h2020.symbiote.core.ci.SparqlQueryResponse;
import eu.h2020.symbiote.core.internal.RDFFormat;
import eu.h2020.symbiote.jsonld.JsonLDHelper;
import static eu.h2020.symbiote.jsonld.JsonLDTypeAnnotationIntrospector.DEFAULT_ALWAY_USE_ARRAY_FOR_TYPE_PROPERTY;
import static eu.h2020.symbiote.jsonld.JsonLDTypeAnnotationIntrospector.DEFAULT_INCLUDE_TYPES_FROM_SUPERCLASSES;
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;
import eu.h2020.symbiote.semantics.ModelHelper;
import eu.h2020.symbiote.semantics.mapping.data.DataMapper;
import eu.h2020.symbiote.semantics.mapping.model.Mapping;
import eu.h2020.symbiote.semantics.mapping.model.UnsupportedMappingException;
import eu.h2020.symbiote.semantics.mapping.parser.ParseException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFactory;
import org.apache.jena.rdf.model.Literal;
import org.springframework.http.HttpStatus;

/**
 *
 * @author Michael Jacoby <michael.jacoby@iosb.fraunhofer.de>
 */
public abstract class AbstractSemanticRAPClient implements SemanticRAPClient {

    private static final Log LOG = LogFactory.getLog(AbstractSemanticRAPClient.class);
    private static final String META_GRAPH = "http://www.symbiote-h2020.eu/ontology/internal/meta";
    private static final String SPARQL_PREFIXES
            = "PREFIX core: <http://www.symbiote-h2020.eu/ontology/core#> \n"
            + "PREFIX meta: <http://www.symbiote-h2020.eu/ontology/meta#> \n"
            + "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#> \n"
            + "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#> \n"
            + "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n\n";
    private boolean alwaysUseArrayForTypeProperty = DEFAULT_ALWAY_USE_ARRAY_FOR_TYPE_PROPERTY;
    private CRAMClient cramClient;
    private boolean includeTypesFromSuperclasses = DEFAULT_INCLUDE_TYPES_FROM_SUPERCLASSES;
    private RAPClient rapClient;
    private SearchClient searchClient;

    protected AbstractSemanticRAPClient(RAPClient rapClient, CRAMClient cramClient, SearchClient searchClient) {
        this(cramClient, searchClient);
        this.rapClient = rapClient;
    }

    protected AbstractSemanticRAPClient(CRAMClient cramClient, SearchClient searchClient) {
        this.cramClient = cramClient;
        this.searchClient = searchClient;
    }

    @Override
    public void actuateWithMapping(String resourceId, String body, boolean serverValidation, Set<String> homePlatformIds) {
        actuate(
                resourceId,
                map(
                        body,
                        getPIMId(homePlatformIds, JsonLDHelper.asJsonNode(body)),
                        getPIMIdByResourceId(resourceId)),
                serverValidation,
                homePlatformIds);
    }

    @Override
    public void actuateWithMapping(String resourceId, String body, boolean serverValidation, Set<String> homePlatformIds, String parameterPIMId, String resultPIMId) {
        actuate(
                resourceId,
                map(body, parameterPIMId, resultPIMId),
                serverValidation,
                homePlatformIds);
    }

    @Override
    public void actuateWithMappingAsGuest(String resourceId, String body, boolean serverValidation) {
        actuateAsGuest(
                resourceId,
                map(
                        body,
                        getPIMIdByTypes(JsonLDHelper.findTypes(JsonLDHelper.asJsonNode(body))),
                        getPIMIdByResourceId(resourceId)),
                serverValidation);
    }

    @Override
    public void actuateWithMappingAsGuest(String resourceId, String body, boolean serverValidation, String parameterPIMId, String resultPIMId) {
        actuateAsGuest(
                resourceId,
                map(body, parameterPIMId, resultPIMId),
                serverValidation);
    }

    @Override
    public boolean getAlwaysUseArrayForTypeProperty() {
        return alwaysUseArrayForTypeProperty;
    }

    @Override
    public void setAlwaysUseArrayForTypeProperty(boolean value) {
        alwaysUseArrayForTypeProperty = value;
    }

    public CRAMClient getCramClient() {
        return cramClient;
    }

    public void setCramClient(CRAMClient cramClient) {
        this.cramClient = cramClient;
    }

    @Override
    public boolean getIncludeTypesFromSuperclasses() {
        return includeTypesFromSuperclasses;
    }

    @Override
    public void setIncludeTypesFromSuperclasses(boolean value) {
        includeTypesFromSuperclasses = value;
    }

    public RAPClient getRapClient() {
        return rapClient;
    }

    public void setRapClient(RAPClient rapClient) {
        this.rapClient = rapClient;
    }

    @Override
    public String getResourceWithMapping(String resourceId, boolean serverValidation, Set<String> homePlatformIds, String resultPIMId) {
        try {
            return map(getResource(resourceId, serverValidation, homePlatformIds), getPIMIdByResourceId(resourceId), resultPIMId);
        } catch (SecurityHandlerException ex) {
            LOG.warn("error accessing resource", ex);
        }
        return null;
    }

    @Override
    public String getResourceWithMapping(String resourceId, boolean serverValidation, Set<String> homePlatformIds) {
        return getResourceWithMapping(resourceId, serverValidation, homePlatformIds, getPIMIdByPlatformId(homePlatformIds.iterator().next()));
    }

    @Override
    public String getResourceWithMappingAsGuest(String resourceId, boolean serverValidation, String resultPIMId) {
        try {
            return map(getResourceAsGuest(resourceId, serverValidation), getPIMIdByResourceId(resourceId), resultPIMId);
        } catch (SecurityHandlerException ex) {
            LOG.warn("error accessing resource", ex);
        }
        return null;
    }

    @Override
    public String getResourceWithMappingAsGuest(String resourceId, JavaType resultType, boolean serverValidation) {
        try {
            return map(
                    getResourceAsGuest(resourceId, serverValidation),
                    getPIMIdByResourceId(resourceId),
                    getPIMIdByTypes(JsonLDHelper.findTypes(resultType.getRawClass())));
        } catch (SecurityHandlerException ex) {
            LOG.warn("error accessing resource", ex);
        }
        return null;
    }

    public SearchClient getSearchClient() {
        return searchClient;
    }

    public void setSearchClient(SearchClient searchClient) {
        this.searchClient = searchClient;
    }

    @Override
    public String invokeServiceWithMapping(String resourceId, String body, boolean serverValidation, Set<String> homePlatformIds) {
        return invokeServiceWithMapping(
                resourceId,
                body,
                serverValidation,
                homePlatformIds,
                getPIMId(homePlatformIds, JsonLDHelper.asJsonNode(body)),
                getPIMIdByResourceId(resourceId));
    }

    @Override
    public String invokeServiceWithMapping(String resourceId, String body, boolean serverValidation, Set<String> homePlatformIds, String parameterPIMId, String resultPIMId) {
        return invokeWithMapping(
                body,
                parameterPIMId,
                resultPIMId,
                x -> getRapClient().invokeService(
                        getCramClient().getResourceUrl(resourceId, true, homePlatformIds).getBody().get(resourceId),
                        x,
                        serverValidation,
                        homePlatformIds));
    }

    @Override
    public String invokeServiceWithMappingAsGuest(String resourceId, String body, boolean serverValidation) {
        return invokeServiceWithMappingAsGuest(
                resourceId,
                body,
                serverValidation,
                getPIMIdByTypes(JsonLDHelper.findTypes(JsonLDHelper.asJsonNode(body))),
                getPIMIdByResourceId(resourceId));
    }

    @Override
    public String invokeServiceWithMappingAsGuest(String resourceId, String body, boolean serverValidation, String parameterPIMId, String resultPIMId) {
        return invokeWithMapping(
                body,
                parameterPIMId,
                resultPIMId,
                x -> getRapClient().invokeServiceAsGuest(
                        getCramClient().getResourceUrlAsGuest(resourceId, true).getBody().get(resourceId),
                        x,
                        serverValidation));
    }

    protected ResultSet executeSparql(String query) {
        SparqlQueryRequest request = new SparqlQueryRequest(query, SparqlQueryOutputFormat.JSON);
        SparqlQueryResponse response = getSearchClient().searchAsGuest(request, false);
        if (Objects.equals(response.getStatus(), HttpStatus.OK.value())) {
            return ResultSetFactory.fromJSON(new ByteArrayInputStream(response.getBody().getBytes()));
        }
        return null;
    }

    protected Mapping getMapping(String sourceId, String targetId) {
        Mapping result = null;
        if (sourceId == null || targetId == null || Objects.equals(sourceId, targetId)) {
            return result;
        }
        try {
            Optional<Literal> sparqlResult = ModelHelper.resultSetAsLiteral(executeSparql(SparqlQueries.getMapping(sourceId, targetId)));
            if (sparqlResult.isPresent()) {
                result = Mapping.parse(sparqlResult.get().getString());
            }

        } catch (IllegalStateException ex) {
            LOG.warn("found multiple meetings with sourceId: " + sourceId + ", targetId: " + targetId);
        } catch (ParseException ex) {
            LOG.warn("failed to parse mapping", ex);
        }
        return result;
    }

    protected String getPIMId(Set<String> homePlatformIds, JsonNode payload) {
        if (homePlatformIds != null && homePlatformIds.size() == 1) {
            return getPIMIdByPlatformId(homePlatformIds.iterator().next());
        } else {
            return getPIMIdByTypes(JsonLDHelper.findTypes(payload));
        }
    }

    protected String getPIMIdByPlatformId(String platformId) {
        String result = null;
        if (platformId == null) {
            return result;
        }
        try {
            Optional<Literal> sparqlResult = ModelHelper.resultSetAsLiteral(executeSparql(SparqlQueries.getPIMIdByPlatformId(platformId)));
            if (sparqlResult.isPresent()) {
                result = sparqlResult.get().getString();
            }

        } catch (IllegalStateException ex) {
            LOG.warn("found multiple information models for platform id: " + platformId);
        }
        return result;
    }

    protected String getPIMIdByResourceId(String resourceId) {
        String result = null;
        if (resourceId == null) {
            return result;
        }
        try {
            Optional<Literal> sparqlResult = ModelHelper.resultSetAsLiteral(executeSparql(SparqlQueries.getPIMIdByResourceId(resourceId)));
            if (sparqlResult.isPresent()) {
                result = sparqlResult.get().getString();
            }

        } catch (IllegalStateException ex) {
            LOG.warn("found multiple information models for resource id: " + resourceId);
        }
        return result;
    }

    protected String getPIMIdByTypes(Set<String> types) {
        String result = null;
        if (types == null || types.isEmpty()) {
            return result;
        }
        try {
            Optional<Literal> sparqlResult = ModelHelper.resultSetAsLiteral(executeSparql(SparqlQueries.getPIMIdByTypes(types)));
            if (sparqlResult.isPresent()) {
                result = sparqlResult.get().getString();
            }

        } catch (IllegalStateException ex) {
            LOG.warn("found multiple information models for types: " + types.stream().collect(Collectors.joining(", ", "<", ">")), ex);
        }
        return result;
    }

    protected void setRAPClient(RAPClient rapClient) {
        this.setRapClient(rapClient);
    }

    protected String invokeWithMapping(
            String body,
            String parameterPIMId,
            String resultPIMId,
            Function<String, String> fctToInvoke) {
        return map(fctToInvoke.apply(map(body, parameterPIMId, resultPIMId)), resultPIMId, parameterPIMId);
    }

    protected String map(String data, String sourcePIMId, String targetPIMId) {
        String result = data;
        Mapping mapping = getMapping(sourcePIMId, targetPIMId);
        if (sourcePIMId != null
                && targetPIMId != null
                && !Objects.equals(sourcePIMId, targetPIMId)) {
            if (JsonLDHelper.isJsonLD(JsonLDHelper.asJsonNode(data))) {
                try {
                    result = map(data, mapping);
                } catch (IOException | UnsupportedMappingException ex) {
                    LOG.warn("mapping data failed", ex);
                }
            }
        }
        return result;
    }

    protected String map(String json, Mapping mapping) throws IOException, UnsupportedMappingException {
        return ModelHelper.writeModel(
                new DataMapper().map(JsonLDHelper.jsonLDToRdf(json), mapping),
                RDFFormat.JSONLD);
    }

}
