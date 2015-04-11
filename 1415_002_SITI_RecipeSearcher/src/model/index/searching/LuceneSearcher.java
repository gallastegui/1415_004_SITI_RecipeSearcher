package model.index.searching;

import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.util.Version;

import model.index.LuceneRecipe;
import model.index.ScoredRecipe;
import model.index.indexing.Index;
import model.index.indexing.LuceneIndexer;

public class LuceneSearcher implements Searcher
{
	private IndexSearcher searcher;

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
        
       try{
    	   /*1 create the necessary elements for retrieve the recipes*/
           Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_31);
           QueryParser parser = new QueryParser(Version.LUCENE_31, "contents", analyzer);
           /*2 parse the query*/
           Query query_parsed = parser.parse(query);
           System.out.println("Searching for: " + query_parsed.toString("contents"));
            
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
	
    public List<ScoredRecipe> scoreDocs(IndexSearcher searcher, Query query)
    {
        List<ScoredRecipe> sct = new ArrayList<ScoredRecipe>();

        TopDocs results = null;
        try
        {
        	/*use the function search of lucene to search*/
            results = searcher.search(query, 50);
        } catch (IOException ex)
        {
            Logger.getLogger(LuceneSearcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        ScoreDoc[] score = results.scoreDocs;    
 
        for(ScoreDoc aux : score)
        {
            try
            {
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
