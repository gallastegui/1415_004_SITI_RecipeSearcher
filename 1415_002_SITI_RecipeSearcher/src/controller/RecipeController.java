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
	
    /**
    * public AdvancedSearchController()
    * Constructor class    
    */
	public RecipeController()
	{
		sqlConn = new SqlConnection("resources\\allrecipesv1.db");
	}
	
	/**
	 * Method that controls all possible actions that can be performed in the view
	 * @param e action performed
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.jframe.setFlag(11);
	}
	
    /**
    * Getter
    * @returns the associated recipe of the controller
    */
	public Recipe getAsocRecipe()
	{
		return asocRecipe;
	}

	/**
	 * Setter
	 * @param asocRecipe the associated recipe of the controller
	 */
	public void setAsocRecipe(Recipe asocRecipe)
	{
		this.asocRecipe = asocRecipe;
	}

    /**
    * Getter
    * @returns the container of all the views
    */
	public SearcherTest getJframe()
	{
		return jframe;
	}

    /**
    * Setter
    * @params jframe the parent container of all the views
    */
	public void setJframe(SearcherTest jframe)
	{
		this.jframe = jframe;
	}
	
	/**
	 * fills the elements of the associated recipe
	 */
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
