package eu.h2020.symbiote.unit.cloud.model.internal;

import eu.h2020.symbiote.cloud.model.internal.CloudResource;
import eu.h2020.symbiote.cloud.model.internal.FederatedResource;
import eu.h2020.symbiote.model.cim.Resource;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FederatedResourceTest {


    @Test(expected = IllegalArgumentException.class)
    public void malformedSymbioteIdTest() {
        String symbioteId = "test1";
        Resource resource = new Resource();
        resource.setInterworkingServiceURL("test");
        CloudResource cloudResource = new CloudResource();
        cloudResource.setResource(resource);

        FederatedResource federatedResource = new FederatedResource(symbioteId, cloudResource, null);
    }

    @Test
    public void getPlatformIdTest() {
        String symbioteId = "test1@test2";
        Resource resource = new Resource();
        resource.setInterworkingServiceURL("test");
        CloudResource cloudResource = new CloudResource();
        cloudResource.setResource(resource);

        FederatedResource federatedResource = new FederatedResource(symbioteId, cloudResource, null);
        assertEquals("test2", federatedResource.getPlatformId());
    }
}