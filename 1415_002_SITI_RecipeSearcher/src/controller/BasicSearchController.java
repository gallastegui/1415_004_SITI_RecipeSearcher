package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import view.SimpleSearchView;

import java.util.*;

import model.connector.SqlConnection;
import model.entity.Recipe;
import model.filtering.*;
import model.searcher.*;

public class BasicSearchController implements IController,ActionListener
{
	private String sql;
	private SqlConnection sqlConn;
	private SimpleSearchView view;
	private ArrayList<IngredientFilter> incIngredients;
	private ArrayList<IngredientFilter> remIngredients;
	private String descriptionText;
	
	public BasicSearchController()
	{
		sqlConn = new SqlConnection("C:\\Users\\g.gallastegui\\db\\allrecipesv1.db");
		this.incIngredients = new ArrayList<IngredientFilter>();
		this.remIngredients = new ArrayList<IngredientFilter>();
	}
	
	public void addIngredientIncList()
	{
		IngredientFilter ingFlt = new IngredientFilter(" "," "," ");
		incIngredients.add(ingFlt);	
	}
	
	public void addIngredientRemList()
	{
		IngredientFilter ingFlt = new IngredientFilter(" "," "," ");
		remIngredients.add(ingFlt);
	}
	
	public void addDescriptionText(String descriptionText)
	{
		this.descriptionText = descriptionText;
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
	
	public ArrayList<Recipe> applyFilteredSearch()
	{
		sql = sqlConn.buildBasicSearchQuery(this.incIngredients, this.remIngredients,this.descriptionText);
		
		return sqlConn.executeSearch(sql);
	}
	
	public SimpleSearchView getView()
	{
		return view;
	}

	public void setView(SimpleSearchView view)
	{
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		 String actionCommand = ((JButton) e.getSource()).getActionCommand();
		 if(actionCommand.equals("btnNewButton"))
		 {
			 this.view.getModel().addRow(new Object[]{"", "", ""});
		 }
		 else if(actionCommand.equals("btnNewButton_1"))
		 {
			 int[] rows = this.view.getTable().getSelectedRows();
			 for(int i=0;i<rows.length;i++)
			 {
				 this.view.getModel().removeRow(rows[i]-i);
			 }
		 }
		 else if(actionCommand.equals("btnNewButton_2"))
		 {
			 this.view.getModel2().addRow(new Object[]{"", "", ""});
		 }
		 else if(actionCommand.equals("btnNewButton_3"))
		 {
			 int[] rows = this.view.getTable2().getSelectedRows();
			 for(int i=0;i<rows.length;i++)
			 {
				 this.view.getModel2().removeRow(rows[i]-i);
			 }
		 }
		 else if(actionCommand.equals("bttn"))
		 {
			getDatIngredients();
			System.out.println(sqlConn.buildBasicSearchQuery(incIngredients, remIngredients, descriptionText));
			sqlConn.executeSearch(sqlConn.buildBasicSearchQuery(incIngredients, remIngredients, descriptionText));
		 }
	}

	private void getDatIngredients()
	{
		int numRows, i;
		String name = null, amount = null, unit = null;
		
		/*obtain the data of the descriptiontext*/
		this.descriptionText = this.view.getJtfText1().getText();
		/*obtain the data of the first table*/
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
		/*obtain the data of the second table*/
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
