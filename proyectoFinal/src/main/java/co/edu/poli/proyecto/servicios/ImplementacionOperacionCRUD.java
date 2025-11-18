package co.edu.poli.proyecto.servicios;

import co.edu.poli.proyecto.modelo.Alimento;
import java.io.*;

/**
 * Implementación de operaciones CRUD y archivo con arreglo estático.
 * @author Santiago Gutiérrez
 * @author Juliana Aponte
 * @version 1.0
 */
public class ImplementacionOperacionCRUD implements OperacionCRUD, OperacionArchivo {
    
    private Alimento[] alimentos;
    private int capacidadInicial;

    /**
     * Constructor por defecto. Inicializa arreglo con capacidad de 50.
     */
    public ImplementacionOperacionCRUD() {
        this.capacidadInicial = 50;
        this.alimentos = new Alimento[capacidadInicial];
    }

    @Override
    public String create(Alimento objeto) {
        for (int i = 0; i < alimentos.length; i++) {
            if (alimentos[i] == null) {
                alimentos[i] = objeto;
                return "Alimento creado exitosamente en posición " + i;
            }
        }
        return "Error: No hay espacio disponible";
    }

    @Override
    public Alimento[] read() {
        return alimentos;
    }

    @Override
    public Alimento readId(String id) {
        for (Alimento a : alimentos) {
            if (a != null && a.getCodigo().equals(id)) {
                return a;
            }
        }
        return null;
    }

    @Override
    public String update(String id, Alimento objeto) {
        for (int i = 0; i < alimentos.length; i++) {
            if (alimentos[i] != null && alimentos[i].getCodigo().equals(id)) {
                alimentos[i] = objeto;
                return "Alimento actualizado exitosamente";
            }
        }
        return "Error: Alimento no encontrado";
    }

    @Override
    public Alimento delete(String id) {
        for (int i = 0; i < alimentos.length; i++) {
            if (alimentos[i] != null && alimentos[i].getCodigo().equals(id)) {
                Alimento eliminado = alimentos[i];
                alimentos[i] = null;
                return eliminado;
            }
        }
        return null;
    }

    @Override
    public String serializar(Alimento[] alimentos, String path, String name) {
        try {
            FileOutputStream fos = new FileOutputStream(path + File.separator + name);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(alimentos);
            oos.close();
            fos.close();
            return "Archivo guardado exitosamente";
        } catch (IOException e) {
            return "Error al guardar: " + e.getMessage();
        }
    }

    @Override
    public Alimento[] deserializar(String path, String name) {
        File archivo = new File(path + File.separator + name);
        if (!archivo.exists()) {
            System.out.println("Archivo no encontrado. Iniciando con datos vacíos.");
            return null;
        }
        
        try {
            FileInputStream fis = new FileInputStream(archivo);
            ObjectInputStream ois = new ObjectInputStream(fis);
            Alimento[] cargados = (Alimento[]) ois.readObject();
            ois.close();
            fis.close();
            return cargados;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar: " + e.getMessage());
            return null;
        }
    }

    public Alimento[] getAlimentos() {
        return alimentos;
    }

    public void setAlimentos(Alimento[] alimentos) {
        this.alimentos = alimentos;
    }
}