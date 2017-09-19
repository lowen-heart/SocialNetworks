package graph.entity;

import java.time.LocalDate;

/**
 * @author LP
 *
 */
public class Review {
	
	private LocalDate date;
	private String content;
	private float overallRating;
	private boolean recommended;
	
	/**
	 * Constructor of object Review
	 * 
	 * @param date
	 * 				Date of the review
	 * @param content
	 * 				Content of the review
	 * @param overallRating
	 * 				Overall rating of the review
	 * @param recommended
	 * 				1 is recommended, 0 is not recommended
	 */
	public Review(LocalDate date, String content, float overallRating, boolean recommended) {
		super();
		if(date == null || content == null){
			throw new NullPointerException("Date or content is null");
		}
		if(overallRating < 0 || overallRating > 10){
			throw new IllegalArgumentException("Overall rating needs to be between 0 and 10");
		}
		this.date = date;
		this.content = content;
		this.overallRating = overallRating;
		this.recommended = recommended;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Review [date=" + date + ", content=" + content + ", overallRating=" + overallRating + ", recommended="
				+ recommended + "]";
	}

	/**
	 * Getter method of date instance variable
	 * 
	 * @return
	 * 			date value
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Getter method of content instance variable
	 * 
	 * @return
	 * 			content value
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Getter method of overall rating instance variable
	 * 
	 * @return
	 * 			overall rating value
	 */
	public float getOverallRating() {
		return overallRating;
	}

	/**
	 * Setter method of overall rating instance variable
	 * 
	 * @param overallRating
	 * 						new value
	 */
	public void setOverallRating(float overallRating) {
		this.overallRating = overallRating;
	}

	/**
	 * Method that checks if the review is recommended or not
	 * 
	 * @return
	 */
	public boolean isRecommended() {
		return recommended;
	}

	
}
