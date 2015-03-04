package view;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ResultsController;

public class ResultsView  extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	private ResultsController controller;
	private JTable table;
	private DefaultTableModel model;
	
	public ResultsController getController()
	{
		return controller;
	}

	public void setController(ResultsController controller)
	{
		this.controller = controller;
	}
	
	public JTable getTable()
	{
		return table;
	}

	public void setTable(JTable table)
	{
		this.table = table;
	}

	public DefaultTableModel getModel()
	{
		return model;
	}

	public void setModel(DefaultTableModel model)
	{
		this.model = model;
	}
}
