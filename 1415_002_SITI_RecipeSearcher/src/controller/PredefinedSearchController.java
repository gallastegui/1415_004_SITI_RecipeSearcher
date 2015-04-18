package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import test.SearcherTest;

public class PredefinedSearchController implements IController,ActionListener
{
	private SearcherTest jframe;
	
    /**
    * Getter
    * @returns the container of all the views
    */
	public SearcherTest getJframe()
	{
		return jframe;
	}
	
    /**
    * Setter
    * @params jframe the parent container of all the views
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
		String actionCommand = e.getActionCommand();
		
		/*2: manage the different possible actions*/
		/*2.1: back button*/
		if(actionCommand.equals("back"))
		{
			/*call the container to go back to the previous view*/
			jframe.setFlag(8);
		}
		
	}

}
