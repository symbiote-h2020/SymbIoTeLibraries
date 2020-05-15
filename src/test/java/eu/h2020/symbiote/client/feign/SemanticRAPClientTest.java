package eu.h2020.symbiote.client.feign;

import static eu.h2020.symbiote.client.AbstractSymbIoTeClientFactory.getFactory;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.jena.ext.com.google.common.collect.Maps;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Sets;

import eu.h2020.symbiote.client.AbstractSymbIoTeClientFactory;
import eu.h2020.symbiote.client.SparqlQueries;
import eu.h2020.symbiote.client.interfaces.CRAMClient;
import eu.h2020.symbiote.client.interfaces.SearchClient;
import eu.h2020.symbiote.core.ci.SparqlQueryRequest;
import eu.h2020.symbiote.core.ci.SparqlQueryResponse;
import eu.h2020.symbiote.core.internal.cram.ResourceUrlsResponse;
import eu.h2020.symbiote.jsonld.JsonLDObjectMapper;
import eu.h2020.symbiote.security.commons.exceptions.custom.SecurityHandlerException;

/**
 *
 * @author Michael Jacoby <michael.jacoby@iosb.fraunhofer.de>
 */
public class SemanticRAPClientTest {

    private static final String CORE_ADDRESS = "https://symbiote-open.man.poznan.pl";
    private static final String MAPPING_A_TO_B_FILE = "AtoB.map";
    private static final String MAPPING_B_TO_A_FILE = "BtoA.map";
    private static final String PIM_ID_MODEL_A = "pimA";
    private static final String PIM_ID_MODEL_B = "pimB";

    private static final String PLATFORM_ID_A = "platformA";
    private static final String PLATFORM_ID_B = "platformB";
    private static final String RESOURCE_ID_RESOURCE_A = "resourceA";
    private static final String RESOURCE_ID_RESOURCE_B = "resourceB";
    private static final String RESOURCE_ID_SERVICE_A = "serviceA";
    private static final String RESOURCE_ID_SERVICE_B = "serviceB";

    @Before
    public void initialize() {

    }

    @Test
    public void testGetResourceWithMappingAsGuest_Success() throws JsonProcessingException, SecurityHandlerException, UnsupportedEncodingException {
        FeignRAPClient semanticRapClient = createSemanticRapClient();
        String name = UUID.randomUUID().toString();
        PersonA personA = new PersonA(name);
        PersonB personB = new PersonB(name);
        String personAJson = new JsonLDObjectMapper().writeValueAsString(personA);
        String personBJson = new JsonLDObjectMapper().writeValueAsString(personB);
        // mock RAP
        mockResource(semanticRapClient, RESOURCE_ID_RESOURCE_A, personAJson);
        mockResource(semanticRapClient, RESOURCE_ID_RESOURCE_B, personBJson);
        // mock PIM ID by resource ID
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByResourceId(RESOURCE_ID_RESOURCE_A), PIM_ID_MODEL_A);
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByResourceId(RESOURCE_ID_RESOURCE_B), PIM_ID_MODEL_B);
        // mock PIM ID by platform ID
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByPlatformId(PLATFORM_ID_A), PIM_ID_MODEL_A);
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByPlatformId(PLATFORM_ID_B), PIM_ID_MODEL_B);
        // mock PIM ID by types
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByTypes(Sets.newHashSet(ModelA.Person)), PIM_ID_MODEL_A);
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByTypes(Sets.newHashSet(ModelB.Person)), PIM_ID_MODEL_B);

        // mock mapping
        mockSparqlResult(semanticRapClient, SparqlQueries.getMapping(PIM_ID_MODEL_A, PIM_ID_MODEL_B), readResourceFile(MAPPING_A_TO_B_FILE));
        mockSparqlResult(semanticRapClient, SparqlQueries.getMapping(PIM_ID_MODEL_B, PIM_ID_MODEL_A), readResourceFile(MAPPING_B_TO_A_FILE));
        // call service B with A data
        PersonA resourceResultA = semanticRapClient.getResourceWithMappingAsGuest(RESOURCE_ID_RESOURCE_B, PersonA.class, true);
        assertEquals(personA, resourceResultA);
        // call service A with B data
        PersonB resourceResultB = semanticRapClient.getResourceWithMappingAsGuest(RESOURCE_ID_RESOURCE_A, PersonB.class, true);
        assertEquals(personB, resourceResultB);
    }

    @Test
    public void testGetResourceWithMapping_Success() throws JsonProcessingException, SecurityHandlerException, UnsupportedEncodingException {
        FeignRAPClient semanticRapClient = createSemanticRapClient();
        String name = UUID.randomUUID().toString();
        PersonA personA = new PersonA(name);
        PersonB personB = new PersonB(name);
        String personAJson = new JsonLDObjectMapper().writeValueAsString(personA);
        String personBJson = new JsonLDObjectMapper().writeValueAsString(personB);
        // mock RAP
        mockResource(semanticRapClient, RESOURCE_ID_RESOURCE_A, personAJson);
        mockResource(semanticRapClient, RESOURCE_ID_RESOURCE_B, personBJson);
        // mock PIM ID by resource ID
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByResourceId(RESOURCE_ID_RESOURCE_A), PIM_ID_MODEL_A);
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByResourceId(RESOURCE_ID_RESOURCE_B), PIM_ID_MODEL_B);
        // mock PIM ID by platform ID
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByPlatformId(PLATFORM_ID_A), PIM_ID_MODEL_A);
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByPlatformId(PLATFORM_ID_B), PIM_ID_MODEL_B);
        // mock PIM ID by types
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByTypes(Sets.newHashSet(ModelA.Person)), PIM_ID_MODEL_A);
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByTypes(Sets.newHashSet(ModelB.Person)), PIM_ID_MODEL_B);

        // mock mapping
        mockSparqlResult(semanticRapClient, SparqlQueries.getMapping(PIM_ID_MODEL_A, PIM_ID_MODEL_B), readResourceFile(MAPPING_A_TO_B_FILE));
        mockSparqlResult(semanticRapClient, SparqlQueries.getMapping(PIM_ID_MODEL_B, PIM_ID_MODEL_A), readResourceFile(MAPPING_B_TO_A_FILE));
        // call service B with A data
        PersonA resourceResultA = semanticRapClient.getResourceWithMapping(RESOURCE_ID_RESOURCE_B, PersonA.class, true, Sets.newHashSet(PLATFORM_ID_A));
        assertEquals(personA, resourceResultA);
        // call service A with B data
        PersonB resourceResultB = semanticRapClient.getResourceWithMapping(RESOURCE_ID_RESOURCE_A, PersonB.class, true, Sets.newHashSet(PLATFORM_ID_B));
        assertEquals(personB, resourceResultB);
    }

    @Test
    public void testInvokeServiceWithMappingAsGuest_Success() throws JsonProcessingException, SecurityHandlerException, UnsupportedEncodingException {
        FeignRAPClient semanticRapClient = createSemanticRapClient();
        String name = UUID.randomUUID().toString();
        PersonA personA = new PersonA(name);
        PersonB personB = new PersonB(name);
        String personAJson = new JsonLDObjectMapper().writeValueAsString(personA);
        String personBJson = new JsonLDObjectMapper().writeValueAsString(personB);
        // mock RAP
        mockService(semanticRapClient, RESOURCE_ID_SERVICE_A, personAJson);
        mockService(semanticRapClient, RESOURCE_ID_SERVICE_B, personBJson);
        // mock PIM ID by resource ID
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByResourceId(RESOURCE_ID_SERVICE_A), PIM_ID_MODEL_A);
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByResourceId(RESOURCE_ID_SERVICE_B), PIM_ID_MODEL_B);
        // mock PIM ID by platform ID
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByPlatformId(PLATFORM_ID_A), PIM_ID_MODEL_A);
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByPlatformId(PLATFORM_ID_B), PIM_ID_MODEL_B);
        // mock PIM ID by types
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByTypes(Sets.newHashSet(ModelA.Person)), PIM_ID_MODEL_A);
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByTypes(Sets.newHashSet(ModelB.Person)), PIM_ID_MODEL_B);

        // mock mapping
        mockSparqlResult(semanticRapClient, SparqlQueries.getMapping(PIM_ID_MODEL_A, PIM_ID_MODEL_B), readResourceFile(MAPPING_A_TO_B_FILE));
        mockSparqlResult(semanticRapClient, SparqlQueries.getMapping(PIM_ID_MODEL_B, PIM_ID_MODEL_A), readResourceFile(MAPPING_B_TO_A_FILE));
        // call service B with A data
        PersonA serviceResultA = semanticRapClient.invokeServiceWithMappingAsGuest(RESOURCE_ID_SERVICE_B, personA, PersonA.class, true);
        assertEquals(personA, serviceResultA);
        // call service A with B data
        PersonB serviceResultB = semanticRapClient.invokeServiceWithMappingAsGuest(RESOURCE_ID_SERVICE_A, personB, PersonB.class, true);
        assertEquals(personB, serviceResultB);
    }

    @Test
    public void testInvokeServiceWithMapping_Success() throws JsonProcessingException, SecurityHandlerException, UnsupportedEncodingException {
        FeignRAPClient semanticRapClient = createSemanticRapClient();
        String name = UUID.randomUUID().toString();
        PersonA personA = new PersonA(name);
        PersonB personB = new PersonB(name);
        String personAJson = new JsonLDObjectMapper().writeValueAsString(personA);
        String personBJson = new JsonLDObjectMapper().writeValueAsString(personB);
        // mock RAP
        mockService(semanticRapClient, RESOURCE_ID_SERVICE_A, personAJson);
        mockService(semanticRapClient, RESOURCE_ID_SERVICE_B, personBJson);
        // mock PIM ID by resource ID
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByResourceId(RESOURCE_ID_SERVICE_A), PIM_ID_MODEL_A);
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByResourceId(RESOURCE_ID_SERVICE_B), PIM_ID_MODEL_B);
        // mock PIM ID by platform ID
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByPlatformId(PLATFORM_ID_A), PIM_ID_MODEL_A);
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByPlatformId(PLATFORM_ID_B), PIM_ID_MODEL_B);
        // mock PIM ID by types
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByTypes(Sets.newHashSet(ModelA.Person)), PIM_ID_MODEL_A);
        mockSparqlResult(semanticRapClient, SparqlQueries.getPIMIdByTypes(Sets.newHashSet(ModelB.Person)), PIM_ID_MODEL_B);

        // mock mapping
        mockSparqlResult(semanticRapClient, SparqlQueries.getMapping(PIM_ID_MODEL_A, PIM_ID_MODEL_B), readResourceFile(MAPPING_A_TO_B_FILE));
        mockSparqlResult(semanticRapClient, SparqlQueries.getMapping(PIM_ID_MODEL_B, PIM_ID_MODEL_A), readResourceFile(MAPPING_B_TO_A_FILE));
        // call service B with A data
        PersonA serviceResultA = semanticRapClient.invokeServiceWithMapping(RESOURCE_ID_SERVICE_B, personA, PersonA.class, true, Sets.newHashSet(PLATFORM_ID_A));
        assertEquals(personA, serviceResultA);
        // call service A with B data
        PersonB serviceResultB = semanticRapClient.invokeServiceWithMapping(RESOURCE_ID_SERVICE_A, personB, PersonB.class, true, Sets.newHashSet(PLATFORM_ID_B));
        assertEquals(personB, serviceResultB);
    }

    private SparqlQueryResponse SparqlIdResponse(String... values) {
        return new SparqlQueryResponse(200, null,
                "{\n"
                + "  \"head\": {\n"
                + "    \"vars\": [ \"id\" ]\n"
                + "  } ,\n"
                + "  \"results\": {\n"
                + "    \"bindings\": [\n"
                + Stream.of(values).collect(Collectors.joining(
                        ",",
                        "      {\n        \"id\": { \"type\": \"literal\" , \"value\": \"",
                        "\" }\n      }\n"))
                + "    ]\n"
                + "  }\n"
                + "}");
    }

    private FeignRAPClient createSemanticRapClient() {
        AbstractSymbIoTeClientFactory factory;
        try {
            factory = getFactory(new AbstractSymbIoTeClientFactory.Config(CORE_ADDRESS, "testKeystore.jks", "testKeystore", AbstractSymbIoTeClientFactory.Type.FEIGN));
        } catch (SecurityHandlerException | NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        FeignRAPClient result = spy((FeignRAPClient) factory.getSemanticRapClient());;
        result.setRapClient(result);
        result.setCramClient(spy((FeignCRAMClient) factory.getCramClient()));
        result.setSearchClient(spy((FeignSearchClient) factory.getSearchClient()));
        return result;
    }

    private FeignSearchClient.SearchI getMockSearchClient(FeignRAPClient semanticRapClient) throws IllegalAccessException, NoSuchFieldException {
        Field searchClientField = semanticRapClient.getSearchClient().getClass().getDeclaredField("searchClient");
        searchClientField.setAccessible(true);
        FeignSearchClient.SearchI mockedClient = mock(FeignSearchClient.SearchI.class, Mockito.RETURNS_DEEP_STUBS);
        searchClientField.set(semanticRapClient.getSearchClient(), mockedClient);
        return mockedClient;
    }

    private FeignRAPClient.RAPI getRAPMockForResource(FeignRAPClient semanticRapClient, String resourceId) throws SecurityHandlerException {
        CRAMClient cramClient = semanticRapClient.getCramClient();
        FeignRAPClient.RAPI result = mock(FeignRAPClient.RAPI.class, Mockito.RETURNS_DEEP_STUBS);
        doReturn(new ResourceUrlsResponse(200, "ok", Maps.newHashMap(ImmutableMap.of(resourceId, resourceId))))
                .when(cramClient).getResourceUrl(eq(resourceId), anyBoolean(), anySet());
        doReturn(new ResourceUrlsResponse(200, "ok", Maps.newHashMap(ImmutableMap.of(resourceId, resourceId))))
                .when(cramClient).getResourceUrlAsGuest(eq(resourceId), anyBoolean());
        doReturn(result).
                when(semanticRapClient).getClient(eq(resourceId));
        return result;
    }

    private void mockResource(FeignRAPClient semanticRapClient, String resourceId, String result) throws SecurityHandlerException {
        when(getRAPMockForResource(semanticRapClient, resourceId).getResource(anyBoolean(), anySet())).thenReturn(result);
    }

    private void mockService(FeignRAPClient semanticRapClient, String resourceId, String result) throws SecurityHandlerException {
        when(getRAPMockForResource(semanticRapClient, resourceId).invokeService(anyString(), anyBoolean(), anySet())).thenReturn(result);
    }

    private void mockSparqlResult(FeignRAPClient semanticRapClient, String query, String... results) {
        SearchClient searchClient = semanticRapClient.getSearchClient();
        doReturn(SparqlIdResponse(results))
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

}
