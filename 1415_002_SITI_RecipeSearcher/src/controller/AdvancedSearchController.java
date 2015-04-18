package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;

import model.connector.SqlConnection;
import model.entity.Recipe;
import model.filtering.IngredientFilter;
import test.SearcherTest;
import view.AdvancedSearchView;
import view.BasicSearchView;

public class AdvancedSearchController implements IController, ActionListener
{
	private SearcherTest jframe;
	private AdvancedSearchView view;
	private SqlConnection sqlConn;
	
	private ArrayList<IngredientFilter> incIngredients;
	private ArrayList<IngredientFilter> remIngredients;
	private String descriptionText;
	private String comboTime;
	private String comboStars;
	private String comboCategory;
	private ArrayList<Recipe> recipeResults;

    /**
    * public AdvancedSearchController()
    * Constructor class    
    */
	public AdvancedSearchController()
	{
		sqlConn = new SqlConnection("resources\\allrecipesv1.db");
		incIngredients = new ArrayList<IngredientFilter>();
		remIngredients = new ArrayList<IngredientFilter>();
	}
	
    /**
    * Getter
    * @returns the associated view of the controller
    */
	public AdvancedSearchView getView()
	{
		return view;
	}
	
    /**
    * Setter
    * @params view the view to associate to the controller
    */
	public void setView(AdvancedSearchView view)
	{
		this.view = view;
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
    * Getter
    * @returns the result list of the search
    */
	public ArrayList<Recipe> getRecipeResults()
	{
		return recipeResults;
	}

    /**
    * Setter
    * @params recipeResults list of the recipes returned by the search engine
    */
	public void setRecipeResults(ArrayList<Recipe> recipeResults)
	{
		this.recipeResults = recipeResults;
	}
	
    /**
    * Getter
    * @returns the list of ingredients that need to appear in the recipe
    */
	public ArrayList<IngredientFilter> getIncIngredients()
	{
		return incIngredients;
	}

    /**
    * Setter
    * @params incIngredients the list of ingredients that need to appear in the recipe
    */
	public void setIncIngredients(ArrayList<IngredientFilter> incIngredients)
	{
		this.incIngredients = incIngredients;
	}

    /**
    * Getter
    * @returns the list of ingredients that not have to appear in the recipe
    */
	public ArrayList<IngredientFilter> getRemIngredients()
	{
		return remIngredients;
	}

    /**
    * Setter
    * @params remIngredients the list of ingredients that not have to appear in the recipe
    */
	public void setRemIngredients(ArrayList<IngredientFilter> remIngredients)
	{
		this.remIngredients = remIngredients;
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
			this.jframe.setFlag(6);
		}
		/*2.2: search button*/
		else if(actionCommand.equals("Search"))
		{
			/*get the filters of the search */
			getDatIngredients();
			/*build the query and execute the results*/
			recipeResults = sqlConn.executeAdvancedSearch(sqlConn.buildAdvancedSearchQuery( incIngredients, remIngredients, descriptionText, comboTime, comboStars, comboCategory));
			/*call the container to show the list results view*/
			this.jframe.setFlag(7);
		}
		/*2.3: add ingredient that have to be in the recipe*/
		else if(actionCommand.equals("plus"))
		{
			this.view.getModel().addRow(new Object[]{"", "", ""});
		}
		/*2.4: add ingredient that not have to be in the recipe*/
		else if(actionCommand.equals("plus2"))
		{
			this.view.getModel2().addRow(new Object[]{"", "", ""});
		}
		/*2.5: remove ingredient that have to be in the recipe*/
		else if(actionCommand.equals("minus"))
		{
			int[] rows = this.view.getTable().getSelectedRows();
			for(int i=0;i<rows.length;i++)
			{
				this.view.getModel().removeRow(rows[i]-i);
			}
		}
		/*2.6: remove ingredient that not have to be in the recipe*/
		else if(actionCommand.equals("minus2"))
		{
			int[] rows = this.view.getTable2().getSelectedRows();
			for(int i=0;i<rows.length;i++)
			{
				this.view.getModel2().removeRow(rows[i]-i);
			}			
		}
	}
	
	/**
	 * Method that fill the filters list's
	 */
	private void getDatIngredients()
	{
		int numRows, i;
		String name = null, amount = null, unit = null;
		

		this.descriptionText = this.view.getTextField().getText();
		this.comboTime = (String) this.view.getComboBox2().getModel().getSelectedItem();
		this.comboStars = (String) this.view.getComboBox_1().getModel().getSelectedItem();
		this.comboCategory = (String) this.view.getComboBox_3().getModel().getSelectedItem();

		numRows = this.view.getModel().getRowCount();
		for(i=0;i<numRows;i++)
		{
			try
			{
				name = (String) ((Vector)this.view.getModel().getDataVector().elementAt(i)).elementAt(0);
			}
			catch(Exception e)
			{
				name = "";
			}
			
			try
			{
				amount = (String) ((Vector)this.view.getModel().getDataVector().elementAt(i)).elementAt(1);
			}
			catch(Exception e)
			{
				amount = "";
			}
				incIngredients.add(new IngredientFilter(name, amount));
		}

		numRows = this.view.getModel2().getRowCount();
		for(i=0;i<numRows;i++)
		{
			name = (String) ((Vector)this.view.getModel2().getDataVector().elementAt(i)).elementAt(0);
			amount = (String) ((Vector)this.view.getModel2().getDataVector().elementAt(i)).elementAt(1);
			remIngredients.add(new IngredientFilter(name, amount));
		}
	}
}
