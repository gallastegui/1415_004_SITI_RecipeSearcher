package model.connector;
import java.sql.*;
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
	
	public boolean executeSearch(String sql)
	{
		Statement stmt = null;
		
		if(sql == null)
			return false;
		
		if(connector == null)
			connectDatabase();
		
		try
		{
			stmt = connector.createStatement();
			stmt.executeUpdate(sql);
		    stmt.close();
		    connector.commit();
		    return true;
		} catch (SQLException e)
		{
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			return false;
		}
	}
}
