package model.index.searching;

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.MultiFieldQueryParser;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.util.Version;

import controller.Preferences;
import model.entity.Ingredient;
import model.index.LuceneRecipe;
import model.index.ScoredRecipe;
import model.index.indexing.Index;
import model.index.indexing.LuceneIndexer;

public class LuceneSearcher implements Searcher
{
	private IndexSearcher searcher;

	public void setSearcher(IndexSearcher searcher)
	{
		this.searcher = searcher;
	}

	public IndexSearcher getSearcher()
	{
		return searcher;
	}

	@Override
	public void build(Index index)
	{
        /*1 read the index (created in LuceneIndexer)*/
        IndexReader reader=((LuceneIndexer)index).getReader();
        /*2 Create the searcher*/
        searcher = new IndexSearcher(reader);	
	}

	@Override
	public List<ScoredRecipe> search(String query)
	{
       List<ScoredRecipe> sct = null;
       String[] fields = {"name","description","timePrep","timeCook","timeTotal","category","ingredient","review","nutrient","direction"};
        
       try{
    	   /*1 create the necessary elements for retrieve the recipes*/
           Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_31);
           //QueryParser parser = new QueryParser(Version.LUCENE_31, "description", analyzer);
           MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_31, fields, analyzer);
           /*2 parse the query*/
           Query query_parsed = parser.parse(query);
           System.out.println("Searching for: " + query_parsed.toString("description"));
            
           /*3 return the list of the recipe's that answer the query order by descendent score*/
           sct =  scoreDocs(searcher, query_parsed);
           
           /*close the searcher*/
           searcher.close();
       }catch(Exception e)
       {
           e.printStackTrace();
       }

        return sct;
	}
	
	public List<ScoredRecipe> AdvancedSearch(ArrayList<Ingredient> incIngredients,ArrayList<Ingredient> remIngredients,String descriptionText,String comboTime,String comboStars,String comboCategory)
	{
		BooleanQuery booleanQuery = new BooleanQuery();
	    String[] fields = {"name","description"};
	    Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_31);
		MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_31, fields, analyzer);
		ArrayList<Query> queryIncIngredient = new ArrayList<Query>();
		ArrayList<Query> queryRemIngredient = new ArrayList<Query>();
		Query queryTime1 = null,queryTime2 = null;
		Query queryStars = null;
		Query queryCategory = null;
		Query queryDescription = null;
		List<ScoredRecipe> sct = null;
		
		try
		{
			for(Ingredient ing : incIngredients)
			{
				if(!ing.getName().isEmpty())
				{
					if(!ing.getAmount().isEmpty())
					{
						queryIncIngredient.add(new TermQuery(new Term("ingredient", ing.getName()+";"+ing.getAmount())));
					}
					else
					{
						queryIncIngredient.add(new TermQuery(new Term("ingredient", ing.getName())));
					}
				}
				else
				{
					if(!ing.getAmount().isEmpty())
					{
						queryIncIngredient.add(new TermQuery(new Term("ingredient", ing.getAmount())));
					}
				}
			}
			
			for(Ingredient ing : remIngredients)
			{
				if(!ing.getName().isEmpty())
				{
					if(!ing.getAmount().isEmpty())
					{
						queryRemIngredient.add(new TermQuery(new Term("ingredient", ing.getName()+";"+ing.getAmount())));
					}
					else
					{
						queryRemIngredient.add(new TermQuery(new Term("ingredient", ing.getName())));
					}
				}
				else
				{
					if(!ing.getAmount().isEmpty())
					{
						queryRemIngredient.add(new TermQuery(new Term("ingredient", ing.getAmount())));
					}
				}
			}
			
			if(!descriptionText.isEmpty())
			{
				queryDescription = new TermQuery(new Term("name", descriptionText));
			}
			else
			{
				queryDescription = null;
			}
			
			if(!comboTime.isEmpty())
			{
				if(comboTime.equals("less than 30 minutes"))
				{
					queryTime1 = NumericRangeQuery.newIntRange("TimeTotal",
	                        new Integer(1), new Integer(30),
	                        true, true);
					queryTime2 = new TermQuery(new Term("TimeTotal","hr"));
				}
				else if(comboTime.equals("between 30 and 60 minutes"))
				{
					queryTime1 = NumericRangeQuery.newIntRange("TimeTotal",
	                        new Integer(31), new Integer(59),
	                        true, true);
					queryTime2 = new TermQuery(new Term("TimeTotal","hr"));
				}
				else if(comboTime.equals("more than 60 minutes"))
				{
					queryTime1 = null;
					queryTime2 = new TermQuery(new Term("TimeTotal","hr"));
				}
			}
			
			if(!comboStars.isEmpty())
			{
				if(comboStars.equals("1 star"))
				{
					queryStars = NumericRangeQuery.newFloatRange("Rating",
	                        new Float(0), new Float(1),
	                        true, true);
				}
				else if(comboStars.equals("2 stars"))
				{
					queryStars = NumericRangeQuery.newFloatRange("Rating",
	                        new Float(2), new Float(5),
	                        true, true);
				}
				else if(comboStars.equals("3 stars"))
				{
					queryStars = NumericRangeQuery.newFloatRange("Rating",
	                        new Float(3), new Float(5),
	                        true, true);
				}
				else if(comboStars.equals("4 stars"))
				{
					queryStars = NumericRangeQuery.newFloatRange("Rating",
	                        new Float(4), new Float(5),
	                        true, true);
				}
				else if(comboStars.equals("5 stars"))
				{
					queryStars = NumericRangeQuery.newFloatRange("Rating",
	                        new Float(5), new Float(5),
	                        true, true);
				}
			}
			else
			{
				queryStars = null;
			}
			
			if(!comboCategory.isEmpty())
			{
				queryCategory = new TermQuery(new Term("Category",comboCategory));
			}
			else
			{
				queryCategory = null;
			}
			
			if(queryDescription != null)
			{
				booleanQuery.add(queryDescription, BooleanClause.Occur.SHOULD);
			}
			
			for(Query incIng : queryIncIngredient)
			{
				booleanQuery.add(incIng, BooleanClause.Occur.MUST);
			}
			
			for(Query remIng: queryRemIngredient)
			{
				booleanQuery.add(remIng, BooleanClause.Occur.MUST_NOT);
			}
			
			if(queryTime1 != null)
			{
				booleanQuery.add(queryTime1, BooleanClause.Occur.MUST);
				booleanQuery.add(queryTime2, BooleanClause.Occur.MUST_NOT);
			}
			
			else if(queryTime2 != null)
			{
				
				booleanQuery.add(queryTime2, BooleanClause.Occur.MUST);
			}
			
			if(queryStars != null)
				booleanQuery.add(queryStars, BooleanClause.Occur.MUST);
			
			if(queryCategory != null)
				booleanQuery.add(queryCategory, BooleanClause.Occur.MUST);
			
			System.out.println("Advanced Searching for: " + booleanQuery.toString());
			sct =  scoreDocs(searcher, booleanQuery);
			
	        /*close the searcher*/
	        searcher.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
        return sct;
	}
	
    public List<ScoredRecipe> scoreDocs(IndexSearcher searcher, Query query)
    {
        List<ScoredRecipe> sct = new ArrayList<ScoredRecipe>();

        TopDocs results = null;
        try
        {
        	/*use the function search of lucene to search*/
            results = searcher.search(query, Preferences.TotalRecipes);
        } catch (IOException ex)
        {
            Logger.getLogger(LuceneSearcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        ScoreDoc[] score = results.scoreDocs;    
 
        for(ScoreDoc aux : score)
        {
            try
            {
            	Document d = searcher.doc(aux.doc);
            	String number = d.getFieldable("recipeId").stringValue();
            	
                sct.add(new ScoredRecipe(new LuceneRecipe(Integer.parseInt(searcher.doc(aux.doc).get("recipeId")), searcher.doc(aux.doc).get("name"), searcher.doc(aux.doc).get("description"), searcher.doc(aux.doc).get("timePrep"), searcher.doc(aux.doc).get("timeCook"), searcher.doc(aux.doc).get("timeTotal"), searcher.doc(aux.doc).get("category"), searcher.doc(aux.doc).get("rating"), searcher.doc(aux.doc).get("ingredient"), searcher.doc(aux.doc).get("direction"), searcher.doc(aux.doc).get("nutrient"), searcher.doc(aux.doc).get("review")),aux.score));
            } catch (CorruptIndexException ex)
            {
                Logger.getLogger(LuceneSearcher.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex)
            {
                Logger.getLogger(LuceneSearcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
 
        Collections.sort(sct, new Comparator<ScoredRecipe>()
        {
            @Override
            public int compare(ScoredRecipe t1, ScoredRecipe t2)
            {
                return new Double(t1.getScoreRec()).compareTo(new Double(t2.getScoreRec()));
            }
        });
 
        Collections.sort(sct);
 
        return sct;
    }
}
