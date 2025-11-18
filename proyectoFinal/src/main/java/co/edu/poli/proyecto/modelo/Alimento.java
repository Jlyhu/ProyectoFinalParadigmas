package co.edu.poli.proyecto.modelo;

import java.io.Serializable;

/**
 * Clase abstracta que representa un alimento espacial.
 * Contiene los atributos comunes a todos los alimentos.
 * @author Santiago Gutiérrez
 * @author Juliana Aponte
 * @version 1.0
 */
public abstract class Alimento implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String codigo;
    private String nombre;
    private String descripcion;
    private int cantidadDisponible;
    private String fechaVencimiento;
    private Empaque empaque;
    private Proveedor proveedor;

    /**
     * Constructor con parámetros
     * @param codigo Código único del alimento
     * @param nombre Nombre del alimento
     * @param descripcion Descripción del alimento
     * @param cantidadDisponible Cantidad disponible
     * @param fechaVencimiento Fecha de vencimiento
     * @param empaque Empaque del alimento
     * @param proveedor Proveedor del alimento
     */
    public Alimento(String codigo, String nombre, String descripcion, 
                    int cantidadDisponible, String fechaVencimiento, 
                    Empaque empaque, Proveedor proveedor) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidadDisponible = cantidadDisponible;
        this.fechaVencimiento = fechaVencimiento;
        this.empaque = empaque;
        this.proveedor = proveedor;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Empaque getEmpaque() {
        return empaque;
    }

    public void setEmpaque(Empaque empaque) {
        this.empaque = empaque;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @Override
    public String toString() {
        return "Código: " + codigo + 
               "\nNombre: " + nombre + 
               "\nDescripción: " + descripcion + 
               "\nCantidad: " + cantidadDisponible + 
               "\nFecha Vencimiento: " + fechaVencimiento +
               "\nProveedor: " + proveedor.getNombre();
    }
}