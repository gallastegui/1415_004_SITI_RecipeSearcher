package model.filtering;
import model.entity.Ingredient;

public class IngredientFilter implements Filter
{
	private String ingredientName;

	public String getIngredientName()
	{
		return ingredientName;
	}

	public void setIngredientName(String ingredientName)
	{
		this.ingredientName = ingredientName;
	}

}
