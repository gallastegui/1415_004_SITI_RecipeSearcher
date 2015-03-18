package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.ResultsController;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ResultsView  extends JPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	
	private ResultsController controller;
	private JTable table;
	private DefaultTableModel model;
	
	public ResultsView(ResultsController controller)
	{
		setBackground(new Color(210, 225, 240));
		this.controller = controller;
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));	
		createComponents();
	}
	
	public void createComponents()
	{
		JPanel panel = new JPanel();
		JPanel panel_1 = new JPanel();
		JScrollPane scrollPane;
		JLabel lblResultadosDeLa = new JLabel("We found these recipes for you:");
		
		this.model = new DefaultTableModel() {

		    @Override
		    public boolean isCellEditable(int row, int column) {
		    	if(column == 4)
		    		return true;
		    	return false;
		    }
		};
		this.model.addColumn("Recipe id");
		this.model.addColumn("Recipe name");
		this.model.addColumn("Total time");
		this.model.addColumn("Rating");
		this.model.addColumn("Explore");
		
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(510, 700));
		table.setFillsViewportHeight(true);
		scrollPane = new JScrollPane(table);
		
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.setBackground(new Color(210, 225, 240));
		panel_1.setBackground(new Color(210, 225, 240));
		panel_1.add(scrollPane);
		panel.add(lblResultadosDeLa);
		add(panel);
		add(panel_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(210, 225, 240));
		add(panel_2);
		
		JButton btnVolver = new JButton("Go Back");
		btnVolver.addActionListener(this.controller);
		btnVolver.setBackground(new Color(255, 102, 0));
		btnVolver.setForeground(Color.WHITE);
		panel_2.add(btnVolver);
	}
	
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
