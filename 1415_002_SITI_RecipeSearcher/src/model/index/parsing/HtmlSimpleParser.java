package model.index.parsing;
import org.jsoup.Jsoup;

public class HtmlSimpleParser implements TextParser
{
	
    /**
    * String parse(String text)
    * Método que devuelve procesado un texto de entrada.
    * @param text String texto a procesar.
    * @return String texto procesado.
    */
    @Override
    public String parse(String text)
    {
       String documentText = Jsoup.parse(text).body().text();
       return documentText;
    }

}
