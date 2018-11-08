package eu.h2020.symbiote.unit.enabler.messaging.model;

import eu.h2020.symbiote.core.ci.SparqlQueryOutputFormat;
import eu.h2020.symbiote.core.ci.SparqlQueryRequest;
import eu.h2020.symbiote.core.internal.CoreQueryRequest;
import eu.h2020.symbiote.enabler.messaging.model.ResourceManagerTaskInfoRequest;
import eu.h2020.symbiote.util.IntervalFormatter;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by vasgl on 7/27/2017.
 */
public class ResourceManagerTaskInfoRequestTests {
    
    @Test
    public void testEquals() {
        CoreQueryRequest coreQueryRequest = new CoreQueryRequest.Builder()
                .locationName("Zurich")
                .observedProperty(Arrays.asList("temperature", "humidity"))
                .build();

        SparqlQueryRequest sparqlQueryRequest = new SparqlQueryRequest("request1",
                SparqlQueryOutputFormat.COUNT,null);

        ResourceManagerTaskInfoRequest request1 = new ResourceManagerTaskInfoRequest("1", 2,
                coreQueryRequest,"P0-0-0T0:0:0.06", true, "P0-0-0T0:0:1",
                true, "TestEnablerLogic", sparqlQueryRequest);
        request1.setMaxNoResources(6);
        ResourceManagerTaskInfoRequest request2 = new ResourceManagerTaskInfoRequest(request1);
        assertEquals(true, request1.equals(request2));

        request2.setTaskId("2");
        assertEquals("1", request1.getTaskId());
        assertEquals(false, request1.equals(request2));
        request2.setTaskId(request1.getTaskId());
        assertEquals(true, request1.equals(request2));

        request2.setMinNoResources(5);
        assertEquals(2, (int) request1.getMinNoResources());
        assertEquals(false, request1.equals(request2));
        request2.setMinNoResources(request1.getMinNoResources());
        assertEquals(true, request1.equals(request2));

        request2.setMaxNoResources(5);
        assertEquals(6, (int) request1.getMaxNoResources());
        assertEquals(false, request1.equals(request2));
        request2.setMaxNoResources(request1.getMaxNoResources());
        assertEquals(true, request1.equals(request2));

        request2.getCoreQueryRequest().setLocation_name("Athens");
        assertEquals("Zurich", request1.getCoreQueryRequest().getLocation_name());
        assertEquals(false, request1.equals(request2));
        request2.getCoreQueryRequest().setLocation_name("Zurich");
        assertEquals(true, request1.equals(request2));

        request2.setQueryInterval("P0-0-0T0:0:0.07");
        assertEquals(60, (long) new IntervalFormatter(request1.getQueryInterval()).getMillis());
        assertEquals(false, request1.equals(request2));
        request2.setQueryInterval(request1.getQueryInterval());
        assertEquals(true, request1.equals(request2));

        request2.setAllowCaching(false);
        assertEquals(true, request1.getAllowCaching());
        assertEquals(false, request1.equals(request2));
        request2.setAllowCaching(request1.getAllowCaching());
        assertEquals(true, request1.equals(request2));

        request2.setCachingInterval("P0-0-0T0:0:0.07");
        assertEquals(1000, (long) new IntervalFormatter(request1.getCachingInterval()).getMillis());
        assertEquals(false, request1.equals(request2));
        request2.setCachingInterval(request1.getCachingInterval());
        assertEquals(true, request1.equals(request2));

        request2.setInformPlatformProxy(false);
        assertEquals(true, request1.getInformPlatformProxy());
        assertEquals(false, request1.equals(request2));
        request2.setInformPlatformProxy(request1.getInformPlatformProxy());
        assertEquals(true, request1.equals(request2));

        request2.setEnablerLogicName("Test");
        assertEquals("TestEnablerLogic", request1.getEnablerLogicName());
        assertEquals(false, request1.equals(request2));
        request2.setEnablerLogicName(request1.getEnablerLogicName());
        assertEquals(true, request1.equals(request2));

        request2.getSparqlQueryRequest().setSparqlQuery("request2");
        assertEquals("request1", request1.getSparqlQueryRequest().getSparqlQuery());
        assertEquals(false, request1.equals(request2));
        request2.getSparqlQueryRequest().setSparqlQuery(request1.getSparqlQueryRequest().getSparqlQuery());
        assertEquals(true, request1.equals(request2));

        request2.getSparqlQueryRequest().setOutputFormat(SparqlQueryOutputFormat.CSV);
        assertEquals(SparqlQueryOutputFormat.COUNT, request1.getSparqlQueryRequest().getOutputFormat());
        assertEquals(false, request1.equals(request2));
        request2.getSparqlQueryRequest().setOutputFormat(request1.getSparqlQueryRequest().getOutputFormat());
        assertEquals(true, request1.equals(request2));
    }
}
