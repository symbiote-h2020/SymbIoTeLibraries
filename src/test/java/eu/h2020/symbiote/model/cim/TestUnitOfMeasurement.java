package eu.h2020.symbiote.model.cim;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

public class TestUnitOfMeasurement {

	@Test
	public void testToString() {
		UnitOfMeasurement u=new UnitOfMeasurement(null, null, null, null);
		
		// Knowing the code we just test the worst case, every possible field null.
		// We don't test the outcode. We are happy when the code has no NPE. 
		String s=u.toString();
		
	}

	
	@Test
	public void testEquals() {
		UnitOfMeasurement u=new UnitOfMeasurement(null, null, null, null);
		UnitOfMeasurement u2=new UnitOfMeasurement(null, null, null, null);

		
		// Let's start with some trivial cases:
		assertNotEquals(u, null);
		assertEquals(u, u);
		assertNotEquals(u, "UoM");
		
		
		
		assertEquals(u, u2);
		
		u=new UnitOfMeasurement("symbol", null, "https://www.symbioteh2020.eu/ontology/uom#uom", null);
		assertNotEquals(u, u2);
		u2=new UnitOfMeasurement("symbol", null, "https://www.symbioteh2020.eu/ontology/uom#uom", null);
		assertEquals(u, u2);
		
		u=new UnitOfMeasurement("symbol", "label", "https://www.symbioteh2020.eu/ontology/uom#uom", Arrays.asList("comment"));
		assertEquals(u, u2);	// albel and Comment not relevant for equals!!
		
	}

	
	@Test
	public void testHash() {
		UnitOfMeasurement u=new UnitOfMeasurement(null, null, null, null);
		assertEquals(u.hashCode(), 0);
		
		u=new UnitOfMeasurement("label", null, "https://www.symbioteh2020.eu/ontology/uom#uom", null);
		assertNotEquals(u.hashCode(), 0);		
	}
	
	@Test
	public void testCopyConstructor() {
		
		// Just see if something breaks with all null
		UnitOfMeasurement u=new UnitOfMeasurement(null, null, null, null);
		UnitOfMeasurement u2=new UnitOfMeasurement(u);
		
		assertEquals(u, u2);

		// Does all copy?
		u=new UnitOfMeasurement("1", "2", "https://www.symbioteh2020.eu/ontology/uom#uom", Arrays.asList("3"));
		u2=new UnitOfMeasurement(u);
		
		assertEquals(u, u2);
		
		
		
	}


}
