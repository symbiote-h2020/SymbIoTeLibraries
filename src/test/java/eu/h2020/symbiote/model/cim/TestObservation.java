package eu.h2020.symbiote.model.cim;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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


	@Test
	public void testCopyConstructor() {
		Observation o=new Observation(null, null, null, null, null);
		Observation o1=new Observation(o);
		
		assertEquals(o, o1);

		List<ObservationValue> theList=new ArrayList<ObservationValue>();
		theList.add(new ObservationValue("1", null, null));
		
		o=new Observation("ID", new eu.h2020.symbiote.model.cim.WGS84Location(0, 0, 0, null, null), "A", "B", theList);
		o1=new Observation(o);
		
		assertEquals(o, o1);	// Note: equals but not the same.
		
		// Make sure it was a deep copy.
		assertNotSame(o.getLocation(), o1.getLocation());
		assertNotSame(o.getObsValues(), o1.getObsValues());
		assertNotSame(o.getObsValues().get(0), o1.getObsValues().get(0));
		
		
	}
}
