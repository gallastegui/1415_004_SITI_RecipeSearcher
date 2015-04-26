package model.entity;

public class Ingredient
{
	private Integer ingredientId;
	private String name;
	private String amount;
	
	public Ingredient(Integer ingredientId, String name, String amount)
	{
		this.ingredientId = ingredientId;
		this.name = name;
		this.amount = amount;
	}
	
	public Ingredient(String name2, String amount2)
	{
		this.name = name2;
		this.amount = amount2;
	}

	public Integer getIngredientId()
	{
		return ingredientId;
	}
	
	public void setIngredientId(Integer ingredientId)
	{
		this.ingredientId = ingredientId;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getAmount()
	{
		return amount;
	}
	
	public void setAmount(String amount)
	{
		this.amount = amount;
	}
	
	
}
