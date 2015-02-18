package model.searcher;

import model.filtering.*;

public class AdvancedSearch implements Search
{
	public String addIngredientFilter(IngredientFilter ingFlt, String sql)
	{
		String sqlAux;
		
		if(sql == null)
			sqlAux = "SELECT * FROM RECIPE as r, INGREDIENT as i WHERE r.recipeId = i.recipeId AND i.name like '%"+ingFlt.getIngredientName()+"%'";
		else
			sqlAux = sql + " AND (r.recipeId = i.recipeId AND i.name like '%"+ingFlt.getIngredientName()+"%')";
		
		
		return sqlAux;
	}
	/*contar el numero de pasos por receta (facil -> menos de 2 pasos) (medio -> entre 2 y 5 pasos) (alto -> más de 5 pasos)*/
	public String addDificultyFilter(String level)
	{
		return null;	
	}
	
	public String addNutrientFilter(NutrientFilter ntrFlt, String sql)
	{
		String sqlAux;
		
		if(sql == null)
		{
			if(ntrFlt.getAmount() == null)
				sqlAux = "SELECT * FROM RECIPE as r, NUTRIENT as n, REL_RECIPE_NUTRITION as rn WHERE rn.recipeId = r.recipeId and rn.nutritionId = n.nutritionId and n.name like '%"+ntrFlt.getNutrientName()+"%'";
			else
				sqlAux = "SELECT * FROM RECIPE as r, NUTRIENT as n, REL_RECIPE_NUTRITION as rn WHERE rn.recipeId = r.recipeId and rn.nutritionId = n.nutritionId and n.name like '%"+ntrFlt.getNutrientName()+"%' and rn.amount like '%"+ntrFlt.getAmount()+"%'";
		}
		else
		{
			if(ntrFlt.getAmount() == null)
				sqlAux = sql + " AND (rn.recipeId = r.recipeId and rn.nutritionId = n.nutritionId and n.name like '%"+ntrFlt.getNutrientName()+"%')";
			else
				sqlAux = sql + " AND (rn.recipeId = r.recipeId and rn.nutritionId = n.nutritionId and n.name like '%"+ntrFlt.getNutrientName()+"%' and rn.amount like '%"+ntrFlt.getAmount()+"%'";
		}
		
		return sqlAux;
	}
	
	public String addSimpleFilter(SimpleFilter smpFlt, String sql)
	{
		String sqlAux;
		
		if(sql == null)
			sqlAux = "SELECT * FROM RECIPE as r where r.name like '%"+smpFlt.getQuery()+"%'";
		else
			sqlAux = sql + " AND (r.name like '%"+smpFlt.getQuery()+"%')";
		
		
		return sqlAux;
	}
	/*¿?*/
	public String addTimeFilter(TimeFilter tmFlt, String sql)
	{
		return null;
	}
}
