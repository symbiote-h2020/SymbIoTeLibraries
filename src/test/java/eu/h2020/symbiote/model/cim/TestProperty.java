package eu.h2020.symbiote.model.cim;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Arrays;

public class TestProperty {

	@Test
	public void testToString() {
		Property p=new Property(null, null, null);
		
		// Knowing the code we just test the worst case, every possible field null.
		// We don't test the outcode. We are happy when the code has no NPE. 
		String s=p.toString();
		
	}

	
	@Test
	public void testEquals() {
		Property p=new Property(null, null, null);
		Property p2=new Property(null, null, null);

		
		// Let's start with some trivial cases:
		assertNotEquals(p, null);
		assertEquals(p, p);
		assertNotEquals(p, "property");
		
		
		
		assertEquals(p, p2);
		
		p=new Property("label", "https://www.symbioteh2020.eu/ontology/prop#prop",null);
		assertNotEquals(p, 2);
		p2=new Property("label","https://www.symbioteh2020.eu/ontology/prop#prop", null);
		assertEquals(p, p2);
		
		p=new Property("label", "https://www.symbioteh2020.eu/ontology/prop#prop",Arrays.asList("Comment"));
		assertEquals(p, p2);	// Comment not relevant for equals!!
		p2=new Property("label", "https://www.symbioteh2020.eu/ontology/prop#prop",Arrays.asList("Comment"));
		assertEquals(p, p2);
		
	}

	
	@Test
	public void testHash() {
		Property p=new Property(null, null, null);
		Property p2=new Property(null, null, null);

		assertEquals(p.hashCode(), p2.hashCode());
		
		p=new Property("label", "https://www.symbioteh2020.eu/ontology/prop#prop", null);
		assertNotEquals(p.hashCode(), p2.hashCode());
		p2=new Property("label", "https://www.symbioteh2020.eu/ontology/prop#prop", null);
		assertEquals(p.hashCode(), p2.hashCode());
		
		p=new Property("label", "https://www.symbioteh2020.eu/ontology/prop#prop", Arrays.asList("Comment"));
		assertEquals(p.hashCode(), p2.hashCode());
		p2=new Property("label", "https://www.symbioteh2020.eu/ontology/prop#prop", Arrays.asList("Comment"));
		assertEquals(p.hashCode(), p2.hashCode());
		
	}

	@Test
	public void testCopyConstructor() {
		Property p=new Property("id", "https://www.symbioteh2020.eu/ontology/prop#prop", Arrays.asList("Comment"));
		
		Property p2=new Property(p);

		assertEquals(p, p2);

		
	}

}
