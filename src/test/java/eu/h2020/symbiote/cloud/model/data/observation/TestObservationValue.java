package eu.h2020.symbiote.cloud.model.data.observation;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestObservationValue {

	@Test
	public void testToString() {
		ObservationValue u=new ObservationValue(null, null, null);
		
		// Knowing the code we just test the worst case, every possible field null.
		// We don't test the outcode. We are happy when the code has no NPE. 
		String s=u.toString();
		
	}

	
	@Test
	public void testEquals() {
		ObservationValue v=new ObservationValue(null, null, null);
		ObservationValue v2=new ObservationValue(null, null, null);

		
		// Let's start with some trivial cases:
		assertNotEquals(v, null);
		assertEquals(v, v);
		assertNotEquals(v, "obsValue");
		
		
		
		assertEquals(v, v2);
		
		v=new ObservationValue("value", null, null);
		assertNotEquals(v, v2);
		v2=new ObservationValue("value", null, null);
		assertEquals(v, v2);
		
		v=new ObservationValue("value", new Property("prop", null), null);
		assertNotEquals(v, v2);
		v2=new ObservationValue("value", new Property("prop", null), null);
		assertEquals(v, v2);
		
		v=new ObservationValue("value", new Property("prop", null), new UnitOfMeasurement("sym", null, null));
		assertNotEquals(v, v2);
		v2=new ObservationValue("value", new Property("prop", null), new UnitOfMeasurement("sym", null, null));
		assertEquals(v, v2);
		
	}

	
	@Test
	public void testHash() {
		// Main thing tested here: it will not break with null's
		ObservationValue v=new ObservationValue(null, null, null);
		assertEquals(v.hashCode(), 42);
		
		// Main thing tested here: It will not break and has another value than with pure nulls.
		v=new ObservationValue("value", new Property("prop", null), new UnitOfMeasurement("sym", null, null));
		assertNotEquals(v.hashCode(), 42);
		
	}

	
	@Test
	public void testCopyConstructor() {
		ObservationValue v=new ObservationValue(null, null, null);
		ObservationValue v1=new ObservationValue(v);

		assertEquals(v, v1);
		
		v=new ObservationValue("1", new Property("1", "2"), new UnitOfMeasurement("A", "B", "C"));
		v1=new ObservationValue(v);

		assertEquals(v, v1);

		assertFalse(v.getObsProperty()==v1.getObsProperty()); // Equals but not same. It's a deep copy
		assertFalse(v.getUom()==v1.getUom());
		
	}

}
