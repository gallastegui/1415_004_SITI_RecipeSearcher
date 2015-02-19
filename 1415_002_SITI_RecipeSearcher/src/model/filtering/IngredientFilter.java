package model.filtering;

public class IngredientFilter implements Filter
{
	private String ingredientName;
	private String ingredientAmount;
	private String ingredientUnit;
	
	
	public IngredientFilter(String ingredientName, String ingredientAmount, String ingredientUnit)
	{
		this.ingredientName=ingredientName;
		this.ingredientAmount = ingredientAmount;
		this.ingredientUnit = ingredientUnit;
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

	public String getIngredientUnit()
	{
		return ingredientUnit;
	}

	public void setIngredientUnit(String ingredientUnit)
	{
		this.ingredientUnit = ingredientUnit;
	}

}
