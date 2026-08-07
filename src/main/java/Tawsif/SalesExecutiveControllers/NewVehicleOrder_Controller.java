package Tawsif.SalesExecutiveControllers;

import Tawsif.Models.VehicleOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

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

    @FXML
    public void handleCalculatePrice(ActionEvent event) {

        if (vehicleModelComboBox.getValue() == null) {
            statusLabel.setText("Select a vehicle model.");
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

        double total = unitPrice * quantity;

        totalPriceLabel.setText(String.format("%.2f", total));

        statusLabel.setText("Price calculated successfully.");
    }

    @FXML
    public void handleConfirmOrder(ActionEvent event) {

        if (customerIdField.getText().isEmpty()
                || customerNameField.getText().isEmpty()
                || vehicleModelComboBox.getValue() == null
                || colorComboBox.getValue() == null
                || transmissionComboBox.getValue() == null
                || deliveryDatePicker.getValue() == null) {

            statusLabel.setText("Please complete all required fields.");
            return;
        }

        double totalPrice;

        try {
            totalPrice = Double.parseDouble(totalPriceLabel.getText());
        } catch (Exception e) {
            statusLabel.setText("Calculate price first.");
            return;
        }

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

        statusLabel.setText("Vehicle order confirmed successfully.");
    }

    @FXML
    public void handleClear(ActionEvent event) {

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

        statusLabel.setText("Ready");
    }

}