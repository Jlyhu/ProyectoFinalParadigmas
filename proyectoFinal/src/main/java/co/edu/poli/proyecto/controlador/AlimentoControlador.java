package co.edu.poli.proyecto.controlador;

import java.io.File;
import co.edu.poli.proyecto.modelo.*;
import co.edu.poli.proyecto.servicios.ImplementacionOperacionCRUD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

public class AlimentoControlador {

    // Instancia de tu clase CRUD
    private ImplementacionOperacionCRUD crud = new ImplementacionOperacionCRUD();
    
    // Lista observable para la tabla
    private ObservableList<Alimento> listaAlimentos = FXCollections.observableArrayList();
    
    // Alimento seleccionado para actualizar
    private Alimento alimentoSeleccionado = null;
    
    // Listas de objetos auxiliares
    private ObservableList<Proveedor> listaProveedores = FXCollections.observableArrayList();
    private ObservableList<Empaque> listaEmpaques = FXCollections.observableArrayList();

    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnCargar;
    @FXML
    private Button btnCrear;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnLimpiar;
    @FXML
    private ComboBox<Empaque> cmbEmpaque;
    @FXML
    private ComboBox<Proveedor> cmbProveedor;
    @FXML
    private TableColumn<Alimento, String> colCodigo;
    @FXML
    private TableColumn<Alimento, String> colNombre;
    @FXML
    private TableColumn<Alimento, String> colDescripcion;
    @FXML
    private TableColumn<Alimento, Integer> colCantidad;
    @FXML
    private TableColumn<Alimento, String> colFechaVenc;
    @FXML
    private TableColumn<Alimento, String> colTipo;
    @FXML
    private TableColumn<Alimento, String> colProveedor;
    @FXML
    private TableColumn<Alimento, String> colEmpaque;
    @FXML
    private DatePicker datePickerVencimiento;
    @FXML
    private TextField lblAtributo1;
    @FXML
    private TextField lblAtributo2;
    @FXML
    private Label lblError;
    @FXML
    private Label lblMensaje;
    @FXML
    private RadioButton rbDeshidratado;
    @FXML
    private RadioButton rbLiofilizado;
    @FXML
    private RadioButton rbLiquido;
    @FXML
    private TableView<Alimento> tblAlimentos;
    @FXML
    private ToggleGroup tipoAlimento;
    @FXML
    private TextField txtCantidad;
    @FXML
    private TextField txtCodigo;
    @FXML
    private TextField txtDescripcion;
    @FXML
    private TextField txtNombre;
    @FXML
    private TextField txtPesoGramos;

    /**
     * Método que se ejecuta automáticamente al cargar el FXML
     */
    @FXML
    public void initialize() {
        configurarTabla();
        cargarDatosAuxiliares();
        configurarComboBoxes();
        configurarEventosRadioButtons();
        cargarDatosIniciales();
    }

    /**
     * Configura las columnas de la tabla
     */
    private void configurarTabla() {
        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidadDisponible"));
        colFechaVenc.setCellValueFactory(new PropertyValueFactory<>("fechaVencimiento"));
        
        // Para el tipo, necesitamos un callback personalizado
        colTipo.setCellValueFactory(cellData -> {
            Alimento alimento = cellData.getValue();
            String tipo = "";
            if (alimento instanceof Liquido) tipo = "Líquido";
            else if (alimento instanceof Liofilizado) tipo = "Liofilizado";
            else if (alimento instanceof Deshidratado) tipo = "Deshidratado";
            return new javafx.beans.property.SimpleStringProperty(tipo);
        });
        
        // Para proveedor y empaque, mostramos el nombre
        colProveedor.setCellValueFactory(cellData -> {
            Proveedor prov = cellData.getValue().getProveedor();
            return new javafx.beans.property.SimpleStringProperty(
                prov != null ? prov.getNombre() : "N/A"
            );
        });
        
        colEmpaque.setCellValueFactory(cellData -> {
            Empaque emp = cellData.getValue().getEmpaque();
            return new javafx.beans.property.SimpleStringProperty(
                emp != null ? emp.getMaterial() : "N/A"
            );
        });
        
        tblAlimentos.setItems(listaAlimentos);
    }

    /**
     * Carga datos de ejemplo para Proveedores y Empaques
     */
    private void cargarDatosAuxiliares() {
        // Crear proveedores de ejemplo
        listaProveedores.add(new Proveedor("P001", "NASA Foods", "USA"));
        listaProveedores.add(new Proveedor("P002", "SpaceX Nutrition", "USA"));
        listaProveedores.add(new Proveedor("P003", "ESA Supplies", "Europa"));
        
        // Crear empaques de ejemplo
        listaEmpaques.add(new Empaque("E001", "Aluminio", "Al vacío"));
        listaEmpaques.add(new Empaque("E002", "Plástico flexible", "Térmico"));
        listaEmpaques.add(new Empaque("E003", "Lata", "Hermético"));
    }

    /**
     * Configura los ComboBoxes
     */
    private void configurarComboBoxes() {
        cmbProveedor.setItems(listaProveedores);
        cmbEmpaque.setItems(listaEmpaques);
        
        // Configurar cómo se muestran en el ComboBox
        cmbProveedor.setConverter(new javafx.util.StringConverter<Proveedor>() {
            @Override
            public String toString(Proveedor p) {
                return p != null ? p.getNombre() : "";
            }
            @Override
            public Proveedor fromString(String string) {
                return null;
            }
        });
        
        cmbEmpaque.setConverter(new javafx.util.StringConverter<Empaque>() {
            @Override
            public String toString(Empaque e) {
                return e != null ? e.getMaterial() : "";
            }
            @Override
            public Empaque fromString(String string) {
                return null;
            }
        });
    }

    /**
     * Configura eventos para los RadioButtons
     */
    private void configurarEventosRadioButtons() {
        rbLiquido.setOnAction(e -> actualizarCamposSegunTipo());
        rbLiofilizado.setOnAction(e -> actualizarCamposSegunTipo());
        rbDeshidratado.setOnAction(e -> actualizarCamposSegunTipo());
    }

    /**
     * Actualiza los campos según el tipo de alimento seleccionado
     */
    private void actualizarCamposSegunTipo() {
        if (rbLiquido.isSelected()) {
            lblAtributo1.setPromptText("Volumen (ml)");
            lblAtributo2.setPromptText("Temperatura");
            txtPesoGramos.setDisable(true);
            txtPesoGramos.clear();
        } else if (rbLiofilizado.isSelected()) {
            lblAtributo1.setPromptText("Tiempo rehidratación (min)");
            lblAtributo2.setPromptText("Peso original (g)");
            txtPesoGramos.setDisable(false);
        } else if (rbDeshidratado.isSelected()) {
            lblAtributo1.setPromptText("% Humedad");
            lblAtributo2.setPromptText("Método deshidratación");
            txtPesoGramos.setDisable(false);
        }
    }

    /**
     * Carga datos iniciales desde el arreglo del CRUD
     */
    private void cargarDatosIniciales() {
        listaAlimentos.clear();
        Alimento[] arreglo = crud.read();
        for (Alimento a : arreglo) {
            if (a != null) {
                listaAlimentos.add(a);
            }
        }
    }

    @FXML
    void crearAlimento(ActionEvent event) {
        try {
            // Validar campos obligatorios
            if (!validarCampos()) {
                mostrarError("Complete todos los campos obligatorios");
                return;
            }

            Alimento nuevoAlimento = null;
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            String descripcion = txtDescripcion.getText();
            int cantidad = Integer.parseInt(txtCantidad.getText());
            String fechaVenc = datePickerVencimiento.getValue().toString();
            Empaque empaque = cmbEmpaque.getValue();
            Proveedor proveedor = cmbProveedor.getValue();

            // Crear el tipo de alimento según el RadioButton seleccionado
            if (rbLiquido.isSelected()) {
                int volumen = Integer.parseInt(lblAtributo1.getText());
                String temperatura = lblAtributo2.getText();
                nuevoAlimento = new Liquido(codigo, nombre, descripcion, cantidad, 
                                           fechaVenc, empaque, proveedor, volumen, temperatura);
                
            } else if (rbLiofilizado.isSelected()) {
                int pesoGramos = Integer.parseInt(txtPesoGramos.getText());
                int tiempoRehidratacion = Integer.parseInt(lblAtributo1.getText());
                int pesoOriginal = Integer.parseInt(lblAtributo2.getText());
                nuevoAlimento = new Liofilizado(codigo, nombre, descripcion, cantidad,
                                               fechaVenc, empaque, proveedor, pesoGramos,
                                               tiempoRehidratacion, pesoOriginal);
                
            } else if (rbDeshidratado.isSelected()) {
                int pesoGramos = Integer.parseInt(txtPesoGramos.getText());
                double porcentajeHumedad = Double.parseDouble(lblAtributo1.getText());
                String metodoDeshidratacion = lblAtributo2.getText();
                nuevoAlimento = new Deshidratado(codigo, nombre, descripcion, cantidad,
                                                 fechaVenc, empaque, proveedor, pesoGramos,
                                                 porcentajeHumedad, metodoDeshidratacion);
            }

            // Llamar al método create del CRUD
            String resultado = crud.create(nuevoAlimento);
            
            if (resultado.contains("exitosamente")) {
                mostrarMensaje(resultado);
                cargarDatosIniciales(); // Recargar tabla desde el CRUD
                limpiarCampos(null);
            } else {
                mostrarError(resultado);
            }
            
        } catch (NumberFormatException e) {
            mostrarError("Verifique que los números sean válidos");
        } catch (Exception e) {
            mostrarError("Error al crear alimento: " + e.getMessage());
        }
    }

    @FXML
    void actualizarAlimento(ActionEvent event) {
        if (alimentoSeleccionado == null) {
            mostrarError("Primero seleccione un alimento de la tabla");
            return;
        }

        try {
            String codigoOriginal = alimentoSeleccionado.getCodigo();
            
            // Crear nuevo alimento según el tipo
            Alimento alimentoActualizado = null;
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            String descripcion = txtDescripcion.getText();
            int cantidad = Integer.parseInt(txtCantidad.getText());
            String fechaVenc = datePickerVencimiento.getValue().toString();
            Empaque empaque = cmbEmpaque.getValue();
            Proveedor proveedor = cmbProveedor.getValue();

            if (rbLiquido.isSelected()) {
                int volumen = Integer.parseInt(lblAtributo1.getText());
                String temperatura = lblAtributo2.getText();
                alimentoActualizado = new Liquido(codigo, nombre, descripcion, cantidad, 
                                                 fechaVenc, empaque, proveedor, volumen, temperatura);
            } else if (rbLiofilizado.isSelected()) {
                int pesoGramos = Integer.parseInt(txtPesoGramos.getText());
                int tiempoRehidratacion = Integer.parseInt(lblAtributo1.getText());
                int pesoOriginal = Integer.parseInt(lblAtributo2.getText());
                alimentoActualizado = new Liofilizado(codigo, nombre, descripcion, cantidad,
                                                     fechaVenc, empaque, proveedor, pesoGramos,
                                                     tiempoRehidratacion, pesoOriginal);
            } else if (rbDeshidratado.isSelected()) {
                int pesoGramos = Integer.parseInt(txtPesoGramos.getText());
                double porcentajeHumedad = Double.parseDouble(lblAtributo1.getText());
                String metodoDeshidratacion = lblAtributo2.getText();
                alimentoActualizado = new Deshidratado(codigo, nombre, descripcion, cantidad,
                                                      fechaVenc, empaque, proveedor, pesoGramos,
                                                      porcentajeHumedad, metodoDeshidratacion);
            }

            // Llamar al método update del CRUD
            String resultado = crud.update(codigoOriginal, alimentoActualizado);
            
            if (resultado.contains("exitosamente")) {
                mostrarMensaje(resultado);
                cargarDatosIniciales(); // Recargar tabla
                limpiarCampos(null);
                alimentoSeleccionado = null;
            } else {
                mostrarError(resultado);
            }
            
        } catch (Exception e) {
            mostrarError("Error al actualizar: " + e.getMessage());
        }
    }

    @FXML
    void eliminarAlimento(ActionEvent event) {
        if (alimentoSeleccionado == null) {
            mostrarError("Primero seleccione un alimento de la tabla");
            return;
        }

        Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
        confirmacion.setTitle("Confirmar eliminación");
        confirmacion.setHeaderText("¿Está seguro de eliminar este alimento?");
        confirmacion.setContentText(alimentoSeleccionado.getNombre());
        
        if (confirmacion.showAndWait().get() == ButtonType.OK) {
            Alimento eliminado = crud.delete(alimentoSeleccionado.getCodigo());
            
            if (eliminado != null) {
                cargarDatosIniciales(); // Recargar tabla desde el CRUD
                mostrarMensaje("Alimento eliminado exitosamente");
                limpiarCampos(null);
                alimentoSeleccionado = null;
            } else {
                mostrarError("No se pudo eliminar el alimento");
            }
        }
    }

    @FXML
    void guardarArchivo(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Guardar archivo");
        fileChooser.setInitialFileName("alimentos.dat");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Archivos serializados", "*.dat")
        );
        
        File archivo = fileChooser.showSaveDialog(btnGuardar.getScene().getWindow());
        
        if (archivo != null) {
            String path = archivo.getParent();
            String name = archivo.getName();
            String resultado = crud.serializar(crud.getAlimentos(), path, name);
            mostrarMensaje(resultado);
        }
    }

    @FXML
    void cargarArchivo(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Cargar archivo");
        fileChooser.getExtensionFilters().add(
            new FileChooser.ExtensionFilter("Archivos serializados", "*.dat")
        );
        
        File archivo = fileChooser.showOpenDialog(btnCargar.getScene().getWindow());
        
        if (archivo != null) {
            String path = archivo.getParent();
            String name = archivo.getName();
            Alimento[] cargados = crud.deserializar(path, name);
            
            if (cargados != null) {
                crud.setAlimentos(cargados);
                cargarDatosIniciales();
                mostrarMensaje("Archivo cargado exitosamente");
            } else {
                mostrarError("Error al cargar el archivo");
            }
        }
    }

    @FXML
    void limpiarCampos(ActionEvent event) {
        txtCodigo.clear();
        txtNombre.clear();
        txtDescripcion.clear();
        txtCantidad.clear();
        txtPesoGramos.clear();
        lblAtributo1.clear();
        lblAtributo2.clear();
        datePickerVencimiento.setValue(null);
        cmbProveedor.setValue(null);
        cmbEmpaque.setValue(null);
        tipoAlimento.selectToggle(null);
        alimentoSeleccionado = null;
        
        lblMensaje.setVisible(false);
        lblError.setVisible(false);
    }

    @FXML
    void seleccionarAlimento(MouseEvent event) {
        alimentoSeleccionado = tblAlimentos.getSelectionModel().getSelectedItem();
        
        if (alimentoSeleccionado != null) {
            txtCodigo.setText(alimentoSeleccionado.getCodigo());
            txtNombre.setText(alimentoSeleccionado.getNombre());
            txtDescripcion.setText(alimentoSeleccionado.getDescripcion());
            txtCantidad.setText(String.valueOf(alimentoSeleccionado.getCantidadDisponible()));
            
            // Convertir String a LocalDate para el DatePicker
            try {
                datePickerVencimiento.setValue(
                    java.time.LocalDate.parse(alimentoSeleccionado.getFechaVencimiento())
                );
            } catch (Exception e) {
                // Si la fecha no se puede parsear, dejarla vacía
            }
            
            cmbProveedor.setValue(alimentoSeleccionado.getProveedor());
            cmbEmpaque.setValue(alimentoSeleccionado.getEmpaque());
            
            // Determinar tipo y llenar campos específicos
            if (alimentoSeleccionado instanceof Liquido) {
                Liquido liq = (Liquido) alimentoSeleccionado;
                rbLiquido.setSelected(true);
                lblAtributo1.setText(String.valueOf(liq.getVolumenML()));
                lblAtributo2.setText(liq.getTemperatura());
                
            } else if (alimentoSeleccionado instanceof Liofilizado) {
                Liofilizado lio = (Liofilizado) alimentoSeleccionado;
                rbLiofilizado.setSelected(true);
                txtPesoGramos.setText(String.valueOf(lio.getPesoGramos()));
                lblAtributo1.setText(String.valueOf(lio.getTiempoRehidratacion()));
                lblAtributo2.setText(String.valueOf(lio.getPesoOriginal()));
                
            } else if (alimentoSeleccionado instanceof Deshidratado) {
                Deshidratado des = (Deshidratado) alimentoSeleccionado;
                rbDeshidratado.setSelected(true);
                txtPesoGramos.setText(String.valueOf(des.getPesoGramos()));
                lblAtributo1.setText(String.valueOf(des.getPorcentajeHumedad()));
                lblAtributo2.setText(des.getMetodoDeshidratacion());
            }
            
            actualizarCamposSegunTipo();
        }
    }

    /**
     * Valida que los campos obligatorios estén llenos
     */
    private boolean validarCampos() {
        return !txtCodigo.getText().isEmpty() 
            && !txtNombre.getText().isEmpty()
            && !txtCantidad.getText().isEmpty()
            && datePickerVencimiento.getValue() != null
            && tipoAlimento.getSelectedToggle() != null
            && cmbProveedor.getValue() != null
            && cmbEmpaque.getValue() != null;
    }

    /**
     * Muestra mensaje de éxito
     */
    private void mostrarMensaje(String mensaje) {
        lblMensaje.setText(mensaje);
        lblMensaje.setTextFill(Color.GREEN);
        lblMensaje.setVisible(true);
        lblError.setVisible(false);
    }

    /**
     * Muestra mensaje de error
     */
    private void mostrarError(String error) {
        lblError.setText(error);
        lblError.setTextFill(Color.RED);
        lblError.setVisible(true);
        lblMensaje.setVisible(false);
    }
}