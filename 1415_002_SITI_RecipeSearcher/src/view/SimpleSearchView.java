package view;
import java.awt.*;
import javax.swing.*;

public class SimpleSearchView extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SimpleSearchView()
	{
		Color bk = new Color(210,225,240);
		JPanel first = new JPanel(new GridLayout(5,1)), second = new JPanel(new BorderLayout()), third = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JPanel wrapper = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING)), wrapper2 = new JPanel(new FlowLayout(FlowLayout.CENTER)), wrapper3 = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING)), wrapper4 = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING));
		JLabel jlb1 = new JLabel("<html><h1><FONT COLOR=#4D2204>Buscador r&aacute;pido</FONT></h1></html>"), jlb2 = new JLabel("<html><h4><FONT COLOR=#4D2204>B&uacute;squeda por t&iacute;tulo:</FONT></h4></html>");
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
		final JTable table = new JTable(data, columnNames), table2 = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);
		table2.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table2.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table), scrollPane2 = new JScrollPane(table2);
		wrapper3.add(scrollPane);
		wrapper4.add(scrollPane2);
		second.add(wrapper3, BorderLayout.WEST);
		second.add(wrapper4, BorderLayout.EAST);
		wrapper2.add(jlb1);
		first.add(wrapper2);
		first.add(jlb2);
		wrapper.add(jtfText1);
		first.add(wrapper);
		first.setBackground(bk);
		third.add(bttn);
		this.getContentPane().setLayout(new GridLayout(3, 1));
		this.getContentPane().add(first);
		this.getContentPane().add(second);
		second.setBackground(Color.green);
		this.getContentPane().add(third);
		this.getContentPane().setBackground(bk);
		this.setSize(1000, 100);
		setVisible(true);
	}
	
	public static void main(String args[]) {
		new SimpleSearchView();
	}

}
