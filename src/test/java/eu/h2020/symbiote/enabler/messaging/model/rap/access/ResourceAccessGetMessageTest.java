package eu.h2020.symbiote.enabler.messaging.model.rap.access;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.h2020.symbiote.enabler.messaging.model.rap.db.ResourceInfo;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ResourceAccessGetMessageTest {

    @Test
    public void testDeserialization() {
        String resourceId = "resourceId";
        String platformResourceId = "platformResourceId";

        ResourceInfo deprInfo = new ResourceInfo("resourceId", "platformResourceId");
        ResourceAccessGetMessage deprMessage = new ResourceAccessGetMessage(new ArrayList<>(Collections.singletonList(deprInfo)));

        ObjectMapper om = new ObjectMapper();

        String deprSerialized = null;
        try {
            deprSerialized = om.writeValueAsString(deprMessage);
        } catch (JsonProcessingException e) {
            fail("Failed to serialize deprecated message");
        }

        eu.h2020.symbiote.cloud.model.rap.access.ResourceAccessGetMessage message = null;
        try {
            message = om.readValue(deprSerialized, eu.h2020.symbiote.cloud.model.rap.access.ResourceAccessGetMessage.class);
        } catch (IOException e) {
            fail("Failed to deserialize deprecated message to new message");
        }

        assertEquals(resourceId, message.getResourceInfo().get(0).getSymbioteId());
    }

}