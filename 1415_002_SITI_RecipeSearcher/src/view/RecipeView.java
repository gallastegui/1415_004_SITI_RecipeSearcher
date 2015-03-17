package view;

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

public class RecipeView extends JPanel
{
	private RecipeController controller;
	private JTextField txtRecipeDescription;
	private JTextField txtRecipeDirections;
	JLabel lblRecipeName;
	JLabel lblRecipeCategory;
	private JTable table;
	private JTable table_1;
	private DefaultTableModel model, model2;
	
	public RecipeView(RecipeController controller)
	{
		txtRecipeDescription = new JTextField();
		txtRecipeDescription.setText("Recipe Description");
		txtRecipeDescription.setColumns(10);
		lblRecipeName = new JLabel("Recipe Name");
		
		JLabel lblDescription = new JLabel("Description");
		
		JLabel lblDirections = new JLabel("Directions:");
		
		txtRecipeDirections = new JTextField();
		txtRecipeDirections.setText("Recipe Directions");
		txtRecipeDirections.setColumns(10);
		
	    lblRecipeCategory = new JLabel("Recipe Category");
		model = new DefaultTableModel();
		model.addColumn("Name");
		model.addColumn("Amount");
		model.addColumn("Unit");
		table = new JTable(model);
		model2 = new DefaultTableModel();
		model2.addColumn("Name");
		model2.addColumn("Amount");
		model2.addColumn("Unit");
		table_1 = new JTable(model2);
		
		JLabel lblIngredients = new JLabel("Ingredients:");
		
		JLabel lblNutrition = new JLabel("Nutrition:");
		
		JButton btnBack = new JButton("Back");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDescription, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblDirections, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(txtRecipeDirections, GroupLayout.PREFERRED_SIZE, 396, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(195)
								.addComponent(lblRecipeName)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(lblRecipeCategory))
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(txtRecipeDescription, GroupLayout.PREFERRED_SIZE, 398, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(table, GroupLayout.PREFERRED_SIZE, 203, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblIngredients))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNutrition)
								.addComponent(table_1, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(195)
							.addComponent(btnBack)))
					.addContainerGap(42, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRecipeName)
						.addComponent(lblRecipeCategory))
					.addGap(5)
					.addComponent(lblDescription)
					.addGap(7)
					.addComponent(txtRecipeDescription, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblDirections)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtRecipeDirections, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addGap(2)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblIngredients)
						.addComponent(lblNutrition))
					.addGap(2)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(table, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE)
						.addComponent(table_1, GroupLayout.PREFERRED_SIZE, 83, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnBack)
					.addGap(7))
		);
		setLayout(groupLayout);
		this.controller = controller;
		//fillParameters();
	}
	
	public void fillParameters()
	{
		String directions = "";
		lblRecipeName.setText(this.controller.getAsocRecipe().getName()+"( rating:"+this.controller.getAsocRecipe().getRating()+" )");
		lblRecipeCategory.setText(this.controller.getAsocRecipe().getCategory());
		this.txtRecipeDescription.setText(this.controller.getAsocRecipe().getDescription());
		
		for(Direction d : this.controller.getAsocRecipe().getDirections())
		{
			directions = directions + d.getDirectionId() +": "+d.getDescription() +"\n\n";
		}
		
		this.txtRecipeDirections.setText(directions);
		
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
