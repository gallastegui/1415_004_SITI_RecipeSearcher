package model.index;

public class LuceneRecipe
{
	private Integer recipeId;
	private String name;
	private String description;
	private String timeCook;
	private String timeTotal;
	private String timePrep;
	private String category;
	private String rating;
	private String ingredient;
	private String direction;
	private String nutrient;
	private String review;
	
	public LuceneRecipe(Integer recipeId, String name, String description, String timePrep, String timeCook, String timeTotal,
			String category, String rating, String ingredient,
			String direction, String nutrient, String review)
	{
		this.recipeId = recipeId;
		this.name = name;
		this.description = description;
		this.timePrep = timePrep;
		this.timeCook = timeCook;
		this.timeTotal = timeTotal;
		this.category = category;
		this.rating = rating;
		this.ingredient = ingredient;
		this.direction = direction;
		this.nutrient = nutrient;
		this.review = review;
	}
	
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
	
	public String getTimeCook()
	{
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
	
	public String getTimePrep()
	{
		return timePrep;
	}
	
	public void setTimePrep(String timePrep)
	{
		this.timePrep = timePrep;
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
	
	public String getIngredient()
	{
		return ingredient;
	}
	
	public void setIngredient(String ingredient)
	{
		this.ingredient = ingredient;
	}
	
	public String getDirection()
	{
		return direction;
	}
	
	public void setDirection(String direction)
	{
		this.direction = direction;
	}
	
	public String getNutrient()
	{
		return nutrient;
	}
	
	public void setNutrient(String nutrient)
	{
		
		this.nutrient = nutrient;
	}
	
	public String getReview()
	{
		return review;
	}
	
	public void setReview(String review)
	{
		this.review = review;
	}
}
