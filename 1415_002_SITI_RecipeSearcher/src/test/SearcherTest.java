package test;

import javax.swing.JFrame;

import model.entity.Recipe;
import controller.AdvancedSearchController;
import controller.BasicSearchController;
import controller.DefaultController;
import controller.PredefinedSearchController;
import controller.Preferences;
import controller.RecipeController;
import controller.ResultsController;
import view.AdvancedSearchView;
import view.BasicSearchView;
import view.DefaultView;
import view.PredefinedSearchView;
import view.RecipeView;
import view.ResultsView;
import view.SimpleSearchView;

public class SearcherTest
{
	JFrame window;
	private int flag, flagRecipe;
	private BasicSearchView bsview;
	private BasicSearchController bscontroller;
	private ResultsView rsview;
	private ResultsController rscontroller;
	private DefaultView dfview;
	private DefaultController dfcontroller;
	private AdvancedSearchView asview;
	private AdvancedSearchController ascontroller;
	private PredefinedSearchView psview;
	private PredefinedSearchController pscontroller;
	private RecipeController rpcontroller;
	private RecipeView rpview;
	private String selectedSearcher;


	public SearcherTest()
	{
		this.flag = 0;
		this.window = new JFrame();
		selectedSearcher = "";
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setVisible(true);
	}
	
	public int getFlag()
	{
		return flag;
	}

	public void setFlag(int flag)
	{
		this.flag = flag;
		/*default to advanced search*/
		if(this.flag == 1)
		{
			selectedSearcher = "advanced";
			this.window.remove(dfview);
			this.dfview.setVisible(false);
			this.asview.setVisible(true);
			this.window.add(asview);
			this.asview.updateUI();
			this.dfview.updateUI();			
		}
		/*default to basic search*/
		else if(this.flag == 2)
		{
			selectedSearcher = "basic";
			this.window.remove(dfview);
			this.dfview.setVisible(false);
			this.bsview.setVisible(true);
			this.window.add(bsview);
			this.bsview.updateUI();
			this.dfview.updateUI();
		}
		/*default to predefined search*/
		else if(this.flag == 3)
		{
			selectedSearcher = "predefined";
			this.window.remove(dfview);
			this.dfview.setVisible(false);
			this.psview.setVisible(true);
			this.window.add(psview);
			this.psview.updateUI();
			this.dfview.updateUI();
		}
		/*basic search-back*/
		else if(this.flag == 4)
		{
			this.window.remove(bsview);
			this.bsview.setVisible(false);
			this.dfview.setVisible(true);
			this.window.add(dfview);
			this.bsview.updateUI();
			this.dfview.updateUI();
		}
		/*basic search-search*/
		else if(this.flag == 5)
		{
			this.window.remove(bsview);
			this.bsview.setVisible(false);
			this.rscontroller.setRecipeResults(this.bscontroller.getRecipeResults());
			this.rsview.setVisible(true);
			this.window.add(rsview);
			this.bsview.updateUI();
			this.rsview.updateUI();
		}
		/*advanced search-back*/
		else if(this.flag == 6)
		{
			this.window.remove(asview);
			this.asview.setVisible(false);
			this.dfview.setVisible(true);
			this.window.add(dfview);
			this.asview.updateUI();
			this.dfview.updateUI();	
		}
		/*advanced search-search*/
		else if(this.flag == 7)
		{
			this.window.remove(asview);
			this.asview.setVisible(false);
			this.rscontroller.setRecipeResults(this.ascontroller.getRecipeResults());
			this.rsview.setVisible(true);
			this.window.add(rsview);
			this.asview.updateUI();
			this.rsview.updateUI();
		}
		/*predefined search-back*/
		else if(this.flag == 8)
		{
			this.window.remove(psview);
			this.psview.setVisible(false);
			this.dfview.setVisible(true);
			this.window.add(dfview);
			this.dfview.updateUI();
			this.psview.updateUI();
		}
		/*predefined search-search*/
		else if(this.flag == 9)
		{
			
		}
		/*view Recipe*/
		else if(this.flag == 10)
		{
			this.window.remove(rsview);
			this.rsview.setVisible(false);
			this.rpview.setVisible(true);
			this.window.add(rpview);
			this.rsview.updateUI();
			this.rpview.updateUI();
		}
		/*Recipe back*/
		else if(this.flag == 11)
		{
			this.window.remove(rpview);
			this.rpview.setVisible(false);
			this.rsview.setVisible(true);
			this.window.add(this.rsview);
			this.rpview.updateUI();
			this.rsview.updateUI();
		}
		else if(this.flag == 12)
		{
			this.window.remove(rsview);
			this.rsview.setVisible(false);
			if(selectedSearcher.equals("basic"))
			{
				this.bsview.setVisible(true);
				this.window.add(this.bsview);
				this.bscontroller.getRecipeResults().clear();
				this.rsview.updateUI();
				this.bsview.updateUI();
			}
			else if(selectedSearcher.equals("advanced"))
			{
				this.ascontroller.getRecipeResults().clear();
				this.ascontroller.getIncIngredients().clear();
				this.ascontroller.getRemIngredients().clear();
				this.asview.setVisible(true);
				this.window.add(this.asview);
				this.rsview.updateUI();
				this.asview.updateUI();
			}
			else if(selectedSearcher.equals("predefined"))
			{
				this.psview.setVisible(true);
				this.window.add(this.psview);
				this.rsview.updateUI();
				this.psview.updateUI();
			}
			this.rscontroller.getRecipeResults().clear();
		}
		//this.rscontroller.setRecipeResults(this.bscontroller.getRecipeResults());
	}
	
	public int getFlagRecipe()
	{
		return flagRecipe;
	}

	public void setFlagRecipe(int flagRecipe)
	{
		this.flagRecipe = flagRecipe;
		for(Recipe r : rscontroller.getRecipeResults())
		{
			if(r.getRecipeId() == this.flagRecipe)
			{
				rpcontroller.setAsocRecipe(r);
			}
		}
		this.rpview.fillParameters();
	}
	
	public static void main(String args[])
	{
		SearcherTest program = new SearcherTest();

		program.bscontroller = new BasicSearchController();
		program.bsview = new BasicSearchView(program.bscontroller);
		
		program.rscontroller = new ResultsController();
		program.rsview = new ResultsView(program.rscontroller);
		
		program.dfcontroller = new DefaultController();
		program.dfview = new DefaultView(program.dfcontroller);
		
		program.ascontroller = new AdvancedSearchController();
		program.asview = new AdvancedSearchView(program.ascontroller);
		
		program.pscontroller = new PredefinedSearchController();
		program.psview = new PredefinedSearchView(program.pscontroller);
		
		program.rpcontroller = new RecipeController();
		program.rpview = new RecipeView(program.rpcontroller);
		
		program.bscontroller.setJframe(program);
		program.dfcontroller.setJframe(program);
		program.ascontroller.setJframe(program);
		program.pscontroller.setJframe(program);
		program.rscontroller.setJframe(program);
		program.rpcontroller.setJframe(program);
		program.bscontroller.setView(program.bsview);
		program.rscontroller.setView(program.rsview);
		program.ascontroller.setView(program.asview);
		
		
		program.bsview.setVisible(true);
		program.rsview.setVisible(true);
		program.dfview.setVisible(true);
		program.asview.setVisible(true);
		program.rpview.setVisible(false);
		program.window.add(program.dfview);
		program.dfview.updateUI();
		program.window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
