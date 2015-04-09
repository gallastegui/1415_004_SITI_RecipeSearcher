package model.index.searching;


import java.util.List;

import model.index.ScoredRecipe;
import model.index.indexing.Index;

/**
* @author Gonzalo Gallastegui y Laura García
* Esta clase representa la estructura básica de un buscador.
* @version 0.1 15 de febrero de 2014
*/
public interface Searcher {
     
    /**
    * void build(Index index)
    * Método que crea un buscador a partir del índice pasado como argumento de entrada.
    * @param index Index índice a partir del cual se crea el buscador.    
    */
    public void build(Index index);
     
    /**
    * List<ScoredTextDocument> search(String query)
    * Método que devuelve un ránking ordenador por score decreciente de documentos, resultantes de ejecutar
    * una consulta dada sobre el índice del buscador.
    * @param query String consulta a ejecutar sobre el buscador.
    * @return List<ScoredTextDocument> listado de los scores de los documentos que satisfacen la consulta.     
    */
    public List<ScoredRecipe> search(String query);
}