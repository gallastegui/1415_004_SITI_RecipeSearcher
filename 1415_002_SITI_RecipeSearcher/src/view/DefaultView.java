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

        
        JLabel lblNewLabel = new JLabel("Buscador de recetas de cocina");
        lblNewLabel.setForeground(new Color(77, 34, 4));
        JLabel label = new JLabel("Seleccione el tipo de b\u00FAsqueda para comenzar:");
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 30));
        lblNewLabel.setBounds(411, 112, 450, 33);
        
        label.setBounds(454, 156, 405, 33);
        label.setForeground(new Color(77, 34, 4));
        
        JButton btnNewButton_1 = new JButton("B\u00FAsqueda avanzada");
        btnNewButton_1.setBackground(new Color(255, 102, 0));
        btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 15));
        btnNewButton_1.setForeground(new Color(255, 255, 255));
        btnNewButton_1.setBounds(128, 384, 279, 93);
        btnNewButton_1.setActionCommand("avanzada");
        btnNewButton_1.addActionListener(this.controller);
        
        JButton btnBsquedaRpida = new JButton("B\u00FAsqueda r\u00E1pida");
        btnBsquedaRpida.setBackground(new Color(255, 102, 0));
        btnBsquedaRpida.setFont(new Font("Arial", Font.BOLD, 15));
        btnBsquedaRpida.setForeground(new Color(255, 255, 255));
        btnBsquedaRpida.setBounds(814, 384, 279, 93);
        btnBsquedaRpida.setActionCommand("rapida");
        btnBsquedaRpida.addActionListener(this.controller);
        
        JButton btnBsquedaPredefinida = new JButton("B\u00FAsqueda predefinida");
        btnBsquedaPredefinida.setFont(new Font("Arial", Font.BOLD, 15));
        btnBsquedaPredefinida.setForeground(new Color(255, 255, 255));
        btnBsquedaPredefinida.setBackground(new Color(255, 102, 0));
        btnBsquedaPredefinida.setBounds(454, 651, 279, 93);
        btnBsquedaPredefinida.setActionCommand("predefinida");
        btnBsquedaPredefinida.addActionListener(this.controller);
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        			.addContainerGap(460, Short.MAX_VALUE)
        			.addComponent(lblNewLabel)
        			.addGap(442))
        		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        			.addContainerGap(527, Short.MAX_VALUE)
        			.addComponent(label)
        			.addGap(486))
        		.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
        			.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(groupLayout.createSequentialGroup()
        					.addContainerGap()
        					.addComponent(btnBsquedaPredefinida, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE))
        				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
        					.addGroup(groupLayout.createSequentialGroup()
        						.addContainerGap()
        						.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 254, GroupLayout.PREFERRED_SIZE))
        					.addGroup(groupLayout.createSequentialGroup()
        						.addGap(587)
        						.addComponent(btnBsquedaRpida, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        			.addGap(503))
        );
        groupLayout.setVerticalGroup(
        	groupLayout.createParallelGroup(Alignment.LEADING)
        		.addGroup(groupLayout.createSequentialGroup()
        			.addGap(25)
        			.addComponent(lblNewLabel)
        			.addGap(59)
        			.addComponent(label)
        			.addGap(86)
        			.addComponent(btnBsquedaRpida, GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
        			.addGap(76)
        			.addComponent(btnNewButton_1, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
        			.addGap(87)
        			.addComponent(btnBsquedaPredefinida, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
        			.addGap(38))
        );
        setLayout(groupLayout);
	}
}
