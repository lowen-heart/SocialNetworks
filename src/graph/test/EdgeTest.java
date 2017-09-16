package graph.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import graph.Edge;

public class EdgeTest {
	
	Edge<String> edgeTest1;
	Edge<String> edgeTest2;

	@Before
	public void setUp() throws Exception {
		edgeTest1 = new Edge<String>("Gregory","Bob");
		edgeTest2 = new Edge<String>("Gregory","Gusti");
	}

	@Test
	public void testToString() {
		System.out.println("-------- START TEST TO STRING  --------");
		
		System.out.println(edgeTest1 + " and " + edgeTest2);
		
		System.out.println("-------- END TEST TO STRING --------");
	}

	@Test
	public void testEqualsObject() {
		System.out.println("-------- START TEST EQUALS --------");
		
		assertFalse("Null object",edgeTest1.equals(null));
		
		assertTrue("Same object: true",edgeTest1.equals(edgeTest1));
		
		assertFalse("Different objects",edgeTest1.equals(edgeTest2));
		
		assertEquals(edgeTest1.equals(edgeTest2),false);
		
		edgeTest2.setTo("Bob");
		
		assertEquals(edgeTest1.equals(edgeTest2),true);
		
		assertFalse("They are not the same object so equals returns false",edgeTest1.equals(new Object()));
		
		System.out.println("-------- END TEST EQUALS --------");
	}

}
