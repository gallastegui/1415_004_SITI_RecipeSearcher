package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;

import model.filtering.IngredientFilter;
import test.SearcherTest;

public class AdvancedSearchController implements IController, ActionListener
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
		String actionCommand = ((JButton) e.getSource()).getActionCommand();
		if(actionCommand.equals("back"))
		{
			this.jframe.setFlag(5);
		}
		else if(actionCommand.equals("search"))
		{
			
		}
	}

}
