package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.entity.Recipe;
import model.filtering.IngredientFilter;
import view.ResultsView;

public class ResultsController implements IController,ActionListener
{
	private ResultsView view;
	private ArrayList<Recipe> recipeResults;
	public ResultsView getView()
	{
		return view;
	}

	public void setView(ResultsView view)
	{
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{

		
	}
	
	public void insertResultsTable()
	{
		for(Recipe r : recipeResults)
		{
			this.view.getModel().addRow(new Object[]{r.getName(), r.getDescription(), r.getTimePrep(), r.getTimeCook(), r.getTimeTotal(), r.getRating()});
		}
	}
}
