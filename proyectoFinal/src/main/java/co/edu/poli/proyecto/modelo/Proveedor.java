package co.edu.poli.proyecto.modelo;

import java.io.Serializable;

/**
 * Clase que representa un proveedor de alimentos espaciales.
 * @author Santiago Gutiérrez
 * @author Juliana Aponte
 * @version 1.0
 */
public class Proveedor implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String idProveedor;
    private String nombre;
    private String pais;

    /**
     * Constructor con parámetros
     * @param idProveedor Identificador del proveedor
     * @param nombre Nombre del proveedor
     * @param pais País de origen
     */
    public Proveedor(String idProveedor, String nombre, String pais) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.pais = pais;
    }

    public String getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(String idProveedor) {
        this.idProveedor = idProveedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return "Proveedor: " + nombre + " (" + pais + ")";
    }
}