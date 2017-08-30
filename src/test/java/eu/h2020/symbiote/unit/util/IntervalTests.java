package eu.h2020.symbiote.unit.util;

import eu.h2020.symbiote.util.IntervalFormatter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by vasgl on 6/29/2017.
 */
public class IntervalTests {


    @Test
    public void getMillisTest() {
        IntervalFormatter interval = new IntervalFormatter("P1-2-3T4:5:6.7");

        assertEquals(36993906700L, (long) interval.getMillis());
    }
}
