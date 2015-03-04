package view;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;
import controller.BasicSearchController;


public class SimpleSearchView extends JPanel
{
	/**
	 * 
	 */
	private BasicSearchController controller;
	private static final long serialVersionUID = 1L;
	private JTable table , table2;
	JTextField jtfText1;
	private DefaultTableModel model, model2;
	
	public SimpleSearchView()
	{
		this.controller = new BasicSearchController();
		this.controller.setView(this);
		this.createComponents();
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
	
	public JTextField getJtfText1()
	{
		return jtfText1;
	}

	public void setJtfText1(JTextField jtfText1)
	{
		this.jtfText1 = jtfText1;
	}
	
	public void createComponents()
	{
		Color bk = new Color(210,225,240);
		JLabel jlb1 = new JLabel("<html><h1><FONT COLOR=#4D2204>Buscador r&aacute;pido</FONT></h1></html>"), jlb2 = new JLabel("<html><h4><FONT COLOR=#4D2204>B&uacute;squeda por t&iacute;tulo:</FONT></h4></html>"), lblIngredientesQueQuiero = new JLabel("Ingredientes que quiero que aparezcan"), lblIngredientesQueNo = new JLabel("Ingredientes que no quiero que aparezcan");
		JPanel first = new JPanel(new GridLayout(4,1)), second = new JPanel(new BorderLayout()), third = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel wrapper = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING)), wrapper2 = new JPanel(new FlowLayout(FlowLayout.CENTER)), wrapper4 = new JPanel();
		JPanel panel = new JPanel(), panel_2 = new JPanel(), panel_1 = new JPanel(), panel_3 = new JPanel(), panel_4 = new JPanel(), panel_5 = new JPanel(), panel_6 = new JPanel(), panel_7 = new JPanel(), panel_8 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout(), flowLayout_1 = (FlowLayout) panel_1.getLayout(), flowLayout_2 = (FlowLayout) panel_2.getLayout(),flowLayout_3 = (FlowLayout) panel_7.getLayout();
		JButton bttn = new JButton("Buscar"), btnNewButton = new JButton("+"), btnNewButton_1 = new JButton("-"),btnNewButton_2 = new JButton("+"), btnNewButton_3 = new JButton("-");
		JScrollPane scrollPane = null, scrollPane2 = null;
		
		/*Inicialize table components*/
		this.model = new DefaultTableModel();
		this.model.addColumn("Ingredient name");
		this.model.addColumn("Total amount");
		this.model.addColumn("Unit");
		this.model2 = new DefaultTableModel();
		this.model2.addColumn("Ingredient name");
		this.model2.addColumn("Total amount");
		this.model2.addColumn("Unit");
		this.table = new JTable(model);
		this.table2 = new JTable(model2);
		table.setPreferredScrollableViewportSize(new Dimension(510, 70));
		table.setFillsViewportHeight(true);
		table2.setPreferredScrollableViewportSize(new Dimension(510, 70));
		table2.setFillsViewportHeight(true);
		
		/*create the scrollPanes and add them to their respectives JPanels*/
		wrapper4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		scrollPane = new JScrollPane(table);
		scrollPane2 = new JScrollPane(table2);
		wrapper4.add(scrollPane);
		panel_4.add(scrollPane2);
		
		/*add the buttons to the JPanel*/
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		panel_3.add(btnNewButton);
		panel_3.add(btnNewButton_1);
		panel_5.add(btnNewButton_2);
		panel_5.add(btnNewButton_3);
	
		/*configure the button listeners*/
		btnNewButton.setActionCommand("btnNewButton");
		btnNewButton.addActionListener(this.controller);
		btnNewButton_1.setActionCommand("btnNewButton_1");
		btnNewButton_1.addActionListener(this.controller);
		btnNewButton_2.setActionCommand("btnNewButton_2");
		btnNewButton_2.addActionListener(this.controller);
		btnNewButton_3.setActionCommand("btnNewButton_3");
		btnNewButton_3.addActionListener(this.controller);
		bttn.setActionCommand("bttn");
		bttn.addActionListener(this.controller);
				
				
		/*configure the JPanels*/
		wrapper4.add(panel_3);
		wrapper4.add(panel_4);
		wrapper4.add(panel_5);
		second.add(wrapper4, BorderLayout.WEST);
		wrapper2.add(jlb1);
		first.add(wrapper2);
		flowLayout_2.setHgap(20);
		panel_1.add(panel_2);
		jlb2.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(jlb2);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		first.add(panel_1);
		flowLayout.setHgap(25);
		wrapper.add(panel);
		jtfText1 = new JTextField(50);
		wrapper.add(jtfText1);
		first.add(wrapper);
		first.setBackground(bk);
		third.add(bttn);
		first.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		flowLayout_3.setHgap(150);
		panel_6.add(panel_7, BorderLayout.WEST);
		lblIngredientesQueQuiero.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_7.add(lblIngredientesQueQuiero);
		panel_6.add(panel_8, BorderLayout.CENTER);
		panel_8.add(lblIngredientesQueNo);
		this.setLayout(new GridLayout(3, 1));
		this.add(first);
		this.add(second);
		this.add(third);
		
		/*Set background colors*/
		first.setBackground(bk);
		second.setBackground(bk);
		third.setBackground(bk);
		wrapper4.setBackground(bk);
		wrapper.setBackground(bk);
		wrapper2.setBackground(bk);
		panel_1.setBackground(bk);
		panel_4.setBackground(bk);
		panel.setBackground(bk);
		panel_2.setBackground(bk);
	}
	
	/*public SimpleSearchView()
	{
		Color bk = new Color(210,225,240);
		JLabel jlb1 = new JLabel("<html><h1><FONT COLOR=#4D2204>Buscador r&aacute;pido</FONT></h1></html>"), jlb2 = new JLabel("<html><h4><FONT COLOR=#4D2204>B&uacute;squeda por t&iacute;tulo:</FONT></h4></html>");
		JPanel first = new JPanel(new GridLayout(4,1)), second = new JPanel(new BorderLayout()), third = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel wrapper = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING)), wrapper2 = new JPanel(new FlowLayout(FlowLayout.CENTER)), wrapper4 = new JPanel();
		JTextField jtfText1 = new JTextField(50);
		JButton bttn = new JButton("Buscar");
		
		
		this.model = new DefaultTableModel();
		this.model.addColumn("Ingredient name");
		this.model.addColumn("Total amount");
		this.model.addColumn("Unit");
		table = new JTable(model);
		this.model2 = new DefaultTableModel();
		this.model2.addColumn("Ingredient name");
		this.model2.addColumn("Total amount");
		this.model2.addColumn("Unit");
		table2 = new JTable(model2);
		
		table.setPreferredScrollableViewportSize(new Dimension(510, 70));
		table.setFillsViewportHeight(true);
		table2.setPreferredScrollableViewportSize(new Dimension(510, 70));
		table2.setFillsViewportHeight(true);
		wrapper4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JScrollPane scrollPane = new JScrollPane(table), scrollPane2 = new JScrollPane(table2);
		//wrapper4.add(scrollPane);
		
		//wrapper3.add(scrollPane);
		wrapper4.add(scrollPane);
		second.add(wrapper4, BorderLayout.WEST);
		
		JPanel panel_3 = new JPanel();
		wrapper4.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					model.addRow(new Object[]{"", "", ""});
				}
			}
		);
		panel_3.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("-");
		btnNewButton_1.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						 DefaultTableModel model = (DefaultTableModel) table.getModel();
						   int[] rows = table.getSelectedRows();
						   for(int i=0;i<rows.length;i++){
						     model.removeRow(rows[i]-i);
						   }
					}
				}
			);
		
		panel_3.add(btnNewButton_1);
		JPanel panel_4 = new JPanel();
		panel_4.add(scrollPane2);
		wrapper4.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		wrapper4.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		JButton btnNewButton_2 = new JButton("+");
		btnNewButton_2.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
						model2.addRow(new Object[]{"", "", ""});
					}
				}
			);
		panel_5.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("-");
		btnNewButton_3.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						 DefaultTableModel model2 = (DefaultTableModel) table2.getModel();
						   int[] rows = table2.getSelectedRows();
						   for(int i=0;i<rows.length;i++){
						     model2.removeRow(rows[i]-i);
						   }
					}
				}
			);
		panel_5.add(btnNewButton_3);
		wrapper2.add(jlb1);
		first.add(wrapper2);
		
		JPanel panel_1 = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panel_1.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		first.add(panel_1);
		
		JPanel panel_2 = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) panel_2.getLayout();
		flowLayout_2.setHgap(20);
		panel_1.add(panel_2);
		jlb2.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(jlb2);
		
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setHgap(25);
		wrapper.add(panel);
		wrapper.add(jtfText1);
		first.add(wrapper);
		first.setBackground(bk);
		third.add(bttn);
		this.getContentPane().setLayout(new GridLayout(3, 1));
		this.getContentPane().add(first);
		this.getContentPane().add(second);
		second.setBackground(bk);
		this.getContentPane().add(third);
		this.getContentPane().setBackground(bk);
		this.setSize(1279, 675);
		first.setBackground(bk);
		second.setBackground(bk);
		third.setBackground(bk);
		wrapper4.setBackground(bk);
		wrapper.setBackground(bk);
		wrapper2.setBackground(bk);
		panel_1.setBackground(bk);
		panel_4.setBackground(bk);
		panel.setBackground(bk);
		panel_2.setBackground(bk);
		
		JPanel panel_6 = new JPanel();
		first.add(panel_6);
		panel_6.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_7 = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) panel_7.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		flowLayout_3.setHgap(150);
		panel_6.add(panel_7, BorderLayout.WEST);
		
		JLabel lblIngredientesQueQuiero = new JLabel("Ingredientes que quiero que aparezcan");
		lblIngredientesQueQuiero.setVerticalAlignment(SwingConstants.BOTTOM);
		panel_7.add(lblIngredientesQueQuiero);
		
		JPanel panel_8 = new JPanel();
		panel_6.add(panel_8, BorderLayout.CENTER);
		
		JLabel lblIngredientesQueNo = new JLabel("Ingredientes que no quiero que aparezcan");
		panel_8.add(lblIngredientesQueNo);
		setVisible(true);
	}*/

}
