package graph.entity;

import java.time.LocalDate;
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

	public Reviewer(int id, String name, String surname, String country) {
		// super(id, name, surname, country);
		this(id, name, surname, country, new ArrayList<ReviewAirline>(),
				new ReviewAirline(ReviewAirline.Classes.ECONOMY.toString()), new ArrayList<ReviewAirline>(),
				new ReviewAirline(ReviewAirline.Classes.PREMIUMECONOMY.toString()), new ArrayList<ReviewAirline>(),
				new ReviewAirline(ReviewAirline.Classes.BUSINESS.toString()), new ArrayList<ReviewAirline>(),
				new ReviewAirline(ReviewAirline.Classes.FIRSTCLASS.toString()), new ArrayList<ReviewAirline>(),
				new ReviewAirline(ReviewAirline.Classes.EMPTY.toString()));
	}

	public Reviewer(int id, String name, String surname, String country, List<ReviewAirline> economyReviews,
			ReviewAirline avgEconomyReview, List<ReviewAirline> premiumReviews, ReviewAirline avgPremiumReview,
			List<ReviewAirline> businessReviews, ReviewAirline avgBusinessReview, List<ReviewAirline> firstReviews,
			ReviewAirline avgFirstReview, List<ReviewAirline> emptyReviews, ReviewAirline avgEmptyReview) {

		super(id, name, surname, country);
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

	public ReviewAirline getAvgEconomyReview() {
		return avgEconomyReview;
	}

	public void setAvgEconomyReview(ReviewAirline avgEconomyReview) {
		this.avgEconomyReview = avgEconomyReview;
	}

	public ReviewAirline getAvgPremiumReview() {
		return avgPremiumReview;
	}

	public void setAvgPremiumReview(ReviewAirline avgPremiumReview) {
		this.avgPremiumReview = avgPremiumReview;
	}

	public ReviewAirline getAvgBusinessReview() {
		return avgBusinessReview;
	}

	public void setAvgBusinessReview(ReviewAirline avgBusinessReview) {
		this.avgBusinessReview = avgBusinessReview;
	}

	public ReviewAirline getAvgFirstReview() {
		return avgFirstReview;
	}

	public void setAvgFirstReview(ReviewAirline avgFirstReview) {
		this.avgFirstReview = avgFirstReview;
	}

	public ReviewAirline getAvgEmptyReview() {
		return avgEmptyReview;
	}

	public void setAvgEmptyReview(ReviewAirline avgEmptyReview) {
		this.avgEmptyReview = avgEmptyReview;
	}

	public void addReview(ReviewAirline ra) {
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
		}
	}

	private void calculateAvgReview(List<ReviewAirline> ra, ReviewAirline avg) {
		ReviewAirline temp = new ReviewAirline(avg.getCabinClass());

		for (ReviewAirline r : ra) {

			temp.setOverallRating(temp.getOverallRating() + r.getOverallRating());
			temp.setFoodBeverageRating(temp.getFoodBeverageRating() + r.getFoodBeverageRating());
			temp.setCabinStaffRating(temp.getCabinStaffRating() + r.getCabinStaffRating());
			temp.setInflightEntertainmentRating(
					temp.getInflightEntertainmentRating() + r.getInflightEntertainmentRating());
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

	public List<ReviewAirline> getEconomyReviews() {
		return economyReviews;
	}

	public List<ReviewAirline> getPremiumReviews() {
		return premiumReviews;
	}

	public List<ReviewAirline> getBusinessReviews() {
		return businessReviews;
	}

	public List<ReviewAirline> getFirstReviews() {
		return firstReviews;
	}

	public List<ReviewAirline> getEmptyReviews() {
		return emptyReviews;
	}

	public boolean reviewersCloseness(Reviewer r, String cabinClass) {

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

	// TODO
	@Override
	public int compareTo(Reviewer o) {

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		return super.toString() + " ; Reviewer [economyReviews=" + economyReviews + ", avgEconomyReview="
				+ avgEconomyReview + ", premiumReviews=" + premiumReviews + ", avgPremiumReview=" + avgPremiumReview
				+ ", businessReviews=" + businessReviews + ", avgBusinessReview=" + avgBusinessReview
				+ ", firstReviews=" + firstReviews + ", avgFirstReview=" + avgFirstReview + ", emptyReviews="
				+ emptyReviews + ", avgEmptyReview=" + avgEmptyReview + "]";
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

}
