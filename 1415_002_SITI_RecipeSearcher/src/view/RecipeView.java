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
		model.addColumn("Unit");
		model2 = new DefaultTableModel();
		model2.addColumn("Name");
		model2.addColumn("Amount");
		model2.addColumn("Unit");
		JLabel lblIngredients = new JLabel("Ingredients:");
		lblIngredients.setFont(new Font("Arial", Font.BOLD, 15));
		
		JLabel lblNutrition = new JLabel("Nutrition:");
		lblNutrition.setFont(new Font("Arial", Font.BOLD, 15));
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setBackground(new Color(255, 102, 0));
		
		this.txtrRecipeDirections = new JTextArea("hola");
		txtrRecipeDirections.setEditable(false);
		txtrRecipeDirections.setText("Recipe Directions");
		
		this.txtrRecipeDescription = new JTextArea("hoa");
		txtrRecipeDescription.setEditable(false);
		txtrRecipeDescription.setText("Recipe Description");
		
		scrollPane = new JScrollPane();
		
		scrollPane_1 = new JScrollPane();
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(466)
					.addComponent(lblRecipeName)
					.addContainerGap(765, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblDescription)
							.addContainerGap())
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addComponent(lblRecipeCategory)
								.addContainerGap())
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblDirections, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
									.addContainerGap())
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addComponent(txtrRecipeDescription, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1298, Short.MAX_VALUE)
										.addComponent(txtrRecipeDirections, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 1298, Short.MAX_VALUE)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.RELATED)
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(lblIngredients, Alignment.LEADING)
												.addComponent(scrollPane, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 570, GroupLayout.PREFERRED_SIZE))
											.addGap(36)
											.addComponent(btnBack)
											.addPreferredGap(ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
											.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 572, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblNutrition))))
									.addGap(35))))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblRecipeName)
					.addGap(5)
					.addComponent(lblDescription)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtrRecipeDescription, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblDirections)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtrRecipeDirections, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblRecipeCategory)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblIngredients)
								.addComponent(lblNutrition))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE))
							.addGap(32))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(btnBack)
							.addGap(31))))
		);
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
		
		for(Direction d : this.controller.getAsocRecipe().getDirections())
		{
			directions = directions + d.getDirectionId() +": "+d.getDescription() +"\n\n";
		}
		
		this.txtrRecipeDirections.setText(directions);
		
		for(Ingredient i : this.controller.getAsocRecipe().getIngredients())
		{
			model.addRow(new Object[]{i.getName(),i.getAmount(),""});
		}
		
		for(Nutrient n : this.controller.getAsocRecipe().getNutrients())
		{
			model2.addRow(new Object[]{n.getName(),"",""});
		}
	}
}
