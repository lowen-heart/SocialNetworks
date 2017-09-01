package graph.entity;

import java.util.Date;

public class ReviewAirline extends Review {
	
	public enum Classes{ECONOMY, PREMIUMECONOMY, BUSINESS, FIRSTCLASS, EMPTY};
	
	private String name;
	private String cabinClass;
	private float seatComfortRating;
	private float cabinStaffRating;
	private float foodBeverageRating;
	private float inflightEntertainmentRating;
	private float valueMoneyRating;
	
	public ReviewAirline(Date date, String content, String cabinClass, float overallRating, String name,
			float seatComfortRating, float cabinStaffRating, float foodBeverageRating,
			float inflightEntertainmentRating, float valueMoneyRating, boolean recommended) {
		
		super(date, content, overallRating, recommended);
		
		this.name = name;
		switch(cabinClass){
			case "Economy" :
				this.cabinClass = ReviewAirline.Classes.ECONOMY.toString();
				break;
			case "First Class" :
				this.cabinClass = ReviewAirline.Classes.FIRSTCLASS.toString();
				break;
			case "Business Class" :
				this.cabinClass = ReviewAirline.Classes.BUSINESS.toString();
				break;
			case "Premium Economy" :
				this.cabinClass = ReviewAirline.Classes.PREMIUMECONOMY.toString();
				break;
			case "" :
				this.cabinClass = ReviewAirline.Classes.EMPTY.toString();
				break;
		}
		this.seatComfortRating = seatComfortRating;
		this.cabinStaffRating = cabinStaffRating;
		this.foodBeverageRating = foodBeverageRating;
		this.inflightEntertainmentRating = inflightEntertainmentRating;
		this.valueMoneyRating = valueMoneyRating;

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

	@Override
	public String toString() {
		return "ReviewAirline [name=" + name + ", cabinClass=" + cabinClass
				+ ", seatComfortRating=" + seatComfortRating + ", cabinStaffRating=" + cabinStaffRating
				+ ", foodBeverageRating=" + foodBeverageRating + ", inflightEntertainmentRating="
				+ inflightEntertainmentRating + ", valueMoneyRating=" + valueMoneyRating + "]";
	}

	/*public ReviewAirline() {
		this(new Date("1970-01-01"),"","", 0.0f,"",0.0f,0.0f,0.0f,0.0f,0.0f,false);
	}*/

}
