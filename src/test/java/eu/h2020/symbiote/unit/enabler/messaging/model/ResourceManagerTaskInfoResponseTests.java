package eu.h2020.symbiote.unit.enabler.messaging.model;

import eu.h2020.symbiote.core.internal.CoreQueryRequest;
import eu.h2020.symbiote.enabler.messaging.model.ResourceManagerTaskInfoRequest;
import eu.h2020.symbiote.enabler.messaging.model.ResourceManagerTaskInfoResponse;
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
        request.setQueryInterval_ms(10);
        request.setAllowCaching(true);
        request.setCachingInterval_ms(new Long(10));
        request.setInformPlatformProxy(true);
        request.setEnablerLogicName("enablerLogic");

        ResourceManagerTaskInfoResponse response = new ResourceManagerTaskInfoResponse(request);
        
        assertEquals(request.getTaskId(), response.getTaskId());
        assertEquals(request.getMinNoResources(), response.getMinNoResources());
        assertEquals(true, response.getCoreQueryRequest().equals(request.getCoreQueryRequest()));
        assertEquals(request.getQueryInterval_ms(), response.getQueryInterval_ms());
        assertEquals(request.getAllowCaching(), response.getAllowCaching());
        assertEquals(request.getCachingInterval_ms(), response.getCachingInterval_ms());
        assertEquals(request.getInformPlatformProxy(), response.getInformPlatformProxy());
        assertEquals(request.getEnablerLogicName(), response.getEnablerLogicName());

        response.getCoreQueryRequest().setObserved_property(Arrays.asList("p1", "p2", "p3"));
        response.setAllowCaching(false);
        response.setCachingInterval_ms(new Long(100));
        response.setQueryInterval_ms(100);
        response.setEnablerLogicName("test");

        assertEquals(2, request.getCoreQueryRequest().getObserved_property().size());
        assertEquals(true, request.getAllowCaching());
        assertEquals(new Long(10), request.getCachingInterval_ms());
        assertEquals(10, (int) request.getQueryInterval_ms());
        assertEquals("enablerLogic", request.getEnablerLogicName());

        assertEquals(3, response.getCoreQueryRequest().getObserved_property().size());
        assertEquals(false, response.getAllowCaching());
        assertEquals(new Long(100), response.getCachingInterval_ms());
        assertEquals(100, (int) response.getQueryInterval_ms());
        assertEquals("test", response.getEnablerLogicName());

    }
}
