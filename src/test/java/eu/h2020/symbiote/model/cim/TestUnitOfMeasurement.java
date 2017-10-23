package eu.h2020.symbiote.model.cim;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUnitOfMeasurement {

	@Test
	public void testToString() {
		UnitOfMeasurement u=new UnitOfMeasurement(null, null, null);
		
		// Knowing the code we just test the worst case, every possible field null.
		// We don't test the outcode. We are happy when the code has no NPE. 
		String s=u.toString();
		
	}

	
	@Test
	public void testEquals() {
		UnitOfMeasurement u=new UnitOfMeasurement(null, null, null);
		UnitOfMeasurement u2=new UnitOfMeasurement(null, null, null);

		
		// Let's start with some trivial cases:
		assertNotEquals(u, null);
		assertEquals(u, u);
		assertNotEquals(u, "UoM");
		
		
		
		assertEquals(u, u2);
		
		u=new UnitOfMeasurement("symbol", null, null);
		assertNotEquals(u, u2);
		u2=new UnitOfMeasurement("symbol", null, null);
		assertEquals(u, u2);
		
		u=new UnitOfMeasurement("symbol", "label", "comment");
		assertEquals(u, u2);	// albel and Comment not relevant for equals!!
		
	}

	
	@Test
	public void testHash() {
		UnitOfMeasurement u=new UnitOfMeasurement(null, null, null);
		assertEquals(u.hashCode(), 0);
		
		u=new UnitOfMeasurement("label", null, null);
		assertNotEquals(u.hashCode(), 0);		
	}
	
	@Test
	public void testCopyConstructor() {
		
		// Just see if something breaks with all null
		UnitOfMeasurement u=new UnitOfMeasurement(null, null, null);
		UnitOfMeasurement u2=new UnitOfMeasurement(u);
		
		assertEquals(u, u2);

		// Does all copy?
		u=new UnitOfMeasurement("1", "2", "3");
		u2=new UnitOfMeasurement(u);
		
		assertEquals(u, u2);
		
		
		
	}


}
