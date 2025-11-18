package co.edu.poli.proyecto.modelo;

/**
 * Clase que representa alimentos deshidratados (comida seca).
 * @author Santiago Gutiérrez
 * @author Juliana Aponte
 * @version 1.0
 */
public class Deshidratado extends Solido {
    private static final long serialVersionUID = 1L;
    
    private double porcentajeHumedad;
    private String metodoDeshidratacion;

    /**
     * Constructor con parámetros
     * @param codigo Código único
     * @param nombre Nombre del alimento
     * @param descripcion Descripción
     * @param cantidadDisponible Cantidad disponible
     * @param fechaVencimiento Fecha de vencimiento
     * @param empaque Empaque
     * @param proveedor Proveedor
     * @param pesoGramos Peso en gramos
     * @param porcentajeHumedad Porcentaje de humedad restante
     * @param metodoDeshidratacion Método usado para deshidratar
     */
    public Deshidratado(String codigo, String nombre, String descripcion, 
                        int cantidadDisponible, String fechaVencimiento, 
                        Empaque empaque, Proveedor proveedor,
                        int pesoGramos, double porcentajeHumedad, String metodoDeshidratacion) {
        super(codigo, nombre, descripcion, cantidadDisponible, fechaVencimiento, empaque, proveedor, pesoGramos);
        this.porcentajeHumedad = porcentajeHumedad;
        this.metodoDeshidratacion = metodoDeshidratacion;
    }

    public double getPorcentajeHumedad() {
        return porcentajeHumedad;
    }

    public void setPorcentajeHumedad(double porcentajeHumedad) {
        this.porcentajeHumedad = porcentajeHumedad;
    }

    public String getMetodoDeshidratacion() {
        return metodoDeshidratacion;
    }

    public void setMetodoDeshidratacion(String metodoDeshidratacion) {
        this.metodoDeshidratacion = metodoDeshidratacion;
    }

    @Override
    public String toString() {
        return "=== DESHIDRATADO ===\n" + 
               super.toString() + 
               "\nHumedad: " + porcentajeHumedad + "%" +
               "\nMétodo: " + metodoDeshidratacion;
    }
}