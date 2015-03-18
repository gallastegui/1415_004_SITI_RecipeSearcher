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
import model.filtering.IngredientFilter;
import test.SearcherTest;
import view.ResultsView;

public class ResultsController implements IController,ActionListener
{
	private ResultsView view;
	private ArrayList<Recipe> recipeResults;
	private SearcherTest jframe;

	private int recipeConsult;

	public ResultsView getView()
	{
		return view;
	}

	public void setView(ResultsView view)
	{
		this.view = view;
	}
	
	public ArrayList<Recipe> getRecipeResults()
	{
		return recipeResults;
	}

	public void setRecipeResults(ArrayList<Recipe> recipeResults)
	{
		this.recipeResults = recipeResults;
		insertResultsTable();	
	}
	
	public int getRecipeConsult()
	{
		return recipeConsult;
	}

	public void setRecipeConsult(int recipeConsult)
	{
		this.recipeConsult = recipeConsult;
		jframe.setFlagRecipe(this.recipeConsult);
		jframe.setFlag(10);
	}
	
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
		int i;
		this.jframe.setFlag(12);
		recipeResults.clear();
		for(i = this.view.getModel().getRowCount() - 1; i>=0;i--)
		{
			this.view.getModel().removeRow(i);
		}
	}
	
	public void insertResultsTable()
	{
		for(Recipe r : recipeResults)
		{
			this.view.getModel().addRow(new Object[]{r.getRecipeId(),r.getName(), r.getTimeTotal(), r.getRating(), "visualize"});
		}
		this.view.getTable().getColumn("Explore").setCellRenderer(new ButtonRenderer());
		this.view.getTable().getColumn("Explore").setCellEditor(new ButtonEditor(new JCheckBox(), this));
	}
}

/**
 * @version 1.0 11/09/98
 */

class ButtonRenderer extends JButton implements TableCellRenderer
{

  public ButtonRenderer() {
    setOpaque(true);
  }

  public Component getTableCellRendererComponent(JTable table, Object value,
      boolean isSelected, boolean hasFocus, int row, int column) {
    if (isSelected) {
      setForeground(table.getSelectionForeground());
      setBackground(table.getSelectionBackground());
    } else {
      setForeground(table.getForeground());
      setBackground(UIManager.getColor("Button.background"));
    }
    setText((value == null) ? "" : value.toString());
    return this;
  }
}

/**
 * @version 1.0 11/09/98
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
    button.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        fireEditingStopped();
      }
    });
  }

  public Component getTableCellEditorComponent(JTable table, Object value,
      boolean isSelected, int row, int column) {
    if (isSelected) {
      button.setForeground(table.getSelectionForeground());
      button.setBackground(table.getSelectionBackground());
    } else {
      button.setForeground(table.getForeground());
      button.setBackground(table.getBackground());
    }
    label = (value == null) ? "" : value.toString();
    labelId =String.valueOf(table.getModel().getValueAt(row, 0));
    button.setText(label);
    isPushed = true;
    return button;
  }

  public Object getCellEditorValue() {
    if (isPushed) {
      // 
      // 
      this.controller.setRecipeConsult(Integer.parseInt(labelId));
      // System.out.println(label + ": Ouch!");
    }
    isPushed = false;
    return new String(label);
  }

  public boolean stopCellEditing() {
    isPushed = false;
    return super.stopCellEditing();
  }

  protected void fireEditingStopped() {
    super.fireEditingStopped();
  }
}

