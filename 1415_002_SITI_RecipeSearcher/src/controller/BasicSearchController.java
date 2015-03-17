package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import test.SearcherTest;
import view.BasicSearchView;
import view.SimpleSearchView;

import java.util.*;

import model.connector.SqlConnection;
import model.entity.Recipe;
import model.filtering.*;

public class BasicSearchController implements IController, ActionListener
{
	private String sql;
	private SqlConnection sqlConn;
	private BasicSearchView view;
	String comboboxText;
	private String descriptionText;
	private SearcherTest jframe;
	private ArrayList<Recipe> recipeResults;

	public BasicSearchController()
	{
		sqlConn = new SqlConnection("C:\\Users\\eps\\allrecipesv1.db");
	}
	
	public void setJframe(SearcherTest jframe)
	{
		this.jframe = jframe;
	}
	
	public void addDescriptionText(String descriptionText)
	{
		this.descriptionText = descriptionText;
	}
	
	public ArrayList<Recipe> applyFilteredSearch()
	{
		//sql = sqlConn.buildBasicSearchQuery(this.incIngredients, this.remIngredients,this.descriptionText);
		
		return sqlConn.executeSearch(sql);
	}
	
	public BasicSearchView getView()
	{
		return view;
	}

	public void setView(BasicSearchView view)
	{
		this.view = view;
	}
	
	public ArrayList<Recipe> getRecipeResults()
	{
		return recipeResults;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		 String actionCommand = ((JButton) e.getSource()).getActionCommand();
		 
		 if(actionCommand.equals("back"))
		 {
			 this.jframe.setFlag(4);
		 }
		 else if(actionCommand.equals("Search"))
		 {
			 String query = "";
			 
			 this.descriptionText = this.view.getTextField().getText();
			 Object[] allSelectedAsArray = this.view.getComboBox().getSelectedObjects();
			 
			 for(Object o : allSelectedAsArray)
			 {
				 comboboxText = (String) o;
			 }
			 
			 query = sqlConn.buildBasicSearchQuery(descriptionText, comboboxText);
			 recipeResults = sqlConn.executeSearch(query);
			 this.jframe.setFlag(5);
			//System.out.println(sqlConn.buildBasicSearchQuery(incIngredients, remIngredients, descriptionText));
			//recipeResults = sqlConn.executeSearch(sqlConn.buildBasicSearchQuery(incIngredients, remIngredients, descriptionText));
			//this.jframe.setFlag(1);
		 }
	}
}
