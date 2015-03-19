package model.entity;

public class Nutrient
{
	private Integer nutritionId;
	private String name;
	private String amount;
	private String percentage;
	
	public Nutrient(Integer nutritionId, String name, String amount, String percentage)
	{
		this.nutritionId = nutritionId;
		this.name = name;
		this.amount = amount;
		this.percentage = percentage;
	}
	
	public String getAmount()
	{
		return amount;
	}

	public void setAmount(String amount)
	{
		this.amount = amount;
	}

	public String getPercentage()
	{
		return percentage;
	}

	public void setPercentage(String percentage)
	{
		this.percentage = percentage;
	}

	public Integer getNutritionId()
	{
		return nutritionId;
	}
	
	public void setNutritionId(Integer nutritionId)
	{
		this.nutritionId = nutritionId;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
}
