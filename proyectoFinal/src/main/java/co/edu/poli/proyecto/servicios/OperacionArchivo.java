package co.edu.poli.proyecto.servicios;

import co.edu.poli.proyecto.modelo.Alimento;

/**
 * Interface para operaciones de serialización.
 * @author Santiago Gutiérrez
 * @author Juliana Aponte
 * @version 1.0
 */
public interface OperacionArchivo {
    
    /**
     * Serializa los alimentos en un archivo
     * @param alimentos Arreglo de alimentos
     * @param path Ruta del archivo
     * @param name Nombre del archivo
     * @return Mensaje de confirmación
     */
    String serializar(Alimento[] alimentos, String path, String name);
    
    /**
     * Deserializa alimentos desde un archivo
     * @param path Ruta del archivo
     * @param name Nombre del archivo
     * @return Arreglo de alimentos
     */
    Alimento[] deserializar(String path, String name);
}