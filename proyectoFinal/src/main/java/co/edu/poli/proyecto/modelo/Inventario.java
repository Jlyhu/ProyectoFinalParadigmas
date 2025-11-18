package co.edu.poli.proyecto.modelo;

import java.io.Serializable;
import java.util.Arrays;

/**
 * Clase que representa un inventario de alimentos para una misión espacial.
 * @author Santiago Gutiérrez
 * @author Juliana Aponte
 * @version 1.0
 */
public class Inventario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String idInventario;
    private String nombreMision;
    private String fechaCreacion;
    private Alimento[] alimentos;
    private Astronauta astronauta;

    /**
     * Constructor con parámetros
     * @param idInventario Identificador del inventario
     * @param nombreMision Nombre de la misión
     * @param fechaCreacion Fecha de creación
     * @param alimentos Arreglo de alimentos
     * @param astronauta Propietario del inventario
     */
    public Inventario(String idInventario, String nombreMision, String fechaCreacion, 
                      Alimento[] alimentos, Astronauta astronauta) {
        super();
        this.idInventario = idInventario;
        this.nombreMision = nombreMision;
        this.fechaCreacion = fechaCreacion;
        this.alimentos = alimentos;
        this.astronauta = astronauta;
    }

    public String getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(String idInventario) {
        this.idInventario = idInventario;
    }

    public String getNombreMision() {
        return nombreMision;
    }

    public void setNombreMision(String nombreMision) {
        this.nombreMision = nombreMision;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Alimento[] getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(Alimento[] alimentos) {
        this.alimentos = alimentos;
    }

    public Astronauta getAstronauta() {
        return astronauta;
    }

    public void setAstronauta(Astronauta astronauta) {
        this.astronauta = astronauta;
    }

    @Override
    public String toString() {
        return "Inventario [idInventario=" + idInventario + ", nombreMision=" + nombreMision + 
               ", fechaCreacion=" + fechaCreacion + ", alimentos=" + Arrays.toString(alimentos) + 
               ", astronauta=" + astronauta + "]";
    }
}