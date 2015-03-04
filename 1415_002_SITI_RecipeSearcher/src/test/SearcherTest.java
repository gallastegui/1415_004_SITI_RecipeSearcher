package test;

import javax.swing.JFrame;

import view.SimpleSearchView;

public class SearcherTest
{
	JFrame window;
	
	public SearcherTest()
	{
		this.window = new JFrame();
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		//window.add(this);
		window.setVisible(true);
	}
	
	public static void main(String args[])
	{
		SearcherTest program = new SearcherTest();
		SimpleSearchView ssview = new SimpleSearchView();
		
		program.window.add(ssview);
		ssview.updateUI();
	}
}
