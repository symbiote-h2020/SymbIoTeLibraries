package eu.h2020.symbiote.cloud.model.data.observation;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestObservation {

	@Test
	public void testToString() {
		Observation o=new Observation(null, null, null, null, null);
		
		// Knowing the code we just test the worst case, every possible field null.
		// We don't test the output. We are happy when the code has no NPE. 
		String s=o.toString();
		
	}

	
	@Test
	public void testEquals() {
		Observation o=new Observation(null, null, null, null, null);
		Observation o2=new Observation(null, null, null, null, null);

		
		// Let's start with some trivial cases:
		assertNotEquals(o, null);
		assertEquals(o, o);
		assertNotEquals(o, "obs");
		
		
		
		assertEquals(o, o2);

		// TODO: Test other fields
		
	}

	
	@Test
	public void testHash() {
		// Main thing tested here: it will not break with null's
		Observation o=new Observation(null, null, null, null, null);
		assertEquals(o.hashCode(), 42);
		
		// Main thing tested here: It will not break and has another value than with pure nulls.
		o=new Observation("someID", null, null, null, null);
		assertNotEquals(o.hashCode(), 42);
		
		// TODO: Test all other fields too
	}


}
