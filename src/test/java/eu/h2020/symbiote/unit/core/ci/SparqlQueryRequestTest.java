package eu.h2020.symbiote.unit.core.ci;

import eu.h2020.symbiote.core.ci.SparqlQueryOutputFormat;
import eu.h2020.symbiote.core.ci.SparqlQueryRequest;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vasgl on 8/22/2017.
 */
public class SparqlQueryRequestTest {

    @Test
    public void isValid() throws Exception {
        SparqlQueryRequest request1 = new SparqlQueryRequest("request1", SparqlQueryOutputFormat.COUNT,null);
        assertEquals(true, request1.isValid());

        SparqlQueryRequest request2 = new SparqlQueryRequest(null, SparqlQueryOutputFormat.COUNT,null);
        assertEquals(false, request2.isValid());

        SparqlQueryRequest request3 = new SparqlQueryRequest("request3", null,null);
        assertEquals(false, request3.isValid());

        SparqlQueryRequest request4 = new SparqlQueryRequest();
        assertEquals(false, request4.isValid());
    }

    @Test
    public void equals() throws Exception {
        SparqlQueryRequest request1 = new SparqlQueryRequest("request1", SparqlQueryOutputFormat.COUNT, null);
        SparqlQueryRequest request2 = new SparqlQueryRequest(request1);

        assertEquals(true, request1.equals(request2));

        request2.setSparqlQuery("request2");
        assertEquals("request1", request1.getSparqlQuery());
        assertEquals(false, request1.equals(request2));
        request2.setSparqlQuery(request1.getSparqlQuery());
        assertEquals(true, request1.equals(request2));

        request2.setOutputFormat(SparqlQueryOutputFormat.CSV);
        assertEquals(SparqlQueryOutputFormat.COUNT, request1.getOutputFormat());
        assertEquals(false, request1.equals(request2));
        request2.setOutputFormat(request1.getOutputFormat());
        assertEquals(true, request1.equals(request2));
    }

}