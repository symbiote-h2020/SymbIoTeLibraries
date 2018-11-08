package eu.h2020.symbiote.unit.enabler.messaging.model;

import eu.h2020.symbiote.core.ci.QueryResourceResult;
import eu.h2020.symbiote.core.ci.QueryResponse;
import eu.h2020.symbiote.core.ci.SparqlQueryOutputFormat;
import eu.h2020.symbiote.core.ci.SparqlQueryRequest;
import eu.h2020.symbiote.core.internal.CoreQueryRequest;
import eu.h2020.symbiote.enabler.messaging.model.ResourceManagerTaskInfoRequest;
import eu.h2020.symbiote.enabler.messaging.model.ResourceManagerTaskInfoResponse;
import eu.h2020.symbiote.enabler.messaging.model.ResourceManagerTaskInfoResponseStatus;
import eu.h2020.symbiote.security.communication.payloads.SecurityRequest;
import eu.h2020.symbiote.util.IntervalFormatter;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by vasgl on 7/27/2017.
 */
public class ResourceManagerTaskInfoResponseTests {
    
    @Test
    public void constructorTest1() {

        CoreQueryRequest coreQueryRequest = new CoreQueryRequest.Builder()
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

        ResourceManagerTaskInfoRequest request = new ResourceManagerTaskInfoRequest("1", 5, coreQueryRequest, "P0-0-0T0:0:0.01",
                true, "P0-0-0T0:0:0.01", true, "enablerLogic", null);

        ResourceManagerTaskInfoResponse response = new ResourceManagerTaskInfoResponse(request);

        assertEquals(request.getTaskId(), response.getTaskId());
        assertEquals(request.getMinNoResources(), response.getMinNoResources());
        assertEquals(request.getMaxNoResources(), response.getMaxNoResources());
        assertEquals(true, response.getCoreQueryRequest().equals(request.getCoreQueryRequest()));
        assertEquals(request.getQueryInterval(), response.getQueryInterval());
        assertEquals(request.getAllowCaching(), response.getAllowCaching());
        assertEquals(request.getCachingInterval(), response.getCachingInterval());
        assertEquals(request.getInformPlatformProxy(), response.getInformPlatformProxy());
        assertEquals(request.getEnablerLogicName(), response.getEnablerLogicName());

        response.getCoreQueryRequest().setObserved_property(Arrays.asList("p1", "p2", "p3"));
        response.setAllowCaching(false);
        response.setEnablerLogicName("test");
        try {
            response.setCachingInterval("P0-0-0T0:0:0.1");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        try {
            response.setQueryInterval("P0-0-0T0:0:0.1");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        assertEquals(2, request.getCoreQueryRequest().getObserved_property().size());
        assertEquals(true, request.getAllowCaching());
        assertEquals(10, (long) new IntervalFormatter(request.getCachingInterval()).getMillis());
        assertEquals(10, (long) new IntervalFormatter(request.getQueryInterval()).getMillis());
        assertEquals("enablerLogic", request.getEnablerLogicName());

        assertEquals(3, response.getCoreQueryRequest().getObserved_property().size());
        assertEquals(false, response.getAllowCaching());
        assertEquals(100, (long) new IntervalFormatter(response.getCachingInterval()).getMillis());
        assertEquals(100, (long) new IntervalFormatter(response.getQueryInterval()).getMillis());
        assertEquals("test", response.getEnablerLogicName());
        assertEquals(0, response.getResourceIds().size());
        assertEquals(ResourceManagerTaskInfoResponseStatus.UNKNOWN, response.getStatus());

    }

    @Test
    public void constructorTest2() {

        CoreQueryRequest coreQueryRequest = new CoreQueryRequest.Builder()
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

        ResourceManagerTaskInfoResponse response1 = new ResourceManagerTaskInfoResponse(
                "1", 5, 10, coreQueryRequest,
                "P0-0-0T0:0:0.01", true, "P0-0-0T0:0:0.01", true,
                "enablerLogic", new SparqlQueryRequest(), new ArrayList<>(), new HashMap<>(),
                new ArrayList<>(), ResourceManagerTaskInfoResponseStatus.SUCCESS, "success");

        ResourceManagerTaskInfoResponse response2 = new ResourceManagerTaskInfoResponse(response1);

        assertEquals(response1.getTaskId(), response2.getTaskId());
        assertEquals(response1.getMinNoResources(), response2.getMinNoResources());
        assertEquals(true, response2.getCoreQueryRequest().equals(response1.getCoreQueryRequest()));
        assertEquals(true, response2.getSparqlQueryRequest().equals(response1.getSparqlQueryRequest()));
        assertEquals(response1.getQueryInterval(), response2.getQueryInterval());
        assertEquals(response1.getAllowCaching(), response2.getAllowCaching());
        assertEquals(response1.getCachingInterval(), response2.getCachingInterval());
        assertEquals(response1.getInformPlatformProxy(), response2.getInformPlatformProxy());
        assertEquals(response1.getEnablerLogicName(), response2.getEnablerLogicName());
        assertEquals(response1.getMessage(), response2.getMessage());
        assertEquals(response1.getResourceIds(), response2.getResourceIds());
        assertEquals(response1.getResourceDescriptions(), response2.getResourceDescriptions());
        assertEquals(response1.getStatus(), response2.getStatus());

        response2.getCoreQueryRequest().setObserved_property(Arrays.asList("p1", "p2", "p3"));
        response2.setAllowCaching(false);
        response2.setEnablerLogicName("test");
        try {
            response2.setCachingInterval("P0-0-0T0:0:0.1");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        try {
            response2.setQueryInterval("P0-0-0T0:0:0.1");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        assertEquals(2, response1.getCoreQueryRequest().getObserved_property().size());
        assertEquals(true, response1.getAllowCaching());
        assertEquals(10, (long) new IntervalFormatter(response1.getCachingInterval()).getMillis());
        assertEquals(10, (long) new IntervalFormatter(response1.getQueryInterval()).getMillis());
        assertEquals("enablerLogic", response1.getEnablerLogicName());

        assertEquals(3, response2.getCoreQueryRequest().getObserved_property().size());
        assertEquals(false, response2.getAllowCaching());
        assertEquals(100, (long) new IntervalFormatter(response2.getCachingInterval()).getMillis());
        assertEquals(100, (long) new IntervalFormatter(response2.getQueryInterval()).getMillis());
        assertEquals("test", response2.getEnablerLogicName());
    }

    @Test
    public void testEquals() {
        CoreQueryRequest coreQueryRequest = new CoreQueryRequest.Builder()
                .locationName("Zurich")
                .observedProperty(Arrays.asList("temperature", "humidity"))
                .build();

        ArrayList<String> resourceIds = new ArrayList<>();
        resourceIds.add("1");
        resourceIds.add("2");

        HashMap<String, String> resourceUrls = new HashMap<>();
        resourceUrls.put("1", "1.com");
        resourceUrls.put("2", "2.com");

        ArrayList<QueryResourceResult> resourceDescriptions = new ArrayList<>();
        QueryResourceResult result1 = new QueryResourceResult();
        result1.setId("1");
        QueryResourceResult result2 = new QueryResourceResult();
        result2.setId("2");
        resourceDescriptions.add(result1);
        resourceDescriptions.add(result2);

        SparqlQueryRequest sparqlQueryRequest = new SparqlQueryRequest("response1",
                SparqlQueryOutputFormat.COUNT,null);
        
        ResourceManagerTaskInfoResponse response1 = new ResourceManagerTaskInfoResponse("1", 2, 10,
                coreQueryRequest,"P0-0-0T0:0:0.06", true, "P0-0-0T0:0:1",
                true, "TestEnablerLogic", sparqlQueryRequest, resourceIds, resourceUrls,
                resourceDescriptions, ResourceManagerTaskInfoResponseStatus.SUCCESS, "success");
        response1.setMaxNoResources(6);

        ResourceManagerTaskInfoResponse response2 = new ResourceManagerTaskInfoResponse(response1);
        assertEquals(response1, response2);

        response2.setTaskId("2");
        assertEquals("1", response1.getTaskId());
        assertNotEquals(response1, response2);
        response2.setTaskId(response1.getTaskId());
        assertEquals(response1, response2);

        response2.setMinNoResources(5);
        assertEquals(2, (int) response1.getMinNoResources());
        assertNotEquals(response1, response2);
        response2.setMinNoResources(response1.getMinNoResources());
        assertEquals(response1, response2);

        response2.getCoreQueryRequest().setLocation_name("Athens");
        assertEquals("Zurich", response1.getCoreQueryRequest().getLocation_name());
        assertNotEquals(response1, response2);
        response2.getCoreQueryRequest().setLocation_name("Zurich");
        assertEquals(response1, response2);

        response2.setQueryInterval("P0-0-0T0:0:0.07");
        assertEquals(60, (long) new IntervalFormatter(response1.getQueryInterval()).getMillis());
        assertNotEquals(response1, response2);
        response2.setQueryInterval(response1.getQueryInterval());
        assertEquals(response1, response2);

        response2.setAllowCaching(false);
        assertEquals(true, response1.getAllowCaching());
        assertNotEquals(response1, response2);
        response2.setAllowCaching(response1.getAllowCaching());
        assertEquals(response1, response2);

        response2.setCachingInterval("P0-0-0T0:0:0.07");
        assertEquals(1000, (long) new IntervalFormatter(response1.getCachingInterval()).getMillis());
        assertNotEquals(response1, response2);
        response2.setCachingInterval(response1.getCachingInterval());
        assertEquals(response1, response2);

        response2.setInformPlatformProxy(false);
        assertEquals(true, response1.getInformPlatformProxy());
        assertNotEquals(response1, response2);
        response2.setInformPlatformProxy(response1.getInformPlatformProxy());
        assertEquals(response1, response2);

        response2.setEnablerLogicName("Test");
        assertEquals("TestEnablerLogic", response1.getEnablerLogicName());
        assertNotEquals(response1, response2);
        response2.setEnablerLogicName(response1.getEnablerLogicName());
        assertEquals(response1, response2);

        response2.getSparqlQueryRequest().setSparqlQuery("response2");
        assertEquals("response1", response1.getSparqlQueryRequest().getSparqlQuery());
        assertNotEquals(response1, response2);
        response2.getSparqlQueryRequest().setSparqlQuery(response1.getSparqlQueryRequest().getSparqlQuery());
        assertEquals(response1, response2);

        response2.getSparqlQueryRequest().setOutputFormat(SparqlQueryOutputFormat.CSV);
        assertEquals(SparqlQueryOutputFormat.COUNT, response1.getSparqlQueryRequest().getOutputFormat());
        assertNotEquals(response1, response2);
        response2.getSparqlQueryRequest().setOutputFormat(response1.getSparqlQueryRequest().getOutputFormat());
        assertEquals(response1, response2);

        response2.getResourceIds().set(0, "3");
        assertEquals(2, response1.getResourceIds().size());
        assertNotEquals(response1, response2);
        response2.getResourceIds().set(0, "1");
        assertEquals(response1, response2);

        response2.getResourceUrls().put("1", "1.eu");
        assertEquals(2, response1.getResourceUrls().size());
        assertNotEquals(response1, response2);
        response2.getResourceUrls().put("1", "1.com");
        assertEquals(response1, response2);

        response2.getResourceDescriptions().get(0).setId("3");
        assertEquals(2, response1.getResourceIds().size());
        assertNotEquals(response1, response2);
        response2.getResourceDescriptions().get(0).setId("1");
        assertEquals(response1, response2);

        response2.setStatus(ResourceManagerTaskInfoResponseStatus.FAILED);
        assertEquals(ResourceManagerTaskInfoResponseStatus.SUCCESS, response1.getStatus());
        assertNotEquals(response1, response2);
        response2.setStatus(response1.getStatus());
        assertEquals(response1, response2);

        response2.setMessage("failed");
        assertEquals("success", response1.getMessage());
        assertNotEquals(response1, response2);
        response2.setMessage(response1.getMessage());
        assertEquals(response1, response2);
    }
}
