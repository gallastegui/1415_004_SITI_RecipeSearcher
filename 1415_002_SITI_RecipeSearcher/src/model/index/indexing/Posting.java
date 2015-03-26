package model.index.indexing;

import java.util.ArrayList;

public class Posting 
{
    /*
    * Identificador único del Posting.
    */
    private String id;
    
    /*
    * Frecuencia de aparición del término en el documento
    */
    private int frec;
    
    /*
    * lista de posiciones del termino
    */
    private ArrayList<Long> termPos;
    
    /**
    * Posting(String documentID, int frec)
    * Constructor de la clase.
    * @param documentID String identificador único del documento en el que aparece un término.
    * @param frec Integer frecuencia de aparición del término en el documento.
    */
    public Posting(String documentID, int frec)
    {
        this.id=documentID;
        this.frec=frec;
        this.termPos = new ArrayList<Long>();
    }
    
    /**
    * int getTermFrequency()
    * Getter.
    * @return int frecuencia del término en el documento
    */
    public int getTermFrequency()
    {
        return this.frec;
    }
}
