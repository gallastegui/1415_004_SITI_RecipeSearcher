package controller;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.TableCellRenderer;

import model.entity.Recipe;
import test.SearcherTest;
import view.ResultsView;

public class ResultsController implements IController,ActionListener
{
	private ResultsView view;
	private ArrayList<Recipe> recipeResults;
	private SearcherTest jframe;

	private int recipeConsult;

    /**
    * Getter
    * @returns the associated view of the controller
    */
	public ResultsView getView()
	{
		return view;
	}

    /**
    * Setter
    * @params view the view to associate to the controller
    */
	public void setView(ResultsView view)
	{
		this.view = view;
	}
	
    /**
    * Getter
    * @returns the result list of the search
    */
	public ArrayList<Recipe> getRecipeResults()
	{
		return recipeResults;
	}

    /**
    * Setter
    * @params recipeResults list of the recipes returned by the search engine
    */
	public void setRecipeResults(ArrayList<Recipe> recipeResults)
	{
		this.recipeResults = recipeResults;
		insertResultsTable();	
	}
	
    /**
    * Getter
    * @returns the recipe id to consult in the recipe view
    */
	public int getRecipeConsult()
	{
		return recipeConsult;
	}

	/**
	 * Setter
	 * @param recipeConsult the recipe id to consult in the recipe view
	 */
	public void setRecipeConsult(int recipeConsult)
	{
		this.recipeConsult = recipeConsult;
		jframe.setFlagRecipe(this.recipeConsult);
		jframe.setFlag(10);
	}
	
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
		int i;
		/*changes to the search view*/
		this.jframe.setFlag(12);
		
		/*clear the elements of the recipe list*/
		recipeResults.clear();
		for(i = this.view.getModel().getRowCount() - 1; i>=0;i--)
		{
			this.view.getModel().removeRow(i);
		}
	}
	
	/**
	 * Fill the table with the recipes retrieved by the search engine
	 */
	public void insertResultsTable()
	{
		/*for each recipe, adds in a row*/
		for(Recipe r : recipeResults)
		{
			this.view.getModel().addRow(new Object[]{r.getRecipeId(),r.getName(), r.getTimeTotal(), r.getRating(), "visualize"});
		}
		
		/*Add the buttons to explore the recipes*/
		this.view.getTable().getColumn("Explore").setCellRenderer(new ButtonRenderer());
		this.view.getTable().getColumn("Explore").setCellEditor(new ButtonEditor(new JCheckBox(), this));
	}
}

/**
 * Class used to add JButton in a JTable
 * @author g.gallastegui
 *
 */
class ButtonRenderer extends JButton implements TableCellRenderer
{

  public ButtonRenderer()
  {
    setOpaque(true);
  }

  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column)
  {
    if (isSelected)
    {
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());
    }
    else
    {
      setForeground(table.getForeground());
      setBackground(UIManager.getColor("Button.background"));
    }
    setText((value == null) ? "" : value.toString());
    return this;
  }
}

/**
 * Class used to add JButton in a JTable
 * @author g.gallastegui
 *
 */
class ButtonEditor extends DefaultCellEditor
{
  protected JButton button;

  private String label;
  private String labelId;
  private boolean isPushed;
  
  private ResultsController controller;

  public ButtonEditor(JCheckBox checkBox, ResultsController controller)
  {
    super(checkBox);
	this.controller = controller;
    button = new JButton();
    button.setOpaque(true);
    button.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        fireEditingStopped();
      }
    });
  }

  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column)
  {
    if (isSelected)
    {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());
    }
    else
    {
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }
    label = (value == null) ? "" : value.toString();
    labelId =String.valueOf(table.getModel().getValueAt(row, 0));
    button.setText(label);
    isPushed = true;
    return button;
  }

  public Object getCellEditorValue()
  {
    if (isPushed)
    {
 
      this.controller.setRecipeConsult(Integer.parseInt(labelId));
    }
    isPushed = false;
    return new String(label);
  }

  public boolean stopCellEditing()
  {
    isPushed = false;
    return super.stopCellEditing();
  }

  protected void fireEditingStopped()
  {
    super.fireEditingStopped();
  }
}

