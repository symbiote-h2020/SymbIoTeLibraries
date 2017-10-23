package eu.h2020.symbiote.model.cim;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestWGS84Location {

	@Test
	public void testHashCode() {
		
		WGS84Location wgs=new WGS84Location(0, 0, 0, null, null);	// Lists are null. Let's see we don't get NPE
		
		int hash=wgs.hashCode();
		
		wgs=new WGS84Location(1.0, 0, 0, null, null);
		int hash2=wgs.hashCode();
		
		assertNotEquals(hash, hash2);		// Should be different. But if not, it also fulfills the criteria for "hashCode"
		
	}

	@Test
	public void testToString() {
		WGS84Location wgs=new WGS84Location(0, 0, 0, null, null);	// Lists are null. Let's see we don't get NPE
		String t=wgs.toString();
	}

	@Test
	public void testEqualsObject() {
		WGS84Location wgs1=new WGS84Location(0, 0, 0, null, null);	// Lists are null. Let's see we don't get NPE
		WGS84Location wgs2=new WGS84Location(0, 0, 0, null, null);
		
		assertTrue(wgs1.equals(wgs1));
		assertFalse(wgs1.equals(null));
		
		assertTrue(wgs1.equals(wgs2));
		
		wgs1=new WGS84Location(10.0, 0, 0, null, null);
		assertFalse(wgs1.equals(wgs2));
		wgs2=(WGS84Location) Location.makeCopy(wgs1);
		assertTrue(wgs1.equals(wgs2));
		
		wgs1=new WGS84Location(0.0, 20, 0, null, null);
		assertFalse(wgs1.equals(wgs2));
		wgs2=(WGS84Location) Location.makeCopy(wgs1);
		assertTrue(wgs1.equals(wgs2));
		
		wgs1=new WGS84Location(0.0, 0, 30, null, null);
		assertFalse(wgs1.equals(wgs2));
		wgs2=(WGS84Location) Location.makeCopy(wgs1);
		assertTrue(wgs1.equals(wgs2));
		
		wgs1=new WGS84Location(0.0, 0, 0, new String(), null);
		assertFalse(wgs1.equals(wgs2));
		wgs2=(WGS84Location) Location.makeCopy(wgs1);
		assertTrue(wgs1.equals(wgs2));
		
		wgs1=new WGS84Location(0.0, 0, 0, null, new ArrayList<String>());
		assertFalse(wgs1.equals(wgs2));
		wgs2=(WGS84Location) Location.makeCopy(wgs1);
		assertTrue(wgs1.equals(wgs2));
		
	}

	/**
	 * Not so much a unit test but a regression test. I've seen people break compatibility with jackson.
	 * @throws IOException 
	 */
	@Test
	public void testJSON() throws IOException {
//		List<String> labels  =Arrays.asList(new String[] {"l1", "l2"});
		String label = "l1";
		List<String> comments=Arrays.asList(new String[] {"c1", "c2"});
		
		WGS84Location wgs1=new WGS84Location(10, 20,30, label, comments);

        ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(wgs1);
		WGS84Location wgs2=mapper.readValue(json, WGS84Location.class);
		
		assertTrue(wgs1.equals(wgs2));
		
	}
}
