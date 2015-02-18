package model.entity;

public class Review
{
	private Integer reviewId;
	private String author;
	private String description;
	
	public Integer getReviewId()
	{
		return reviewId;
	}
	
	public void setReviewId(Integer reviewId)
	{
		this.reviewId = reviewId;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public void setAuthor(String author)
	{
		this.author = author;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
}
