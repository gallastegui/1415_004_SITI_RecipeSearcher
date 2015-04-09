package model.index;

public class ScoredRecipe implements java.lang.Comparable
{
	LuceneRecipe recipeAsoc;
	private double scoreRec;
	
	public ScoredRecipe(LuceneRecipe recipeAsoc, double scoreRec)
	{
		this.recipeAsoc = recipeAsoc;
		this.scoreRec = scoreRec;
	}
	
	public LuceneRecipe getRecipeAsoc()
	{
		return recipeAsoc;
	}

	public void setRecipeAsoc(LuceneRecipe recipeAsoc)
	{
		this.recipeAsoc = recipeAsoc;
	}
	
	public double getScoreRec()
	{
		return scoreRec;
	}
	
	public void setScoreRec(double scoreRec)
	{
		this.scoreRec = scoreRec;
	}
	
	@Override
	public int compareTo(Object o)
	{
		ScoredRecipe aux = (ScoredRecipe)o;
		return new Double(aux.getScoreRec()).compareTo(new Double(this.scoreRec));
	}
}
