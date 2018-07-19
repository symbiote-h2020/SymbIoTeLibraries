package eu.h2020.symbiote.unit.cloud.model.internal;

import eu.h2020.symbiote.cloud.model.internal.PlatformRegistryQuery;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by vasgl on 7/21/2017.
 */
public class PlatformRegistryQueryTests {

    private static Logger log = LoggerFactory
            .getLogger(PlatformRegistryQueryTests.class);

    @Test
    public void buildQueryTest() {
        PlatformRegistryQuery platformRegistryQuery = new PlatformRegistryQuery.Builder()
                .names(new ArrayList<>(Arrays.asList("name1", "name2")))
                .descriptions(new ArrayList<>(Arrays.asList("desc1", "desc2")))
                .ids(new ArrayList<>(Arrays.asList("id1", "id2")))
                .federationIds(new ArrayList<>(Arrays.asList("fed1", "fed2")))
                .observesProperties(new ArrayList<>(Arrays.asList("prop1", "prop2")))
                .resourceType("type")
                .locationNames(new ArrayList<>(Arrays.asList("loc1", "loc2")))
                .locationLat(1.0)
                .locationLong(2.0)
                .maxDistance(3.0)
                .resourceTrust(4.0)
                .adaptiveTrust(5.0)
                .sort("sort")
                .build();

        String platformUrl = "http://example.com";
        String expectedAnswer = platformUrl + "/pr/search?name=name1,name2&description=desc1,desc2&" +
                "id=id1,id2&federationId=fed1,fed2&observes_property=prop1,prop2&resource_type=type&" +
                "location_name=loc1,loc2&location_lat=1.0&location_long=2.0&max_distance=3.0&" +
                "resource_trust=4.0&adaptive_trust=5.0&sort=sort";

        String realURL = platformRegistryQuery.buildQuery(platformUrl);
        log.info("platformRegistryQuery.buildQuery(platformUrl) = " + realURL);
        assertEquals(expectedAnswer, platformRegistryQuery.buildQuery(platformUrl));

        platformRegistryQuery = new PlatformRegistryQuery.Builder()
                .resourceType("type")
                .locationNames(new ArrayList<>(Arrays.asList("loc1", "loc2")))
                .build();

        expectedAnswer = platformUrl + "/pr/search?resource_type=type&location_name=loc1,loc2";
        assertEquals(expectedAnswer, platformRegistryQuery.buildQuery(platformUrl));

        platformRegistryQuery = new PlatformRegistryQuery.Builder()
                .locationNames(new ArrayList<>(Arrays.asList("loc1", "loc2")))
                .sort("sort")
                .build();

        expectedAnswer = platformUrl + "/pr/search?location_name=loc1,loc2&sort=sort";
        assertEquals(expectedAnswer, platformRegistryQuery.buildQuery(platformUrl));

        platformRegistryQuery = new PlatformRegistryQuery.Builder()
                .sort("sort")
                .build();

        expectedAnswer = platformUrl + "/pr/search?sort=sort";
        assertEquals(expectedAnswer, platformRegistryQuery.buildQuery(platformUrl));
    }
}