package test;

import javax.swing.JFrame;

import controller.BasicSearchController;
import controller.ResultsController;
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
			this.rscontroller.setRecipeResults(this.sscontroller.getRecipeResults());
			this.window.remove(ssview);
			this.rsview.setVisible(true);
			this.window.add(rsview);
			this.rsview.updateUI();
			this.ssview.updateUI();
		}
	}
	
	public static void main(String args[])
	{
		SearcherTest program = new SearcherTest();
		
		program.sscontroller = new BasicSearchController();
		program.ssview = new SimpleSearchView(program.sscontroller);
		
		program.rscontroller = new ResultsController();
		program.rsview = new ResultsView(program.rscontroller);
		
		program.sscontroller.setJframe(program);
		program.sscontroller.setView(program.ssview);
		program.rscontroller.setView(program.rsview);
		
		program.ssview.setVisible(true);
		program.rsview.setVisible(true);
		program.window.add(program.ssview);
		program.ssview.updateUI();
	}
}
