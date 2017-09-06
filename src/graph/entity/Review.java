package graph.entity;

import java.time.LocalDate;

public class Review {
	
	private LocalDate date;
	private String content;
	private float overallRating;
	private boolean recommended;
	
	/**
	 * @param date
	 * @param content
	 * @param overallRating
	 * @param recommended
	 */
	public Review(LocalDate date, String content, float overallRating, boolean recommended) {
		super();
		if(date == null || content == null){
			throw new IllegalArgumentException("Date or content is null");
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
	 * @return
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * @return
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @return
	 */
	public float getOverallRating() {
		return overallRating;
	}

	/**
	 * @param overallRating
	 */
	public void setOverallRating(float overallRating) {
		this.overallRating = overallRating;
	}

	/**
	 * @return
	 */
	public boolean isRecommended() {
		return recommended;
	}

	
}
