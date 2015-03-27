package model.index.parsing;

public class StopwordParser implements TextParser
{

	@Override
	public String parse(String text)
	{
        //Cadena de caracteres original a sustituir
        String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        //Cadena de caracteres ASCII que reemplazarán los originales
        String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
        String output = text;
        for (int i=0; i<original.length(); i++) {
            //Reemplazamos los caracteres especiales
            output = output.replace(original.charAt(i), ascii.charAt(i));
        }
        output = output.replace("?", "");
        output = output.replace("¿", "");
        output = output.replace("¡", "");
        output = output.replace("!", "");
        output = output.replace(",", "");
        output = output.replace(".", "");
        output = output.replace(";", "");

        return output;
	}
}
