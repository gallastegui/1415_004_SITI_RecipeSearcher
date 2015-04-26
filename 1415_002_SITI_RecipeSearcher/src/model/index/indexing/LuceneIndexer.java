package model.index.indexing;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
import model.index.LuceneRecipe;
import model.index.parsing.TextParser;

public class LuceneIndexer implements Index
{
    /*
    * IndexReader de la clase utilizado para leer del �ndice
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
			/*1 create the directory and the necessary elements to build the index*/
			Directory dir = new SimpleFSDirectory(new File(outputIndexPath));
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_31);
            IndexWriterConfig iwc = new IndexWriterConfig(Version.LUCENE_31, analyzer);
            
            /*2 build the index*/
            iwc.setOpenMode(OpenMode.CREATE);
            
            /*call the function that indexes the documents in the index*/
            IndexWriter writer = new IndexWriter(dir, iwc);
            indexDocs(connection, writer, textParser);
            writer.close();
            
            /*Inizialize the reader to inspect the index¿?*/
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
		ArrayList<String> stopWords =  new ArrayList<String>();
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        String line = null;
        String[] terms = null;
        int i, number;
        
		/*1 : Connect with database*/
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
	    
	    /*2: Obtain the stopwords*/
        try
        {
            stopWords = new ArrayList<String>();
            archivo = new File ("resources/stop-words.txt");
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            
            while((line=br.readLine())!=null)
            {
                stopWords.add(line);
            } 
        }
        catch(Exception e){}
        finally
        {
            try 
            {
                fr.close();
                br.close();
            }
            catch (IOException ex)
            {
                Logger.getLogger(LuceneIndexer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        line = "";
        /*3: Obtain the recipes*/
		try
		{
			stmt = connector.prepareStatement(sql1);
			stmt.setFetchSize(1000);
			rs = stmt.executeQuery();

			while (rs.next())
			{
				/*3.1 create the document*/
				doc = new Document();
	
				/*3.2 Add the last modified date in the document*/
				NumericField modifiedField = new NumericField("modified");
				modifiedField.setLongValue(Calendar.getInstance().getTimeInMillis());
				doc.add(modifiedField);
				
				/*3.3 add the id of the recipe in the document*/
				doc.add(new Field("recipeId", String.valueOf(rs.getInt("recipeId")), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
				
				/*3.4.1 remove the stopwords from the name*/
				line = rs.getString("name");
				terms = line.split(" ");
				line = "";
	            for(i = 0;i < terms.length;i++)
	            {
	            	if(!stopWords.contains(terms[i]))
	            	{
	            		line = line+ " " + terms[i];
	            	}
	            }
				/*3.4.2 add the name in the document*/
				doc.add(new Field("name", line, Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));

				/*3.5.1 remove the stopwords from the description*/
				line = rs.getString("description");
				terms = line.split(" ");
				line = "";
	            for(i = 0;i < terms.length;i++)
	            {
	            	if(!stopWords.contains(terms[i]))
	            	{
	            		line = line +" " + terms[i];
	            	}
	            }
	            /*3.5.2 add the description in the document*/
				doc.add(new Field("description", line, Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
				/*3.6 add the rest of the fields in the document*/
				doc.add(new Field("timePrep", rs.getString("TimePrep"), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
				doc.add(new Field("timeCook", rs.getString("TimeCook"), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
				doc.add(new Field("timeTotal",rs.getString("TimeTotal"), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
				doc.add(new Field("Category", rs.getString("Category"), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
				doc.add(new Field("Rating", rs.getString("Rating"), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
				
				/*3.7.1 Obtain the Ingredients*/
				stmt2 = connector.prepareStatement(sql2);
				stmt2.setFetchSize(1000);
				stmt2.setInt(1, rs.getInt("recipeId"));
				
				rs2 = stmt2.executeQuery();
				/*3.7.2 for each ingredient, remove the stopwords and then add in the document*/
				while(rs2.next())
				{
					ingredient_aux = ingredient_aux + rs2.getString("name") + ";" + rs2.getString("amount");
					terms = ingredient_aux.split(" ");
					ingredient_aux = "";
		            for(i = 0;i < terms.length;i++)
		            {
		            	if(!stopWords.contains(terms[i]))
		            	{
		            		ingredient_aux = ingredient_aux +" " + terms[i];
		            	}
		            }
					doc.add(new Field("ingredient", ingredient_aux, Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
					ingredient_aux = "";
				}
				
				/*3.8.1 Obtain the directions*/
				stmt2 = connector.prepareStatement(sql3);
				stmt2.setFetchSize(1000);
				stmt2.setInt(1, rs.getInt("recipeId"));
				rs2 = stmt2.executeQuery();
				
				/*3.8.2 for each direction, remove the stopwords and then add in the document*/
				while(rs2.next())
				{
					direction_aux = direction_aux + rs2.getInt("directionId") + ";" + rs2.getString("description");

					terms = direction_aux.split(" ");
					direction_aux = "";
		            for(i = 0;i < terms.length;i++)
		            {
		            	if(!stopWords.contains(terms[i]))
		            	{
		            		direction_aux = direction_aux+ " " + terms[i];
		            	}
		            }
					doc.add(new Field("direction", direction_aux, Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
					direction_aux = "";
				}
				
				/*3.8.1 Obtain the nutrients*/
				stmt2 = connector.prepareStatement(sql4);
				stmt2.setFetchSize(1000);
				stmt2.setInt(1, rs.getInt("recipeId"));
				rs2 = stmt2.executeQuery();	
				/*3.8.2 for each nutrient, add in the document*/
				while(rs2.next())
				{
					nutrient_aux = nutrient_aux + rs2.getString("name") + ";" + rs2.getString("amount") + ";" + rs2.getString("percentage");
					doc.add(new Field("nutrient", nutrient_aux, Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
					nutrient_aux = "";
				}
				
				/*3.8.1 Obtain the reviews*/
				stmt2 = connector.prepareStatement(sql5);
				stmt2.setFetchSize(1000);
				stmt2.setInt(1, rs.getInt("recipeId"));
				rs2 = stmt2.executeQuery();		
				
				/*3.8.2 for each review, remove the stopwords and then add in the document*/
				while(rs2.next())
				{
					review_aux = review_aux + rs2.getInt("reviewId") + ";" + rs2.getString("author") + rs2.getString("description");
					terms = review_aux.split(" ");
					review_aux = "";
		            for(i = 0;i < terms.length;i++)
		            {
		            	if(!stopWords.contains(terms[i]))
		            	{
		            		review_aux = review_aux+ " " + terms[i];
		            	}
		            }
					doc.add(new Field("review", review_aux, Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
					review_aux = "";
				}
				try
				{
					/*3.9 try to write the document in the index*/
					writer.addDocument(doc);
					System.out.println("Indexada receta con id "+rs.getInt("recipeId"));
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
		/*try to get the index in the memory ram*/
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
	public LuceneRecipe getDocument(String documentId)
	{
        Document d = null;
        LuceneRecipe r = null;
        try {
            /*Lucene returns the id associated with the document*/
            d = reader.document(Integer.parseInt(documentId));
            /*Create the recipe*/
            r = new LuceneRecipe(Integer.parseInt(d.get("recipeId")), d.get("name"), d.get("description"), d.get("timePrep"), d.get("timeCook"), d.get("timeTotal"), d.get("category"), d.get("rating"), d.get("ingredient"), d.get("direction"), d.get("nutrient"), d.get("review"));
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
