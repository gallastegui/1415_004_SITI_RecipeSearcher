package model.connector;
import java.sql.*;
import java.util.*;

import model.entity.*;
import model.filtering.*;

public class SqlConnection
{
	private Connection connector = null;
	private final String dbName;
	
	public SqlConnection(String dbName)
	{
		this.dbName=dbName;
	}
	
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
				recipes.add(new Recipe(rs.getInt("id"), rs.getString("recipeName"), rs.getString("recipeDescription"), rs.getString("recipeTimePrep"), rs.getString("recipeTimeCook"), rs.getString("recipeTimeTotal"), rs.getString("recipeRating"), rs.getString("recipeCategory")));
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
	
	public String buldBasicSearchQuery(String name, String category)
	{
		String query = "";
		if(!name.isEmpty())
		{
			query = "SELECT * FROM RECIPE r WHERE r.name like '%"+name.replace("'", "\"")+"%'";
			if(!category.equals("All"))
				query = query + " AND r.category like '%"+category.replace("'", "\"")+"%'";
		}
		else
		{
			query = "SELECT * FROM RECIPE r";
			if(!category.equals("All"))
			{
				query = query + " WHERE r.category like '%"+category+"%'";
			}
		}
		return query;
	}
	
	public String buildBasicSearchQuery(ArrayList<IngredientFilter> incIngredients, ArrayList<IngredientFilter> remIngredients, String descriptionText)
	{
		String query = "SELECT r.recipeId as id, r.name as recipeName,r.description as recipeDescription, r.timePrep as recipeTimePrep, r.timeCook as recipeTimeCook, r.timeTotal as recipeTimeTotal, r.rating as recipeRating, r.category as recipeCategory FROM RECIPE as r, INGREDIENT as i WHERE r.recipeId=i.recipeId";
		int i;
		
		if(!descriptionText.isEmpty())
			query = query + " AND (r.name LIKE '%"+descriptionText+"%')";
		if(!incIngredients.isEmpty())
		{
			for(i=0;i<incIngredients.size();i++)
			{
				query = query + " AND (";
				if(!incIngredients.get(i).getIngredientName().isEmpty())
				{
					query = query + "i.name LIKE '%"+incIngredients.get(i).getIngredientName()+"%' ";
					if(!incIngredients.get(i).getIngredientAmount().isEmpty())
					{
						query = query + "AND i.amount LIKE '%"+incIngredients.get(i).getIngredientAmount()+"%' ";
					}					
					if(!incIngredients.get(i).getIngredientUnit().isEmpty())
						query = query + "AND i.unit LIKE '%"+incIngredients.get(i).getIngredientUnit()+"%' ";			
				}
				else
				{
					if(!incIngredients.get(i).getIngredientAmount().isEmpty())
					{
						query = query + "i.amount LIKE '%"+incIngredients.get(i).getIngredientAmount()+"%' ";
						if(!incIngredients.get(i).getIngredientUnit().isEmpty())
							query = query + "AND i.unit LIKE '%"+incIngredients.get(i).getIngredientUnit()+"%' ";
					}
					else
					{
						if(!incIngredients.get(i).getIngredientUnit().isEmpty())
							query = query + "i.unit LIKE '%"+incIngredients.get(i).getIngredientUnit()+"%' ";
					}
				}
				
				query = query + ")";
			}
		}
		if(!remIngredients.isEmpty())
		{
			for(i=0;i<remIngredients.size();i++)
			{
				query = query + " AND (";
				if(!remIngredients.get(i).getIngredientName().isEmpty())
				{
					query = query + "i.name LIKE '%"+remIngredients.get(i).getIngredientName()+"%' ";
					if(!remIngredients.get(i).getIngredientAmount().isEmpty())
					{
						query = query + "AND i.amount LIKE '%"+remIngredients.get(i).getIngredientAmount()+"%' ";
					}					
					if(!remIngredients.get(i).getIngredientUnit().isEmpty())
						query = query + "AND i.unit LIKE '%"+remIngredients.get(i).getIngredientUnit()+"%' ";			
				}
				else
				{
					if(!remIngredients.get(i).getIngredientAmount().isEmpty())
					{
						query = query + "i.amount LIKE '%"+remIngredients.get(i).getIngredientAmount()+"%' ";
						if(!remIngredients.get(i).getIngredientUnit().isEmpty())
							query = query + "AND i.unit LIKE '%"+remIngredients.get(i).getIngredientUnit()+"%' ";
					}
					else
					{
						if(!remIngredients.get(i).getIngredientUnit().isEmpty())
							query = query + "i.unit LIKE '%"+remIngredients.get(i).getIngredientUnit()+"%' ";
					}
				}
				query = query + ")";
			}			
		}
		
		query = query + ";";
		
		return query;
	}
}
