package eu.h2020.symbiote.unit.enabler.messaging.model;

import eu.h2020.symbiote.core.internal.CoreQueryRequest;
import eu.h2020.symbiote.enabler.messaging.model.ResourceManagerTaskInfoRequest;
import eu.h2020.symbiote.enabler.messaging.model.ResourceManagerTaskInfoResponse;
import eu.h2020.symbiote.util.IntervalFormatter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by vasgl on 7/27/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ResourceManagerTaskInfoResponseTests {
    
    @Test
    public void constructorTest() {

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

    }
}
