package co.edu.poli.proyecto.modelo;

/**
 * Clase abstracta que representa alimentos sólidos.
 * @author Santiago Gutiérrez
 * @author Juliana Aponte
 * @version 1.0
 */
public abstract class Solido extends Alimento {
    private static final long serialVersionUID = 1L;
    
    private int pesoGramos;

    /**
     * Constructor con parámetros
     * @param codigo Código único
     * @param nombre Nombre del sólido
     * @param descripcion Descripción
     * @param cantidadDisponible Cantidad disponible
     * @param fechaVencimiento Fecha de vencimiento
     * @param empaque Empaque
     * @param proveedor Proveedor
     * @param pesoGramos Peso en gramos
     */
    public Solido(String codigo, String nombre, String descripcion, 
                  int cantidadDisponible, String fechaVencimiento, 
                  Empaque empaque, Proveedor proveedor,
                  int pesoGramos) {
        super(codigo, nombre, descripcion, cantidadDisponible, fechaVencimiento, empaque, proveedor);
        this.pesoGramos = pesoGramos;
    }

    public int getPesoGramos() {
        return pesoGramos;
    }

    public void setPesoGramos(int pesoGramos) {
        this.pesoGramos = pesoGramos;
    }

    @Override
    public String toString() {
        return super.toString() + 
               "\nPeso: " + pesoGramos + " g";
    }
}