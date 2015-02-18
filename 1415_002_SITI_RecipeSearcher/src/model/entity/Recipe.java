package model.entity;

public class Recipe
{
	private Integer recipeId;
	private String name;
	private String description;
	private String timePrep;
	private String timeCook;
	private String timeTotal;
	private String category;
	private String rating;
	
	public Integer getRecipeId()
	{
		return recipeId;
	}

	public void setRecipeId(Integer recipeId)
	{
		this.recipeId = recipeId;
	}	
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public String getTimePrep()
	{
		return timePrep;
	}
	
	public void setTimePrep(String timePrep) {
		this.timePrep = timePrep;
	}
	
	public String getTimeCook() {
		return timeCook;
	}
	
	public void setTimeCook(String timeCook)
	{
		this.timeCook = timeCook;
	}
	
	public String getTimeTotal()
	{
		return timeTotal;
	}
	
	public void setTimeTotal(String timeTotal)
	{
		this.timeTotal = timeTotal;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public void setCategory(String category)
	{
		this.category = category;
	}
	
	public String getRating()
	{
		return rating;
	}
	
	public void setRating(String rating)
	{
		this.rating = rating;
	}
}
