package model.connector;
import java.sql.*;
import java.util.*;

import model.entity.*;
import model.filtering.*;

public class SqlConnection
{
	private Connection connector = null;
	private static final String dbName = "";
	
	public Connection getConnector()
	{
		return connector;
	}

	public void setConnector(Connection connector)
	{
		this.connector = connector;
	}
	
	public boolean connectDatabase()
	{
		if(dbName == null)
			return false;
		try 
		{
		      Class.forName("org.sqlite.JDBC");
		      connector = DriverManager.getConnection("jdbc:sqlite:"+dbName);
		      connector.setAutoCommit(false);
		}
		catch (Exception e)
		{
		      System.err.println(e.getClass().getName() + ": " + e.getMessage());
		      return false;
		}
		return true;
	}
	
	public boolean disconnectDatabase()
	{
		if(dbName == null)
			 return false;
		try 
		{
			 connector.close();
		}
		catch (Exception e)
		{
			 System.err.println(e.getClass().getName() + ": " + e.getMessage());
			 return false;
		}
		return true;
	}
	
	public ArrayList<Recipe> executeSearch(String sql)
	{
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		
		if(sql == null)
			return null;
		
		if(connector == null)
			connectDatabase();
		
		try
		{
			stmt = connector.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next())
			{
				recipes.add(new Recipe(rs.getInt("recipeId"), rs.getString("name"), rs.getString("description"), rs.getString("timePrep"), rs.getString("timeCook"), rs.getString("timeTotal"), rs.getString("category"), rs.getString("rating")));
			}
			rs.close();
		    stmt.close();
		    return recipes;
		} catch (SQLException e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return null;
		}
	}
	
	public String buildBasicSearchQuery(ArrayList<IngredientFilter> incIngredients, ArrayList<IngredientFilter> remIngredients, String descriptionText)
	{
		String query = "SELECT * FROM RECIPE as r, INGREDIENT as i WHERE r.recipeId=i.recipeId";
		int i;
		
		if(!descriptionText.isEmpty())
			query = query + " AND (r.name LIKE '%"+descriptionText+"%')";
		if(!incIngredients.isEmpty())
		{
			query = query + " AND (";
			for(i=0;i<incIngredients.size();i++)
			{
				query = query + "i.name LIKE '%"+incIngredients.get(i).getIngredientName()+"%'";
				if(i < incIngredients.size() -1)
					query = query + " AND ";
			}
			query = query + ")";
		}
		if(!remIngredients.isEmpty())
		{
			query = query + " AND (";
			for(i=0;i<remIngredients.size();i++)
			{
				query = query + "i.name NOT LIKE '%"+remIngredients.get(i).getIngredientName()+"%'";
				if(i < remIngredients.size() -1)
					query = query + " AND ";
			}
			query = query + ")";			
		}
		query = query + ";";
		
		return query;
	}
}
