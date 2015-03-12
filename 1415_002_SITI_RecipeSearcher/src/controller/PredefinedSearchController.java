package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import test.SearcherTest;

public class PredefinedSearchController implements IController,ActionListener
{
	private SearcherTest jframe;
	
	public SearcherTest getJframe()
	{
		return jframe;
	}

	public void setJframe(SearcherTest jframe)
	{
		this.jframe = jframe;
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		String actionCommand = e.getActionCommand();
		
		if(actionCommand.equals("back"))
		{
			jframe.setFlag(8);
		}
		
	}

}
