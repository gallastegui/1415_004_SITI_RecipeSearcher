package controller;

import java.util.*;

import model.connector.SqlConnection;
import model.entity.Recipe;
import model.filtering.*;
import model.searcher.*;

public class BasicSearchController implements IController
{
	private String sql;
	private SqlConnection sqlConn;
	private final String[] columnNames = new String[] {"name", "amount", "unit"};
	private ArrayList<IngredientFilter> incIngredients;
	private ArrayList<IngredientFilter> remIngredients;
	private String descriptionText;
	
	public BasicSearchController()
	{
		sqlConn = new SqlConnection();
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
}
