package graph.entity;

import java.util.ArrayList;
import java.util.List;

public class Reviewer extends Person implements Comparable<Reviewer> {

	private List<ReviewAirline> economyReviews;
	private ReviewAirline avgEconomyReview;

	private List<ReviewAirline> premiumReviews;
	private ReviewAirline avgPremiumReview;

	private List<ReviewAirline> businessReviews;
	private ReviewAirline avgBusinessReview;

	private List<ReviewAirline> firstReviews;
	private ReviewAirline avgFirstReview;

	private List<ReviewAirline> emptyReviews;
	private ReviewAirline avgEmptyReview;

	/**
	 * 
	 * Constructor method of object Reviewer
	 * 
	 * @param id
	 * 			Identifier
	 * @param name
	 * 			Name of reviewer
	 * @param surname
	 * 			Surname of reviewer
	 * @param country
	 * 			Country of reviewer
	 * @throws IllegalAccessException
	 */
	public Reviewer(int id, String name, String surname, String country) throws IllegalAccessException {
		// super(id, name, surname, country);
		this(id, name, surname, country, new ArrayList<ReviewAirline>(),
				new ReviewAirline(ReviewAirline.Classes.ECONOMY.toString()), new ArrayList<ReviewAirline>(),
				new ReviewAirline(ReviewAirline.Classes.PREMIUMECONOMY.toString()), new ArrayList<ReviewAirline>(),
				new ReviewAirline(ReviewAirline.Classes.BUSINESS.toString()), new ArrayList<ReviewAirline>(),
				new ReviewAirline(ReviewAirline.Classes.FIRSTCLASS.toString()), new ArrayList<ReviewAirline>(),
				new ReviewAirline(ReviewAirline.Classes.EMPTY.toString()));
	}

	/**
	 * Constructor method of object Reviewer
	 * 
	 * @param id
	 * 			Identifier
	 * @param name
	 * 			Name of reviewer
	 * @param surname
	 * 			Surname of reviewer
	 * @param country
	 * 			Country of reviewer
	 * @param economyReviews
	 * @param avgEconomyReview
	 * 			Average economy review
	 * @param premiumReviews
	 * @param avgPremiumReview
	 * 			Average premium review
	 * @param businessReviews
	 * @param avgBusinessReview
	 * 			Average business review
	 * @param firstReviews
	 * @param avgFirstReview
	 * 			Average first review
	 * @param emptyReviews
	 * @param avgEmptyReview
	 * 			Average empty review
	 * @throws IllegalAccessException
	 * @throws NullPointerException
	 */
	public Reviewer(int id, String name, String surname, String country, List<ReviewAirline> economyReviews,
			ReviewAirline avgEconomyReview, List<ReviewAirline> premiumReviews, ReviewAirline avgPremiumReview,
			List<ReviewAirline> businessReviews, ReviewAirline avgBusinessReview, List<ReviewAirline> firstReviews,
			ReviewAirline avgFirstReview, List<ReviewAirline> emptyReviews, ReviewAirline avgEmptyReview)
			throws IllegalArgumentException, NullPointerException {

		super(id, name, surname, country);
		if (economyReviews == null || avgEconomyReview == null || premiumReviews == null || avgPremiumReview == null
				|| businessReviews == null || avgBusinessReview == null || firstReviews == null
				|| avgFirstReview == null || emptyReviews == null || avgEmptyReview == null) {
			throw new NullPointerException("One or more arguments passed is/are null");
		}
		this.economyReviews = economyReviews;
		this.avgEconomyReview = avgEconomyReview;
		this.premiumReviews = premiumReviews;
		this.avgPremiumReview = avgPremiumReview;
		this.businessReviews = businessReviews;
		this.avgBusinessReview = avgBusinessReview;
		this.firstReviews = firstReviews;
		this.avgFirstReview = avgFirstReview;
		this.emptyReviews = emptyReviews;
		this.avgEmptyReview = avgEmptyReview;

	}

	/**
	 * Getter method of average economy review
	 * 
	 * @return Average economy review 
	 */
	public ReviewAirline getAvgEconomyReview() {
		return avgEconomyReview;
	}

	/**
	 * Getter method of average premium review
	 * 
	 * @return Average premium review 
	 */
	public ReviewAirline getAvgPremiumReview() {
		return avgPremiumReview;
	}

	/**
	  * Getter method of average business review
	 * 
	 * @return Average business review 
	 */
	public ReviewAirline getAvgBusinessReview() {
		return avgBusinessReview;
	}

	/**
	  * Getter method of average first review
	 * 
	 * @return Average first review 
	 */
	public ReviewAirline getAvgFirstReview() {
		return avgFirstReview;
	}

	/**
	 * Getter method of average empty review
	 * 
	 * @return Average empty review 
	 */
	public ReviewAirline getAvgEmptyReview() {
		return avgEmptyReview;
	}

	/**
	 * Method that adds a new review based on its class
	 * 
	 * @param ra
	 * 			New review to add
	 */
	public void addReview(ReviewAirline ra) {
		if (ra == null) {
			throw new NullPointerException("Argument passed is null");
		}
		switch (ra.getCabinClass()) {
		case "ECONOMY":
			economyReviews.add(ra);
			calculateAvgReview(economyReviews, avgEconomyReview);
			break;
		case "PREMIUMECONOMY":
			premiumReviews.add(ra);
			calculateAvgReview(premiumReviews, avgPremiumReview);
			break;
		case "BUSINESS":
			businessReviews.add(ra);
			calculateAvgReview(businessReviews, avgBusinessReview);
			break;
		case "FIRSTCLASS":
			firstReviews.add(ra);
			calculateAvgReview(firstReviews, avgFirstReview);
			break;
		case "EMPTY":
			emptyReviews.add(ra);
			calculateAvgReview(emptyReviews, avgEmptyReview);
			break;
		default:
			throw new IllegalArgumentException("Cabin class is not one of the pre-defined ones");
		}
	}

	/**
	 * Helper method that calculates average review for all classes
	 * 
	 * @param ra
	 * 			List of reviews
	 * @param avg
	 * 			Current average review
	 */
	private void calculateAvgReview(List<ReviewAirline> ra, ReviewAirline avg) {
		if (ra == null || avg == null) {
			throw new NullPointerException("One or more arguments passed is/are null");
		}
		ReviewAirline temp = new ReviewAirline(avg.getCabinClass());

		for (ReviewAirline r : ra) {

			temp.setOverallRating(temp.getOverallRating() + r.getOverallRating());
			temp.setFoodBeverageRating(temp.getFoodBeverageRating() + r.getFoodBeverageRating());
			temp.setCabinStaffRating(temp.getCabinStaffRating() + r.getCabinStaffRating());
			temp.setInflightEntertainmentRating(temp.getInflightEntertainmentRating() + r.getInflightEntertainmentRating());
			temp.setSeatComfortRating(temp.getSeatComfortRating() + r.getSeatComfortRating());
			temp.setValueMoneyRating(temp.getValueMoneyRating() + r.getValueMoneyRating());

		}

		avg.setOverallRating(temp.getOverallRating() / ra.size());
		avg.setFoodBeverageRating(temp.getFoodBeverageRating() / ra.size());
		avg.setCabinStaffRating(temp.getCabinStaffRating() / ra.size());
		avg.setInflightEntertainmentRating(temp.getInflightEntertainmentRating() / ra.size());
		avg.setSeatComfortRating(temp.getSeatComfortRating() / ra.size());
		avg.setValueMoneyRating(temp.getValueMoneyRating() / ra.size());

	}

	/**
	 * Getter method of economy reviews
	 * 
	 * @return List of economy reviews
	 */
	public List<ReviewAirline> getEconomyReviews() {
		return economyReviews;
	}

	/**
	 * Getter method of premium reviews
	 * 
	 * @return List of premium reviews
	 */
	public List<ReviewAirline> getPremiumReviews() {
		return premiumReviews;
	}

	/**
	 * Getter method of business reviews
	 * 
	 * @return List of business reviews
	 */
	public List<ReviewAirline> getBusinessReviews() {
		return businessReviews;
	}

	/**
	 * Getter method of first reviews
	 * 
	 * @return List of first reviews
	 */
	public List<ReviewAirline> getFirstReviews() {
		return firstReviews;
	}

	/**
	 * Getter method of empty reviews
	 * 
	 * @return List of empty reviews
	 */
	public List<ReviewAirline> getEmptyReviews() {
		return emptyReviews;
	}

	/**
	 * Method that calculates the closeness between this reviewer and another given on a defined cabin class.
	 * It will use a method matches that actually does all the calculation about closeness.
	 * 
	 * @param r
	 * 			Reviewer to compare
	 * @param cabinClass
	 * 			Cabin class on which compare the two reviewer
	 * @return True if the reviewers are close, false otherwise.
	 */
	public boolean reviewersCloseness(Reviewer r, String cabinClass) {

		if(r == null){
			throw new NullPointerException("Reviewer argument is null");
		}
		
		if(cabinClass == null){
			throw new NullPointerException("Cabin class argument is null");
		}
		
		switch (cabinClass) {
		case "ECONOMY":
			return (this.avgEconomyReview.matches(r.avgEconomyReview));
		case "PREMIUMECONOMY":
			return (this.avgPremiumReview.matches(r.avgPremiumReview));
		case "BUSINESS":
			return (this.avgBusinessReview.matches(r.avgBusinessReview));
		case "FIRSTCLASS":
			return (this.avgFirstReview.matches(r.avgFirstReview));
		case "EMPTY":
			return (this.avgEmptyReview.matches(r.avgEmptyReview));
		}

		return false;
	}

	/* (non-Javadoc)
	 * @see graph.entity.Person#print()
	 */
	@Override
	public String print() {
		return super.print() + " ; Reviewer [economyReviews=" + economyReviews + ", avgEconomyReview="
				+ avgEconomyReview + ", premiumReviews=" + premiumReviews + ", avgPremiumReview=" + avgPremiumReview
				+ ", businessReviews=" + businessReviews + ", avgBusinessReview=" + avgBusinessReview
				+ ", firstReviews=" + firstReviews + ", avgFirstReview=" + avgFirstReview + ", emptyReviews="
				+ emptyReviews + ", avgEmptyReview=" + avgEmptyReview + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Reviewer o) {
		System.out.println("Compare Reviewer");
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graph.entity.Person#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graph.entity.Person#toString()
	 */
	@Override
	public String toString() {
		return super.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graph.entity.Person#hashCode()
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
