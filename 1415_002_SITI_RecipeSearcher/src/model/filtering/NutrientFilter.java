package model.filtering;
import model.entity.*;

public class NutrientFilter implements Filter
{
	private String nutrientName;
	private String amount;
	
	public String getNutrientName()
	{
		return nutrientName;
	}

	public void setNutrientName(String nutrientName)
	{
		this.nutrientName = nutrientName;
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
