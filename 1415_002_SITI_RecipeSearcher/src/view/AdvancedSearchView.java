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

public class AdvancedSearchView extends JPanel
{
	private static final long serialVersionUID = 4L;
	private JTextField textField;
	private JTable table;
	private JTable table2;
	private DefaultTableModel model, model2;
	private DefaultComboBoxModel model3;
	final String labels[] = { "menos de 30 mins", "entre 30-60 mins", "más de 60 mins"};
	
	public AdvancedSearchView()
	{
		setBackground(new Color(210, 225, 240));
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize());
		this.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Buscador avanzado");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(504, 70, 283, 50);
		lblNewLabel.setForeground(new Color(77, 34, 4));
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("B\u00FAsqueda por t\u00EDtulo:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 15));
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
		btnNewButton.setBounds(120, 259, 41, 34);
		add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Add ingredient that appears on the plate");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_2.setBounds(171, 250, 294, 50);
		add(lblNewLabel_2);
		
		JButton button = new JButton("+");
		button.setForeground(Color.WHITE);
		button.setBackground(new Color(255, 102, 0));
		button.setBounds(742, 259, 41, 34);
		add(button);
		
		JLabel lblAddIngredientThat = new JLabel("Add ingredient that does not appear on the plate");
		lblAddIngredientThat.setFont(new Font("Arial", Font.BOLD, 15));
		lblAddIngredientThat.setBounds(793, 250, 353, 50);
		add(lblAddIngredientThat);
		table = new JTable(model);
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(120, 304, 400, 200);
		add(scrollPane);
		
		model3 = new DefaultComboBoxModel(labels);
		JComboBox comboBox2 = new JComboBox(model3);
		comboBox2.setBounds(61, 707, 149, 20);
		add(comboBox2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(299, 707, 140, 20);
		add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(554, 707, 140, 20);
		add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(793, 707, 140, 20);
		add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(1033, 707, 140, 20);
		add(comboBox_3);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setForeground(Color.WHITE);
		btnSearch.setBackground(new Color(255, 102, 0));
		btnSearch.setBounds(581, 882, 94, 34);
		add(btnSearch);
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(Color.WHITE);
		btnBack.setBackground(new Color(255, 102, 0));
		btnBack.setBounds(61, 882, 94, 34);
		add(btnBack);
	}
}
