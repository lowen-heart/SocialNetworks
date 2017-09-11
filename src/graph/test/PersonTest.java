package graph.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import graph.Vertex;
import graph.entity.Person;

public class PersonTest {

	Person p0;
	Person p1;
	Person p2;

	@Before
	public void setUp() throws Exception {
		p0 = new Person(0, "Bob", "Thomson", "USA");
		p1 = new Person(1, "Gusti", "Garcia", "Argentina");
		p2 = new Person(2, "Gregory", "Gunter", "USA");
	}
	
	@Test
	public void testConstructors(){
		try {
			Person p3 = new Person(-1,"Test","Test","Test");
		} catch (IllegalAccessException e) {
			System.out.println("Illegal ID negative value");
		}
		
		try {
			Person p3 = new Person(1,"","Test","Test");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		try {
			Person p3 = new Person(1,"Test","","Test");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		try {
			Person p3 = new Person(1,"Test","Test","");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		try {
			Person p3 = new Person(1,"","Test","Test");
		} catch (IllegalAccessException e) {
			System.out.println("Illegal ID value");
		}
		
		try {
			Person p3 = new Person(1,"","Test","Test");
		} catch (IllegalAccessException e) {
			System.out.println("Illegal ID value");
		}
		
		try {
			Person p3 = new Person(1,null,"Test","Test");
		} catch (IllegalAccessException e) {
			System.out.println("Illegal Name null value");
		}
		
		try {
			Person p3 = new Person(1,"Test",null,"Test");
		} catch (IllegalAccessException e) {
			System.out.println("Illegal Surname null value");
		}
		
		try {
			Person p3 = new Person(1,"Test","Test",null);
		} catch (IllegalAccessException e) {
			System.out.println("Illegal Country null value");
		}
	}

	@Test
	public void testHashCode() {
		System.out.println("-------- START TEST HASH CODE --------");

		System.out.println("Not equals HashCode p1: " + p1.hashCode() + " HashCode p2: " + p2.hashCode());
		assertNotEquals(p1.hashCode(), p2.hashCode());
		
		System.out.println("Change p2 to p1 value");
		
		p2 = p1;
		
		System.out.println("Equals HashCode p1: " + p1.hashCode() + " HashCode p2: " + p2.hashCode());
		assertEquals(p1.hashCode(), p2.hashCode());

		System.out.println("-------- END TEST HASH CODE --------");
	}

	@Test
	public void testEqualsObject() {
		System.out.println("-------- START TEST EQUALS --------");

		assertEquals(p1.equals(null), false);
		System.out.println("Equals: " + p1.equals(null) + " p1: " + p1 + " null");
		
		assertEquals(p0.equals(p0), true);
		System.out.println("Equals: " + p0.equals(p0) + " p0: " + p0 + " p0: " + p0);

		assertEquals(p1.equals(p0), false);
		System.out.println("Equals: " + p1.equals(p0) + " p1: " + p1 + " p0: " + p0);

		Person p3;
		try {
			System.out.println("Create new person as P0");
			p3 = new Person(p0.getId(),p0.getName(),p0.getSurname(),p0.getCountry());
			assertEquals(p0.equals(p3), true);
			System.out.println("Equals: " + p0.equals(p3) + " p0: " + p0 + " p3: " + p3);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		assertEquals(p0.equals(p2), false);
		System.out.println("Equals: " + p0.equals(p2) + " p0: " + p0 + " p2: " + p2);
		
		assertEquals(p0.equals(new Object()), false);
		System.out.println("Not same object: " + p0.equals(new Object()));

		System.out.println("-------- END TEST EQUALS --------");
	}

}
