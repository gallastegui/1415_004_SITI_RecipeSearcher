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
		BooleanQuery booleanQuery = new BooleanQuery(), aux = new BooleanQuery(), booleanTime = new BooleanQuery(), booleanTime2 = new BooleanQuery();
	    String[] fields = {"name","description"}, _1to30 = {"1 min","2 mins","3 mins","4 mins","5 mins","6 mins","7 mins","8 mins","9 mins","10 mins","11 mins","12 mins","13 mins","14 mins","15 mins","16 mins","17 mins","18 mins","19 mins","20 mins","21 mins","22 mins","23 mins","24 mins","25 mins","26 mins","27 mins","28 mins","29 mins"},
	    		_31to59 = {"30 mins","31 mins","32 mins","33 mins","34 mins","35 mins","36 mins","37 mins","38 mins","39 mins","40 mins","41 mins","42 mins","43 mins","44 mins","45 mins","46 mins","47 mins","48 mins","49 mins","50 mins","51 mins","52 mins","53 mins","54 mins","55 mins","56 mins","57 mins","58 mins","59 mins"};
	    Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_31);
	    QueryParser queryParserName = new QueryParser(Version.LUCENE_31, "name", new StandardAnalyzer(Version.LUCENE_31)),queryParserDes= new QueryParser(Version.LUCENE_31, "description", new StandardAnalyzer(Version.LUCENE_31)), queryParserIng = new QueryParser(Version.LUCENE_31, "ingredient", new StandardAnalyzer(Version.LUCENE_31));;
		ArrayList<Query> queryIncIngredient = new ArrayList<Query>();
		ArrayList<Query> queryRemIngredient = new ArrayList<Query>();
		ArrayList<Query> queryTime1 = new ArrayList<Query>(), queryTime2 = new ArrayList<Query>();
		Query queryStars = null;
		Query queryCategory = null;
		Query queryName = null;
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
						queryIncIngredient.add(queryParserIng.parse(ing.getName()+";"+ing.getAmount()));
					}
					else
					{
						queryIncIngredient.add(queryParserIng.parse(ing.getName()));
					}
				}
				else
				{
					if(!ing.getAmount().isEmpty())
					{
						queryIncIngredient.add(queryParserIng.parse(ing.getAmount()));
					}
				}
			}
			
			for(Ingredient ing : remIngredients)
			{
				if(!ing.getName().isEmpty())
				{
					if(!ing.getAmount().isEmpty())
					{
						queryRemIngredient.add(queryParserIng.parse(ing.getName()+";"+ing.getAmount()));
					}
					else
					{
						queryRemIngredient.add(queryParserIng.parse(ing.getName()));
					}
				}
				else
				{
					if(!ing.getAmount().isEmpty())
					{
						queryRemIngredient.add(queryParserIng.parse(ing.getAmount()));
					}
				}
			}
			
			if(!descriptionText.isEmpty())
			{
				queryName = queryParserName.parse(descriptionText);
				queryDescription = queryParserDes.parse(descriptionText);
			}
			else
			{
				queryDescription = null;
			}
			
			if(!comboTime.isEmpty())
			{
				if(comboTime.equals("less than 30 mins"))
				{
					for(String timeAux : _1to30)
					{
						queryTime1.add(new TermQuery(new Term("timeTotal", timeAux)));
					}

					queryTime2.add(new TermQuery(new Term("timeTotal", "hr")));
					queryTime2.add(new TermQuery(new Term("timeTotal", "hrs")));
					queryTime2.add(new TermQuery(new Term("timeTotal", "day")));
					queryTime2.add(new TermQuery(new Term("timeTotal", "days")));
				}
				else if(comboTime.equals("between 30-60 mins"))
				{
					for(String timeAux : _31to59)
					{
						queryTime1.add(new TermQuery(new Term("timeTotal", timeAux)));
					}

					queryTime2.add(new TermQuery(new Term("timeTotal", "hr")));
					queryTime2.add(new TermQuery(new Term("timeTotal", "hrs")));
					queryTime2.add(new TermQuery(new Term("timeTotal", "day")));
					queryTime2.add(new TermQuery(new Term("timeTotal", "days")));
				}
				else if(comboTime.equals("more than 60 mins"))
				{
					queryTime1 = null;
					queryTime2.add(new TermQuery(new Term("timeTotal", "hr")));
					queryTime2.add(new TermQuery(new Term("timeTotal", "hrs")));
					queryTime2.add(new TermQuery(new Term("timeTotal", "day")));
					queryTime2.add(new TermQuery(new Term("timeTotal", "days")));
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
				aux.add(queryName,BooleanClause.Occur.SHOULD);
				aux.add(queryDescription, BooleanClause.Occur.SHOULD);
				booleanQuery.add(aux, BooleanClause.Occur.MUST);
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
				if(!queryTime1.isEmpty())
				{
					for(Query timeAux : queryTime1)
					{
						booleanTime.add(timeAux,BooleanClause.Occur.SHOULD);
					}
					booleanQuery.add(booleanTime, BooleanClause.Occur.MUST);
					for(Query timeAux: queryTime2)
					{
						booleanTime2.add(timeAux,BooleanClause.Occur.SHOULD);
					}
					booleanQuery.add(booleanTime2, BooleanClause.Occur.MUST_NOT);
				}
			}
			else if(queryTime2 != null)
			{
				for(Query timeAux: queryTime2)
				{
					booleanTime2.add(timeAux,BooleanClause.Occur.SHOULD);
				}
				booleanQuery.add(booleanTime2, BooleanClause.Occur.MUST);
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
 // olive oil;3 tablespoons
        return sct;
    }
}
