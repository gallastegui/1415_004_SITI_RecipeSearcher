package model.searcher;

import model.filtering.IngredientFilter;
import model.filtering.SimpleFilter;

public class SimpleSearch implements Search
{
	public String addSimpleFilter(SimpleFilter smpFlt, String sql)
	{
		String sqlAux;
		
		if(sql == null)
			sqlAux = "SELECT * FROM RECIPE as r where r.name like '%"+smpFlt.getQuery()+"%'";
		else
			sqlAux = sql + " AND (r.name like '%"+smpFlt.getQuery()+"%')";
		
		
		return sqlAux;
	}
	
	public String addIngredientFilter(IngredientFilter ingFlt, String sql)
	{
		String sqlAux;
		
		if(sql == null)
			sqlAux = "SELECT * FROM RECIPE as r, INGREDIENT as i WHERE r.recipeId = i.recipeId AND i.name like '%"+ingFlt.getIngredientName()+"%'";
		else
			sqlAux = sql + " AND (r.recipeId = i.recipeId AND i.name like '%"+ingFlt.getIngredientName()+"%')";
		
		
		return sqlAux;
	}
}
