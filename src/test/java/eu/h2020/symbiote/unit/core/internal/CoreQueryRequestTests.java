package eu.h2020.symbiote.unit.core.internal;

import eu.h2020.symbiote.core.internal.CoreQueryRequest;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by vasgl on 7/21/2017.
 */
public class CoreQueryRequestTests {

    private static Logger log = LoggerFactory
            .getLogger(CoreQueryRequestTests.class);

    @Test
    public void buildQueryTest() {
        CoreQueryRequest coreQueryRequest = new CoreQueryRequest.Builder()
                .build();

        String symbioteUrl = "http://example.com";
        String expectedAnswer = symbioteUrl + "/query?";
        assertEquals(expectedAnswer, coreQueryRequest.buildQuery(symbioteUrl));

        coreQueryRequest = new CoreQueryRequest.Builder()
                .platformId("pId")
                .platformName("pName")
                .owner("owner")
                .name("name")
                .id("id")
                .description("desc")
                .locationName("lName")
                .locationLat(123.0)
                .locationLong(456.0)
                .maxDistance(10)
                .observedProperty(Arrays.asList("p1 2", "p2"))
                .observedPropertyIri(Arrays.asList("http://test1#test", "http://test2#test"))
                .resourceType("type")
                .shouldRank(Boolean.FALSE)
                .build();

        expectedAnswer = symbioteUrl + "/query?platform_id=pId&platform_name=pName&owner=owner&name=name"
                + "&id=id&description=desc&location_name=lName&location_lat=123.0&location_long=456.0&max_distance=10"
                + "&observed_property=p1+2,p2&observed_property_iri=http%3A%2F%2Ftest1%23test,http%3A%2F%2Ftest2%23test"
                + "&resource_type=type&should_rank=false";

        String realURL = coreQueryRequest.buildQuery(symbioteUrl);
        log.info("coreQueryRequest.buildQuery(symbioteUrl) = " + realURL);
        assertEquals(expectedAnswer, coreQueryRequest.buildQuery(symbioteUrl));

        coreQueryRequest = new CoreQueryRequest.Builder()
                .name("name")
                .observedProperty(Arrays.asList("p1 ", "p2"))
                .shouldRank(Boolean.FALSE)
                .build();

        expectedAnswer = symbioteUrl + "/query?name=name&observed_property=p1+,p2&should_rank=false";
        assertEquals(expectedAnswer, coreQueryRequest.buildQuery(symbioteUrl));

        coreQueryRequest = new CoreQueryRequest.Builder()
                .observedProperty(Arrays.asList("p1", "p2"))
                .shouldRank(Boolean.TRUE)
                .build();

        expectedAnswer = symbioteUrl + "/query?observed_property=p1,p2&should_rank=true";
        assertEquals(expectedAnswer, coreQueryRequest.buildQuery(symbioteUrl));

        coreQueryRequest = new CoreQueryRequest.Builder()
                .shouldRank(Boolean.FALSE)
                .build();

        expectedAnswer = symbioteUrl + "/query?should_rank=false";
        assertEquals(expectedAnswer, coreQueryRequest.buildQuery(symbioteUrl));
    }

    @Test
    public void equalsTest() {
        CoreQueryRequest coreQueryRequest1 = new CoreQueryRequest.Builder()
                .platformId("pId")
                .platformName("pName")
                .owner("owner")
                .name("name")
                .id("id")
                .description("desc")
                .locationName("lName")
                .locationLat(123.0)
                .locationLong(456.0)
                .maxDistance(10)
                .observedProperty(Arrays.asList("p1", "p2"))
                .observedPropertyIri(Arrays.asList("http://test1#test", "http://test2#test"))
                .resourceType("type")
                .securityRequest(new SecurityRequest("token"))
                .build();

        CoreQueryRequest coreQueryRequest2 = new CoreQueryRequest.Builder()
                .platformId("pId")
                .platformName("pName")
                .owner("owner")
                .name("name")
                .id("id")
                .description("desc")
                .locationName("lName")
                .locationLat(123.0)
                .locationLong(456.0)
                .maxDistance(10)
                .observedProperty(Arrays.asList("p1", "p2"))
                .observedPropertyIri(Arrays.asList("http://test1#test", "http://test2#test"))
                .resourceType("type")
                .securityRequest(new SecurityRequest("token"))
                .build();

        CoreQueryRequest coreQueryRequest3 = new CoreQueryRequest.Builder()
                .platformId("pId")
                .platformName("pName")
                .owner("owner")
                .name("name")
                .id("id")
                .description("desc")
                .locationName("lName")
                .locationLat(123.0)
                .locationLong(456.0)
                .maxDistance(10)
                .observedProperty(Arrays.asList("p1", "p2", "p3"))
                .resourceType("type")
                .securityRequest(new SecurityRequest("token"))
                .build();

        assertEquals(true, coreQueryRequest1.equals(coreQueryRequest1));
        assertEquals(true, coreQueryRequest1.equals(coreQueryRequest2));
        assertEquals(false, coreQueryRequest1.equals(coreQueryRequest3));
    }

    @Test
    public void copyConstructorTest() {
        CoreQueryRequest coreQueryRequest1 = new CoreQueryRequest.Builder()
                .platformId("pId")
                .platformName("pName")
                .owner("owner")
                .name("name")
                .id("id")
                .description("desc")
                .locationName("lName")
                .locationLat(123.0)
                .locationLong(456.0)
                .maxDistance(10)
                .observedProperty(Arrays.asList("p1", "p2"))
                .resourceType("type")
                .securityRequest(new SecurityRequest("token"))
                .build();

        CoreQueryRequest coreQueryRequest2 = CoreQueryRequest.newInstance(coreQueryRequest1);
        assertEquals(true, coreQueryRequest1.equals(coreQueryRequest2));

        coreQueryRequest2.setObserved_property(Arrays.asList("p1", "p2", "p3"));
        coreQueryRequest2.setName("newName");
        coreQueryRequest2.setMax_distance(1000);

        assertEquals(2, coreQueryRequest1.getObserved_property().size());
        assertEquals("name", coreQueryRequest1.getName());
        assertEquals(10, (int) coreQueryRequest1.getMax_distance());

        assertEquals(3, coreQueryRequest2.getObserved_property().size());
        assertEquals("newName", coreQueryRequest2.getName());
        assertEquals(1000, (int) coreQueryRequest2.getMax_distance());

        assertEquals(false, coreQueryRequest1.equals(coreQueryRequest2));


    }
}