package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import test.SearcherTest;
import model.entity.Recipe;

public class RecipeController implements IController,ActionListener
{
	private Recipe asocRecipe;
	private SearcherTest jframe;
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		
		
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
}
