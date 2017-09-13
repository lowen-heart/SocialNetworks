package graph.entity;

import java.time.LocalDate;

/**
 * @author LP
 *
 */
public class ReviewAirline extends Review implements Comparable<ReviewAirline> {

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
	 * @param date
	 * @param content
	 * @param cabinClass
	 * @param overallRating
	 * @param name
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
	 * @param cabinClass
	 */
	public ReviewAirline(String cabinClass) {
		this(LocalDate.now(), "", cabinClass, 0.0f, "", 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, false);
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public String getCabinClass() {
		return cabinClass;
	}

	/**
	 * @return
	 */
	public float getSeatComfortRating() {
		return seatComfortRating;
	}

	/**
	 * @param seatComfortRating
	 */
	public void setSeatComfortRating(float seatComfortRating) {
		this.seatComfortRating = seatComfortRating;
	}

	/**
	 * @return
	 */
	public float getCabinStaffRating() {
		return cabinStaffRating;
	}

	/**
	 * @param cabinStaffRating
	 */
	public void setCabinStaffRating(float cabinStaffRating) {
		this.cabinStaffRating = cabinStaffRating;
	}

	/**
	 * @return
	 */
	public float getFoodBeverageRating() {
		return foodBeverageRating;
	}

	/**
	 * @param foodBeverageRating
	 */
	public void setFoodBeverageRating(float foodBeverageRating) {
		this.foodBeverageRating = foodBeverageRating;
	}

	/**
	 * @return
	 */
	public float getInflightEntertainmentRating() {
		return inflightEntertainmentRating;
	}

	/**
	 * @param inflightEntertainmentRating
	 */
	public void setInflightEntertainmentRating(float inflightEntertainmentRating) {
		this.inflightEntertainmentRating = inflightEntertainmentRating;
	}

	/**
	 * @return
	 */
	public float getValueMoneyRating() {
		return valueMoneyRating;
	}

	/**
	 * @param valueMoneyRating
	 */
	public void setValueMoneyRating(float valueMoneyRating) {
		this.valueMoneyRating = valueMoneyRating;
	}

	/**
	 * @param r
	 * @return
	 */
	public boolean matches(ReviewAirline r) {

		if (r == null) {
			throw new NullPointerException("Airline review is null");
		}
		int matches = 0;

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
	public int compareTo(ReviewAirline o) {

		if (this == o)
			return 0;
		if (o == null || !(o instanceof ReviewAirline)) {
			throw new IllegalArgumentException("Object is null or not a ReviewAirline object");
		}

		ReviewAirline reviewAirline = (ReviewAirline) o;

		if (equals(o)) {
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

		return -3;

	}

}
