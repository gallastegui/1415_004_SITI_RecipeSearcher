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
		JPanel first = new JPanel(new GridLayout(5,1)), second = new JPanel(new GridLayout(7,1));
		JLabel jlb1 = new JLabel("<html><h1><FONT COLOR=#4D2204>Buscador r&aacute;pido</FONT></h1></html>"), jlb2 = new JLabel("<html><h4><FONT COLOR=#4D2204>B&uacute;squeda por t&iacute;tulo:</FONT></h4></html>");
		JPanel wrapper = new JPanel(new FlowLayout(0, 0, FlowLayout.LEADING)), wrapper2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JTextField jtfText1 = new JTextField(20);
		wrapper2.add(jlb1);
		first.add(wrapper2);
		first.add(jlb2);
		wrapper.add(jtfText1);
		first.add(wrapper);
		first.setBackground(bk);
		this.getContentPane().setLayout(new GridLayout(3, 1));
		
		this.getContentPane().add(first);
		this.getContentPane().add(second);
		this.getContentPane().setBackground(bk);
		this.setSize(1000, 100);
		setVisible(true);
	}
	
	public static void main(String args[]) {
		new SimpleSearchView();
	}

}
