package graph.test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import graph.entity.Person;
import graph.entity.ReviewAirline;
import graph.entity.Reviewer;

public class ReviewAirlineTest {

	ReviewAirline eco0;
	ReviewAirline fc0;
	ReviewAirline bc0;
	ReviewAirline pec0;
	ReviewAirline empty0;
	ReviewAirline eco1;
	ReviewAirline fc1;
	ReviewAirline bc1;
	ReviewAirline pec1;
	ReviewAirline empty1;
	ReviewAirline eco2;
	ReviewAirline fc2;
	ReviewAirline bc2;
	ReviewAirline pec2;
	ReviewAirline empty2;
	ReviewAirline eco3;
	ReviewAirline fc3;
	ReviewAirline bc3;
	ReviewAirline pec3;
	ReviewAirline empty3;

	@Before
	public void setUp() throws Exception {

		eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f, false);
		eco1 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 2.0f, 2.0f,
				3.0f, 4.0f, 5.0f, false);
		eco2 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f, 1.0f,
				3.0f, 3.0f, 5.0f, false);
		eco3 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f, 5.0f,
				1.0f, 1.0f, 1.0f, false);

		fc0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.FIRSTCLASS.toString(), 0.0f, "", 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f, false);
		fc1 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.FIRSTCLASS.toString(), 0.0f, "", 2.0f, 2.0f,
				3.0f, 4.0f, 5.0f, false);
		fc2 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.FIRSTCLASS.toString(), 0.0f, "", 0.0f, 1.0f,
				3.0f, 3.0f, 5.0f, false);
		fc3 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.FIRSTCLASS.toString(), 0.0f, "", 0.0f, 5.0f,
				1.0f, 1.0f, 1.0f, false);

		bc0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.BUSINESS.toString(), 0.0f, "", 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f, false);
		bc1 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.BUSINESS.toString(), 0.0f, "", 2.0f, 2.0f,
				3.0f, 4.0f, 5.0f, false);
		bc2 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.BUSINESS.toString(), 0.0f, "", 0.0f, 1.0f,
				3.0f, 3.0f, 5.0f, false);
		bc3 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.BUSINESS.toString(), 0.0f, "", 0.0f, 5.0f,
				1.0f, 1.0f, 1.0f, false);

		pec0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.PREMIUMECONOMY.toString(), 0.0f, "", 0.0f,
				0.0f, 0.0f, 0.0f, 0.0f, false);
		pec1 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.PREMIUMECONOMY.toString(), 0.0f, "", 2.0f,
				2.0f, 3.0f, 4.0f, 5.0f, false);
		pec2 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.PREMIUMECONOMY.toString(), 0.0f, "", 0.0f,
				1.0f, 3.0f, 3.0f, 5.0f, false);
		pec3 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.PREMIUMECONOMY.toString(), 0.0f, "", 0.0f,
				5.0f, 1.0f, 1.0f, 1.0f, false);

		empty0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.EMPTY.toString(), 0.0f, "", 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f, false);
		empty1 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.EMPTY.toString(), 0.0f, "", 2.0f, 2.0f,
				3.0f, 4.0f, 5.0f, false);
		empty2 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.EMPTY.toString(), 0.0f, "", 0.0f, 1.0f,
				3.0f, 3.0f, 5.0f, false);
		empty3 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.EMPTY.toString(), 0.0f, "", 0.0f, 5.0f,
				1.0f, 1.0f, 1.0f, false);

	}

	@Test
	public void testMatches() {

		System.out.println("-------- START TEST MATCHES --------");

		System.out.println("Reviewers matches on " + ReviewAirline.Classes.ECONOMY.toString() + " cabin class "
				+ eco0.matches(eco1));
		assertEquals(false, eco0.matches(eco1));

		System.out.println("Reviewers matches on " + ReviewAirline.Classes.PREMIUMECONOMY.toString() + " cabin class "
				+ pec0.matches(pec1));
		assertEquals(false, pec0.matches(pec1));

		System.out.println("Reviewers matches on " + ReviewAirline.Classes.BUSINESS.toString() + " cabin class "
				+ bc0.matches(bc1));
		assertEquals(false, bc0.matches(bc1));

		System.out.println("Reviewers matches on " + ReviewAirline.Classes.FIRSTCLASS.toString() + " cabin class "
				+ fc0.matches(fc1));
		assertEquals(false, fc0.matches(fc1));

		System.out.println("Reviewers matches on " + ReviewAirline.Classes.EMPTY.toString() + " cabin class "
				+ empty0.matches(empty1));
		assertEquals(false, empty0.matches(empty1));

		System.out.println("Reviewers matches on " + ReviewAirline.Classes.ECONOMY.toString() + " cabin class "
				+ eco0.matches(eco3));
		assertEquals(true, eco0.matches(eco3));

		System.out.println("Reviewers matches on " + ReviewAirline.Classes.PREMIUMECONOMY.toString() + " cabin class "
				+ pec0.matches(pec3));
		assertEquals(true, pec0.matches(pec3));

		System.out.println("Reviewers matches on " + ReviewAirline.Classes.BUSINESS.toString() + " cabin class "
				+ bc0.matches(bc3));
		assertEquals(true, bc0.matches(bc3));

		System.out.println("Reviewers matches on " + ReviewAirline.Classes.FIRSTCLASS.toString() + " cabin class "
				+ fc0.matches(fc3));
		assertEquals(true, fc0.matches(fc3));

		System.out.println("Reviewers matches on " + ReviewAirline.Classes.EMPTY.toString() + " cabin class "
				+ empty0.matches(empty3));
		assertEquals(true, empty0.matches(empty3));

		try {
			empty0.matches(null);
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("-------- END TEST MATCHES --------");

	}

	@Test
	public void testEqualsObject() {

		System.out.println("-------- START TEST EQUALS --------");

		System.out.println("Test not equal");
		assertFalse(eco0.equals(pec0));

		System.out.println("Test same object");
		assertTrue(eco0.equals(eco0));

		System.out.println("Test not equal");
		assertFalse(eco1.equals(eco2));

		ReviewAirline eco4 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "",
				2.0f, 2.0f, 3.0f, 4.0f, 5.0f, false);
		System.out.println("Test equal");
		assertTrue(eco1.equals(eco4));

		System.out.println("Test null");
		assertFalse(eco1.equals(null));

		System.out.println("Test different object");
		assertFalse(eco1.equals(new Integer(10)));

		System.out.println("-------- END TEST EQUALS --------");
	}

	@Test
	public void testCompareTo() {
		System.out.println("-------- START TEST COMPARE --------");

		System.out.println(eco0);
		System.out.println(eco1);
		System.out.println("Compare to: " + eco0.compareTo(eco1));
		assertEquals(-1, eco0.compareTo(eco1));
		
		System.out.println(eco1);
		System.out.println(eco0);
		System.out.println("Compare to: " + eco1.compareTo(eco0));
		assertEquals(1, eco1.compareTo(eco0));

		System.out.println(eco2);
		System.out.println(eco3);
		System.out.println("Compare to: " + eco2.compareTo(eco3));
		assertEquals(0, eco2.compareTo(eco3));

		System.out.println(eco2);
		System.out.println(eco2);
		System.out.println("Compare to same object: " + eco2.compareTo(eco2));
		assertEquals(0, eco2.compareTo(eco2));
		try {
			System.out.println("Compare to null");
			assertEquals(0, eco2.compareTo(null));
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}


		System.out.println("-------- END TEST COMPARE --------");
	}

	@Test
	public void testConstructors() {

		System.out.println("-------- START TEST CONSTRUCTORS --------");

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					-1.0f, 0.0f, 0.0f, 0.0f, false);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					Float.MAX_VALUE, 0.0f, 0.0f, 0.0f, false);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					5.0f, 0.0f, 0.0f, 0.0f, false);
			System.out.println("Correct cabin staff rating: " + eco0.getCabinStaffRating());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					0.0f, 0.0f, 0.0f, 0.0f, false);
			System.out.println("Correct cabin staff rating: " + eco0.getCabinStaffRating());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					0.0f, -1.0f, 0.0f, 0.0f, false);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					0.0f, 6.0f, 0.0f, 0.0f, false);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					0.0f, 5.0f, 0.0f, 0.0f, false);
			System.out.println("Correct food and beverage rating: " + eco0.getFoodBeverageRating());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					0.0f, 0.0f, 0.0f, 0.0f, false);
			System.out.println("Correct food and beverage rating: " + eco0.getFoodBeverageRating());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					0.0f, 0.0f, -1.0f, 0.0f, false);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					0.0f, 0.0f, 6.0f, 0.0f, false);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					0.0f, 0.0f, 5.0f, 0.0f, false);
			System.out.println("Correct in flight entertainment rating: " + eco0.getInflightEntertainmentRating());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					0.0f, 0.0f, 0.0f, 0.0f, false);
			System.out.println("Correct in flight entertainment rating: " + eco0.getInflightEntertainmentRating());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					0.0f, 0.0f, 0.0f, -1.0f, false);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					0.0f, 0.0f, 0.0f, 6.0f, false);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					0.0f, 0.0f, 0.0f, 5.0f, false);
			System.out.println("Correct value money rating: " + eco0.getValueMoneyRating());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					0.0f, 0.0f, 0.0f, 0.0f, false);
			System.out.println("Correct value money rating: " + eco0.getValueMoneyRating());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					0.0f, 0.0f, -1.0f, 0.0f, false);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					0.0f, 0.0f, 6.0f, 0.0f, false);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					0.0f, 0.0f, 5.0f, 0.0f, false);
			System.out.println("Correct in flight entertainment rating: " + eco0.getInflightEntertainmentRating());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", "LUXURY", 0.0f, "", 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, false);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					0.0f, 0.0f, Float.MIN_VALUE, 0.0f, false);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					0.0f, 0.0f, Float.MAX_VALUE, 0.0f, false);
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					0.0f, 0.0f, 5.0f, 0.0f, false);
			System.out.println("Correct in flight entertainment rating: " + eco0.getInflightEntertainmentRating());
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", null, 0.0f, "", 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, false);
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(null, "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f, 0.0f, 0.0f,
					0.0f, 0.0f, false);
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), null, ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					0.0f, 0.0f, 0.0f, 0.0f, false);
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, null, 0.0f,
					0.0f, 0.0f, 0.0f, 0.0f, false);
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}

		try {
			eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f,
					0.0f, 0.0f, 0.0f, 0.0f, true);
			System.out.println("Recommended true");
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("-------- END TEST CONSTRUCTORS --------");

	}

}
