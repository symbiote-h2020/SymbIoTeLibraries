package eu.h2020.symbiote.client.feign;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;
import eu.h2020.symbiote.client.AbstractSymbIoTeClientFactory;
import static eu.h2020.symbiote.client.AbstractSymbIoTeClientFactory.getFactory;
import eu.h2020.symbiote.client.SparqlQueries;
import eu.h2020.symbiote.core.ci.SparqlQueryRequest;
import eu.h2020.symbiote.core.ci.SparqlQueryResponse;
import eu.h2020.symbiote.core.internal.cram.ResourceUrlsResponse;
import eu.h2020.symbiote.jsonld.JsonLDObjectMapper;
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.jena.ext.com.google.common.collect.Maps;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

/**
 *
 * @author Michael Jacoby <michael.jacoby@iosb.fraunhofer.de>
 */
public class SemanticRAPClientTest {

    private static final String PLATFORM_ID_A = "platformA";
    private static final String PLATFORM_ID_B = "platformB";
    private static final String RESOURCE_ID_SERVICE_A = "serviceA";
    private static final String RESOURCE_ID_SERVICE_B = "serviceB";
    private static final String PIM_ID_MODEL_A = "5bf272564f5ab04324f1ca68";
    private static final String PIM_ID_MODEL_B = "pimB";
    private static final String coreAddress = "https://symbiote-dev.man.poznan.pl";

    private FeignRAPClient semanticRAPClient;
    private FeignSearchClient searchClient;
    private FeignCRAMClient cramClient;

    @Before
    public void initialize() {
        AbstractSymbIoTeClientFactory factory;
        try {
            factory = getFactory(new AbstractSymbIoTeClientFactory.Config(coreAddress, "testKeystore.jks", "testKeystore", AbstractSymbIoTeClientFactory.Type.FEIGN));
        } catch (SecurityHandlerException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return;
        }
        this.searchClient = spy((FeignSearchClient) factory.getSearchClient());
        this.cramClient = spy((FeignCRAMClient) factory.getCramClient());
        this.semanticRAPClient = spy((FeignRAPClient) factory.getSemanticRapClient());
        this.semanticRAPClient.setRapClient(semanticRAPClient);
        semanticRAPClient.setCramClient(cramClient);
        semanticRAPClient.setSearchClient(searchClient);
    }

    private FeignSearchClient.SearchI getMockSearchClient() throws IllegalAccessException, NoSuchFieldException {
        Field searchClientField = searchClient.getClass().getDeclaredField("searchClient");
        searchClientField.setAccessible(true);
        FeignSearchClient.SearchI mockedClient = mock(FeignSearchClient.SearchI.class, Mockito.RETURNS_DEEP_STUBS);
        searchClientField.set(searchClient, mockedClient);
        return mockedClient;
    }

    private FeignRAPClient.RAPI getRAPMockForResource(String resourceId) throws SecurityHandlerException {
        FeignRAPClient.RAPI result = mock(FeignRAPClient.RAPI.class, Mockito.RETURNS_DEEP_STUBS);
        doReturn(new ResourceUrlsResponse(200, "ok", Maps.newHashMap(ImmutableMap.of(resourceId, resourceId))))
                .when(cramClient).getResourceUrl(eq(resourceId), anyBoolean(), anySet());

        doReturn(result).
                when(semanticRAPClient).getClient(eq(resourceId));
        return result;
    }

    private void mockService(String resourceId, String result) throws SecurityHandlerException {

        when(getRAPMockForResource(resourceId).invokeService(anyString(), anyBoolean(), anySet())).thenReturn(result);
    }

    private SparqlQueryResponse singleValueSparqlResponse(String value) {
        return new SparqlQueryResponse(200, null,
                "{\n"
                + "  \"head\": {\n"
                + "    \"vars\": [ \"id\" ]\n"
                + "  } ,\n"
                + "  \"results\": {\n"
                + "    \"bindings\": [\n"
                + "      {\n"
                + "        \"id\": { \"type\": \"literal\" , \"value\": \"" + value + "\" }\n"
                + "      }\n"
                + "    ]\n"
                + "  }\n"
                + "}");
    }

    private void mockSparqlSingleResult(String query, String result) {
        doReturn(singleValueSparqlResponse(result))
                .when(searchClient).searchAsGuest(
                argThat((SparqlQueryRequest request) -> request.getSparqlQuery().equalsIgnoreCase(query)),
                anyBoolean());
    }

    private String readResourceFile(String filename) throws UnsupportedEncodingException {

        return new BufferedReader(new InputStreamReader(
                ClassLoader.getSystemClassLoader().getResourceAsStream("semanticmapping/" + filename), "UTF-8"))
                .lines()
                .parallel()
                .collect(Collectors.joining());
    }

    @Test
    public void testSemanticMappingOnDevMockingOnlyRAP() throws NoSuchFieldException, IllegalAccessException, SecurityHandlerException, JsonProcessingException, UnsupportedEncodingException {
        PersonA personA = new PersonA("Foo");
        PersonB personB = new PersonB("Foo");
        String personAJson = new JsonLDObjectMapper().writeValueAsString(personA);
        String personBJson = new JsonLDObjectMapper().writeValueAsString(personB);
        // mock RAP
        mockService(RESOURCE_ID_SERVICE_A, personAJson);
        mockService(RESOURCE_ID_SERVICE_B, personBJson);
        // mock PIM ID by resource ID
        mockSparqlSingleResult(SparqlQueries.getPIMIdByResourceId(RESOURCE_ID_SERVICE_A), PIM_ID_MODEL_A);
        mockSparqlSingleResult(SparqlQueries.getPIMIdByResourceId(RESOURCE_ID_SERVICE_B), PIM_ID_MODEL_B);
        // mock PIM ID by platform ID
        mockSparqlSingleResult(SparqlQueries.getPIMIdByPlatformId(PLATFORM_ID_A), PIM_ID_MODEL_A);
        mockSparqlSingleResult(SparqlQueries.getPIMIdByPlatformId(PLATFORM_ID_B), PIM_ID_MODEL_B);       
        // mock PIM ID by types
        mockSparqlSingleResult(SparqlQueries.getPIMIdByTypes(Sets.newHashSet(ModelA.Person)), PIM_ID_MODEL_A);
        mockSparqlSingleResult(SparqlQueries.getPIMIdByTypes(Sets.newHashSet(ModelB.Person)), PIM_ID_MODEL_B);
        
        // mock mapping
        mockSparqlSingleResult(SparqlQueries.getMapping(PIM_ID_MODEL_A, PIM_ID_MODEL_B), readResourceFile("AtoB.map"));
        mockSparqlSingleResult(SparqlQueries.getMapping(PIM_ID_MODEL_B, PIM_ID_MODEL_A), readResourceFile("BtoA.map"));
        // call service B with A data
        PersonA serviceResultA = semanticRAPClient.invokeServiceWithMapping(RESOURCE_ID_SERVICE_B, personA, PersonA.class, true, Sets.newHashSet(PLATFORM_ID_A));
        assertEquals(personA, serviceResultA);
        // call service A with B data        
        PersonB serviceResultB = semanticRAPClient.invokeServiceWithMapping(RESOURCE_ID_SERVICE_A, personB, PersonB.class, true, Sets.newHashSet(PLATFORM_ID_B));
        assertEquals(personB, serviceResultB);

    }
}
