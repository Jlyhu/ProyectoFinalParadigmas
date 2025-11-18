package co.edu.poli.proyecto.modelo;

import java.io.Serializable;

/**
 * Clase que representa un astronauta asignado a una misión.
 * @author Santiago Gutiérrez
 * @author Juliana Aponte
 * @version 1.0
 */
public class Astronauta implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String idAstronauta;
    private String nombre;
    private String especialidad;

    /**
     * Constructor con parámetros
     * @param idAstronauta Identificador del astronauta
     * @param nombre Nombre del astronauta
     * @param especialidad Especialidad
     */
    public Astronauta(String idAstronauta, String nombre, String especialidad) {
        this.idAstronauta = idAstronauta;
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    public String getIdAstronauta() {
        return idAstronauta;
    }

    public void setIdAstronauta(String idAstronauta) {
        this.idAstronauta = idAstronauta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return "Astronauta: " + nombre + " (" + especialidad + ")";
    }
}