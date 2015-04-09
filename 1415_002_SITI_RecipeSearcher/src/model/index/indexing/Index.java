package model.index.indexing;

import java.sql.ResultSet;
import java.util.*;

import model.entity.Recipe;
import model.index.LuceneRecipe;
import model.index.parsing.TextParser;

public interface Index
{
    /**
    * void build(String inputCollectionPath, String outputIndexPath, TextParser textParser)
    * Método que construye un índice a partir de una colección de documentos de texto plano.
    * @param inputCollectionPath String ruta de la carpeta donde se encuentran los documentos a indexar.
    * @param outputIndexPath String ruta de la carpeta en la que almacenar el índice creado.
    * @param textParser TextParser parser de texto que procesará el texto de los documentos para su indexación.     
    */	
	public void build(String connection, String outputIndexPath, TextParser textParser);
	
	public void load(String indexPath);
	
	public List<String> getDocumentIds();
	
	public LuceneRecipe getDocument(String documentId);
	
	public List<String> getTerms();
	
	public List<Posting> getTermPostings (String term);
	
}
