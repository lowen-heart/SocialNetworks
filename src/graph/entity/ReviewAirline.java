package graph.entity;

import java.time.LocalDate;

public class ReviewAirline extends Review implements Comparable {

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

	public ReviewAirline(LocalDate date, String content, String cabinClass, float overallRating, String name,
			float seatComfortRating, float cabinStaffRating, float foodBeverageRating,
			float inflightEntertainmentRating, float valueMoneyRating, boolean recommended) {

		super(date, content, overallRating, recommended);

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
		}
		this.seatComfortRating = seatComfortRating;
		this.cabinStaffRating = cabinStaffRating;
		this.foodBeverageRating = foodBeverageRating;
		this.inflightEntertainmentRating = inflightEntertainmentRating;
		this.valueMoneyRating = valueMoneyRating;

	}

	public ReviewAirline(String cabinClass) {
		this(LocalDate.now(), "", cabinClass, 0.0f, "", 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, false);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCabinClass() {
		return cabinClass;
	}

	public void setCabinClass(String cabinClass) {
		this.cabinClass = cabinClass;
	}

	public float getSeatComfortRating() {
		return seatComfortRating;
	}

	public void setSeatComfortRating(float seatComfortRating) {
		this.seatComfortRating = seatComfortRating;
	}

	public float getCabinStaffRating() {
		return cabinStaffRating;
	}

	public void setCabinStaffRating(float cabinStaffRating) {
		this.cabinStaffRating = cabinStaffRating;
	}

	public float getFoodBeverageRating() {
		return foodBeverageRating;
	}

	public void setFoodBeverageRating(float foodBeverageRating) {
		this.foodBeverageRating = foodBeverageRating;
	}

	public float getInflightEntertainmentRating() {
		return inflightEntertainmentRating;
	}

	public void setInflightEntertainmentRating(float inflightEntertainmentRating) {
		this.inflightEntertainmentRating = inflightEntertainmentRating;
	}

	public float getValueMoneyRating() {
		return valueMoneyRating;
	}

	public void setValueMoneyRating(float valueMoneyRating) {
		this.valueMoneyRating = valueMoneyRating;
	}

	public boolean matches(ReviewAirline r) {

		int matches = 0;
		float limit = 1.0f;

		if ((this.getCabinStaffRating() <= (r.getCabinStaffRating() + limit))
				&& (this.getCabinStaffRating() >= (r.getCabinStaffRating() - limit))) {
			matches++;
		}
		if ((this.getFoodBeverageRating() <= (r.getFoodBeverageRating() + limit))
				&& (this.getFoodBeverageRating() >= (r.getFoodBeverageRating() + limit))) {
			matches++;
		}
		if ((this.getInflightEntertainmentRating() <= (r.getInflightEntertainmentRating() + limit))
				&& (this.getInflightEntertainmentRating() >= (r.getInflightEntertainmentRating() - limit))) {
			matches++;
		}
		if ((this.getSeatComfortRating() <= (r.getSeatComfortRating() + limit))
				&& (this.getFoodBeverageRating() >= (r.getSeatComfortRating() - limit))) {
			matches++;
		}
		if ((this.getValueMoneyRating() <= (r.getValueMoneyRating() + limit))
				&& (this.getFoodBeverageRating() >= (r.getValueMoneyRating() - limit))) {
			matches++;
		}

		if (matches >= 4) {
			return true;
		}

		return false;
	}

	@Override
	public String toString() {
		return super.toString() + " ; ReviewAirline [name=" + name + ", cabinClass=" + cabinClass
				+ ", seatComfortRating=" + seatComfortRating + ", cabinStaffRating=" + cabinStaffRating
				+ ", foodBeverageRating=" + foodBeverageRating + ", inflightEntertainmentRating="
				+ inflightEntertainmentRating + ", valueMoneyRating=" + valueMoneyRating + "]";
	}

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

	@Override
	public int compareTo(Object o) {

		if (this == o)
			return 0;
		if (o == null || !(o instanceof ReviewAirline)) {
			throw new IllegalArgumentException("Object is null or not a ReviewAirline object");
		}
		
		ReviewAirline reviewAirline = (ReviewAirline) o;
		
		if (equals(o)) {
			return 0;
		}else {
			if (this.cabinClass.equals(reviewAirline.getCabinClass())
					&& this.cabinStaffRating >= reviewAirline.getCabinStaffRating()
					&& this.foodBeverageRating >= reviewAirline.getFoodBeverageRating()
					&& this.inflightEntertainmentRating >= reviewAirline.getInflightEntertainmentRating()
					&& this.seatComfortRating >= reviewAirline.getSeatComfortRating()
					&& this.valueMoneyRating >= reviewAirline.getValueMoneyRating()
					&& this.name.equals(reviewAirline.getName())){
				return 1;
			}else{
				return -1;
			}
		}
	
	}

}
