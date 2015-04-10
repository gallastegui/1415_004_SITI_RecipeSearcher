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
        //read the index (created in LuceneIndexer)
        IndexReader reader=((LuceneIndexer)index).getReader();
        //Create the searcher
        searcher = new IndexSearcher(reader);	
	}

	@Override
	public List<ScoredRecipe> search(String query)
	{
        
       List<ScoredRecipe> sct = null;
        
       try{
           Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_31);
           QueryParser parser = new QueryParser(Version.LUCENE_31, "contents", analyzer);

           Query query_parsed = parser.parse(query);
           System.out.println("Searching for: " + query_parsed.toString("contents"));
            
           //return the list of the recipe's that answer the query
           //order by descendent score
           sct =  scoreDocs(searcher, query_parsed);

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
         
        //Método search del searcher (que no tiene nada que ver con el de LuceneSearcher)
        //query a buscar, número de resultados a obtener
        TopDocs results = null;
        try {
            results = searcher.search(query, 50);
        } catch (IOException ex) {
            Logger.getLogger(LuceneSearcher.class.getName()).log(Level.SEVERE, null, ex);
        }
        ScoreDoc[] score = results.scoreDocs;    
 
        for(ScoreDoc aux : score)
        {
            try
            {
                //ScoredTextDocument: TextDocument docAsoc, double scoreDoc
                //TextDocument: String id, String path
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
