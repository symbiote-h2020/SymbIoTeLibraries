package eu.h2020.symbiote.model.cim;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestWKTLocation {

	@Test
	public void testHashCode() {
		
		WKTLocation wkt=new WKTLocation(null, null, null);	// everything null. Let's see we don't get NPE		
		int hash=wkt.hashCode();
		
		wkt=new WKTLocation("Some WKT", null, null);
		int hash2=wkt.hashCode();
		
		assertNotEquals(hash, hash2);		// Should be different. But if not, it also fulfills the criteria for "hashCode"
		
	}

	@Test
	public void testToString() {
		WKTLocation wkt=new WKTLocation(null, null, null);	// Lists are null. Let's see we don't get NPE
		String t=wkt.toString();
	}

	@Test
	public void testEqualsObject() {
		WKTLocation wkt1=new WKTLocation(null, null, null);	// Lists are null. Let's see we don't get NPE
		WKTLocation wkt2=new WKTLocation(null, null, null);
		
		assertTrue(wkt1.equals(wkt1));
		assertFalse(wkt1.equals(null));
		
		assertTrue(wkt1.equals(wkt2));
		
		wkt1=new WKTLocation("Some Value", null, null);
		assertFalse(wkt1.equals(wkt2));
		wkt2=(WKTLocation) Location.makeCopy(wkt1);
		assertTrue(wkt1.equals(wkt2));
		
		wkt1=new WKTLocation(null, "l1", null);
		assertFalse(wkt1.equals(wkt2));
		wkt2=(WKTLocation) Location.makeCopy(wkt1);
		assertTrue(wkt1.equals(wkt2));
		
		wkt1=new WKTLocation(null, null, Arrays.asList(new String[] {"l1"}));
		assertFalse(wkt1.equals(wkt2));
		wkt2=(WKTLocation) Location.makeCopy(wkt1);
		assertTrue(wkt1.equals(wkt2));
		
	}

	/**
	 * Not so much a unit test but a regression test. I've seen people break compatibility with jackson.
	 * @throws IOException 
	 */
	@Test
	public void testJSON() throws IOException {
//		List<String> labels  =Arrays.asList(new String[] {"l1", "l2"});
		String label = "11";
		List<String> comments=Arrays.asList(new String[] {"c1", "c2"});
		
		WKTLocation wkt1=new WKTLocation("Some String", label, comments);

        ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(wkt1);
		WKTLocation wkt2=mapper.readValue(json, WKTLocation.class);
		
		assertTrue(wkt1.equals(wkt2));
		
	}
}
