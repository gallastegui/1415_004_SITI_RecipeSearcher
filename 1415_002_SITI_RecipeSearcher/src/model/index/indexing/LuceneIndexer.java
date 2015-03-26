package model.index.indexing;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericField;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

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
	public void build(ResultSet rs, String outputIndexPath, TextParser textParser)
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
            indexDocs(writer, rs, textParser);
            writer.close();
            
            /*Se inicializa un reader para inspeccionar el índice*/
            reader = IndexReader.open(dir);
		} 
		catch (IOException e) 
		{
			System.out.println(" caught a " + e.getClass() + "\n with message: " + e.getMessage());
		}
	}

	private void indexDocs(IndexWriter writer, ResultSet rs, TextParser textParser) 
	{
		Recipe aux = null;
		
		try
		{
			while (rs.next())
			{
				aux = new Recipe(rs.getInt("recipeId"), rs.getString("name"), rs.getString("description"), rs.getString("TimePrep"), rs.getString("TimeCook"), rs.getString("TimeTotal"),rs.getString("Category"), rs.getString("Rating"));
				Document doc = new Document();
	
				/* Add the last modified date of the file a field named "modified".*/
				NumericField modifiedField = new NumericField("modified");
				modifiedField.setLongValue(Calendar.getInstance().getTimeInMillis());
				doc.add(modifiedField);
				    
				/*add the recipeId*/
				doc.add(new NumericField("recipeId", aux.getRecipeId()));
				doc.add(new Field("name", aux.getName(), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
				doc.add(new Field("description", aux.getDescription(), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
				doc.add(new Field("timePrep", aux.getTimePrep(), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
				doc.add(new Field("timeCook", aux.getTimeCook(), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
				doc.add(new Field("timeTotal", aux.getTimeTotal(), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
				doc.add(new Field("Category", aux.getCategory(), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
				doc.add(new Field("Rating", aux.getRating(), Field.Store.YES, Field.Index.ANALYZED, Field.TermVector.WITH_POSITIONS_OFFSETS));
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
		
		
	}

	@Override
	public List<String> getDocumentIds()
	{
		
		return null;
	}

	@Override
	public Recipe getDocument(String documentId)
	{
		
		return null;
	}

	@Override
	public List<String> getTerms()
	{
		
		return null;
	}

	@Override
	public List<Posting> getTermPostings(String term)
	{
		
		return null;
	}

}
