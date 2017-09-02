package graph.entity;

import java.time.LocalDate;

public abstract class Review {
	
	private LocalDate date;
	private String content;
	private float overallRating;
	private boolean recommended;
	
	public Review(LocalDate date, String content, float overallRating, boolean recommended) {
		super();
		this.date = date;
		this.content = content;
		this.overallRating = overallRating;
		this.recommended = recommended;
	}

	@Override
	public String toString() {
		return "Review [date=" + date + ", content=" + content + ", overallRating=" + overallRating + ", recommended="
				+ recommended + "]";
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public float getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(float overallRating) {
		this.overallRating = overallRating;
	}

	public boolean isRecommended() {
		return recommended;
	}

	public void setRecommended(boolean recommended) {
		this.recommended = recommended;
	}
	
	
}
