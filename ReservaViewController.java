package co.edu.uniquindio.poo.viewController;

import co.edu.uniquindio.poo.model.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class ReservaViewController {
    @FXML
    private TableView<Reserva> tblReservas;
    @FXML
    private TableColumn<Reserva, String> colVehiculo;
    @FXML
    private TableColumn<Reserva, String> colFecha;
    @FXML
    private TableColumn<Reserva, String> colDias;
    @FXML
    private ComboBox<Vehiculo> comboVehiculo;
    @FXML
    private DatePicker dpFechaInicio;
    @FXML
    private TextField txtDias;

    private ObservableList<Reserva> listaReservas = FXCollections.observableArrayList();

    // Inicialización de vehículos
    private Auto auto;
    private Moto moto;
    private Camioneta camioneta;

    @FXML
    public void initialize() {
        // Inicializa la tabla
        colVehiculo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getVehiculo().getModelo()));
        colFecha.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFechaInicio().toString()));
        colDias.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getDias())));

        // Crear instancias de vehículos
        auto = new Auto("Toyota", "Corolla", "ABC123",4);
        moto = new Moto("Yamaha", "YZF-R3", "XYZ456",true);
        camioneta = new Camioneta("Ford", "F-150", "LMN789",2.5);

        // Llenar ComboBox con vehículos disponibles
        comboVehiculo.getItems().addAll(auto, moto, camioneta);
    }

    @FXML
    private void onAgregarReserva() {
        Vehiculo vehiculoSeleccionado = comboVehiculo.getValue();
        LocalDate fechaInicio = dpFechaInicio.getValue();
        int dias = Integer.parseInt(txtDias.getText());

        // Asegúrate de que la clase Reserva acepte LocalDate
        Reserva nuevaReserva = new Reserva(vehiculoSeleccionado, fechaInicio, dias);
        listaReservas.add(nuevaReserva);
        tblReservas.setItems(listaReservas);
        limpiarCampos();
    }

    @FXML
    private void onEliminarReserva() {
        Reserva reservaSeleccionada = tblReservas.getSelectionModel().getSelectedItem();
        if (reservaSeleccionada != null) {
            listaReservas.remove(reservaSeleccionada);
        }
    }

    private void limpiarCampos() {
        comboVehiculo.setValue(null);
        dpFechaInicio.setValue(null);
        txtDias.clear();
    }
}
