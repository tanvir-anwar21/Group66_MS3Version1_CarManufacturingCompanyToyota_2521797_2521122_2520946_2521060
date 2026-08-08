package Tawsif.SalesExecutiveControllers;

import Tawsif.Models.VehicleOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class TrackVehicleOrder_Controller {


    @FXML
    private DatePicker deliveryDatePicker;

    @FXML
    private Button searchButton;

    @FXML
    private Button refreshButton;

    @FXML
    private Button closeButton;


    @FXML
    private TableColumn<VehicleOrder, String> remarksColumn;

    @FXML
    private TableColumn<VehicleOrder, LocalDate> dateColumn;

    @FXML
    private TableColumn<VehicleOrder, String> eventColumn;


    @FXML
    private TableView<VehicleOrder> trackingTableView;


    @FXML
    private ProgressBar orderProgressBar;


    @FXML
    private TextField customerField;

    @FXML
    private TextField vehicleField;

    @FXML
    private TextField statusField;

    @FXML
    private TextField orderIdField;


    @FXML
    private Label statusLabel;


    private ObservableList<VehicleOrder> vehicleOrderList;


    @FXML
    public void initialize() {


        vehicleOrderList = FXCollections.observableArrayList();

        trackingTableView.setItems(vehicleOrderList);


        dateColumn.setCellValueFactory(
                data -> new javafx.beans.property.SimpleObjectProperty<>(
                        data.getValue().getDeliveryDate()
                )
        );


        eventColumn.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(
                        data.getValue().getStatus()
                )
        );


        remarksColumn.setCellValueFactory(
                data -> new javafx.beans.property.SimpleStringProperty(
                        "Vehicle delivery tracking"
                )
        );


        orderProgressBar.setProgress(0);

    }



    @FXML
    private void searchOrder() {


        String orderId = orderIdField.getText();


        if(orderId.isEmpty()) {

            statusLabel.setText("Enter Order ID");
            return;

        }


        // Temporary data
        VehicleOrder order = new VehicleOrder(
                orderId,
                "C001",
                "Rahim Motors",
                "Toyota Corolla",
                "Black",
                "Automatic",
                1,
                3500000,
                LocalDate.now(),
                LocalDate.now().plusDays(7),
                "Manufacturing Completed"
        );


        customerField.setText(order.getCustomerName());

        vehicleField.setText(order.getVehicleModel());

        statusField.setText(order.getStatus());


        orderProgressBar.setProgress(0.8);


        vehicleOrderList.clear();

        vehicleOrderList.add(order);


        statusLabel.setText("Order Found");


    }



    @FXML
    private void refreshTracking() {

        trackingTableView.refresh();

        statusLabel.setText("Tracking Updated");

    }



    @FXML
    private void closeWindow() {

        Stage stage =
                (Stage) closeButton.getScene().getWindow();

        stage.close();

    }


    @FXML
    public void handleBackToSalesExecutiveDashboard(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/SalesExecutive/SalesExecutiveDashboardView.fxml"
                    )
            );

            if (loader.getLocation() == null) {
                throw new IOException("SalesExecutiveDashboardView.fxml not found!");
            }

            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(scene);
            stage.setTitle("Sales Executive Dashboard");
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

    private void showAlert(String error, String s, Alert.AlertType alertType) {
        
    }
}