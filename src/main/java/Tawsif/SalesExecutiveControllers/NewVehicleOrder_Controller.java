package Tawsif.SalesExecutiveControllers;

import Tawsif.Models.VehicleOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class NewVehicleOrder_Controller {

    @FXML
    private DatePicker deliveryDatePicker;

    @FXML
    private TableColumn<VehicleOrder, String> orderIdColumn;

    @FXML
    private TableColumn<VehicleOrder, LocalDate> deliveryColumn;

    @FXML
    private TableColumn<VehicleOrder, String> customerColumn;

    @FXML
    private Button confirmOrderButton;

    @FXML
    private TableView<VehicleOrder> orderTableView;

    @FXML
    private Label totalPriceLabel;

    @FXML
    private TextField emailField;

    @FXML
    private TableColumn<VehicleOrder, String> colorColumn;

    @FXML
    private ComboBox<String> vehicleModelComboBox;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField phoneField;

    @FXML
    private Spinner<Integer> quantitySpinner;

    @FXML
    private TableColumn<VehicleOrder, String> vehicleColumn;

    @FXML
    private TableColumn<VehicleOrder, Integer> quantityColumn;

    @FXML
    private Button clearButton;

    @FXML
    private TextField customerNameField;

    @FXML
    private ComboBox<String> transmissionComboBox;

    @FXML
    private ComboBox<String> colorComboBox;

    @FXML
    private TextField customerIdField;

    @FXML
    private Button calculatePriceButton;

    @FXML
    private TextField addressField;

    @FXML
    private TableColumn<VehicleOrder, Double> priceColumn;

    private final ObservableList<VehicleOrder> orderList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        vehicleModelComboBox.getItems().addAll(
                "Corolla",
                "Camry",
                "Yaris",
                "Hilux",
                "Land Cruiser",
                "Prius"
        );

        colorComboBox.getItems().addAll(
                "White",
                "Black",
                "Silver",
                "Gray",
                "Blue",
                "Red"
        );

        transmissionComboBox.getItems().addAll(
                "Automatic",
                "Manual"
        );

        quantitySpinner.setValueFactory(
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 10, 1)
        );

        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        vehicleColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleModel"));
        colorColumn.setCellValueFactory(new PropertyValueFactory<>("color"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));
        deliveryColumn.setCellValueFactory(new PropertyValueFactory<>("deliveryDate"));

        orderTableView.setItems(orderList);

        totalPriceLabel.setText("0.00");
        statusLabel.setText("Ready");
    }

    private void showAlert(String title, String message, Alert.AlertType type) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {

        customerIdField.clear();
        customerNameField.clear();
        phoneField.clear();
        emailField.clear();
        addressField.clear();

        vehicleModelComboBox.getSelectionModel().clearSelection();
        colorComboBox.getSelectionModel().clearSelection();
        transmissionComboBox.getSelectionModel().clearSelection();

        quantitySpinner.getValueFactory().setValue(1);

        deliveryDatePicker.setValue(null);

        totalPriceLabel.setText("0.00");

        orderTableView.getSelectionModel().clearSelection();
    }

    @FXML
    public void handleCalculatePrice(ActionEvent event) {

        if (vehicleModelComboBox.getValue() == null) {

            showAlert("Vehicle Model",
                    "Please select a vehicle model.",
                    Alert.AlertType.WARNING);
            return;
        }

        double unitPrice = 0;

        switch (vehicleModelComboBox.getValue()) {

            case "Corolla":
                unitPrice = 3200000;
                break;

            case "Camry":
                unitPrice = 4500000;
                break;

            case "Yaris":
                unitPrice = 2500000;
                break;

            case "Hilux":
                unitPrice = 5500000;
                break;

            case "Land Cruiser":
                unitPrice = 12000000;
                break;

            case "Prius":
                unitPrice = 4200000;
                break;
        }

        int quantity = quantitySpinner.getValue();

        double totalPrice = unitPrice * quantity;

        totalPriceLabel.setText(String.format("%.2f", totalPrice));

        statusLabel.setText("Price calculated successfully.");
    }

    @FXML
    public void handleConfirmOrder(ActionEvent event) {

        if (customerIdField.getText().isEmpty()
                || customerNameField.getText().isEmpty()
                || phoneField.getText().isEmpty()
                || emailField.getText().isEmpty()
                || addressField.getText().isEmpty()
                || vehicleModelComboBox.getValue() == null
                || colorComboBox.getValue() == null
                || transmissionComboBox.getValue() == null
                || deliveryDatePicker.getValue() == null) {

            showAlert("Validation",
                    "Please complete all required fields.",
                    Alert.AlertType.WARNING);
            return;
        }

        if (totalPriceLabel.getText().equals("0.00")) {

            showAlert("Price",
                    "Please calculate the total price first.",
                    Alert.AlertType.WARNING);
            return;
        }

        double totalPrice = Double.parseDouble(totalPriceLabel.getText());

        VehicleOrder order = new VehicleOrder(

                "ORD" + (orderList.size() + 1),
                customerIdField.getText(),
                customerNameField.getText(),
                vehicleModelComboBox.getValue(),
                colorComboBox.getValue(),
                transmissionComboBox.getValue(),
                quantitySpinner.getValue(),
                totalPrice,
                LocalDate.now(),
                deliveryDatePicker.getValue(),
                "Pending"

        );

        orderList.add(order);

        orderTableView.refresh();

        statusLabel.setText("Vehicle order confirmed successfully.");

        showAlert(
                "Success",
                "Vehicle order has been placed successfully.",
                Alert.AlertType.INFORMATION
        );

        clearFields();
    }

    @Deprecated
    public void handleSearch(ActionEvent event) {

        String customerId = customerIdField.getText().trim();

        if (customerId.isEmpty()) {

            showAlert("Search",
                    "Please enter Customer ID.",
                    Alert.AlertType.WARNING);
            return;
        }

        for (VehicleOrder order : orderList) {

            if (order.getCustomerId().equalsIgnoreCase(customerId)) {

                customerNameField.setText(order.getCustomerName());
                vehicleModelComboBox.setValue(order.getVehicleModel());
                colorComboBox.setValue(order.getColor());
                transmissionComboBox.setValue(order.getTransmission());

                quantitySpinner.getValueFactory().setValue(order.getQuantity());

                deliveryDatePicker.setValue(order.getDeliveryDate());

                totalPriceLabel.setText(
                        String.format("%.2f", order.getTotalPrice())
                );

                orderTableView.getSelectionModel().select(order);

                statusLabel.setText("Order found.");

                return;
            }
        }

        showAlert("Search",
                "No order found for this Customer ID.",
                Alert.AlertType.INFORMATION);
    }

    @Deprecated
    public void handleDelete(ActionEvent event) {

        VehicleOrder selectedOrder =
                orderTableView.getSelectionModel().getSelectedItem();

        if (selectedOrder == null) {

            showAlert("Delete",
                    "Please select an order from the table.",
                    Alert.AlertType.WARNING);
            return;
        }

        orderList.remove(selectedOrder);

        orderTableView.refresh();

        clearFields();

        statusLabel.setText("Order deleted successfully.");

        showAlert("Success",
                "Vehicle order deleted successfully.",
                Alert.AlertType.INFORMATION);
    }

    @Deprecated
    public void handleRefresh(ActionEvent event) {

        orderTableView.refresh();

        statusLabel.setText("Table refreshed.");
    }

    @FXML
    public void handleClear(ActionEvent event) {

        clearFields();

        statusLabel.setText("Ready");
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
}