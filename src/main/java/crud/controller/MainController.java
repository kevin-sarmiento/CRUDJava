package crud.controller;

import crud.model.Genero;
import crud.model.Persona;
import crud.service.PersonaService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MainController {
    @FXML private TextField txtNombre;
    @FXML private TextField txtEdad;
    @FXML private ComboBox<Genero> cmbGenero;
    @FXML private ListView<Persona> lstPersonas;

    private final PersonaService service = new PersonaService();

    @FXML
    public void initialize() {
        cmbGenero.setItems(FXCollections.observableArrayList(Genero.values()));
        refrescarLista();
    }

    @FXML
    protected void agregar() {
        try {
            String nombre = txtNombre.getText();
            int edad = Integer.parseInt(txtEdad.getText());
            Genero genero = cmbGenero.getValue();

            if (nombre.isEmpty() || genero == null)
                throw new IllegalArgumentException("Debe completar todos los campos.");

            service.agregar(new Persona(nombre, edad, genero));
            limpiarCampos();
            refrescarLista();
        } catch (NumberFormatException e) {
            mostrarError("La edad debe ser un número.");
        } catch (Exception e) {
            mostrarError(e.getMessage());
        }
    }

    @FXML
    protected void eliminar() {
        Persona seleccionada = lstPersonas.getSelectionModel().getSelectedItem();
        if (seleccionada != null) {
            service.eliminar(seleccionada.getId());
            refrescarLista();
        } else {
            mostrarError("Seleccione una persona para eliminar.");
        }
    }

    private void refrescarLista() {
        lstPersonas.setItems(FXCollections.observableArrayList(service.listar()));
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtEdad.clear();
        cmbGenero.setValue(null);
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR, mensaje, ButtonType.OK);
        alert.showAndWait();
    }
}
