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

	public AdvancedSearchController()
	{
		sqlConn = new SqlConnection("C:\\Users\\eps\\allrecipesv1.db");
		incIngredients = new ArrayList<IngredientFilter>();
		remIngredients = new ArrayList<IngredientFilter>();
	}
	
	public AdvancedSearchView getView()
	{
		return view;
	}

	public void setView(AdvancedSearchView view)
	{
		this.view = view;
	}

	public SearcherTest getJframe()
	{
		return jframe;
	}

	public void setJframe(SearcherTest jframe)
	{
		this.jframe = jframe;
	}
	
	public ArrayList<Recipe> getRecipeResults()
	{
		return recipeResults;
	}

	public void setRecipeResults(ArrayList<Recipe> recipeResults)
	{
		this.recipeResults = recipeResults;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		String actionCommand = ((JButton) e.getSource()).getActionCommand();
		if(actionCommand.equals("back"))
		{
			this.jframe.setFlag(6);
		}
		else if(actionCommand.equals("Search"))
		{
			getDatIngredients();
			System.out.println(sqlConn.buildAdvancedSearchQuery( incIngredients, remIngredients, descriptionText, comboTime, comboStars, comboCategory));
			recipeResults = sqlConn.executeSearch(sqlConn.buildAdvancedSearchQuery( incIngredients, remIngredients, descriptionText, comboTime, comboStars, comboCategory));
			this.jframe.setFlag(7);
		}
		else if(actionCommand.equals("plus"))
		{
			this.view.getModel().addRow(new Object[]{"", "", ""});
		}
		else if(actionCommand.equals("plus2"))
		{
			this.view.getModel2().addRow(new Object[]{"", "", ""});
		}
		else if(actionCommand.equals("minus"))
		{
			int[] rows = this.view.getTable().getSelectedRows();
			for(int i=0;i<rows.length;i++)
			{
				this.view.getModel().removeRow(rows[i]-i);
			}
		}
		else if(actionCommand.equals("minus2"))
		{
			int[] rows = this.view.getTable2().getSelectedRows();
			for(int i=0;i<rows.length;i++)
			{
				this.view.getModel2().removeRow(rows[i]-i);
			}			
		}
	}
	/*
	 * 	public void addIngredientIncList()
	{
		IngredientFilter ingFlt = new IngredientFilter(" "," "," ");
		incIngredients.add(ingFlt);	
	}
	
	public void addIngredientRemList()
	{
		IngredientFilter ingFlt = new IngredientFilter(" "," "," ");
		remIngredients.add(ingFlt);
	}
		public void setValueIncList(Object aValue, int rowIndex, int columnIndex)
	{
		IngredientFilter ingFlt = incIngredients.get(rowIndex);
		if(0 == columnIndex)
		{
			ingFlt.setIngredientName((String) aValue);
		}
		else if(1 == columnIndex)
		{
			ingFlt.setIngredientAmount((String) aValue);
		}
		else if(2 == columnIndex)
		{
			ingFlt.setIngredientUnit((String) aValue);
		}
	}
	
	public void setValueRemList(Object aValue, int rowIndex, int columnIndex)
	{
		IngredientFilter ingFlt = remIngredients.get(rowIndex);
		if(0 == columnIndex)
		{
			ingFlt.setIngredientName((String) aValue);
		}
		else if(1 == columnIndex)
		{
			ingFlt.setIngredientAmount((String) aValue);
		}
		else if(2 == columnIndex)
		{
			ingFlt.setIngredientUnit((String) aValue);
		}
	}
	 * */
	/*public void actionPerformed(ActionEvent e)
	{
		 String actionCommand = ((JButton) e.getSource()).getActionCommand();
		 if(actionCommand.equals("btnNewButton"))
		 {
			 this.view.getModel().addRow(new Object[]{"", "", ""});
		 }
		 else if(actionCommand.equals("button_1"))
		 {
			 int[] rows = this.view.getTable().getSelectedRows();
			 for(int i=0;i<rows.length;i++)
			 {
				 this.view.getModel().removeRow(rows[i]-i);
			 }
		 }
		 else if(actionCommand.equals("button"))
		 {
			 this.view.getModel2().addRow(new Object[]{"", "", ""});
		 }
		 else if(actionCommand.equals("button_2"))
		 {
			 int[] rows = this.view.getTable2().getSelectedRows();
			 for(int i=0;i<rows.length;i++)
			 {
				 this.view.getModel2().removeRow(rows[i]-i);
			 }
		 }
		 else if(actionCommand.equals("back"))
		 {
			 this.jframe.setFlag(4);
		 }
		 else if(actionCommand.equals("bttn"))
		 {
			getDatIngredients();
			System.out.println(sqlConn.buildBasicSearchQuery(incIngredients, remIngredients, descriptionText));
			recipeResults = sqlConn.executeSearch(sqlConn.buildBasicSearchQuery(incIngredients, remIngredients, descriptionText));
			this.jframe.setFlag(1);
		 }
	}*/
	
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
			
			try
			{
				unit = (String) ((Vector)this.view.getModel().getDataVector().elementAt(i)).elementAt(2);
			}
			catch(Exception e)
			{
				unit = "";
			}
				incIngredients.add(new IngredientFilter(name, amount, unit));
		}

		numRows = this.view.getModel2().getRowCount();
		for(i=0;i<numRows;i++)
		{
			name = (String) ((Vector)this.view.getModel2().getDataVector().elementAt(i)).elementAt(0);
			amount = (String) ((Vector)this.view.getModel2().getDataVector().elementAt(i)).elementAt(1);
			unit = (String) ((Vector)this.view.getModel2().getDataVector().elementAt(i)).elementAt(2);
			remIngredients.add(new IngredientFilter(name, amount, unit));
		}
	}

}
