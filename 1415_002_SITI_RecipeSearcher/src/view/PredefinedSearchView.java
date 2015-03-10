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

public class PredefinedSearchView extends JPanel
{
	private static final long serialVersionUID = 4L;
	private DefaultTableModel model;


	private BasicSearchController controller;
	private JTable table;
	
	public PredefinedSearchView(BasicSearchController controller)
	{
		setBackground(new Color(210, 225, 240));
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize());
		this.setLayout(null);
		
		this.controller = controller;
		
		JLabel lblNewLabel = new JLabel("Predefined searcher");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setBounds(507, 88, 290, 50);
		lblNewLabel.setForeground(new Color(77, 34, 4));
		add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(399, 179, 478, 468);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnSearch = new JButton("search");
		btnSearch.setBounds(586, 819, 89, 23);
		add(btnSearch);
		
		JButton btnBack = new JButton("back");
		btnBack.setBounds(51, 819, 89, 23);
		add(btnBack);
		
	}
}
