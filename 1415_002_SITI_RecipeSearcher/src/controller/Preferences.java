package controller;

public class Preferences 
{
	public static final int TotalRecipes = 52344;
	public static final String pathIndex = "resources\\index";
	public static final String pathDatabase = "resources\\allrecipesv1.db";
	public static final typeOfSearch searchType = typeOfSearch.LUCENE;
	
	public static enum typeOfSearch
	{
	    SQLITE, LUCENE
	}
}
