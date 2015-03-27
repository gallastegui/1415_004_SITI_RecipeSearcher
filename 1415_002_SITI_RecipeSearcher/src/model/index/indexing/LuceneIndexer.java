package model.index.indexing;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.index.TermFreqVector;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.tartarus.snowball.ext.PorterStemmer;

import model.entity.Recipe;
import model.index.parsing.TextParser;

public class LuceneIndexer implements Index
{
    /*
    * IndexReader de la clase utilizado para leer del índice
    */
    private IndexReader reader;

	public IndexReader getReader()
	{
		return reader;
	}
	
    /**
    * LuceneIndexer()
    * Constructor por defecto de la clase.
    */	
	public LuceneIndexer(){}

	@Override
	public void build(String connection, String outputIndexPath, TextParser textParser)
	{		
		try 
		{
			Directory dir = new SimpleFSDirectory(new File(outputIndexPath));
			
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_31);
            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_31, analyzer);
            
            /*Se crea índice*/
            iwc.setOpenMode(OpenMode.CREATE);
            
            /*Se indexan documentos del directorio*/
            IndexWriter writer = new IndexWriter(dir, iwc);
            indexDocs(connection, writer, textParser);
            writer.close();
            
            /*Se inicializa un reader para inspeccionar el índice*/
            reader = IndexReader.open(dir);
		} 
		catch (IOException e) 
		{
			System.out.println(" caught a " + e.getClass() + "\n with message: " + e.getMessage());
		}
	}

	private void indexDocs(String connection, IndexWriter writer, TextParser textParser) 
	{
		PorterStemmer stemmer = new PorterStemmer();
		ResultSet rs = null, rs2 = null;
		Connection connector = null;
		PreparedStatement stmt = null, stmt2 = null;
		Document doc;
		String sql1 = "SELECT * from RECIPE", sql2 = "SELECT i.ingredientId, i.name, i.amount FROM RECIPE r left join INGREDIENT i ON r.recipeId=i.recipeId WHERE r.recipeId = ?", sql3 ="SELECT d.directionId, d.description FROM RECIPE r left join DIRECTION d ON r.recipeId = d.recipeId WHERE r.recipeId = ?", sql4 = "SELECT n.name, rel.amount, rel.percentage FROM NUTRITION n left join REL_RECIPE_NUTRITION rel ON n.nutritionId=rel.nutritionId left join RECIPE r ON r.recipeId = rel.recipeId where r.recipeId = ?", sql5 = "SELECT v.reviewId, v.author, v.description FROM RECIPE r left join REVIEW v ON r.recipeId = v.recipeId WHERE r.recipeId = ? ";
		String ingredient_aux = "", direction_aux = "", nutrient_aux = "", review_aux = "";
		
	    try
	    {
			Class.forName("org.sqlite.JDBC");
		    connector = DriverManager.getConnection("jdbc:sqlite:"+connection);
		    connector.setAutoCommit(false);
		}
	    catch (Exception e)
	    {
	    	System.out.println(" caught a " + e.getClass() + "\n with message: " + e.getMessage());
	    	return;
		}
	    
		try
		{
			/*Obtain the recipes*/
			stmt = connector.prepareStatement(sql1);
			stmt.setFetchSize(1000);
			rs = stmt.executeQuery();

			while (rs.next())
			{
				doc = new Document();
	
				/* Add the last modified date of the file a field named "modified".*/
				NumericField modifiedField = new NumericField("modified");
				modifiedField.setLongValue(Calendar.getInstance().getTimeInMillis());
				doc.add(modifiedField);
				    
				/*add all the fields from the recipe*/
				doc.add(new NumericField("recipeId", rs.getInt("recipeId")));
				doc.add(new Field("name", textParser.parse(rs.getString("name")), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
				doc.add(new Field("description", textParser.parse(rs.getString("description")), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
				doc.add(new Field("timePrep", textParser.parse(rs.getString("TimePrep")), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
				doc.add(new Field("timeCook", textParser.parse(rs.getString("TimeCook")), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
				doc.add(new Field("timeTotal",textParser.parse(rs.getString("TimeTotal")), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
				doc.add(new Field("Category", textParser.parse(rs.getString("Category")), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
				doc.add(new Field("Rating", textParser.parse(rs.getString("Rating")), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
				
				/*Obtain the Ingredients*/
				stmt2 = connector.prepareStatement(sql2);
				stmt2.setFetchSize(1000);
				stmt2.setInt(1, rs.getInt("recipeId"));
				
				rs2 = stmt2.executeQuery();
				
				while(rs2.next())
				{
					ingredient_aux = ingredient_aux + rs2.getInt("ingredientId") + ";" + textParser.parse(rs2.getString("name")) + ";" + rs2.getString("amount");
					doc.add(new Field("ingredient", ingredient_aux, Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
					ingredient_aux = "";
				}
				
				stmt2 = connector.prepareStatement(sql3);
				stmt2.setFetchSize(1000);
				stmt2.setInt(1, rs.getInt("recipeId"));
				rs2 = stmt2.executeQuery();
				
				while(rs2.next())
				{
					direction_aux = direction_aux + rs2.getInt("directionId") + ";" + textParser.parse(rs2.getString("description"));
					doc.add(new Field("direction", direction_aux, Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
					direction_aux = "";
				}
				
				stmt2 = connector.prepareStatement(sql4);
				stmt2.setFetchSize(1000);
				stmt2.setInt(1, rs.getInt("recipeId"));
				rs2 = stmt2.executeQuery();	
			
				while(rs2.next())
				{
					nutrient_aux = nutrient_aux + rs2.getInt("nutrientId") + ";" + textParser.parse(rs2.getString("name")) + ";" + rs2.getString("amount") + ";" + rs2.getString("percentage");
					doc.add(new Field("nutrient", nutrient_aux, Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
					nutrient_aux = "";
				}
				
				stmt2 = connector.prepareStatement(sql5);
				stmt2.setFetchSize(1000);
				stmt2.setInt(1, rs.getInt("recipeId"));
				rs2 = stmt2.executeQuery();		
				
				while(rs2.next())
				{
					review_aux = review_aux + rs2.getInt("reviewId") + ";" + textParser.parse(rs2.getString("author")) + textParser.parse(rs2.getString("description"));
					doc.add(new Field("review", review_aux, Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
					review_aux = "";
				}
				
				try
				{
					writer.addDocument(doc);
				}
				catch (Exception e)
				{
					System.out.println(" caught a " + e.getClass() + "\n with message: " + e.getMessage());
				}
			}
		}
		catch (SQLException e)
		{
			System.out.println(" caught a " + e.getClass() + "\n with message: " + e.getMessage());
		}
	}

	@Override
	public void load(String indexPath)
	{
        try
        {
            reader = IndexReader.open(new RAMDirectory(FSDirectory.open(new File(indexPath))));
        } catch (Exception ex)
        {
            Logger.getLogger(LuceneIndexer.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	@Override
	public List<String> getDocumentIds()
	{
		
        List<String> docIds = new ArrayList<String>();
        /*look in the index*/
        for (int docID = 0; docID < reader.numDocs(); docID++)
        {
            if(reader.isDeleted(docID))
            {
                continue;
            }
            else
            {
                docIds.add(Integer.toString(docID));
            }
        }
        return docIds;
	}

	@Override
	public Recipe getDocument(String documentId)
	{
        Document d = null;
        Recipe r = null;
        try {
            /*Lucene returns the id associated with the document*/
            d = reader.document(Integer.parseInt(documentId));
            /*Create the recipe*/
            r = new Recipe(Integer.parseInt(d.get("recipeId")), d.get("name"), d.get("description"), d.get("timePrep"), d.get("timeCook"), d.get("timeTotal"), d.get("category"), d.get("rating"));
        } catch (Exception ex)
        {
            Logger.getLogger(LuceneIndexer.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        return r;
	}

	@Override
	public List<String> getTerms()
	{
		
		return null;
	}

	@Override
	public List<Posting> getTermPostings(String term)
	{
        ArrayList<Posting> post = new ArrayList<Posting>();
        String terms[];
        int freqs[];
        for (int docID = 0; docID < reader.numDocs(); docID++)
        {
            TermFreqVector docVector = null;
            try 
            {
            	/*look at the field name*/
                docVector = reader.getTermFreqVector(docID, "name");
                terms = docVector.getTerms();
                freqs = docVector.getTermFrequencies();
                /*go over all the terms from the fields and search the term*/
                for(int i = 0; i<terms.length; i++)
                {
                    if(terms[i].equals(term))
                    {
                        Posting p = new Posting(Integer.toString(docID),freqs[i]);
                        post.add(p);
                    }
                }
                
            	/*look at the field description*/
                docVector = reader.getTermFreqVector(docID, "description");
                terms = docVector.getTerms();
                freqs = docVector.getTermFrequencies();
                /*go over all the terms from the fields and search the term*/
                for(int i = 0; i<terms.length; i++)
                {
                    if(terms[i].equals(term))
                    {
                        Posting p = new Posting(Integer.toString(docID),freqs[i]);
                        post.add(p);
                    }
                }
                
            	/*look at the field timePrep*/
                docVector = reader.getTermFreqVector(docID, "timePrep");
                terms = docVector.getTerms();
                freqs = docVector.getTermFrequencies();
                /*go over all the terms from the fields and search the term*/
                for(int i = 0; i<terms.length; i++)
                {
                    if(terms[i].equals(term))
                    {
                        Posting p = new Posting(Integer.toString(docID),freqs[i]);
                        post.add(p);
                    }
                }
                
            	/*look at the field timeCook*/
                docVector = reader.getTermFreqVector(docID, "timeCook");
                terms = docVector.getTerms();
                freqs = docVector.getTermFrequencies();
                /*go over all the terms from the fields and search the term*/
                for(int i = 0; i<terms.length; i++)
                {
                    if(terms[i].equals(term))
                    {
                        Posting p = new Posting(Integer.toString(docID),freqs[i]);
                        post.add(p);
                    }
                }
                
            	/*look at the field timeTotal*/
                docVector = reader.getTermFreqVector(docID, "timeTotal");
                terms = docVector.getTerms();
                freqs = docVector.getTermFrequencies();
                /*go over all the terms from the fields and search the term*/
                for(int i = 0; i<terms.length; i++)
                {
                    if(terms[i].equals(term))
                    {
                        Posting p = new Posting(Integer.toString(docID),freqs[i]);
                        post.add(p);
                    }
                }
                
            	/*look at the field category*/
                docVector = reader.getTermFreqVector(docID, "category");
                terms = docVector.getTerms();
                freqs = docVector.getTermFrequencies();
                /*go over all the terms from the fields and search the term*/
                for(int i = 0; i<terms.length; i++)
                {
                    if(terms[i].equals(term))
                    {
                        Posting p = new Posting(Integer.toString(docID),freqs[i]);
                        post.add(p);
                    }
                }
                
            	/*look at the field rating*/
                docVector = reader.getTermFreqVector(docID, "rating");
                terms = docVector.getTerms();
                freqs = docVector.getTermFrequencies();
                /*go over all the terms from the fields and search the term*/
                for(int i = 0; i<terms.length; i++)
                {
                    if(terms[i].equals(term))
                    {
                        Posting p = new Posting(Integer.toString(docID),freqs[i]);
                        post.add(p);
                    }
                }
                
            	/*look at the field ingredient*/
                docVector = reader.getTermFreqVector(docID, "ingredient");
                terms = docVector.getTerms();
                freqs = docVector.getTermFrequencies();
                /*go over all the terms from the fields and search the term*/
                for(int i = 0; i<terms.length; i++)
                {
                    if(terms[i].equals(term))
                    {
                        Posting p = new Posting(Integer.toString(docID),freqs[i]);
                        post.add(p);
                    }
                }
                
            	/*look at the field direction*/
                docVector = reader.getTermFreqVector(docID, "direction");
                terms = docVector.getTerms();
                freqs = docVector.getTermFrequencies();
                /*go over all the terms from the fields and search the term*/
                for(int i = 0; i<terms.length; i++)
                {
                    if(terms[i].equals(term))
                    {
                        Posting p = new Posting(Integer.toString(docID),freqs[i]);
                        post.add(p);
                    }
                }
                
            	/*look at the field nutrient*/
                docVector = reader.getTermFreqVector(docID, "nutrient");
                terms = docVector.getTerms();
                freqs = docVector.getTermFrequencies();
                /*go over all the terms from the fields and search the term*/
                for(int i = 0; i<terms.length; i++)
                {
                    if(terms[i].equals(term))
                    {
                        Posting p = new Posting(Integer.toString(docID),freqs[i]);
                        post.add(p);
                    }
                }
                
            	/*look at the field review*/
                docVector = reader.getTermFreqVector(docID, "review");
                terms = docVector.getTerms();
                freqs = docVector.getTermFrequencies();
                /*go over all the terms from the fields and search the term*/
                for(int i = 0; i<terms.length; i++)
                {
                    if(terms[i].equals(term))
                    {
                        Posting p = new Posting(Integer.toString(docID),freqs[i]);
                        post.add(p);
                    }
                }
            }
            catch (IOException ex)
            {
                Logger.getLogger(LuceneIndexer.class.getName()).log(Level.SEVERE, null, ex);
            }            
        }
        return post;
	}

}
