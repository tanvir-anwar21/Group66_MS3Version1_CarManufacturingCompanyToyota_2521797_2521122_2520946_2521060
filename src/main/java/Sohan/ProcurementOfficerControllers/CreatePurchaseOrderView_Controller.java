package Sohan.ProcurementOfficerControllers;

import Sohan.ModelClasses.ProcurementOfficer.PurchaseOrder;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class CreatePurchaseOrderView_Controller
{
    @FXML
    private DatePicker deliveryDatePicker;
    @FXML
    private TextField orderRefField;
    @FXML
    private RadioButton normalPriorityRadio;
    @FXML
    private RadioButton urgentPriorityRadio;
    @FXML
    private ComboBox<String> materialCombo;
    @FXML
    private ToggleGroup priorityGroup;
    @FXML
    private ComboBox<String> supplierCombo;
    @FXML
    private TextField quantityField;
    @FXML
    private RadioButton criticalPriorityRadio;
    @FXML
    private CheckBox insuranceCheckBox;
    @FXML
    private CheckBox expressDeliveryCheckBox;
    @FXML
    private Label statusLabel;

    private static final String PURCHASE_ORDERS_DIR = "purchase_orders/";
    private static final String PO_LOG = PURCHASE_ORDERS_DIR + "po_log.txt";

    @FXML
    public void initialize() {
        new File(PURCHASE_ORDERS_DIR).mkdirs();
        setupComboBoxes();
        setDefaultValues();
    }

    private void setupComboBoxes() {
        supplierCombo.setItems(javafx.collections.FXCollections.observableArrayList(
                "ABC Industries", "XYZ Corp", "MNO Supplies",
                "PQR Logistics", "DEF Parts", "GHI Materials"
        ));
        supplierCombo.setValue("ABC Industries");

        materialCombo.setItems(javafx.collections.FXCollections.observableArrayList(
                "Steel Sheets", "Aluminum", "Plastic", "Packaging",
                "Electronics", "Rubber", "Glass", "Paint", "Wood"
        ));
        materialCombo.setValue("Steel Sheets");
    }

    private void setDefaultValues() {
        deliveryDatePicker.setValue(LocalDate.now().plusDays(7));
        priorityGroup.selectToggle(normalPriorityRadio);
        orderRefField.setText("PO-" + LocalDate.now().getYear() + "-" + System.currentTimeMillis() % 10000);
    }

    @FXML
    public void handleClear(ActionEvent actionEvent) {
        supplierCombo.setValue("ABC Industries");
        materialCombo.setValue("Steel Sheets");
        quantityField.clear();
        deliveryDatePicker.setValue(LocalDate.now().plusDays(7));
        insuranceCheckBox.setSelected(false);
        expressDeliveryCheckBox.setSelected(false);
        priorityGroup.selectToggle(normalPriorityRadio);
        orderRefField.setText("PO-" + LocalDate.now().getYear() + "-" + System.currentTimeMillis() % 10000);
        statusLabel.setText("");
    }

    @FXML
    public void handleCreatePurchaseOrder(ActionEvent actionEvent) {
        try {
            String supplier = supplierCombo.getValue();
            String material = materialCombo.getValue();
            String quantityStr = quantityField.getText().trim();
            LocalDate deliveryDate = deliveryDatePicker.getValue();

            if (supplier == null || material == null || quantityStr.isEmpty() || deliveryDate == null) {
                showAlert("Validation Error", "Please fill in all required fields.", Alert.AlertType.ERROR);
                return;
            }

            int quantity;
            try {
                quantity = Integer.parseInt(quantityStr);
                if (quantity <= 0) {
                    showAlert("Validation Error", "Quantity must be greater than 0.", Alert.AlertType.ERROR);
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Validation Error", "Invalid quantity format. Please enter a number.", Alert.AlertType.ERROR);
                return;
            }

            if (deliveryDate.isBefore(LocalDate.now())) {
                showAlert("Validation Error", "Delivery date cannot be in the past.", Alert.AlertType.ERROR);
                return;
            }

            RadioButton selectedPriority = (RadioButton) priorityGroup.getSelectedToggle();
            String priority = selectedPriority != null ? selectedPriority.getText().toLowerCase() : "normal";

            String poId = "PO-" + LocalDate.now().getYear() + "-" + String.format("%04d", System.currentTimeMillis() % 10000);

            PurchaseOrder po = new PurchaseOrder(poId, "SUP-001", supplier, material, quantity, 0.0);
            po.setExpectedDeliveryDate(deliveryDate);
            po.setPriority(priority);
            po.setInsured(insuranceCheckBox.isSelected());
            po.setTracked(expressDeliveryCheckBox.isSelected());
            po.setOrderReference(orderRefField.getText());
            po.setCreatedBy("Procurement Officer");
            po.setStatus("Pending");

            String filename = PURCHASE_ORDERS_DIR + "po_" + poId + ".ser";
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                oos.writeObject(po);
            }

            appendToPOLog(po);

            statusLabel.setText("✓ Purchase Order created successfully! ID: " + poId);
            statusLabel.setStyle("-fx-text-fill: green;");

            showAlert("Success", "Purchase Order created successfully.\nPO ID: " + poId,
                    Alert.AlertType.INFORMATION);
            handleClear(null);

        } catch (Exception e) {
            statusLabel.setText("✗ Error: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
            showAlert("Error", "Failed to create purchase order: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void appendToPOLog(PurchaseOrder po) {
        try (FileWriter fw = new FileWriter(PO_LOG, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(po.getOrderID() + "," +
                    po.getSupplierName() + "," +
                    po.getMaterialName() + "," +
                    po.getQuantity() + "," +
                    po.getStatus() + "," +
                    po.getOrderDate());
        } catch (IOException e) {
            System.err.println("Failed to append to PO log: " + e.getMessage());
        }
    }

    @FXML
    public void handleBackToDashboard(ActionEvent actionEvent) {
        Stage stage = (Stage) supplierCombo.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}