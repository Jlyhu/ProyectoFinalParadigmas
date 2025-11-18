package co.edu.poli.proyecto.modelo;

import java.io.Serializable;

/**
 * Clase que representa el empaque de un alimento espacial.
 * @author Santiago Gutiérrez
 * @author Juliana Aponte
 * @version 1.0
 */
public class Empaque implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String idEmpaque;
    private String material;
    private String tipoSellado;

    /**
     * Constructor con parámetros
     * @param idEmpaque Identificador del empaque
     * @param material Material del empaque
     * @param tipoSellado Tipo de sellado
     */
    public Empaque(String idEmpaque, String material, String tipoSellado) {
        this.idEmpaque = idEmpaque;
        this.material = material;
        this.tipoSellado = tipoSellado;
    }

    public String getIdEmpaque() {
        return idEmpaque;
    }

    public void setIdEmpaque(String idEmpaque) {
        this.idEmpaque = idEmpaque;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getTipoSellado() {
        return tipoSellado;
    }

    public void setTipoSellado(String tipoSellado) {
        this.tipoSellado = tipoSellado;
    }

    @Override
    public String toString() {
        return "Empaque: " + material + " - " + tipoSellado;
    }
}