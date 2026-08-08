package Tawsif.SalesExecutiveControllers;

import Tawsif.Models.Vehicle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class VehicleInventory_Controller {

    // =========================
    // TABLE VIEW
    // =========================

    @FXML
    private TableView<Vehicle> inventoryTableView;

    @FXML
    private TableColumn<Vehicle, String> vehicleIdColumn;

    @FXML
    private TableColumn<Vehicle, String> modelColumn;

    @FXML
    private TableColumn<Vehicle, String> colorColumn;

    @FXML
    private TableColumn<Vehicle, String> transmissionColumn;

    @FXML
    private TableColumn<Vehicle, Double> priceColumn;

    @FXML
    private TableColumn<Vehicle, Integer> quantityColumn;

    @FXML
    private TableColumn<Vehicle, String> warehouseColumn;

    @FXML
    private TableColumn<Vehicle, String> engineColumn;

    @FXML
    private TableColumn<Vehicle, String> statusColumn;


    // =========================
    // COMBO BOXES
    // =========================

    @FXML
    private ComboBox<String> modelComboBox;

    @FXML
    private ComboBox<String> colorComboBox;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private ComboBox<String> warehouseComboBox;


    // =========================
    // BUTTONS
    // =========================

    @FXML
    private Button searchButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button refreshButton;


    // =========================
    // LABELS
    // =========================

    @FXML
    private Label statusLabel;

    @FXML
    private Label availableVehiclesLabel;


    // =========================
    // VEHICLE LIST
    // =========================

    private final ObservableList<Vehicle> vehicleList =
            FXCollections.observableArrayList();


    // =========================
    // INITIALIZE
    // =========================

    @FXML
    public void initialize() {
        priceColumn.setCellValueFactory(
                new PropertyValueFactory<>("price")
        );

        vehicleIdColumn.setCellValueFactory(
                new PropertyValueFactory<>("vehicleId")
        );

        modelColumn.setCellValueFactory(
                new PropertyValueFactory<>("model")
        );

        colorColumn.setCellValueFactory(
                new PropertyValueFactory<>("color")
        );

        transmissionColumn.setCellValueFactory(
                new PropertyValueFactory<>("transmission")
        );

        priceColumn.setCellValueFactory(
                new PropertyValueFactory<>("price")
        );

        quantityColumn.setCellValueFactory(
                new PropertyValueFactory<>("stock")
        );

        warehouseColumn.setCellValueFactory(
                new PropertyValueFactory<>("warehouse")
        );

        engineColumn.setCellValueFactory(
                new PropertyValueFactory<>("engine")
        );

        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );


        inventoryTableView.setItems(vehicleList);


        // =========================
        // MODEL
        // =========================

        modelComboBox.getItems().addAll(
                "Toyota Corolla",
                "Toyota Axio",
                "Toyota Premio",
                "Toyota Camry"
        );


        // =========================
        // COLOR
        // =========================

        colorComboBox.getItems().addAll(
                "Black",
                "White",
                "Silver",
                "Blue"
        );


        // =========================
        // STATUS
        // =========================

        statusComboBox.getItems().addAll(
                "Available",
                "Out of Stock"
        );


        // =========================
        // WAREHOUSE
        // =========================

        warehouseComboBox.getItems().addAll(
                "Main Warehouse",
                "Dhaka Warehouse"
        );


        statusLabel.setText("Ready");

        availableVehiclesLabel.setText(
                "Available Vehicles: 0"
        );
    }


    // =========================
    // SEARCH
    // =========================

    @Deprecated
    public void handleSearch(ActionEvent event) {

        vehicleList.clear();

        Vehicle vehicle = new Vehicle(
                "V001",
                "Toyota Corolla",
                "Black",
                "Automatic",
                3500000,
                5
        );

        vehicleList.add(vehicle);

        availableVehiclesLabel.setText(
                "Available Vehicles: "
                        + vehicleList.size()
        );

        statusLabel.setText(
                "Vehicle inventory loaded."
        );
    }


    // =========================
    // CLEAR
    // =========================

    @Deprecated
    public void handleClear(ActionEvent event) {

        modelComboBox.setValue(null);

        colorComboBox.setValue(null);

        statusComboBox.setValue(null);

        warehouseComboBox.setValue(null);

        vehicleList.clear();

        availableVehiclesLabel.setText(
                "Available Vehicles: 0"
        );

        statusLabel.setText(
                "Filters cleared."
        );
    }


    // =========================
    // REFRESH
    // =========================

    @Deprecated
    public void handleRefresh(ActionEvent event) {

        inventoryTableView.refresh();

        statusLabel.setText(
                "Inventory refreshed."
        );
    }


    // =========================
    // BACK TO SALES EXECUTIVE
    // =========================

    @FXML
    public void handleBackToSalesExecutiveDashboard(
            ActionEvent event
    ) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/SalesExecutive/SalesExecutiveDashboardView.fxml"
                    )
            );

            if (loader.getLocation() == null) {

                throw new IOException(
                        "SalesExecutiveDashboardView.fxml not found!"
                );
            }

            Scene scene = new Scene(
                    loader.load()
            );

            Stage stage = (Stage)
                    ((Node) event.getSource())
                            .getScene()
                            .getWindow();

            stage.setScene(scene);

            stage.setTitle(
                    "Sales Executive Dashboard"
            );

            stage.show();

        } catch (IOException e) {

            e.printStackTrace();

            showAlert(
                    "Error",
                    "Could not open Sales Executive Dashboard.\n\n"
                            + e.getMessage(),
                    Alert.AlertType.ERROR
            );
        }
    }


    // =========================
    // ALERT
    // =========================

    private void showAlert(
            String title,
            String message,
            Alert.AlertType alertType
    ) {

        Alert alert = new Alert(alertType);

        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }
}