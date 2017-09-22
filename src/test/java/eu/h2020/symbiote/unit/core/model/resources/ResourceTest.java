package eu.h2020.symbiote.unit.core.model.resources;

import eu.h2020.symbiote.core.model.resources.Resource;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ResourceTest {

    @Test
    public void equals() throws Exception {
        Resource resource1 = new Resource();
        resource1.setId("id1");
        resource1.setLabels(Arrays.asList("label1", "label2"));
        resource1.setComments(Arrays.asList("comment1", "comment2"));
        resource1.setInterworkingServiceURL("resource1.com");

        Resource resource2 = new Resource();
        resource2.setId("id1");
        resource2.setLabels(Arrays.asList("label1", "label2"));
        resource2.setComments(Arrays.asList("comment1", "comment2"));
        resource2.setInterworkingServiceURL("resource1.com");

        Resource resource3 = new Resource();
        resource3.setId("id1");
        resource3.setLabels(Arrays.asList("label1", "label2"));
        resource3.setComments(Arrays.asList("comment1", "comment3"));
        resource3.setInterworkingServiceURL("resource1.com");

        assertEquals(true, resource1.equals(resource2));
        assertEquals(false, resource1.equals(resource3));
    }

}