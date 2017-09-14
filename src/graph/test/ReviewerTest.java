package graph.test;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import graph.entity.Person;
import graph.entity.ReviewAirline;
import graph.entity.Reviewer;

public class ReviewerTest {

	Reviewer r0;
	Reviewer r1;
	Reviewer r2;
	Reviewer r3;
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
		r0 = new Reviewer(0, "Bob", "Thomson", "USA");
		r1 = new Reviewer(1, "Gusti", "Garcia", "Argentina");
		r2 = new Reviewer(2, "Gregory", "Gunter", "USA");

		eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f, false);
		eco1 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 2.0f, 2.0f,
				3.0f, 4.0f, 5.0f, false);
		eco2 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f, 1.0f,
				3.0f, 3.0f, 4.0f, false);
		eco3 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f, 1.0f,
				1.0f, 3.0f, 4.0f, false);
		
		fc0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.FIRSTCLASS.toString(), 0.0f, "", 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f, false);
		fc1 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.FIRSTCLASS.toString(), 0.0f, "", 2.0f, 2.0f,
				3.0f, 4.0f, 5.0f, false);
		fc2 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.FIRSTCLASS.toString(), 0.0f, "", 0.0f, 1.0f,
				3.0f, 3.0f, 4.0f, false);
		fc3 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.FIRSTCLASS.toString(), 0.0f, "", 0.0f, 1.0f,
				1.0f, 3.0f, 4.0f, false);
		
		bc0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.BUSINESS.toString(), 0.0f, "", 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f, false);
		bc1 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.BUSINESS.toString(), 0.0f, "", 2.0f, 2.0f,
				3.0f, 4.0f, 5.0f, false);
		bc2 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.BUSINESS.toString(), 0.0f, "", 0.0f, 1.0f,
				3.0f, 3.0f, 4.0f, false);
		bc3 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.BUSINESS.toString(), 0.0f, "", 0.0f, 1.0f,
				1.0f, 3.0f, 4.0f, false);
		
		pec0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.PREMIUMECONOMY.toString(), 0.0f, "", 0.0f,
				0.0f, 0.0f, 0.0f, 0.0f, false);
		pec1 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.PREMIUMECONOMY.toString(), 0.0f, "", 2.0f, 2.0f,
				3.0f, 4.0f, 5.0f, false);
		pec2 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.PREMIUMECONOMY.toString(), 0.0f, "", 0.0f, 1.0f,
				3.0f, 3.0f, 4.0f, false);
		pec3 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.PREMIUMECONOMY.toString(), 0.0f, "", 0.0f, 1.0f,
				1.0f, 3.0f, 4.0f, false);
		
		empty0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.EMPTY.toString(), 0.0f, "", 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f, false);
		empty1 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.EMPTY.toString(), 0.0f, "", 2.0f, 2.0f,
				3.0f, 4.0f, 5.0f, false);
		empty2 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.EMPTY.toString(), 0.0f, "", 0.0f, 1.0f,
				3.0f, 3.0f, 4.0f, false);
		empty3 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.EMPTY.toString(), 0.0f, "", 0.0f, 1.0f,
				1.0f, 3.0f, 4.0f, false);
		

		r3 = new Reviewer(3, "Test", "Person", "Unknown", new ArrayList<ReviewAirline>(),
				new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 10.0f, "", 5.0f, 5.0f,
						5.0f, 5.0f, 5.0f, false),
				new ArrayList<ReviewAirline>(),
				new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.PREMIUMECONOMY.toString(), 10.0f, "", 5.0f,
						5.0f, 5.0f, 5.0f, 5.0f, false),
				new ArrayList<ReviewAirline>(),
				new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.BUSINESS.toString(), 10.0f, "", 5.0f, 5.0f,
						5.0f, 5.0f, 5.0f, false),
				new ArrayList<ReviewAirline>(),
				new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.FIRSTCLASS.toString(), 10.0f, "", 5.0f,
						5.0f, 5.0f, 5.0f, 5.0f, false),
				new ArrayList<ReviewAirline>(), new ReviewAirline(LocalDate.now(), "",
						ReviewAirline.Classes.EMPTY.toString(), 10.0f, "", 5.0f, 5.0f, 5.0f, 5.0f, 5.0f, false));
	}

	@Test
	public void testAddReview() {
		System.out.println("-------- START TEST ADD REVIEW --------");

		System.out.println("Add review ECONOMY");
		r0.addReview(eco0);
		try {
			assertEquals("ECONOMY", r0.getEconomyReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("ECONOMY: Array out of bounds");
		}

		try {
			assertEquals("PREMIUMECONOMY", r0.getPremiumReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("PREMIUM: Array out of bounds");
		}

		try {
			assertEquals("BUSINESS", r0.getBusinessReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("BUSINESS: Array out of bounds");
		}

		try {
			assertEquals("FIRSTCLASS", r0.getFirstReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("FIRSTCLASS: Array out of bounds");
		}

		try {
			assertEquals("EMPTY", r0.getEmptyReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("EMPTY: Array out of bounds");
		}

		System.out.println("Add review PREMIUM ECONOMY");
		r0.addReview(pec0);
		try {
			assertEquals("ECONOMY", r0.getEconomyReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("ECONOMY: Array out of bounds");
		}

		try {
			assertEquals("PREMIUMECONOMY", r0.getPremiumReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("PREMIUM: Array out of bounds");
		}

		try {
			assertEquals("BUSINESS", r0.getBusinessReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("BUSINESS: Array out of bounds");
		}

		try {
			assertEquals("FIRSTCLASS", r0.getFirstReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("FIRSTCLASS: Array out of bounds");
		}

		try {
			assertEquals("EMPTY", r0.getEmptyReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("EMPTY: Array out of bounds");
		}

		System.out.println("Add review BUSINESS CLASS");
		r0.addReview(bc0);
		try {
			assertEquals("ECONOMY", r0.getEconomyReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("ECONOMY: Array out of bounds");
		}

		try {
			assertEquals("PREMIUMECONOMY", r0.getPremiumReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("PREMIUM: Array out of bounds");
		}

		try {
			assertEquals("BUSINESS", r0.getBusinessReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("BUSINESS: Array out of bounds");
		}

		try {
			assertEquals("FIRSTCLASS", r0.getFirstReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("FIRSTCLASS: Array out of bounds");
		}

		try {
			assertEquals("EMPTY", r0.getEmptyReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("EMPTY: Array out of bounds");
		}

		System.out.println("Add review FIRST CLASS");
		r0.addReview(fc0);
		try {
			assertEquals("ECONOMY", r0.getEconomyReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("ECONOMY: Array out of bounds");
		}

		try {
			assertEquals("PREMIUMECONOMY", r0.getPremiumReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("PREMIUM: Array out of bounds");
		}

		try {
			assertEquals("BUSINESS", r0.getBusinessReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("BUSINESS: Array out of bounds");
		}

		try {
			assertEquals("FIRSTCLASS", r0.getFirstReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("FIRSTCLASS: Array out of bounds");
		}

		try {
			assertEquals("EMPTY", r0.getEmptyReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("EMPTY: Array out of bounds");
		}

		System.out.println("Add review EMPTY");
		r0.addReview(empty0);
		try {
			assertEquals("ECONOMY", r0.getEconomyReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("ECONOMY: Array out of bounds");
		}

		try {
			assertEquals("PREMIUMECONOMY", r0.getPremiumReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("PREMIUM: Array out of bounds");
		}

		try {
			assertEquals("BUSINESS", r0.getBusinessReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("BUSINESS: Array out of bounds");
		}

		try {
			assertEquals("FIRSTCLASS", r0.getFirstReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("FIRSTCLASS: Array out of bounds");
		}

		try {
			assertEquals("EMPTY", r0.getEmptyReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("EMPTY: Array out of bounds");
		}

		try {
			System.out.println("Add review null");
			r0.addReview(null);
		} catch (NullPointerException e) {
			System.out.println("Null pointer exception catched");
		}

		try {
			System.out.println("Add review that is not in the class");
			ReviewAirline luxury = new ReviewAirline(LocalDate.now(), "", "SUPER LUXURY", 0.0f, "", 0.0f, 0.0f, 0.0f,
					0.0f, 0.0f, false);
			r0.addReview(luxury);

		} catch (IllegalArgumentException e) {
			System.out.println("Illegal argument exceptiion catched");
		}

		ReviewAirline eco1 = new ReviewAirline(LocalDate.now(), "", "Economy", 0.0f, "", 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
				false);
		r0.addReview(eco1);

		System.out.println("-------- END TEST ADD REVIEW--------");
	}

	@Test
	public void testReviewersCloseness() {
		System.out.println("-------- START TEST REVIEWERS CLOSENESS --------");

		try {
			r0.reviewersCloseness(null, ReviewAirline.Classes.ECONOMY.toString());
		} catch (NullPointerException e) {
			System.out.println("Null review argument");
		}

		r0.reviewersCloseness(r1, "LUXURY");
		System.out.println("Reviewers closeness on a non-existent cabin class " + r0.reviewersCloseness(r1, "LUXURY"));
		assertEquals(false, r0.reviewersCloseness(r1, "LUXURY"));

		r0.reviewersCloseness(r1, "");
		System.out.println("Reviewers closeness on a empty string cabin class " + r0.reviewersCloseness(r1, ""));
		assertEquals(false, r0.reviewersCloseness(r1, ""));

		try {
			r0.reviewersCloseness(r1, null);
		} catch (NullPointerException e) {
			System.out.println("Reviewers closeness on a null cabin class");
		}

		r0.addReview(eco1);
		r0.addReview(fc1);
		r0.addReview(bc1);
		r0.addReview(pec1);
		r0.addReview(empty1);
		r1.addReview(eco2);
		r1.addReview(fc2);
		r1.addReview(bc2);
		r1.addReview(pec2);
		r1.addReview(empty2);

		closenessHelper(r0, r1,true);
		
		r0.addReview(eco3);
		r0.addReview(fc3);
		r0.addReview(bc3);
		r0.addReview(pec3);
		r0.addReview(empty3);

		closenessHelper(r0, r1, false);

		System.out.println("-------- END TEST REVIEWERS CLOSENESS --------");
	}

	private void closenessHelper(Reviewer r0, Reviewer r1, boolean expected) {

		System.out.println("Reviewers closeness on " + ReviewAirline.Classes.ECONOMY.toString() + " cabin class "
				+ r0.reviewersCloseness(r1, ReviewAirline.Classes.ECONOMY.toString()));
		assertEquals(expected, r0.reviewersCloseness(r1, ReviewAirline.Classes.ECONOMY.toString()));

		System.out.println("Reviewers closeness on " + ReviewAirline.Classes.PREMIUMECONOMY.toString() + " cabin class "
				+ r0.reviewersCloseness(r1, ReviewAirline.Classes.PREMIUMECONOMY.toString()));
		assertEquals(expected, r0.reviewersCloseness(r1, ReviewAirline.Classes.PREMIUMECONOMY.toString()));

		System.out.println("Reviewers closeness on " + ReviewAirline.Classes.BUSINESS.toString() + " cabin class "
				+ r0.reviewersCloseness(r1, ReviewAirline.Classes.BUSINESS.toString()));
		assertEquals(expected, r0.reviewersCloseness(r1, ReviewAirline.Classes.BUSINESS.toString()));

		System.out.println("Reviewers closeness on " + ReviewAirline.Classes.FIRSTCLASS.toString() + " cabin class "
				+ r0.reviewersCloseness(r1, ReviewAirline.Classes.FIRSTCLASS.toString()));
		assertEquals(expected, r0.reviewersCloseness(r1, ReviewAirline.Classes.FIRSTCLASS.toString()));

		System.out.println("Reviewers closeness on " + ReviewAirline.Classes.EMPTY.toString() + " cabin class "
				+ r0.reviewersCloseness(r1, ReviewAirline.Classes.EMPTY.toString()));
		assertEquals(expected, r0.reviewersCloseness(r1, ReviewAirline.Classes.EMPTY.toString()));

	}

	@Test
	public void testCalculateAvgReview() {
		System.out.println("-------- START TEST CALCULATE AVG REVIEW --------");

		System.out.println("-------- END TEST CALCULATE AVG REVIEW --------");
	}

	@Test
	public void testConstructors() {
		System.out.println("-------- START TEST CONSTRUCTORS --------");

		System.out.println("-------- END TEST CONSTRUCTORS--------");
	}

}
