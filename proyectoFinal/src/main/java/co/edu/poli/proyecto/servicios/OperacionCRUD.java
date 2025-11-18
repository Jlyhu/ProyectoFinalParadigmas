package co.edu.poli.proyecto.servicios;

import co.edu.poli.proyecto.modelo.Alimento;

/**
 * Interface que define las operaciones CRUD para alimentos espaciales.
 * @author Santiago Gutiérrez
 * @author Juliana Aponte
 * @version 1.0
 */
public interface OperacionCRUD {
    
    /**
     * Crea un nuevo alimento espacial
     * @param objeto Alimento a crear
     * @return Mensaje de confirmación
     */
    String create(Alimento objeto);
    
    /**
     * Lee todos los alimentos espaciales
     * @return Arreglo con todos los alimentos
     */
    Alimento[] read();
    
    /**
     * Lee un alimento por su código
     * @param id Código del alimento
     * @return Alimento encontrado o null
     */
    Alimento readId(String id);
    
    /**
     * Actualiza un alimento existente
     * @param id Código del alimento a actualizar
     * @param objeto Nuevos datos
     * @return Mensaje de confirmación
     */
    String update(String id, Alimento objeto);
    
    /**
     * Elimina un alimento
     * @param id Código del alimento a eliminar
     * @return Alimento eliminado o null
     */
    Alimento delete(String id);
}