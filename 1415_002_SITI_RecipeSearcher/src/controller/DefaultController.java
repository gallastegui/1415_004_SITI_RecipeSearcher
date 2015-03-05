package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import test.SearcherTest;

public class DefaultController implements IController,ActionListener
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
		if(actionCommand.equals("rapida"))
		{
			this.jframe.setFlag(2);
		}
		else if(actionCommand.equals("avanzada"))
		{
			this.jframe.setFlag(1);
		}
		else if(actionCommand.equals("predefinida"))
		{
			this.jframe.setFlag(3);
		}
	}

}
