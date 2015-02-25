package view;
import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SimpleSearchView extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SimpleSearchView()
	{
		Color bk = new Color(210,225,240);
		JLabel jlb1 = new JLabel("<html><h1><FONT COLOR=#4D2204>Buscador r&aacute;pido</FONT></h1></html>"), jlb2 = new JLabel("<html><h4><FONT COLOR=#4D2204>B&uacute;squeda por t&iacute;tulo:</FONT></h4></html>");
		JPanel first = new JPanel(new GridLayout(4,1)), second = new JPanel(new BorderLayout()), third = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel wrapper = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING)), wrapper2 = new JPanel(new FlowLayout(FlowLayout.CENTER)), wrapper3 = new JPanel(new GridLayout(1,1)), wrapper4 = new JPanel();
		JTextField jtfText1 = new JTextField(50);
		JButton bttn = new JButton("Buscar");
		String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
		Object[][] data = {
		{"Kathy", "Smith",
		"Snowboarding", new Integer(5), new Boolean(false)},
		{"John", "Doe",
		"Rowing", new Integer(3), new Boolean(true)},
		{"Sue", "Black",
		"Knitting", new Integer(2), new Boolean(false)},
		{"Jane", "White",
		"Speed reading", new Integer(20), new Boolean(true)},
		{"Joe", "Brown",
		"Pool", new Integer(10), new Boolean(false)}
		};
		String[] columnNames2 = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
		Object[][] data2 = {
		{"Kathy", "Smith",
		"Snowboarding", new Integer(5), new Boolean(false)},
		{"John", "Doe",
		"Rowing", new Integer(3), new Boolean(true)},
		{"Sue", "Black",
		"Knitting", new Integer(2), new Boolean(false)},
		{"Jane", "White",
		"Speed reading", new Integer(20), new Boolean(true)},
		{"Joe", "Brown",
		"Pool", new Integer(10), new Boolean(false)}
		};
		final JTable table = new JTable(data, columnNames), table2 = new JTable(data2, columnNames2);
		table.setPreferredScrollableViewportSize(new Dimension(510, 70));
		table.setFillsViewportHeight(true);
		table2.setPreferredScrollableViewportSize(new Dimension(510, 70));
		table2.setFillsViewportHeight(true);
		wrapper4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JScrollPane scrollPane = new JScrollPane(table), scrollPane2 = new JScrollPane(table2);
		wrapper4.add(scrollPane);
		
		//wrapper3.add(scrollPane);
		wrapper4.add(scrollPane);
		second.add(wrapper4, BorderLayout.WEST);
		
		JPanel panel_3 = new JPanel();
		wrapper4.add(panel_3);
		panel_3.setLayout(new BoxLayout(panel_3, BoxLayout.Y_AXIS));
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panel_3.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("-");
		panel_3.add(btnNewButton_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.add(scrollPane2);
		wrapper4.add(panel_4);
		
		JPanel panel_5 = new JPanel();
		wrapper4.add(panel_5);
		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));
		
		JButton btnNewButton_2 = new JButton("+");
		panel_5.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("-");
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
	}
	
	
	public static void main(String args[]) {
		new SimpleSearchView();
	}

}
