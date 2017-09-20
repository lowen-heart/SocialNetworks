package graph.entity;

import java.time.LocalDate;

/**
 * @author LP
 *
 */
public class ReviewAirline extends Review implements Comparable<ReviewAirline> {

	/**
	 * @author LP
	 * 
	 * Enum class that defines cabin classes
	 *
	 */
	public enum Classes {
		ECONOMY, PREMIUMECONOMY, BUSINESS, FIRSTCLASS, EMPTY
	};

	private String name;
	private String cabinClass;
	private float seatComfortRating;
	private float cabinStaffRating;
	private float foodBeverageRating;
	private float inflightEntertainmentRating;
	private float valueMoneyRating;

	/**
	 * Constructor method of ReviewAirline object
	 * 
	 * @param date
	 * @param content
	 * @param cabinClass
	 * 			Cabin class in one of the pre-defined, see enum Classes
	 * @param overallRating
	 * @param name
	 * 			Name of airline company
	 * @param seatComfortRating
	 * @param cabinStaffRating
	 * @param foodBeverageRating
	 * @param inflightEntertainmentRating
	 * @param valueMoneyRating
	 * @param recommended
	 */
	public ReviewAirline(LocalDate date, String content, String cabinClass, float overallRating, String name,
			float seatComfortRating, float cabinStaffRating, float foodBeverageRating,
			float inflightEntertainmentRating, float valueMoneyRating, boolean recommended) {

		super(date, content, overallRating, recommended);

		if(cabinClass == null){
			throw new NullPointerException("Cabin class is null");
		}
		
		if(name == null){
			throw new NullPointerException("Name is null");
		}
		
		if (seatComfortRating < 0 || seatComfortRating > 5) {
			throw new IllegalArgumentException("Seat comfort rating is less than 0 or greater than 5");
		}
		if (cabinStaffRating < 0 || cabinStaffRating > 5) {
			throw new IllegalArgumentException("Cabin staff rating is less than 0 or greater than 5");
		}
		if (foodBeverageRating < 0 || foodBeverageRating > 5) {
			throw new IllegalArgumentException("Food and beverage rating is less than 0 or greater than 5");
		}
		if (inflightEntertainmentRating < 0 || inflightEntertainmentRating > 5) {
			throw new IllegalArgumentException("In-flight entertainment rating is less than 0 or greater than 5");
		}
		if (valueMoneyRating < 0 || valueMoneyRating > 5) {
			throw new IllegalArgumentException("Value money rating is less than 0 or greater than 5");
		}

		this.name = name;
		
		switch (cabinClass) {
		case "Economy":
		case "ECONOMY":
			this.cabinClass = ReviewAirline.Classes.ECONOMY.toString();
			break;
		case "First Class":
		case "FIRSTCLASS":
			this.cabinClass = ReviewAirline.Classes.FIRSTCLASS.toString();
			break;
		case "Business Class":
		case "BUSINESS":
			this.cabinClass = ReviewAirline.Classes.BUSINESS.toString();
			break;
		case "Premium Economy":
		case "PREMIUMECONOMY":
			this.cabinClass = ReviewAirline.Classes.PREMIUMECONOMY.toString();
			break;
		case "":
		case "EMPTY":
			this.cabinClass = ReviewAirline.Classes.EMPTY.toString();
			break;
		default:
			throw new IllegalArgumentException("Review is not one of the pre-defined classes");
		}
		
		this.seatComfortRating = seatComfortRating;
		this.cabinStaffRating = cabinStaffRating;
		this.foodBeverageRating = foodBeverageRating;
		this.inflightEntertainmentRating = inflightEntertainmentRating;
		this.valueMoneyRating = valueMoneyRating;

	}

	/**
	 * Basic constructor method of object ReviewAirline.
	 * It will create a review with current date, no content, no name, a defined cabin class and all zeros ratings.
	 * 
	 * @param cabinClass
	 * 			cabin class defined for the review
	 */
	public ReviewAirline(String cabinClass) {
		this(LocalDate.now(), "", cabinClass, 0.0f, "", 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, false);
	}

	/**
	 * Getter method of name instance variable
	 * 
	 * @return Name of airline company
	 */
	public String getName() {
		return name;
	}

	/**
	 * Getter method of cabin class instance variable
	 * 
	 * @return Cabin class
	 */
	public String getCabinClass() {
		return cabinClass;
	}

	/**
	 * Getter method of seat comfort rating
	 * 
	 * @return seat comfort rating
	 */
	public float getSeatComfortRating() {
		return seatComfortRating;
	}

	/**
	 * Setter method of seat comfort rating
	 * 
	 * @param seatComfortRating
	 */
	public void setSeatComfortRating(float seatComfortRating) {
		this.seatComfortRating = seatComfortRating;
	}

	/**
	 * Getter method of cabin staff rating
	 * 
	 * @return cabin staff rating
	 */
	public float getCabinStaffRating() {
		return cabinStaffRating;
	}

	/**
	 * Setter method of cabin staff rating
	 * 
	 * @param cabinStaffRating
	 */
	public void setCabinStaffRating(float cabinStaffRating) {
		this.cabinStaffRating = cabinStaffRating;
	}

	/**
	 * Getter method of food and beverage rating
	 * 
	 * @return food and beverage rating
	 */
	public float getFoodBeverageRating() {
		return foodBeverageRating;
	}

	/**
	 * Setter method of food and beverage rating
	 * 
	 * @param foodBeverageRating
	 */
	public void setFoodBeverageRating(float foodBeverageRating) {
		this.foodBeverageRating = foodBeverageRating;
	}

	/**
	 * Getter method of in flight entertainment rating
	 * 
	 * @return in flight entertainment rating
	 */
	public float getInflightEntertainmentRating() {
		return inflightEntertainmentRating;
	}

	/**
	 * Setter method of in flight entertainment rating
	 * 
	 * @param inflightEntertainmentRating
	 */
	public void setInflightEntertainmentRating(float inflightEntertainmentRating) {
		this.inflightEntertainmentRating = inflightEntertainmentRating;
	}

	/**
	 * Getter method of value money rating
	 * 
	 * @return value money rating
	 */
	public float getValueMoneyRating() {
		return valueMoneyRating;
	}

	/**
	 * Setter method of value money rating
	 * 
	 * @param valueMoneyRating
	 */
	public void setValueMoneyRating(float valueMoneyRating) {
		this.valueMoneyRating = valueMoneyRating;
	}

	/**
	 * Core method that checks if this reviewer and another passed through argument are a match or not.
	 * They are a match if the delta of at least 4 of 5 detailed review are in the range -/+ 1
	 *  
	 * @param r
	 * 			Reviewer to compare
	 * @return
	 * 			true they are a match (4+ detailed review -1 < x < +1), false otherwise
	 */
	public boolean matches(ReviewAirline r) {
		
		if (r == null) {
			throw new NullPointerException("Airline review is null");
		}

		if(!this.getCabinClass().equals(r.getCabinClass())){
			throw new IllegalArgumentException("Reviews are not on the same cabin class");
		}
		
		//counter for matches
		int matches = 0;

		//calculate all the delta between the ratings. It must be a delta +/- 1 to be ok as close review
		float cabinDelta = this.getCabinStaffRating() - r.getCabinStaffRating();
		float foodDelta = this.getFoodBeverageRating() - r.getFoodBeverageRating();
		float entertainmentDelta = this.getInflightEntertainmentRating() - r.getInflightEntertainmentRating();
		float seatDelta = this.getSeatComfortRating() - r.getSeatComfortRating();
		float moneyDelta = this.getValueMoneyRating() - r.getValueMoneyRating();

		if (cabinDelta >= -1 && cabinDelta <= 1) {
			matches++;
		}
		if (foodDelta >= -1 && foodDelta <= 1) {
			matches++;
		}
		if (entertainmentDelta >= -1 && entertainmentDelta <= 1) {
			matches++;
		}
		if (seatDelta >= -1 && seatDelta <= 1) {
			matches++;
		}
		if (moneyDelta >= -1 && moneyDelta <= 1) {
			matches++;
		}

		//if there are 4 or more matches the two reviews are close each other
		if (matches >= 4) {
			return true;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see graph.entity.Review#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + " ; ReviewAirline [name=" + name + ", cabinClass=" + cabinClass
				+ ", seatComfortRating=" + seatComfortRating + ", cabinStaffRating=" + cabinStaffRating
				+ ", foodBeverageRating=" + foodBeverageRating + ", inflightEntertainmentRating="
				+ inflightEntertainmentRating + ", valueMoneyRating=" + valueMoneyRating + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj)
			return true;
		if (obj == null || !(obj instanceof ReviewAirline)) {
			return false;
		}

		ReviewAirline reviewAirline = (ReviewAirline) obj;

		if (this.cabinClass.equals(reviewAirline.getCabinClass())
				&& this.cabinStaffRating == reviewAirline.getCabinStaffRating()
				&& this.foodBeverageRating == reviewAirline.getFoodBeverageRating()
				&& this.inflightEntertainmentRating == reviewAirline.getInflightEntertainmentRating()
				&& this.seatComfortRating == reviewAirline.getSeatComfortRating()
				&& this.valueMoneyRating == reviewAirline.getValueMoneyRating()
				&& this.name.equals(reviewAirline.getName())) {
			return true;
		}

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(ReviewAirline reviewAirline) {

		if (this == reviewAirline)
			return 0;
		if (reviewAirline == null) {
			throw new NullPointerException("Object is null");
		}

		if (equals(reviewAirline)) {
			return 0;
		} else {
			if (this.cabinClass.equals(reviewAirline.getCabinClass())
					&& this.cabinStaffRating >= reviewAirline.getCabinStaffRating()
					&& this.foodBeverageRating >= reviewAirline.getFoodBeverageRating()
					&& this.inflightEntertainmentRating >= reviewAirline.getInflightEntertainmentRating()
					&& this.seatComfortRating >= reviewAirline.getSeatComfortRating()
					&& this.valueMoneyRating >= reviewAirline.getValueMoneyRating()
					&& this.name.equals(reviewAirline.getName())) {
				return 1;
			} else if (this.cabinClass.equals(reviewAirline.getCabinClass())
					&& this.cabinStaffRating <= reviewAirline.getCabinStaffRating()
					&& this.foodBeverageRating <= reviewAirline.getFoodBeverageRating()
					&& this.inflightEntertainmentRating <= reviewAirline.getInflightEntertainmentRating()
					&& this.seatComfortRating <= reviewAirline.getSeatComfortRating()
					&& this.valueMoneyRating <= reviewAirline.getValueMoneyRating()
					&& this.name.equals(reviewAirline.getName())) {
				return -1;
			}
		}

		return 0;

	}

}
