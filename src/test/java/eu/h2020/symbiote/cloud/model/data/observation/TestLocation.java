package eu.h2020.symbiote.cloud.model.data.observation;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestLocation {

	@Test
	public void testToString() {
		Location loc=new Location();
		
		// Knowing the code we just test the worst case, every possible field null.
		// We don't test the outcode. We are happy when the code has no NPE. 
		String s=loc.toString();
		
	}

	
	@Test
	public void testEquals() {
		Location loc=new Location();
		Location loc2=new Location();

		
		// Let's start with some trivial cases:
		assertNotEquals(loc, null);
		assertEquals(loc, loc);
		assertNotEquals(loc, "loc");
		
		
		
		assertEquals(loc, loc2);
		
		loc.setId("ID");
		assertNotEquals(loc, loc2);
		loc2.setId("ID");
		assertEquals(loc, loc2);
		
		loc.setName("name");
		assertNotEquals(loc, loc2);
		loc2.setName("name");
		assertEquals(loc, loc2);
		
		loc.setLatitude(10.0);
		assertNotEquals(loc, loc2);
		loc2.setLatitude(10.0);
		assertEquals(loc, loc2);
		
		loc.setLongitude(14.0);
		assertNotEquals(loc, loc2);
		loc2.setLongitude(14.0);
		assertEquals(loc, loc2);

		loc.setAltitude(20.0);
		assertNotEquals(loc, loc2);
		loc2.setAltitude(20.0);
		assertEquals(loc, loc2);

		loc.setDescription("Some descrition");
		assertEquals(loc, loc2);	// !!!!
		loc2.setDescription("Some other descrition");
		assertEquals(loc, loc2);	// !!!!

		// Is there a reason, setComment does not exist?		
		
	}

	
	@Test
	public void testHash() {
		Location loc=new Location();
		Location loc2=new Location();

		assertEquals(loc.hashCode(), loc2.hashCode());
		
		loc.setId("ID");
		assertNotEquals(loc.hashCode(), loc2.hashCode());
		loc2.setId("ID");
		assertEquals(loc.hashCode(), loc2.hashCode());
		
		loc.setName("name");
		assertNotEquals(loc.hashCode(), loc2.hashCode());
		loc2.setName("name");
		assertEquals(loc.hashCode(), loc2.hashCode());
		
		loc.setLatitude(10.0);
		assertNotEquals(loc.hashCode(), loc2.hashCode());
		loc2.setLatitude(10.0);
		assertEquals(loc.hashCode(), loc2.hashCode());
		
		loc.setLongitude(14.0);
		assertNotEquals(loc.hashCode(), loc2.hashCode());
		loc2.setLongitude(14.0);
		assertEquals(loc.hashCode(), loc2.hashCode());

		loc.setAltitude(20.0);
		assertNotEquals(loc.hashCode(), loc2.hashCode());
		loc2.setAltitude(20.0);
		assertEquals(loc.hashCode(), loc2.hashCode());

		loc.setDescription("Some descrition");
		assertEquals(loc.hashCode(), loc2.hashCode());
		loc2.setDescription("Some other descrition");
		assertEquals(loc.hashCode(), loc2.hashCode());

		// Is there a reason, setComment does not exist?		
		
	}

	@Test
	public void testCopyConstructor() {
		Location loc=new Location();
		
		loc.setId("ID");
		loc.setName("name");
		loc.setLatitude(10.0);
		loc.setLongitude(14.0);
		loc.setAltitude(20.0);
		loc.setDescription("Some descrition");

		Location loc2=new Location(loc);

		assertEquals(loc, loc2);

		
	}

}
