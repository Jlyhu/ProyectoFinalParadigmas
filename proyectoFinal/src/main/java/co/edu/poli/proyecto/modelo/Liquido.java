package co.edu.poli.proyecto.modelo;

/**
 * Clase que representa un alimento líquido (bebidas, sopas).
 * @author Santiago Gutiérrez
 * @author Juliana Aponte
 * @version 1.0
 */
public class Liquido extends Alimento {
    private static final long serialVersionUID = 1L;
    
    private int volumenML;
    private String temperatura;

    /**
     * Constructor con parámetros
     * @param codigo Código único
     * @param nombre Nombre del líquido
     * @param descripcion Descripción
     * @param cantidadDisponible Cantidad disponible
     * @param fechaVencimiento Fecha de vencimiento
     * @param empaque Empaque
     * @param proveedor Proveedor
     * @param volumenML Volumen en mililitros
     * @param temperatura Temperatura de almacenamiento
     */
    public Liquido(String codigo, String nombre, String descripcion, 
                   int cantidadDisponible, String fechaVencimiento, 
                   Empaque empaque, Proveedor proveedor,
                   int volumenML, String temperatura) {
        super(codigo, nombre, descripcion, cantidadDisponible, fechaVencimiento, empaque, proveedor);
        this.volumenML = volumenML;
        this.temperatura = temperatura;
    }

    public int getVolumenML() {
        return volumenML;
    }

    public void setVolumenML(int volumenML) {
        this.volumenML = volumenML;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    @Override
    public String toString() {
        return "=== LÍQUIDO ===\n" + 
               super.toString() + 
               "\nVolumen: " + volumenML + " ml" +
               "\nTemperatura: " + temperatura;
    }
}