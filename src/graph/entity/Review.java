package graph.entity;

import java.util.Date;

public abstract class Review {
	
	private Date date;
	private String content;
	private float overallRating;
	private boolean recommended;
	
	public Review(Date date, String content, float overallRating, boolean recommended) {
		super();
		this.date = date;
		this.content = content;
		this.overallRating = overallRating;
		this.recommended = recommended;
	}
	
}
