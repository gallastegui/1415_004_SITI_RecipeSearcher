package controller;

import java.util.*;
import model.filtering.*;
import model.searcher.*;

public class BasicSearchController implements IController
{
	private String sql;
	/*Por el momento mantener, si sobra pasar los metodos de sql a sqlconnection*/
	private SimpleSearch search;
	private ArrayList<IngredientFilter> incIngredient;
	
	public BasicSearchController()
	{
		this.search = new SimpleSearch();
		this.incIngredient = new ArrayList<IngredientFilter>();
	}
	
	public void addIngredientList(String ingredientName)
	{
		IngredientFilter ingFlt;
		
		ingFlt = new IngredientFilter(ingredientName);
		
		if(sql == null)
			search.addIngredientFilter(ingFlt, null);
		else
			search.addIngredientFilter(ingFlt, sql);
		
	}

}
