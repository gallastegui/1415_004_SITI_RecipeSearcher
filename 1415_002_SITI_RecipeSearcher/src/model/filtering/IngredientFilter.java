package model.filtering;

public class IngredientFilter implements Filter
{
	private String ingredientName;
	private String ingredientAmount;
	
	
	public IngredientFilter(String ingredientName, String ingredientAmount)
	{
		this.ingredientName=ingredientName;
		this.ingredientAmount = ingredientAmount;
	}

	public String getIngredientName()
	{
		return ingredientName;
	}

	public void setIngredientName(String ingredientName)
	{
		this.ingredientName = ingredientName;
	}
	
	public String getIngredientAmount()
	{
		return ingredientAmount;
	}

	public void setIngredientAmount(String ingredientAmount)
	{
		this.ingredientAmount = ingredientAmount;
	}
}
