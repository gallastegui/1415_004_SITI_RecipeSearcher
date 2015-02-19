package model.filtering;

public class IngredientFilter implements Filter
{
	private String ingredientName;
	
	public IngredientFilter(String ingredientName)
	{
		this.ingredientName = ingredientName;
	}

	public String getIngredientName()
	{
		return ingredientName;
	}

	public void setIngredientName(String ingredientName)
	{
		this.ingredientName = ingredientName;
	}

}
