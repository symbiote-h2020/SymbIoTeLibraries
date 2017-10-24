package eu.h2020.symbiote.unit.core.model.resources;

import eu.h2020.symbiote.model.cim.Resource;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ResourceTest {

    @Test
    public void equals() throws Exception {
        Resource resource1 = new Resource();
        resource1.setId("id1");
        resource1.setName("label1");
        resource1.setDescription(Arrays.asList("comment1", "comment2"));
        resource1.setInterworkingServiceURL("resource1.com");

        Resource resource2 = new Resource();
        resource2.setId("id1");
        resource2.setName("label1");
        resource2.setDescription(Arrays.asList("comment1", "comment2"));
        resource2.setInterworkingServiceURL("resource1.com");

        Resource resource3 = new Resource();
        resource3.setId("id1");
        resource3.setName("label1");
        resource3.setDescription(Arrays.asList("comment1", "comment3"));
        resource3.setInterworkingServiceURL("resource1.com");

        assertEquals(true, resource1.equals(resource2));
        assertEquals(false, resource1.equals(resource3));
    }

}