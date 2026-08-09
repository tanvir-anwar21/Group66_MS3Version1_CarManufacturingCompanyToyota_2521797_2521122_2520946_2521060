package Sohan.ProcurementOfficerControllers;

import Sohan.ModelClasses.ProcurementOfficer.PurchaseOrder;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class OrderRawMaterialsView_Controller
{
    @FXML
    private RadioButton standardRadio;
    @FXML
    private ComboBox<String> unitCombo;
    @FXML
    private CheckBox trackingCheckBox;
    @FXML
    private ToggleGroup priorityGroup;
    @FXML
    private TextField costField;
    @FXML
    private Label statusLabel;
    @FXML
    private RadioButton urgentRadio;
    @FXML
    private RadioButton emergencyRadio;
    @FXML
    private DatePicker requiredDatePicker;
    @FXML
    private ComboBox<String> materialCombo;
    @FXML
    private CheckBox insuredCheckBox;
    @FXML
    private ComboBox<String> supplierCombo;
    @FXML
    private TextField quantityField;
    @FXML
    private CheckBox qualityCertCheckBox;

    private static final String RAW_MATERIALS_DIR = "raw_materials/";
    private static final String RM_LOG = RAW_MATERIALS_DIR + "rm_order_log.txt";

    @FXML
    public void initialize() {
        new File(RAW_MATERIALS_DIR).mkdirs();
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
                "Steel Sheets", "Aluminum", "Plastic", "Rubber",
                "Glass", "Paint", "Wood", "Chemicals", "Electronics"
        ));
        materialCombo.setValue("Steel Sheets");

        unitCombo.setItems(javafx.collections.FXCollections.observableArrayList(
                "kg", "tons", "pieces", "liters", "meters", "boxes"
        ));
        unitCombo.setValue("kg");
    }

    private void setDefaultValues() {
        requiredDatePicker.setValue(LocalDate.now().plusDays(14));
        priorityGroup.selectToggle(standardRadio);
    }

    @FXML
    public void handleClear(ActionEvent actionEvent) {
        supplierCombo.setValue("ABC Industries");
        materialCombo.setValue("Steel Sheets");
        quantityField.clear();
        unitCombo.setValue("kg");
        costField.clear();
        requiredDatePicker.setValue(LocalDate.now().plusDays(14));
        priorityGroup.selectToggle(standardRadio);
        insuredCheckBox.setSelected(false);
        trackingCheckBox.setSelected(false);
        qualityCertCheckBox.setSelected(false);
        statusLabel.setText("");
    }

    @FXML
    public void handlePlaceOrder(ActionEvent actionEvent) {
        try {
            String supplier = supplierCombo.getValue();
            String material = materialCombo.getValue();
            String quantityStr = quantityField.getText().trim();
            String unit = unitCombo.getValue();
            String costStr = costField.getText().trim();
            LocalDate requiredDate = requiredDatePicker.getValue();

            if (supplier == null || material == null || quantityStr.isEmpty() ||
                    unit == null || requiredDate == null) {
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
                showAlert("Validation Error", "Invalid quantity format.", Alert.AlertType.ERROR);
                return;
            }

            double cost = 0;
            if (!costStr.isEmpty()) {
                try {
                    cost = Double.parseDouble(costStr);
                } catch (NumberFormatException e) {
                    showAlert("Validation Error", "Invalid cost format.", Alert.AlertType.ERROR);
                    return;
                }
            }

            if (requiredDate.isBefore(LocalDate.now())) {
                showAlert("Validation Error", "Required date cannot be in the past.", Alert.AlertType.ERROR);
                return;
            }

            RadioButton selectedPriority = (RadioButton) priorityGroup.getSelectedToggle();
            String priority = selectedPriority != null ? selectedPriority.getText().toLowerCase() : "standard";

            String orderId = "RM-" + System.currentTimeMillis();

            PurchaseOrder order = new PurchaseOrder(orderId, "SUP-001", supplier, material, quantity, cost);
            order.setExpectedDeliveryDate(requiredDate);
            order.setPriority(priority);
            order.setInsured(insuredCheckBox.isSelected());
            order.setTracked(trackingCheckBox.isSelected());
            order.setQualityCertificateRequired(qualityCertCheckBox.isSelected());
            order.setStatus("Pending");
            order.setUnit(unit);
            order.setCreatedBy("Procurement Officer");

            String filename = RAW_MATERIALS_DIR + "rm_order_" + orderId + ".ser";
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                oos.writeObject(order);
            }

            appendToRMOrderLog(order);

            statusLabel.setText("✓ Raw material order placed successfully! ID: " + orderId);
            statusLabel.setStyle("-fx-text-fill: green;");

            showAlert("Success", "Raw material order placed successfully.\nOrder ID: " + orderId,
                    Alert.AlertType.INFORMATION);
            handleClear(null);

        } catch (Exception e) {
            statusLabel.setText("✗ Error: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
            showAlert("Error", "Failed to place order: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void appendToRMOrderLog(PurchaseOrder order) {
        try (FileWriter fw = new FileWriter(RM_LOG, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(order.getOrderID() + "," +
                    order.getSupplierName() + "," +
                    order.getMaterialName() + "," +
                    order.getQuantity() + "," +
                    order.getPriority() + "," +
                    order.getOrderDate());
        } catch (IOException e) {
            System.err.println("Failed to append to RM order log: " + e.getMessage());
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