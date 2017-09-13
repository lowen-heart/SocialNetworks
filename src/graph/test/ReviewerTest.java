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

	@Before
	public void setUp() throws Exception {
		r0 = new Reviewer(0, "Bob", "Thomson", "USA");
		r1 = new Reviewer(1, "Gusti", "Garcia", "Argentina");
		r2 = new Reviewer(2, "Gregory", "Gunter", "USA");

		eco0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.ECONOMY.toString(), 0.0f, "", 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f, false);
		fc0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.FIRSTCLASS.toString(), 0.0f, "", 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f, false);
		bc0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.BUSINESS.toString(), 0.0f, "", 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f, false);
		pec0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.PREMIUMECONOMY.toString(), 0.0f, "", 0.0f,
				0.0f, 0.0f, 0.0f, 0.0f, false);
		empty0 = new ReviewAirline(LocalDate.now(), "", ReviewAirline.Classes.EMPTY.toString(), 0.0f, "", 0.0f, 0.0f,
				0.0f, 0.0f, 0.0f, false);

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
		try{
			assertEquals("ECONOMY", r0.getEconomyReviews().get(0).getCabinClass().toString());
		}catch (IndexOutOfBoundsException e) {
			System.out.println("ECONOMY: Array out of bounds");
		}
		
		try {
			assertEquals("PREMIUMECONOMY", r0.getPremiumReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("PREMIUM: Array out of bounds");
		}
		
		try{
			assertEquals("BUSINESS",r0.getBusinessReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("BUSINESS: Array out of bounds");
		}
		
		try{
			assertEquals("FIRSTCLASS",r0.getFirstReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("FIRSTCLASS: Array out of bounds");
		}
		
		try{
			assertEquals("EMPTY",r0.getEmptyReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("EMPTY: Array out of bounds");
		}

		System.out.println("Add review PREMIUM ECONOMY");
		r0.addReview(pec0);
		try{
			assertEquals("ECONOMY", r0.getEconomyReviews().get(0).getCabinClass().toString());
		}catch (IndexOutOfBoundsException e) {
			System.out.println("ECONOMY: Array out of bounds");
		}
		
		try {
			assertEquals("PREMIUMECONOMY", r0.getPremiumReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("PREMIUM: Array out of bounds");
		}
		
		try{
			assertEquals("BUSINESS",r0.getBusinessReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("BUSINESS: Array out of bounds");
		}
		
		try{
			assertEquals("FIRSTCLASS",r0.getFirstReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("FIRSTCLASS: Array out of bounds");
		}
		
		try{
			assertEquals("EMPTY",r0.getEmptyReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("EMPTY: Array out of bounds");
		}

		System.out.println("Add review BUSINESS CLASS");
		r0.addReview(bc0);
		try{
			assertEquals("ECONOMY", r0.getEconomyReviews().get(0).getCabinClass().toString());
		}catch (IndexOutOfBoundsException e) {
			System.out.println("ECONOMY: Array out of bounds");
		}
		
		try {
			assertEquals("PREMIUMECONOMY", r0.getPremiumReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("PREMIUM: Array out of bounds");
		}
		
		try{
			assertEquals("BUSINESS",r0.getBusinessReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("BUSINESS: Array out of bounds");
		}
		
		try{
			assertEquals("FIRSTCLASS",r0.getFirstReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("FIRSTCLASS: Array out of bounds");
		}
		
		try{
			assertEquals("EMPTY",r0.getEmptyReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("EMPTY: Array out of bounds");
		}

		System.out.println("Add review FIRST CLASS");
		r0.addReview(fc0);
		try{
			assertEquals("ECONOMY", r0.getEconomyReviews().get(0).getCabinClass().toString());
		}catch (IndexOutOfBoundsException e) {
			System.out.println("ECONOMY: Array out of bounds");
		}
		
		try {
			assertEquals("PREMIUMECONOMY", r0.getPremiumReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("PREMIUM: Array out of bounds");
		}
		
		try{
			assertEquals("BUSINESS",r0.getBusinessReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("BUSINESS: Array out of bounds");
		}
		
		try{
			assertEquals("FIRSTCLASS",r0.getFirstReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("FIRSTCLASS: Array out of bounds");
		}
		
		try{
			assertEquals("EMPTY",r0.getEmptyReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("EMPTY: Array out of bounds");
		}

		System.out.println("Add review EMPTY");
		r0.addReview(empty0);
		try{
			assertEquals("ECONOMY", r0.getEconomyReviews().get(0).getCabinClass().toString());
		}catch (IndexOutOfBoundsException e) {
			System.out.println("ECONOMY: Array out of bounds");
		}
		
		try {
			assertEquals("PREMIUMECONOMY", r0.getPremiumReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("PREMIUM: Array out of bounds");
		}
		
		try{
			assertEquals("BUSINESS",r0.getBusinessReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("BUSINESS: Array out of bounds");
		}
		
		try{
			assertEquals("FIRSTCLASS",r0.getFirstReviews().get(0).getCabinClass().toString());
		} catch (IndexOutOfBoundsException e) {
			System.out.println("FIRSTCLASS: Array out of bounds");
		}
		
		try{
			assertEquals("EMPTY",r0.getEmptyReviews().get(0).getCabinClass().toString());
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

		System.out.println("-------- END TEST REVIEWERS CLOSENESS --------");
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
