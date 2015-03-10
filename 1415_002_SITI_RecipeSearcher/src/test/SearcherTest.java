package test;

import javax.swing.JFrame;

import controller.AdvancedSearchController;
import controller.BasicSearchController;
import controller.DefaultController;
import controller.ResultsController;
import view.AdvancedSearchView;
import view.BasicSearchView;
import view.DefaultView;
import view.ResultsView;
import view.SimpleSearchView;

public class SearcherTest
{
	JFrame window;
	private int flag;
	private BasicSearchView bsview;
	private BasicSearchController bscontroller;
	private ResultsView rsview;
	private ResultsController rscontroller;
	private DefaultView dfview;
	private DefaultController dfcontroller;
	private AdvancedSearchView asview;
	private AdvancedSearchController ascontroller;
	

	public SearcherTest()
	{
		this.flag = 0;
		this.window = new JFrame();
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
			
		}
		/*predefined search-back*/
		else if(this.flag == 8)
		{
			
		}
		/*predefined search-search*/
		else if(this.flag == 9)
		{
			
		}
		//this.rscontroller.setRecipeResults(this.bscontroller.getRecipeResults());
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
		
		program.bscontroller.setJframe(program);
		program.dfcontroller.setJframe(program);
		program.ascontroller.setJframe(program);
		program.bscontroller.setView(program.bsview);
		program.rscontroller.setView(program.rsview);
		
		program.bsview.setVisible(true);
		program.rsview.setVisible(true);
		program.dfview.setVisible(true);
		program.asview.setVisible(true);
		program.window.add(program.dfview);
		program.dfview.updateUI();
	}
}