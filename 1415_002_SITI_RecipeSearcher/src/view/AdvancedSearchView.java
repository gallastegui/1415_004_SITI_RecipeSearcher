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
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class AdvancedSearchView extends JPanel
{
	private static final long serialVersionUID = 4L;
	private JTextField textField;
	private JTable table;
	private JTable table2;
	private DefaultTableModel model, model2;
	private DefaultComboBoxModel model3, model4, model5, model6, model7;
	final String labels[] = { "", "less than 30 mins", "between 30-60 mins", "more than 60 mins"};
	final String labels2[] = { "", "Suitable for diabetics", "Suitable for celiacs", "Suitable for vegetarians", "Suitable for vegans"};
	final String labels3[] = { "", "1 star", "2 stars", "3 stars", "4 stars", "5 stars"};
	final String labels4[] = { "", "Easy", "Medium", "Hard"};
	private String labels5[] = null;
	JComboBox comboBox2, comboBox_1, comboBox_2, comboBox_3;

	private AdvancedSearchController controller;
	
	public AdvancedSearchView(AdvancedSearchController controller)
	{
		setBackground(new Color(210, 225, 240));
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize());
		
		this.controller = controller;
		this.controller.setView(this);
		this.controller.setCategories();
		
		JLabel lblNewLabel = new JLabel("Advanced searcher");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setForeground(new Color(77, 34, 4));
		
		JLabel lblNewLabel_1 = new JLabel("Search by title:");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 15));
		
		textField = new JTextField();
		textField.setColumns(10);
		this.model = new DefaultTableModel();
		this.model.addColumn("Ingredient name");
		this.model.addColumn("Total amount");
		table = new JTable(model);
		
		this.model2 = new DefaultTableModel();
		this.model2.addColumn("Ingredient name");
		this.model2.addColumn("Total amount");
		table2 = new JTable(model2);
		table2.setFillsViewportHeight(true);
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.setBackground(new Color(255, 102, 0));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setActionCommand("plus");
		btnNewButton.addActionListener(this.controller);
		
		JLabel lblNewLabel_2 = new JLabel("Add ingredient that appears on the plate");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 15));
		
		JButton button = new JButton("+");
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(255, 102, 0));
		button.setActionCommand("plus2");
		button.addActionListener(this.controller);
		
		JLabel lblAddIngredientThat = new JLabel("Add ingredient that does not appear on the plate");
		lblAddIngredientThat.setFont(new Font("Arial", Font.BOLD, 15));

		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		JScrollPane scrollPane_1 = new JScrollPane(table2);
		model3 = new DefaultComboBoxModel(labels);
		comboBox2 = new JComboBox(model3);
		
		model4 = new DefaultComboBoxModel(labels2);
		
		model5 = new DefaultComboBoxModel(labels3);
		comboBox_1 = new JComboBox(model5);
		
		model6 = new DefaultComboBoxModel(labels4);
		comboBox_2 = new JComboBox(model6);
		
		model7 = new DefaultComboBoxModel(labels5);
		comboBox_3 = new JComboBox(model7);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBackground(new Color(255, 102, 0));
		btnSearch.setActionCommand("Search");
		btnSearch.addActionListener(this.controller);
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color(255, 102, 0));
		btnBack.setActionCommand("back");
		btnBack.addActionListener(this.controller);
		
		JLabel lblTime = new JLabel("Time");
		lblTime.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel lblStars = new JLabel("Stars");
		lblStars.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel lblDificulty = new JLabel("Dificulty");
		lblDificulty.setFont(new Font("Arial", Font.BOLD, 14));
		
		JLabel lblCategory = new JLabel("Category");
		lblCategory.setFont(new Font("Arial", Font.BOLD, 14));
		
		JButton button_1 = new JButton("-");
		button_1.setForeground(Color.WHITE);
		button_1.setBackground(new Color(255, 102, 0));
		button_1.setActionCommand("minus");
		button_1.addActionListener(this.controller);
		
		JButton button_2 = new JButton("-");
		button_2.setForeground(Color.WHITE);
		button_2.setBackground(new Color(255, 102, 0));
		button_2.setActionCommand("minus2");
		button_2.addActionListener(this.controller);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(499)
							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(115)
							.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(115)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(115)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 294, GroupLayout.PREFERRED_SIZE)
							.addGap(332)
							.addComponent(lblAddIngredientThat, GroupLayout.PREFERRED_SIZE, 353, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(115)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(399)
											.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGap(399)
											.addComponent(btnNewButton))
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE)))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(70)
										.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(173)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblTime))
										.addPreferredGap(ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
											.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
											.addComponent(lblStars, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
										.addGap(82))))
							.addGap(52)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblDificulty, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
									.addGap(147)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCategory)))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(399)
									.addComponent(button))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(399)
									.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 41, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))))
					.addGap(171))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(65)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(10)
					.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(50)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblAddIngredientThat, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE))
					.addGap(4)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(30)
							.addComponent(button_1, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(30)
							.addComponent(button_2, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCategory, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblTime, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStars, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDificulty, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboBox_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(comboBox_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(78)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(16))
		);
		setLayout(groupLayout);
	}
	
	public String[] getLabels5()
	{
		return labels5;
	}

	public void setLabels5(String[] labels5)
	{
		this.labels5 = labels5;
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
	
	public JTextField getTextField()
	{
		return textField;
	}

	public void setTextField(JTextField textField)
	{
		this.textField = textField;
	}
	
	public JComboBox getComboBox2()
	{
		return comboBox2;
	}

	public void setComboBox2(JComboBox comboBox2)
	{
		this.comboBox2 = comboBox2;
	}

	public JComboBox getComboBox_1()
	{
		return comboBox_1;
	}

	public void setComboBox_1(JComboBox comboBox_1)
	{
		this.comboBox_1 = comboBox_1;
	}

	public JComboBox getComboBox_2()
	{
		return comboBox_2;
	}

	public void setComboBox_2(JComboBox comboBox_2)
	{
		this.comboBox_2 = comboBox_2;
	}

	public JComboBox getComboBox_3()
	{
		return comboBox_3;
	}

	public void setComboBox_3(JComboBox comboBox_3)
	{
		this.comboBox_3 = comboBox_3;
	}
}
