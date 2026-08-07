package Tawsif.SalesExecutiveControllers;

import Tawsif.Models.Vehicle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class VehicleInventory_Controller {


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
    private ComboBox<String> modelComboBox;

    @FXML
    private ComboBox<String> colorComboBox;

    @FXML
    private ComboBox<String> statusComboBox;

    @FXML
    private ComboBox<String> warehouseComboBox;


    @FXML
    private Button searchButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button refreshButton;


    @FXML
    private Label statusLabel;

    @FXML
    private Label availableVehiclesLabel;



    private final ObservableList<Vehicle> vehicleList =
            FXCollections.observableArrayList();



    @FXML
    public void initialize() {


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


        inventoryTableView.setItems(vehicleList);



        modelComboBox.getItems().addAll(
                "Toyota Corolla",
                "Toyota Axio",
                "Toyota Premio",
                "Toyota Camry"
        );


        colorComboBox.getItems().addAll(
                "Black",
                "White",
                "Silver",
                "Blue"
        );


        statusComboBox.getItems().addAll(
                "Available",
                "Out of Stock"
        );


        warehouseComboBox.getItems().addAll(
                "Main Warehouse",
                "Dhaka Warehouse"
        );


        statusLabel.setText("Ready");

        availableVehiclesLabel.setText(
                "Available Vehicles: 0"
        );

    }



    @FXML
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



    @FXML
    public void handleClear(ActionEvent event) {


        modelComboBox.setValue(null);

        colorComboBox.setValue(null);

        statusComboBox.setValue(null);

        warehouseComboBox.setValue(null);


        statusLabel.setText(
                "Filters cleared."
        );

    }



    @FXML
    public void handleRefresh(ActionEvent event) {


        inventoryTableView.refresh();


        statusLabel.setText(
                "Inventory refreshed."
        );

    }

}