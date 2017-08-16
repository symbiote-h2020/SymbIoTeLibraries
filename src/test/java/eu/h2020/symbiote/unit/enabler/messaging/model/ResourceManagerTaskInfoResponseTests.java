package eu.h2020.symbiote.unit.enabler.messaging.model;

import eu.h2020.symbiote.core.internal.CoreQueryRequest;
import eu.h2020.symbiote.enabler.messaging.model.ResourceManagerResponseStatus;
import eu.h2020.symbiote.enabler.messaging.model.ResourceManagerTaskInfoRequest;
import eu.h2020.symbiote.enabler.messaging.model.ResourceManagerTaskInfoResponse;
import eu.h2020.symbiote.util.IntervalFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by vasgl on 7/27/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
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
                .token("token")
                .build();

        ResourceManagerTaskInfoRequest request = new ResourceManagerTaskInfoRequest();
        request.setTaskId("1");
        request.setMinNoResources(5);
        request.setCoreQueryRequest(coreQueryRequest);
        request.setAllowCaching(true);
        request.setInformPlatformProxy(true);
        request.setEnablerLogicName("enablerLogic");

        try {
            request.setQueryInterval("P0-0-0T0:0:0.01");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        try {
            request.setCachingInterval("P0-0-0T0:0:0.01");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        ResourceManagerTaskInfoResponse response = new ResourceManagerTaskInfoResponse(request);
        
        assertEquals(request.getTaskId(), response.getTaskId());
        assertEquals(request.getMinNoResources(), response.getMinNoResources());
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
        assertEquals(ResourceManagerResponseStatus.UNKNOWN, response.getStatus());

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
                .token("token")
                .build();

        ResourceManagerTaskInfoResponse response1 = new ResourceManagerTaskInfoResponse();
        response1.setTaskId("1");
        response1.setMinNoResources(5);
        response1.setCoreQueryRequest(coreQueryRequest);
        response1.setAllowCaching(true);
        response1.setInformPlatformProxy(true);
        response1.setEnablerLogicName("enablerLogic");
        response1.setStatus(ResourceManagerResponseStatus.SUCCESS);
        response1.setResourceIds(new ArrayList<>());
        try {
            response1.setQueryInterval("P0-0-0T0:0:0.01");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        try {
            response1.setCachingInterval("P0-0-0T0:0:0.01");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        ResourceManagerTaskInfoResponse response2 = new ResourceManagerTaskInfoResponse(response1);

        assertEquals(response1.getTaskId(), response2.getTaskId());
        assertEquals(response1.getMinNoResources(), response2.getMinNoResources());
        assertEquals(true, response2.getCoreQueryRequest().equals(response1.getCoreQueryRequest()));
        assertEquals(response1.getQueryInterval(), response2.getQueryInterval());
        assertEquals(response1.getAllowCaching(), response2.getAllowCaching());
        assertEquals(response1.getCachingInterval(), response2.getCachingInterval());
        assertEquals(response1.getInformPlatformProxy(), response2.getInformPlatformProxy());
        assertEquals(response1.getEnablerLogicName(), response2.getEnablerLogicName());

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
}
