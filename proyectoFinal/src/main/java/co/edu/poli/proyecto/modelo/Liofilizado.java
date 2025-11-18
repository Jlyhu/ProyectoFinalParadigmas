package co.edu.poli.proyecto.modelo;

/**
 * Clase que representa alimentos liofilizados (congelados y deshidratados).
 * @author Santiago Gutiérrez
 * @author Juliana Aponte
 * @version 1.0
 */
public class Liofilizado extends Solido {
    private static final long serialVersionUID = 1L;
    
    private int tiempoRehidratacion;
    private int pesoOriginal;

    /**
     * Constructor con parámetros
     * @param codigo Código único
     * @param nombre Nombre del alimento
     * @param descripcion Descripción
     * @param cantidadDisponible Cantidad disponible
     * @param fechaVencimiento Fecha de vencimiento
     * @param empaque Empaque
     * @param proveedor Proveedor
     * @param pesoGramos Peso actual en gramos
     * @param tiempoRehidratacion Tiempo de rehidratación en minutos
     * @param pesoOriginal Peso original antes de liofilizar
     */
    public Liofilizado(String codigo, String nombre, String descripcion, 
                       int cantidadDisponible, String fechaVencimiento, 
                       Empaque empaque, Proveedor proveedor,
                       int pesoGramos, int tiempoRehidratacion, int pesoOriginal) {
        super(codigo, nombre, descripcion, cantidadDisponible, fechaVencimiento, empaque, proveedor, pesoGramos);
        this.tiempoRehidratacion = tiempoRehidratacion;
        this.pesoOriginal = pesoOriginal;
    }

    public int getTiempoRehidratacion() {
        return tiempoRehidratacion;
    }

    public void setTiempoRehidratacion(int tiempoRehidratacion) {
        this.tiempoRehidratacion = tiempoRehidratacion;
    }

    public int getPesoOriginal() {
        return pesoOriginal;
    }

    public void setPesoOriginal(int pesoOriginal) {
        this.pesoOriginal = pesoOriginal;
    }

    @Override
    public String toString() {
        return "=== LIOFILIZADO ===\n" + 
               super.toString() + 
               "\nTiempo Rehidratación: " + tiempoRehidratacion + " min" +
               "\nPeso Original: " + pesoOriginal + " g";
    }
}