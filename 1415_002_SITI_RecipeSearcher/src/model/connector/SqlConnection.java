package model.connector;
import java.sql.*;
import java.util.*;

import model.entity.*;

public class SqlConnection
{
	private Connection connector = null;
	private final String dbName;
	
    /**
    * Constructor class    
    */
	public SqlConnection(String dbName)
	{
		this.dbName=dbName;
	}
	
    /**
    * Getter
    * @returns the associated connector of the controller
    */
	public Connection getConnector()
	{
		return connector;
	}

    /**
    * Setter
    * @params connector the connector to associate to the controller
    */
	public void setConnector(Connection connector)
	{
		this.connector = connector;
	}
	
	/**
	 * connects with the database
	 * @return true if gets a successful connection, false otherwise
	 */
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
	
	/**
	 * 
	 * @return true if gets a successful disconnection, false otherwise
	 */
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
	
	/**
	 * execute the query and retrieve the recipes from the database
	 * @param sql query to execute
	 * @return list of the recipe results to show in the view
	 */
	public ArrayList<Recipe> executeSearch(String sql)
	{
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		
		if(sql == null)
			return null;
		/*get the connection first*/
		if(connector == null)
			connectDatabase();
		
		try
		{
			/*create the statement*/
			stmt = connector.createStatement();
			/*execute the query*/
			rs = stmt.executeQuery(sql);
			/*get the results*/
			while (rs.next())
			{
				recipes.add(new Recipe(rs.getInt("recipeId"), rs.getString("name"), rs.getString("description"), rs.getString("TimePrep"), rs.getString("TimeCook"), rs.getString("TimeTotal"),rs.getString("Category"), rs.getString("Rating")));
			}
			/*close connections*/
			rs.close();
		    stmt.close();
		    return recipes;
		} catch (SQLException e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return null;
		}
	}
	/**
	 * method that use preparedstatements to consult the database, and retrieve the recipes 
	 * @param prepared necessary information to build the query
	 * @return the recipe list of the search
	 */
	public ArrayList<Recipe> executeAdvancedSearch(ArrayList<String> prepared)
	{
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		String sql;
		
		if(prepared.isEmpty())
			return recipes;
		/*get the query placed in the last position of the array*/
		sql = prepared.get(prepared.size()-1);
		
		if(sql == null)
			return null;
		
		if(connector == null)
			connectDatabase();
		
		try
		{
			/*create the statement with the query*/
			stmt = connector.prepareStatement(sql);
			stmt.setFetchSize(1000);
			/*fill the parameters of the query*/
			for(int i=0;i<prepared.size()-1;i++)
			{
				stmt.setString(i+1, prepared.get(i));
			}
			
			/*execute the query*/
			rs = stmt.executeQuery();
			
			/*create the recipe list to return*/
			while (rs.next())
			{
				recipes.add(new Recipe(rs.getInt("recipeId"), rs.getString("name"), rs.getString("description"), rs.getString("TimePrep"), rs.getString("TimeCook"), rs.getString("TimeTotal"),rs.getString("Category"), rs.getString("Rating")));
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
	
	/**
	 * method that retrieves the ingredients from the database
	 * @param sql query to execute
	 * @return list of ingredients found
	 */
	public ArrayList<Ingredient> executeIngredientSearch(String sql)
	{
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Ingredient> ings = new ArrayList<Ingredient>();
		
		if(sql == null)
			return null;
		
		if(connector == null)
			connectDatabase();
		
		try
		{
			/*create the statement*/
			stmt = connector.createStatement();
			/*execute the query*/
			rs = stmt.executeQuery(sql);
			/*create the list of ingredients to return*/
			while (rs.next())
			{
				ings.add(new Ingredient(rs.getInt("ingredientId"), rs.getString("name"), rs.getString("amount")));
			}
			rs.close();
		    stmt.close();
		    return ings;
		}
		catch (SQLException e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return null;
		}		
	}
	
	/**
	 * method that retrieves the nutrients from the database
	 * @param sql query to execute
	 * @return list of nutrients found
	 */
	public ArrayList<Nutrient> executeNutrientSearch(String sql)
	{
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Nutrient> nut = new ArrayList<Nutrient>();
		
		if(sql == null)
			return null;
		
		if(connector == null)
			connectDatabase();
		
		try
		{
			/*create the statement*/
			stmt = connector.createStatement();
			/*execute the query*/
			rs = stmt.executeQuery(sql);
			/*create the list of nutrients to return*/
			while (rs.next())
			{
				nut.add(new Nutrient(rs.getInt("nutritionId"), rs.getString("name"), rs.getString("amount"), rs.getString("percentage")));
			}
			rs.close();
		    stmt.close();
		    return nut;
		}
		catch (SQLException e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return null;
		}		
	}
	
	/**
	 * method that retrieves the directions from the database
	 * @param sql query to execute
	 * @return list of directions found
	 */
	public ArrayList<Direction> executeDirectionSearch(String sql)
	{
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Direction> dir = new ArrayList<Direction>();
		
		if(sql == null)
			return null;
		
		if(connector == null)
			connectDatabase();
		
		try
		{
			/*create the statement*/
			stmt = connector.createStatement();
			/*execute the query*/
			rs = stmt.executeQuery(sql);
			/*create the list of directions to return*/
			while (rs.next())
			{
				dir.add(new Direction(rs.getInt("directionId"), rs.getString("description")));
			}
			rs.close();
		    stmt.close();
		    return dir;
		} catch (SQLException e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return null;
		}
	}
	
	/**
	 * create the basic query
	 * @return String the query searching with the recipe name and category
	 */
	public String buildBasicSearchQuery(String name, String category)
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
	
	/**
	 * create the advances search query.
	 * @param incIngredients ingredients that appear in the recipe.
	 * @param remIngredients ingredients that don't appear in the plate.
	 * @param descriptionText text to search in the recipe name.
	 * @param comboTime
	 * @param comboStars
	 * @param comboCategory
	 * @return prepared information for construct the query
	 */
	public ArrayList<String> buildAdvancedSearchQuery(ArrayList<Ingredient> incIngredients, ArrayList<Ingredient> remIngredients, String descriptionText, String comboTime, String comboStars, String comboCategory)
	{
		ArrayList<String> rowsPrepared = new ArrayList<String>();
		String query = "SELECT DISTINCT r.recipeId as recipeId, r.name as name,r.description as description, r.timePrep as TimePrep, r.timeCook as TimeCook, r.timeTotal as TimeTotal, r.category as category, r.rating as rating FROM RECIPE r left join INGREDIENT as i  on r.recipeId=i.recipeId";
		int i;
		if(!incIngredients.isEmpty() || !remIngredients.isEmpty() || !descriptionText.isEmpty() || !comboStars.isEmpty() || !comboTime.isEmpty())
			query = query + " WHERE (1=1)";
		
		if(!descriptionText.isEmpty())
		{
			query = query + " AND (r.name LIKE ?)";
			rowsPrepared.add("%"+descriptionText+"%");
		}
		if(!comboTime.isEmpty())
		{
			if(comboTime.equals("less than 30 minutes"))
			{
				query = query + " AND ( r.timeTotal like '% 1 hr %')";
			}
			else if(comboTime.equals("between 30 and 60 minutes"))
			{
				query = query + " AND ( r.timeTotal not like '%hr%' and r.timeTotal not like '' and cast(substr(r.timeTotal,2,2) as integer) < 30)";
			}
			else if(comboTime.equals("more than 60 minutes"))
			{
				query = query + " AND ( timeTotal not like '%hr%' and timetotal not like '' and cast(substr(timeTotal,2,2) as integer) > 30 and cast(substr(timeTotal,2,2) as integer) < 60)";
			}
		}
		
		if(!comboStars.isEmpty())
		{
			if(comboStars.equals("1 star"))
			{
				query = query + " AND (cast(r.rating as decimal) >= 1)";
			}
			else if(comboStars.equals("2 stars"))
			{
				query = query + " AND (cast(r.rating as decimal) >= 2)";
			}
			else if(comboStars.equals("3 stars"))
			{
				query = query + " AND (cast(r.rating as decimal) >= 3)";
			}
			else if(comboStars.equals("4 stars"))
			{
				query = query + " AND (cast(r.rating as decimal) >= 4)";
			}
			else if(comboStars.equals("5 stars"))
			{
				query = query + " AND (cast(r.rating as decimal) = 5)";
			}
		}
		
		if(!incIngredients.isEmpty())
		{
			for(i=0;i<incIngredients.size();i++)
			{
				query = query + " AND (";
				if(!incIngredients.get(i).getName().isEmpty())
				{
					query = query + "i.name LIKE ? ";
					rowsPrepared.add("%"+incIngredients.get(i).getName()+"%");
					if(!incIngredients.get(i).getAmount().isEmpty())
					{
						query = query + "AND i.amount LIKE ? ";
						rowsPrepared.add("%"+incIngredients.get(i).getAmount()+"%");
					}	
					/*if(!incIngredients.get(i).getIngredientUnit().isEmpty())
						query = query + "AND i.unit LIKE '%"+incIngredients.get(i).getIngredientUnit()+"%' ";*/		
				}
				else
				{
					if(!incIngredients.get(i).getAmount().isEmpty())
					{
						query = query + "i.amount LIKE ? ";
						rowsPrepared.add("%"+incIngredients.get(i).getAmount()+"%");
						/*if(!incIngredients.get(i).getIngredientUnit().isEmpty())
							query = query + "AND i.unit LIKE '%"+incIngredients.get(i).getIngredientUnit()+"%' ";*/
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
				if(!remIngredients.get(i).getName().isEmpty())
				{
					query = query + "i.name NOT LIKE ? ";
					rowsPrepared.add("%"+remIngredients.get(i).getName()+"%");
					if(!remIngredients.get(i).getAmount().isEmpty())
					{
						query = query + "AND i.amount NOT LIKE ? ";
						rowsPrepared.add("%"+remIngredients.get(i).getAmount()+"%");
					}					
		/*if(!remIngredients.get(i).getIngredientUnit().isEmpty())
						query = query + "AND i.unit NOT LIKE '%"+remIngredients.get(i).getIngredientUnit()+"%' ";	*/		
				}
				else
				{
					if(!remIngredients.get(i).getAmount().isEmpty())
					{
						query = query + "i.amount NOT LIKE ? ";
						rowsPrepared.add("%"+remIngredients.get(i).getAmount()+"%");
						/*if(!remIngredients.get(i).getIngredientUnit().isEmpty())
							query = query + "AND i.unit NOT LIKE '%"+remIngredients.get(i).getIngredientUnit()+"%' ";*/
					}
				}
				query = query + ")";
			}			
		}
		
		query = query + " limit 100;";
		rowsPrepared.add(query);
		return rowsPrepared;
	}
	
	/*public String buildAdvancedSearchQuery(ArrayList<IngredientFilter> incIngredients, ArrayList<IngredientFilter> remIngredients, String descriptionText, String comboTime, String comboStars, String comboCategory)
	{
		ArrayList<String> rowsPrepared = new ArrayList<String>();
		String query = "SELECT DISTINCT r.recipeId as recipeId, r.name as name,r.description as description, r.timePrep as TimePrep, r.timeCook as TimeCook, r.timeTotal as TimeTotal, r.category as category, r.rating as rating FROM RECIPE r left join INGREDIENT as i  on r.recipeId=i.recipeId";
		int i;
		if(!incIngredients.isEmpty() || !remIngredients.isEmpty() || !descriptionText.isEmpty() || !comboStars.isEmpty() || !comboTime.isEmpty())
			query = query + " WHERE (1=1)";
		
		if(!descriptionText.isEmpty())
			query = query + " AND (r.name LIKE '%"+descriptionText+"%')";
		if(!comboTime.isEmpty())
		{
			if(comboTime.equals("less than 30 minutes"))
			{
				query = query + " AND ( r.timeTotal like '% 1 hr %')";
			}
			else if(comboTime.equals("between 30 and 60 minutes"))
			{
				query = query + " AND ( r.timeTotal not like '%hr%' and r.timeTotal not like '' and cast(substr(r.timeTotal,2,2) as integer) < 30)";
			}
			else if(comboTime.equals("more than 60 minutes"))
			{
				query = query + " AND ( timeTotal not like '%hr%' and timetotal not like '' and cast(substr(timeTotal,2,2) as integer) > 30 and cast(substr(timeTotal,2,2) as integer) < 60)";
			}
		}
		
		if(!comboStars.isEmpty())
		{
			if(comboStars.equals("1 star"))
			{
				query = query + " AND (cast(r.rating as decimal) >= 1)";
			}
			else if(comboStars.equals("2 stars"))
			{
				query = query + " AND (cast(r.rating as decimal) >= 2)";
			}
			else if(comboStars.equals("3 stars"))
			{
				query = query + " AND (cast(r.rating as decimal) >= 3)";
			}
			else if(comboStars.equals("4 stars"))
			{
				query = query + " AND (cast(r.rating as decimal) >= 4)";
			}
			else if(comboStars.equals("5 stars"))
			{
				query = query + " AND (cast(r.rating as decimal) = 5)";
			}
		}
		
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
	
				}
				else
				{
					if(!incIngredients.get(i).getIngredientAmount().isEmpty())
					{
						query = query + "i.amount LIKE '%"+incIngredients.get(i).getIngredientAmount()+"%' ";

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
					query = query + "i.name NOT LIKE '%"+remIngredients.get(i).getIngredientName()+"%' ";
					if(!remIngredients.get(i).getIngredientAmount().isEmpty())
					{
						query = query + "AND i.amount NOT LIKE '%"+remIngredients.get(i).getIngredientAmount()+"%' ";
					}					
	
				}
				else
				{
					if(!remIngredients.get(i).getIngredientAmount().isEmpty())
					{
						query = query + "i.amount NOT LIKE '%"+remIngredients.get(i).getIngredientAmount()+"%' ";

					}
				}
				query = query + ")";
			}			
		}
		
		query = query + " limit 100;";
		
		return query;
	}*/
	
	public String buildRecipeDirectionsQuery(int recipeId)
	{
		return "SELECT d.directionId, d.description FROM RECIPE r left JOIN DIRECTION d ON r.recipeID = d.recipeId where r.recipeId = "+recipeId+" order by directionId";
	}
	public String buildRecipeIngredientsQuery(int recipeId)
	{
		
		return "SELECT i.ingredientId, i.name, i.amount FROM RECIPE r LEFT JOIN INGREDIENT i ON r.recipeId = i.recipeId where r.recipeId ="+recipeId;
	}
	
	public String buildRecipeNutritionsQuery(int recipeId)
	{
		return "select n.nutritionId, n.name, re.amount, re.percentage from RECIPE r, NUTRITION n, REL_RECIPE_NUTRITION re where r.recipeId = re.recipeId AND n.nutritionId = re.nutritionId AND r.recipeId = "+recipeId;
	}

	public String getCategories()
	{
		String categories = "";
		Statement stmt = null;
		ResultSet rs = null;
		String sql = "SELECT distinct category from recipe";
		int count = 0;
		
		/*get the connection first*/
		if(connector == null)
			connectDatabase();
		
		try
		{
			/*create the statement*/
			stmt = connector.createStatement();
			/*execute the query*/
			rs = stmt.executeQuery(sql);
			/*get the results*/
			while (rs.next())
			{
				categories = categories + ";"+ rs.getString("category");
			}
			/*close connections*/
			rs.close();
		    stmt.close();
		    return categories;
		} 
		catch (SQLException e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return null;
		}
	}
}
