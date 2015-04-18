package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import test.SearcherTest;

public class DefaultController implements IController,ActionListener
{
	private SearcherTest jframe;
	
    /**
    * Getter
    * @returns the associated view of the controller
    */
	public SearcherTest getJframe()
	{
		return jframe;
	}
	
    /**
    * Setter
    * @params view the view to associate to the controller
    */
	public void setJframe(SearcherTest jframe)
	{
		this.jframe = jframe;
	}
	
	/**
	 * Method that controls all possible actions that can be performed in the view
	 * @param e action performed
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		/*1: get the identifier of the button that performs the action*/
		String actionCommand = ((JButton) e.getSource()).getActionCommand();
		/*2: manage the different possible actions*/
		/*2.1: fast search button*/
		if(actionCommand.equals("rapida"))
		{
			this.jframe.setFlag(2);
		}
		/*2.2: advanced search button*/
		else if(actionCommand.equals("avanzada"))
		{
			this.jframe.setFlag(1);
		}
		/*2.3: predefined search button*/
		else if(actionCommand.equals("predefinida"))
		{
			this.jframe.setFlag(3);
		}
	}

}
