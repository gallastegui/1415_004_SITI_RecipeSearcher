package view;

import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import controller.AdvancedSearchController;
import controller.BasicSearchController;

public class BasicSearchView extends JPanel
{
	private static final long serialVersionUID = 4L;
	private JTextField textField;
	private JTable table, table2;
	private DefaultTableModel model, model2;

	final String labels[] = { "", "less than 30 mins", "between 30-60 mins", "more than 60 mins"};
	final String labels2[] = { "", "Suitable for diabetics", "Suitable for celiacs", "Suitable for vegetarians", "Suitable for vegans"};
	final String labels3[] = { "", "1 star", "2 stars", "3 stars", "4 stars", "5 stars"};
	final String labels4[] = { "", "Easy", "Medium", "Hard"};
	final String labels5[] = { "", "Appetizer", "Breakfast & Brunch", "Chicken", "Main Dish"};
	private BasicSearchController controller;
	
	public BasicSearchView(BasicSearchController controller)
	{
		setBackground(new Color(210, 225, 240));
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize());
		this.setLayout(null);
		
		this.controller = controller;
		
		JLabel lblNewLabel = new JLabel("Basic searcher");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(504, 70, 283, 50);
		lblNewLabel.setForeground(new Color(77, 34, 4));
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Search by title:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1.setBounds(120, 130, 200, 50);
		add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(120, 180, 600, 20);
		add(textField);
		textField.setColumns(10);
		this.model = new DefaultTableModel();
		this.model.addColumn("Ingredient name");
		this.model.addColumn("Total amount");
		this.model.addColumn("Unit");
		
		this.model2 = new DefaultTableModel();
		this.model2.addColumn("Ingredient name");
		this.model2.addColumn("Total amount");
		this.model2.addColumn("Unit");
		table2 = new JTable(model2);
		table2.setFillsViewportHeight(true);

		JScrollPane scrollPane_1 = new JScrollPane(table2);
		scrollPane_1.setBounds(746, 304, 400, 200);
		add(scrollPane_1);
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.setBackground(new Color(255, 102, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBounds(519, 304, 41, 34);
		btnNewButton.setActionCommand("btnNewButton");
		btnNewButton.addActionListener(this.controller);
		add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Add ingredient that appears on the plate");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2.setBounds(120, 250, 294, 50);
		add(lblNewLabel_2);
		
		JButton button = new JButton("+");
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(255, 102, 0));
		button.setBounds(1145, 304, 41, 34);
		button.setActionCommand("button");
		button.addActionListener(this.controller);
		add(button);
		
		JLabel lblAddIngredientThat = new JLabel("Add ingredient that does not appear on the plate");
		lblAddIngredientThat.setFont(new Font("Arial", Font.BOLD, 15));
		lblAddIngredientThat.setBounds(746, 250, 353, 50);
		add(lblAddIngredientThat);
		table = new JTable(model);
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(120, 304, 400, 200);
		add(scrollPane);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBackground(new Color(255, 102, 0));
		btnSearch.setBounds(581, 882, 94, 34);
		btnSearch.setActionCommand("Search");
		btnSearch.addActionListener(this.controller);
		add(btnSearch);
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color(255, 102, 0));
		btnBack.setBounds(61, 882, 94, 34);
		btnBack.setActionCommand("back");
		btnBack.addActionListener(this.controller);
		add(btnBack);
		
		JButton button_1 = new JButton("-");
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(new Color(255, 102, 0));
		button_1.setBounds(519, 334, 41, 34);
		button_1.setActionCommand("button_1");
		button_1.addActionListener(this.controller);
		add(button_1);
		
		JButton button_2 = new JButton("-");
		button_2.setForeground(Color.WHITE);
		button_2.setBackground(new Color(255, 102, 0));
		button_2.setBounds(1145, 334, 41, 34);
		button_2.setActionCommand("button_2");
		button_2.addActionListener(this.controller);
		add(button_2);
	}
	
	public JTextField getTextField()
	{
		return textField;
	}

	public void setTextField(JTextField textField)
	{
		this.textField = textField;
	}
	
	public DefaultTableModel getModel()
	{
		return model;
	}

	public void setModel(DefaultTableModel model)
	{
		this.model = model;
	}

	public DefaultTableModel getModel2()
	{
		return model2;
	}

	public void setModel2(DefaultTableModel model2)
	{
		this.model2 = model2;
	}
	
	public JTable getTable()
	{
		return table;
	}

	public void setTable(JTable table)
	{
		this.table = table;
	}

	public JTable getTable2()
	{
		return table2;
	}

	public void setTable2(JTable table2)
	{
		this.table2 = table2;
	}
}
