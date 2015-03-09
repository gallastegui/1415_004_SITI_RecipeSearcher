package test;

import javax.swing.JFrame;

import controller.AdvancedSearchController;
import controller.BasicSearchController;
import controller.DefaultController;
import controller.ResultsController;
import view.AdvancedSearchView;
import view.DefaultView;
import view.ResultsView;
import view.SimpleSearchView;

public class SearcherTest
{
	JFrame window;
	private int flag;
	private SimpleSearchView ssview;
	private BasicSearchController sscontroller;
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
		if(this.flag == 1)
		{
			this.window.remove(dfview);
			this.asview.setVisible(true);
			this.window.add(asview);
			this.asview.updateUI();
			this.dfview.updateUI();			
		}
		else if(this.flag == 2)
		{
			this.window.remove(dfview);
			this.ssview.setVisible(true);
			this.window.add(ssview);
			this.ssview.updateUI();
			this.dfview.updateUI();
		}
		else if(this.flag == 3)
		{
			
		}
		else if(this.flag == 4)
		{
			this.rscontroller.setRecipeResults(this.sscontroller.getRecipeResults());
			this.window.remove(ssview);
			this.rsview.setVisible(true);
			this.window.add(rsview);
			this.rsview.updateUI();
			this.ssview.updateUI();
		}
		else if(this.flag == 5)
		{
			this.window.remove(asview);
			this.dfview.setVisible(true);
			this.window.add(dfview);
			this.dfview.updateUI();
			this.asview.updateUI();
		}
		
	}
	
	public static void main(String args[])
	{
		SearcherTest program = new SearcherTest();
		
		program.sscontroller = new BasicSearchController();
		program.ssview = new SimpleSearchView(program.sscontroller);
		
		program.rscontroller = new ResultsController();
		program.rsview = new ResultsView(program.rscontroller);
		
		program.dfcontroller = new DefaultController();
		program.dfview = new DefaultView(program.dfcontroller);
		
		program.ascontroller = new AdvancedSearchController();
		program.asview = new AdvancedSearchView(program.ascontroller);
		
		program.sscontroller.setJframe(program);
		program.dfcontroller.setJframe(program);
		program.ascontroller.setJframe(program);
		program.sscontroller.setView(program.ssview);
		program.rscontroller.setView(program.rsview);
		
		program.ssview.setVisible(true);
		program.rsview.setVisible(true);
		program.dfview.setVisible(true);
		program.asview.setVisible(true);
		program.window.add(program.dfview);
		program.dfview.updateUI();
	}
}
