package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import test.SearcherTest;
import model.connector.SqlConnection;
import model.entity.Recipe;

public class RecipeController implements IController,ActionListener
{
	private Recipe asocRecipe;
	private SearcherTest jframe;
	private SqlConnection sqlConn;
	
	
	public RecipeController()
	{
		sqlConn = new SqlConnection("resources\\allrecipesv1.db");
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.jframe.setFlag(11);
	}
	
	public Recipe getAsocRecipe()
	{
		return asocRecipe;
	}

	public void setAsocRecipe(Recipe asocRecipe)
	{
		this.asocRecipe = asocRecipe;
	}

	public SearcherTest getJframe()
	{
		return jframe;
	}

	public void setJframe(SearcherTest jframe)
	{
		this.jframe = jframe;
	}
	
	public void getRecipeValues()
	{
		String sql;
		
		if(asocRecipe != null)
		{
			sql = sqlConn.buildRecipeDirectionsQuery(asocRecipe.getRecipeId());
			asocRecipe.setDirections(sqlConn.executeDirectionSearch(sql));
			sql = sqlConn.buildRecipeIngredientsQuery(asocRecipe.getRecipeId());
			asocRecipe.setIngredients(sqlConn.executeIngredientSearch(sql));
			sql = sqlConn.buildRecipeNutritionsQuery(asocRecipe.getRecipeId());
			asocRecipe.setNutrients(sqlConn.executeNutrientSearch(sql));
		}
	}
}
