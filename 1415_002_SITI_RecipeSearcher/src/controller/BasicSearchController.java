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
import model.index.ScoredRecipe;
import model.index.indexing.LuceneIndexer;
import model.index.searching.LuceneSearcher;

public class BasicSearchController implements IController, ActionListener
{
	private String sql;
	private SqlConnection sqlConn;
	private LuceneIndexer indexer;


	private LuceneSearcher searcher;
	private BasicSearchView view;
	String comboboxText;
	private String descriptionText;
	private SearcherTest jframe;
	private ArrayList<Recipe> recipeResults;

    /**
    * public BasicSearchController()
    * Constructor class    
    */
	public BasicSearchController()
	{
		sqlConn = new SqlConnection(Preferences.pathDatabase);
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
    * Setter
    * @params descriptionText set the query to search
    */	
	public void addDescriptionText(String descriptionText)
	{
		this.descriptionText = descriptionText;
	}
	
    /**
    * Getter
    * @returns the associated view of the controller
    */
	public BasicSearchView getView()
	{
		return view;
	}
	
    /**
    * Setter
    * @params view the view to associate to the controller
    */
	public void setView(BasicSearchView view)
	{
		this.view = view;
	}
	
    /**
    * Getter
    * @returns the result list of the search
    */
	public ArrayList<Recipe> getRecipeResults()
	{
		return recipeResults;
	}
	
	/**
	 * Execute de search
	 * @return recipeList list of the recipes that recommend the searcher
	 */
	public ArrayList<Recipe> applyFilteredSearch()
	{
		return sqlConn.executeSearch(sql);
	}
	
	/**
	 * Method that controls all possible actions that can be performed in the view
	 * @param e action performed
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		 /*1: get the identifier of the button that performs the action*/
		 String actionCommand = ((JButton) e.getSource()).getActionCommand();
		 
		 /*2: manage the different possible actions*/
		 /*2.1: back button*/
		 if(actionCommand.equals("back"))
		 {
			 /*call the container to go back to the previous view*/
			 this.jframe.setFlag(4);
		 }
		 /*2.2: search button*/
		 else if(actionCommand.equals("Search"))
		 {
			 this.descriptionText = this.view.getTextField().getText();
			 Object[] allSelectedAsArray = this.view.getComboBox().getSelectedObjects();
			 
			 for(Object o : allSelectedAsArray)
			 {
				 comboboxText = (String) o;
			 }
			 /*get the recipe list using SQLITE or LUCENE*/
			 fillRecipes(Preferences.searchType);
			 /*change to the recipe list view*/
			 this.jframe.setFlag(5);
		 }
	}
	/**
	 * Get the recipe List result
	 * @param mode method to use when searching the recipes
	 */
	public void fillRecipes(Preferences.typeOfSearch mode)
	{
		String query = "";
		
		/* Search the recipes using the database*/
		if(mode.equals(Preferences.typeOfSearch.SQLITE))
		{
			/*get the connection if still not connected*/
			if(sqlConn == null)
				sqlConn = new SqlConnection(Preferences.pathDatabase);
			/*build the query to use in the search*/
			query = sqlConn.buildBasicSearchQuery(descriptionText, comboboxText);
			/*Get the recipe list*/
			recipeResults = sqlConn.executeSearch(query);
		}
		/*Search the recipes using the index*/
		else if(mode.equals(Preferences.typeOfSearch.LUCENE))
		{
			ArrayList<ScoredRecipe> scoredRecipes = new ArrayList<ScoredRecipe>();
			/*load the index only if it was not already loaded*/
			if(indexer == null)
			{
				indexer = new LuceneIndexer();
				indexer.load(Preferences.pathIndex);
			}
			/*build the searcher*/
			if(searcher == null)
			{
				searcher = new LuceneSearcher();
				if(searcher.getSearcher() == null)
				{
					/*search in the index*/
					searcher.build(indexer);
				}
			}
			/*we cannot directly display the information recovered by the index, because of the StopWords analyzer,
			 *  so we take the recipes from the database looking for the ID obtained from the index*/
			scoredRecipes = (ArrayList<ScoredRecipe>) searcher.search(descriptionText);
			if(!scoredRecipes.isEmpty())
			{
				
				query = query + "SELECT * FROM RECIPE WHERE recipeId = "+ scoredRecipes.get(0).getRecipeAsoc().getRecipeId();
				for(ScoredRecipe aux : scoredRecipes)
				{
					query = query + " OR recipeId = "+aux.getRecipeAsoc().getRecipeId();
				}
				recipeResults = sqlConn.executeSearch(query);
			}
		}
	}

	public void setCategories()
	{
		String[] results = null;
		/*get the connection if still not connected*/
		if(sqlConn == null)
			sqlConn = new SqlConnection(Preferences.pathDatabase);
		/*build the query to use in the search*/
		results = sqlConn.getCategories().split(";");
		
		view.setLabels5(results);
	}
	
	public LuceneIndexer getIndexer()
	{

		return this.indexer;
	}
	
	public void setIndexer(LuceneIndexer indexer)
	{
		this.indexer = indexer;
	}
	
	public LuceneSearcher getSearcher()
	{
		return searcher;
	}

	public void setSearcher(LuceneSearcher searcher)
	{
		this.searcher = searcher;
	}
}
