package view;

import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;

import controller.RecipeController;
import model.entity.*;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.Font;
import javax.swing.DropMode;
import javax.swing.JScrollBar;

public class RecipeView extends JPanel
{
	private RecipeController controller;
	JLabel lblRecipeName;
	JLabel lblRecipeCategory;
	JTextArea txtrRecipeDirections;
	JTextArea txtrRecipeDescription;
	private JTable table;
	private JTable table_1;
	private DefaultTableModel model, model2;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	
	private static final int TA_ROWS = 20;
	private static final int TA_COLS = 35;
	
	public RecipeView(RecipeController controller)
	{
		setBackground(new Color(210, 225, 240));
		lblRecipeName = new JLabel("Recipe Name");
		lblRecipeName.setForeground(new Color(77, 34, 4));
		lblRecipeName.setFont(new Font("Arial", Font.BOLD, 20));
		
		JLabel lblDescription = new JLabel("Description:");
		lblDescription.setFont(new Font("Arial", Font.BOLD, 15));
		
		JLabel lblDirections = new JLabel("Directions:");
		lblDirections.setFont(new Font("Arial", Font.BOLD, 15));
		
	    lblRecipeCategory = new JLabel("Recipe Category");
	    lblRecipeCategory.setFont(new Font("Arial", Font.BOLD, 20));
		model = new DefaultTableModel();
		model.addColumn("Name");
		model.addColumn("Amount");
		model2 = new DefaultTableModel();
		model2.addColumn("Name");
		model2.addColumn("Amount");
		model2.addColumn("Percentage");
		JLabel lblIngredients = new JLabel("Ingredients:");
		lblIngredients.setFont(new Font("Arial", Font.BOLD, 15));
		
		JLabel lblNutrition = new JLabel("Nutrition:");
		lblNutrition.setFont(new Font("Arial", Font.BOLD, 15));
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(255, 102, 0));
		
		scrollPane = new JScrollPane();
		
		scrollPane_1 = new JScrollPane();
		
		JScrollPane scrollPane_2 = new JScrollPane();
		
		JScrollPane scrollPane_3 = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(466)
							.addComponent(lblRecipeName))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(21)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDescription)
								.addComponent(lblRecipeCategory)
								.addComponent(lblDirections, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(scrollPane_3, Alignment.LEADING)
											.addComponent(scrollPane_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1294, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(lblIngredients)
												.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 570, GroupLayout.PREFERRED_SIZE))
											.addGap(53)
											.addComponent(btnBack)
											.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 572, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNutrition))
											.addGap(35)))))))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRecipeName)
					.addGap(5)
					.addComponent(lblDescription)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_2, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDirections)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addComponent(lblRecipeCategory)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIngredients)
								.addComponent(lblNutrition))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
							.addGap(32))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnBack)
							.addGap(72))))
		);
		
		this.txtrRecipeDirections = new JTextArea(TA_ROWS, TA_COLS);
		scrollPane_3.setViewportView(txtrRecipeDirections);
		this.txtrRecipeDirections.setWrapStyleWord(true);
		this.txtrRecipeDirections.setLineWrap(true);
		txtrRecipeDirections.setEditable(false);
		txtrRecipeDirections.setText("Recipe Directions");
		
		this.txtrRecipeDescription = new JTextArea(TA_ROWS, TA_COLS);
		scrollPane_2.setViewportView(txtrRecipeDescription);
		this.txtrRecipeDescription.setWrapStyleWord(true);
		this.txtrRecipeDescription.setLineWrap(true);
		txtrRecipeDescription.setEditable(false);
		txtrRecipeDescription.setText("Recipe Description");
		groupLayout.setHonorsVisibility(false);
		this.table_1 = new JTable(model2);
		scrollPane_1.setViewportView(table_1);
		this.table = new JTable(model);
		scrollPane.setViewportView(table);
		setLayout(groupLayout);
		this.setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		this.controller = controller;
		btnBack.addActionListener(controller);
		//fillParameters();
	}
	
	public void fillParameters()
	{
		String directions = "";
		lblRecipeName.setText(this.controller.getAsocRecipe().getName()+"( rating:"+this.controller.getAsocRecipe().getRating()+" )");
		lblRecipeCategory.setText(this.controller.getAsocRecipe().getCategory());
		this.txtrRecipeDescription.setText(this.controller.getAsocRecipe().getDescription());
		this.controller.getAsocRecipe().getIngredients().clear();
		this.controller.getAsocRecipe().getNutrients().clear();
		this.controller.getAsocRecipe().getDirections().clear();
		int i;
		
		for(i = model.getRowCount() - 1; i >= 0; i--)
		{
			model.removeRow(i);
		}
		for(i = model2.getRowCount() - 1; i >= 0; i--)
		{
			model2.removeRow(i);
		}
		
		this.controller.getRecipeValues();
		i = 1;
		for(Direction d : this.controller.getAsocRecipe().getDirections())
		{
			directions = directions + i +": "+d.getDescription() +"\n\n";
			i++;
		}
		
		this.txtrRecipeDirections.setText(directions);
		
		for(Ingredient in : this.controller.getAsocRecipe().getIngredients())
		{
			model.addRow(new Object[]{in.getName(),in.getAmount()});
		}
		
		for(Nutrient n : this.controller.getAsocRecipe().getNutrients())
		{
			model2.addRow(new Object[]{n.getName(),n.getAmount(),n.getPercentage()});
		}
	}
}
