package view;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import controller.DefaultController;

import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class DefaultView extends JPanel
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3L;

	private DefaultController controller;
	
	public DefaultView(DefaultController controller)
	{
		this.controller = controller;
		setBackground(new Color(210, 225, 240));
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setPreferredSize( Toolkit.getDefaultToolkit().getScreenSize());

        
        JLabel lblNewLabel = new JLabel("Recipe searcher for cooking");
        lblNewLabel.setForeground(new Color(77, 34, 4));
        JLabel lblSelectTheSearch = new JLabel("select the search type to start:");
        lblSelectTheSearch.setFont(new Font("Arial", Font.PLAIN, 16));
        
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
        lblNewLabel.setBounds(411, 112, 450, 33);
        
        lblSelectTheSearch.setBounds(454, 156, 405, 33);
        lblSelectTheSearch.setForeground(new Color(77, 34, 4));
        
        JButton btnNewButton_1 = new JButton("Advanced Search");
        btnNewButton_1.setBackground(new Color(255, 102, 0));
        btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 15));
        btnNewButton_1.setForeground(new Color(255, 255, 255));
        btnNewButton_1.setBounds(128, 384, 279, 93);
        btnNewButton_1.setActionCommand("avanzada");
        btnNewButton_1.addActionListener(this.controller);
        
        JButton btnBsquedaPredefinida = new JButton("Predefined Search");
        btnBsquedaPredefinida.setFont(new Font("Arial", Font.BOLD, 15));
        btnBsquedaPredefinida.setForeground(new Color(255, 255, 255));
        btnBsquedaPredefinida.setBackground(new Color(255, 102, 0));
        btnBsquedaPredefinida.setBounds(454, 651, 279, 93);
        btnBsquedaPredefinida.setActionCommand("predefinida");
        btnBsquedaPredefinida.addActionListener(this.controller);
        
        JButton btnQuickSearch = new JButton("Quick Search");
        btnQuickSearch.setForeground(Color.WHITE);
        btnQuickSearch.setFont(new Font("Arial", Font.BOLD, 15));
        btnQuickSearch.setBackground(new Color(255, 102, 0));
        btnQuickSearch.setActionCommand("rapida");
        btnQuickSearch.addActionListener(this.controller);
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(452)
        			.addComponent(lblNewLabel)
        			.addContainerGap(496, Short.MAX_VALUE))
        		.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
        			.addGap(121)
        			.addComponent(btnQuickSearch, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
        			.addGap(181)
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addComponent(btnBsquedaPredefinida, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap())
        				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        					.addGroup(groupLayout.createSequentialGroup()
        						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE)
        						.addGap(88))
        					.addGroup(groupLayout.createSequentialGroup()
        						.addComponent(lblSelectTheSearch)
        						.addContainerGap(591, Short.MAX_VALUE)))))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(29)
        					.addComponent(lblNewLabel)
        					.addGap(61)
        					.addComponent(lblSelectTheSearch))
        				.addGroup(groupLayout.createSequentialGroup()
        					.addGap(230)
        					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
        						.addComponent(btnQuickSearch, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE))))
        			.addPreferredGap(ComponentPlacement.RELATED, 183, Short.MAX_VALUE)
        			.addComponent(btnBsquedaPredefinida, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
        			.addGap(82))
        );
        setLayout(groupLayout);
	}
}
