package graph.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import graph.Vertex;

public class VertexTest {

	Vertex<Integer> v1;
	Vertex<Integer> v2;
	
	@Before
	public void setUp() throws Exception {
		v1 = new Vertex<Integer>(10);
		v2 = new Vertex<Integer>(7);
	}

	@Test
	public void testHashCode() {
		System.out.println("HashCode v1: " + v1.hashCode() + " HashCode v2: " + v2.hashCode());
	}

	@Test
	public void testEquals(){
		Vertex<Integer> v3 = new Vertex<Integer>(10);
		assertEquals(v1,v3);
		System.out.println("Equals v1: " + v1 + " v3: " + v3);
	}
}
