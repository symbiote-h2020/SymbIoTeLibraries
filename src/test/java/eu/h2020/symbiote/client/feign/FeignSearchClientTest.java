package eu.h2020.symbiote.client.feign;

import eu.h2020.symbiote.client.feign.FeignSearchClient.SearchI;
import eu.h2020.symbiote.core.ci.SparqlQueryRequest;
import eu.h2020.symbiote.core.ci.SparqlQueryResponse;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class FeignSearchClientTest {

    private static final String coreAddress = "https://test.com";
    private FeignSearchClient feignSearchClient;

    @Before
    public void initialize() {
        this.feignSearchClient = new FeignSearchClient(null, coreAddress);
    }

    @Test
    public void searchAsGuestSparql() throws NoSuchFieldException, IllegalAccessException {

        // Use Reflection to assign a mock Search client
        Field searchClientField = feignSearchClient.getClass().getDeclaredField("searchClient");
        searchClientField.setAccessible(true);

        // Mock the searchClient Field of feignSearchClient
        SearchI mockedClient = mock(SearchI.class);
        searchClientField.set(feignSearchClient, mockedClient);

        SparqlQueryResponse mockedResponse = new SparqlQueryResponse(200, "message", "body");
        when(mockedClient.querySparql(any(), anyBoolean(), anySet())).thenReturn(mockedResponse);

        SparqlQueryResponse response = feignSearchClient.searchAsGuest(new SparqlQueryRequest(), false);
        assertEquals(mockedResponse.getStatus(), response.getStatus());
        assertEquals(mockedResponse.getMessage(), response.getMessage());
        assertEquals(mockedResponse.getBody(), response.getBody());
    }
}