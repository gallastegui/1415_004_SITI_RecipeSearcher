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
import controller.PredefinedSearchController;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PredefinedSearchView extends JPanel
{
	private static final long serialVersionUID = 4L;
	private DefaultTableModel model;


	private PredefinedSearchController controller;
	private JTable table;
	
	public PredefinedSearchView(PredefinedSearchController controller)
	{
		setBackground(new Color(210, 225, 240));
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize());
		
		this.controller = controller;
		
		JLabel lblNewLabel = new JLabel("Predefined searcher");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
		lblNewLabel.setForeground(new Color(77, 34, 4));
		
		JScrollPane scrollPane = new JScrollPane();
		this.model = new DefaultTableModel();
		this.model.addColumn("Name");
		this.model.addColumn("Description");
		table = new JTable(this.model);
		scrollPane.setViewportView(table);
		
		JButton btnSearch = new JButton("search");
		btnSearch.setForeground(new Color(255, 255, 255));
		btnSearch.setBackground(new Color(255, 102, 0));
		btnSearch.setFont(new Font("Arial", Font.BOLD, 14));
		btnSearch.setActionCommand("search");
		btnSearch.addActionListener(this.controller);
		
		JButton btnBack = new JButton("back");
		btnBack.setBackground(new Color(255, 102, 0));
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setFont(new Font("Arial", Font.BOLD, 14));
		btnBack.setActionCommand("back");
		btnBack.addActionListener(this.controller);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(411)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 478, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(500)
							.addComponent(lblNewLabel)))
					.addContainerGap(455, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(51)
					.addComponent(btnBack, GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
					.addGap(416)
					.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addGap(655))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(51)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 468, GroupLayout.PREFERRED_SIZE)
					.addGap(127))
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(635, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnBack, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
					.addGap(53))
		);
		setLayout(groupLayout);
		
	}
}
