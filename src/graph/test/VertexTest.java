package graph.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import graph.Vertex;

public class VertexTest {

	Vertex<Integer> v0;
	Vertex<Integer> v1;
	Vertex<Integer> v2;
	Vertex<Integer> v3;
	Vertex<Integer> v4;
	
	@Before
	public void setUp() throws Exception {
		v0 = new Vertex<Integer>(0);
		v1 = new Vertex<Integer>(10);
		v2 = new Vertex<Integer>(7);
		v3 = new Vertex<Integer>(-10);
		v4 = new Vertex<Integer>(-7);
	}

	@Test
	public void testNegativeDegreeConstructor() {
		
		System.out.println("-------- START TEST NEGATIVE DEGREE CONSTRUCTOR --------");
		
		try{
			Vertex<Integer> v = new Vertex<Integer>(-9,-7);
		}catch(IllegalArgumentException e){
			System.out.println("Exception on degree less than 0");
		}
		
		System.out.println("-------- END TEST NEGATIVE DEGREE CONSTRUCTOR --------");
	}
	
	@Test
	public void testHashCode() {
		
		System.out.println("-------- START TEST HASH CODE --------");
		
		System.out.println("HashCode v1: " + v1.hashCode() + " HashCode v2: " + v2.hashCode());
		assertNotEquals(v1.hashCode(),v2.hashCode());
		System.out.println("Change v2 to v1 value");
		v2.setValue(10);
		System.out.println("HashCode v1: " + v1.hashCode() + " HashCode v2: " + v2.hashCode());
		assertEquals(v1.hashCode(),v2.hashCode());
		
		System.out.println("HashCode v3: " + v3.hashCode() + " HashCode v4: " + v4.hashCode());
		assertNotEquals(v3.hashCode(),v4.hashCode());
		System.out.println("Change v3 to v4 value");
		v4.setValue(-10);
		System.out.println("HashCode v3: " + v3.hashCode() + " HashCode v4: " + v4.hashCode());
		assertEquals(v1.hashCode(),v2.hashCode());
		
		System.out.println("HashCode v3: " + v3.hashCode() + " HashCode v0: " + v0.hashCode());
		assertNotEquals(v3.hashCode(),v0.hashCode());
		
		System.out.println("-------- END TEST HASH CODE --------");
	}

	@Test
	public void testEquals(){
		System.out.println("-------- START TEST EQUALS --------");
		
		assertEquals(v1.equals(null),false);
		System.out.println("Equals: " + v1.equals(null) + " v1: " + v1 + " null");
		
		assertEquals(v1.equals(v3),false);
		System.out.println("Equals: " + v1.equals(v3) + " v1: " + v1 + " v3: " + v3);
		
		Vertex<Integer> v8 = new Vertex<Integer>(10);
		
		assertEquals(v1.equals(v8),true);
		System.out.println("Equals: " + v1.equals(v8) + " v1: " + v1 + " v8: " + v8);
		
		assertEquals(v3.equals(v4),false);
		System.out.println("Equals: " + v3.equals(v4) + " v3: " + v3 + " v4: " + v4);
		
		v8.setValue(-7); 
		
		assertEquals(v4.equals(v8),true);
		System.out.println("Equals: " + v4.equals(v8) + " v4: " + v4 + " v8: " + v8);
		
		assertEquals(v3.equals(v8),false);
		System.out.println("Equals: " + v3.equals(v8) + " v3: " + v3 + " v0: " + v0);
		
		assertEquals(v2.equals(v0),false);
		System.out.println("Equals: " + v2.equals(v0) + " v2: " + v2 + " v0: " + v0);
		
		System.out.println("-------- END TEST EQUALS --------");
	}
	
	@Test
	public void testCompareTo(){
		System.out.println("-------- START TEST COMPARE TO FOR TOMITA ALGORITHM --------");
		
		System.out.println("Positive compare degree v1: " + v1.getDegree() + " v2: " + v2.getDegree() + " Compare: " + v1.compareTo(v2));
		assertEquals(v1.compareTo(v2),1);
		
		v1.setDegree(3);
		
		System.out.println("Negative compare degree v1: " + v1.getDegree() + " v2: " + v2.getDegree()+ " Compare: " + v1.compareTo(v2));
		assertEquals(v1.compareTo(v2),-1);
		
		v2.setDegree(5);
		
		System.out.println("Positive compare degree v1: " + v1.getDegree() + " v2: " + v2.getDegree()+ " Compare: " + v1.compareTo(v2));
		assertEquals(v1.compareTo(v2),1);
		
		v2.setDegree(3);
		
		System.out.println("Positive compare degree v1: " + v1.getDegree() + " v2: " + v2.getDegree()+ " Compare: " + v1.compareTo(v2));
		assertEquals(v1.compareTo(v2),1);

		v2.setDegree(1);
		
		System.out.println("Negative compare degree v1: " + v1.getDegree() + " v2: " + v2.getDegree()+ " Compare: " + v1.compareTo(v2));
		assertEquals(v1.compareTo(v2),-1);
		
		System.out.println("-------- END TEST COMPARE TO FOR TOMITA ALGORITHM --------");
	}
	
	@Test
	public void testSetDegreeAndNebDeg(){
		System.out.println("-------- START TEST SET DEGREE AND NEBDEG --------");
		
		System.out.println("Set positive degree");
		v1.setDegree(4);
		
		try{
			v1.setDegree(-3);
		}catch(IllegalArgumentException e){
			System.out.println("Correct catch on set negative degree");
		}
		

		System.out.println("Set positive nebDeg");
		v2.setNebDeg(4);
		
		try{
			v2.setNebDeg(-3);
		}catch(IllegalArgumentException e){
			System.out.println("Correct catch on set negative nebDeg");
		}
		
		System.out.println("-------- END TEST SET DEGREE AND NEBDEGREE --------");
	}
	
	
}
