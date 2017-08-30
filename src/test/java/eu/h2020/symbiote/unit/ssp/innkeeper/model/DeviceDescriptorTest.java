package eu.h2020.symbiote.unit.ssp.innkeeper.model;

import eu.h2020.symbiote.ssp.communication.rest.AgentType;
import eu.h2020.symbiote.ssp.communication.rest.DeviceDescriptor;
import eu.h2020.symbiote.ssp.exception.InvalidMacAddressException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by vasgl on 8/23/2017.
 */
public class DeviceDescriptorTest {

    @Test
    public void constructor2Test() {
        DeviceDescriptor deviceDescriptor;

        try {
            deviceDescriptor = new DeviceDescriptor("7A:0b:1c:7A:0b:1c", "name", "description",
                    true, AgentType.SDEV, 1000);
            assertEquals("7A:0b:1c:7A:0b:1c", deviceDescriptor.getMac());
            assertEquals("name", deviceDescriptor.getName());
            assertEquals("description", deviceDescriptor.getDescription());
            assertEquals(true, deviceDescriptor.getSleeping());
            assertEquals(AgentType.SDEV, deviceDescriptor.getAgentType());
            assertEquals(1000, (int) deviceDescriptor.getReadingInterval());
        } catch (InvalidMacAddressException e) {
            e.printStackTrace();
            fail("InvalidMacAddressException was thrown unexpectedly");
        }

        deviceDescriptor = null;
        try {
            deviceDescriptor = new DeviceDescriptor("7A:0b:1c:7A-0b:1c", "name", "description",
                    true, AgentType.SDEV, 1000);
            fail("A InvalidMacAddressException should have been thrown");
        } catch (InvalidMacAddressException e) {
            e.printStackTrace();
            assertEquals(null, deviceDescriptor);
        }
    }

    @Test
    public void setMacTest() {
        DeviceDescriptor deviceDescriptor = new DeviceDescriptor();
        boolean invalidMacAddressExceptionThrown = false;

        try {
            deviceDescriptor.setMac("7A:0b:1c:7A:0b:1c");
        } catch (InvalidMacAddressException e) {
            invalidMacAddressExceptionThrown = true;
            e.printStackTrace();
        }
        assertEquals("7A:0b:1c:7A:0b:1c", deviceDescriptor.getMac());
        assertEquals(false, invalidMacAddressExceptionThrown);

        deviceDescriptor = new DeviceDescriptor();
        invalidMacAddressExceptionThrown = false;
        try {
            deviceDescriptor.setMac("7A-0b-1c-7A-0b-1c");
        } catch (InvalidMacAddressException e) {
            invalidMacAddressExceptionThrown = true;
            e.printStackTrace();
        }
        assertEquals("7A-0b-1c-7A-0b-1c", deviceDescriptor.getMac());
        assertEquals(false, invalidMacAddressExceptionThrown);

        deviceDescriptor = new DeviceDescriptor();
        invalidMacAddressExceptionThrown = false;
        try {
            deviceDescriptor.setMac("7A-0b:1c-7A-0b-1c");
        } catch (InvalidMacAddressException e) {
            invalidMacAddressExceptionThrown = true;
            e.printStackTrace();
        }
        assertEquals(null, deviceDescriptor.getMac());
        assertEquals(true, invalidMacAddressExceptionThrown);

        deviceDescriptor = new DeviceDescriptor();
        invalidMacAddressExceptionThrown = false;
        try {
            deviceDescriptor.setMac("7A-0b-1c-7A-0b-1c-10");
        } catch (InvalidMacAddressException e) {
            invalidMacAddressExceptionThrown = true;
            e.printStackTrace();
        }
        assertEquals(null, deviceDescriptor.getMac());
        assertEquals(true, invalidMacAddressExceptionThrown);
    }

}