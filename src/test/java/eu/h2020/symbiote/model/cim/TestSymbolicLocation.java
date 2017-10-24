package eu.h2020.symbiote.model.cim;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * As symbolic location is mostly a stub, these tests are a stub. 
 * But the are here to make life easier for the guy who fills symbolic location with life.
 * @author DuennebeilG
 *
 */
public class TestSymbolicLocation {

	@Test
	public void testHashCode() {
		
		SymbolicLocation sl=new SymbolicLocation();		
		int hash=sl.hashCode();
		
		sl=new SymbolicLocation();
		int hash2=sl.hashCode();
		
		assertEquals(hash, hash2);
		
	}

	@Test
	public void testToString() {
		SymbolicLocation sl=new SymbolicLocation();	// Lists are null. Let's see we don't get NPE
		String t=sl.toString();
	}

	@Test
	public void testEqualsObject() {
		SymbolicLocation sl1=new SymbolicLocation();
		SymbolicLocation sl2=new SymbolicLocation();
		
		assertTrue(sl1.equals(sl1));
		assertFalse(sl1.equals(null));
		
		assertTrue(sl1.equals(sl2));
		
		
	}

	/**
	 * Not so much a unit test but a regression test. I've seen people break compatibility with jackson.
	 * @throws IOException 
	 */
	@Test
	public void testJSON() throws IOException {
		SymbolicLocation sl1=new SymbolicLocation();

        ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(sl1);
		SymbolicLocation sl2=mapper.readValue(json, SymbolicLocation.class);
		
		assertTrue(sl1.equals(sl2));
		
	}
}
