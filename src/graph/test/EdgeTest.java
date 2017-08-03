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
		edgeTest1 = new Edge<String>("Alice","Bob");
		edgeTest2 = new Edge<String>("Alice","Cher");
	}

	@Test
	public void testToString() {
		System.out.println(edgeTest1 + " and " + edgeTest2);
	}

	@Test
	public void testEqualsObject() {
		edgeTest2.setTo("Bob");
		assertEquals(edgeTest1,edgeTest2);
	}

}
