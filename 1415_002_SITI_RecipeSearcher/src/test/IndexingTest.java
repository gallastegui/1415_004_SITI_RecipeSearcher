package test;

import model.index.indexing.LuceneIndexer;
import model.index.parsing.HtmlSimpleParser;

public class IndexingTest
{
    public static void main(String[] args)
    {
        int i, numFrec=0, numDoc=0;
        LuceneIndexer li = new LuceneIndexer();
        HtmlSimpleParser parser = new HtmlSimpleParser();
 
            //Se inicia creación del índice
            li.build("C:\\Users\\g.gallastegui\\db\\allrecipesv1.db", "resources\\index", parser);
    }
}
