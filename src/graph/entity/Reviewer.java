package graph.entity;

import java.util.ArrayList;
import java.util.List;

public class Reviewer extends Person implements Comparable<Reviewer>{

	private List<ReviewAirline> economyReviews;
	private ReviewAirline avgEconomyReview;
	
	private List<ReviewAirline>	premiumReviews;
	private ReviewAirline avgPremiumReview;
	
	private List<ReviewAirline> businessReviews;
	private ReviewAirline avgBusinessReview;
	
	private List<ReviewAirline> firstReviews;
	private ReviewAirline avgFirstReview;
	
	private List<ReviewAirline> emptyReviews;
	private ReviewAirline avgEmptyReview;
	
	public Reviewer(int id, String name, String surname, String country) {
		super(id, name, surname, country);
		
		economyReviews = new ArrayList<ReviewAirline>();
		premiumReviews = new ArrayList<ReviewAirline>();
		businessReviews = new ArrayList<ReviewAirline>();
		firstReviews = new ArrayList<ReviewAirline>();
		emptyReviews = new ArrayList<ReviewAirline>();
		
		/*avgEconomyReview = new ReviewAirline();
		avgPremiumReview = new ReviewAirline();
		avgBusinessReview = new ReviewAirline();
		avgFirstReview = new ReviewAirline();
		avgEmptyReview = new ReviewAirline();*/
		
	}
	
	public void addReview(ReviewAirline ra){
		switch(ra.getCabinClass()){
		case "ECONOMY":
				economyReviews.add(ra);
				break;
		case "PREMIUMECONOMY":
				premiumReviews.add(ra);
				break;
		case "BUSINESS":
				businessReviews.add(ra);
				break;
		case "FIRSTCLASS":
				firstReviews.add(ra);
				break;
		case "EMPTY":
				emptyReviews.add(ra);
				break;
		}
	}
	
	private void calculateAvgReview(List<ReviewAirline> ra){
		
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
	
	//TODO
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
		
		return super.toString() + "Reviewer [economyReviews=" + economyReviews + ", premiumReviews=" + premiumReviews
				+ ", businessReviews=" + businessReviews + ", firstReviews=" + firstReviews + ", emptyReviews="
				+ emptyReviews + "]";
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
	
	
}
