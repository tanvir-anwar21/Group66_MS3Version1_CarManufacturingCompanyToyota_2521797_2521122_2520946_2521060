package Sohan.ProcurementOfficerControllers;

import Sohan.ModelClasses.ProcurementOfficer.DeliveryApproval;
import Sohan.ModelClasses.ProcurementOfficer.PurchaseOrder;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class ApproveDeliveryView_Controller
{
    @FXML
    private Label supplierDisplay;
    @FXML
    private TextField acceptedQuantityField;
    @FXML
    private TextArea notesArea;
    @FXML
    private ToggleGroup qualityGroup;
    @FXML
    private TextField poIdField;
    @FXML
    private Label statusLabel;
    @FXML
    private CheckBox updateInventoryCheckBox;
    @FXML
    private RadioButton partialRadio;
    @FXML
    private Label poIdDisplay;
    @FXML
    private TextField deliveryIdField;
    @FXML
    private Label materialDisplay;
    @FXML
    private Label quantityDisplay;
    @FXML
    private Label deliveryDateDisplay;
    @FXML
    private RadioButton passRadio;
    @FXML
    private Label deliveryIdDisplay;
    @FXML
    private RadioButton failRadio;

    private static final String DELIVERIES_DIR = "deliveries/";
    private static final String DELIVERY_LOG = DELIVERIES_DIR + "delivery_log.txt";
    private DeliveryApproval currentDelivery;

    @FXML
    public void initialize() {
        new File(DELIVERIES_DIR).mkdirs();
        setupToggleGroups();
        clearDisplay();
    }

    private void setupToggleGroups() {
        qualityGroup.selectToggle(passRadio);
    }

    private void clearDisplay() {
        deliveryIdField.clear();
        poIdField.clear();
        acceptedQuantityField.clear();
        notesArea.clear();
        supplierDisplay.setText("");
        materialDisplay.setText("");
        quantityDisplay.setText("");
        deliveryDateDisplay.setText("");
        poIdDisplay.setText("");
        deliveryIdDisplay.setText("");
        updateInventoryCheckBox.setSelected(true);
        statusLabel.setText("");
        currentDelivery = null;
    }

    @FXML
    public void handleSearch(ActionEvent actionEvent) {
        String deliveryId = deliveryIdField.getText().trim();
        if (deliveryId.isEmpty()) {
            showAlert("Validation Error", "Please enter Delivery ID.", Alert.AlertType.ERROR);
            return;
        }

        if (deliveryId.matches("^DEL\\d{6}$")) {
            currentDelivery = new DeliveryApproval(
                    "APP-" + System.currentTimeMillis(),
                    deliveryId,
                    "PO-2026-001",
                    "ABC Industries",
                    "Steel Sheets",
                    500.0
            );
            currentDelivery.setQuantityOrdered(500.0);
            currentDelivery.setDeliveryDate(LocalDate.now());
            currentDelivery.setQualityCheckResult("Passed");
            currentDelivery.setDeliveryStatus("Pending");

            deliveryIdDisplay.setText(deliveryId);
            poIdDisplay.setText(currentDelivery.getPurchaseOrderID());
            supplierDisplay.setText(currentDelivery.getSupplierName());
            materialDisplay.setText(currentDelivery.getMaterialName());
            quantityDisplay.setText(String.valueOf(currentDelivery.getQuantityDelivered()));
            deliveryDateDisplay.setText(currentDelivery.getDeliveryDate().toString());

            statusLabel.setText("✓ Delivery found. Ready for approval.");
            statusLabel.setStyle("-fx-text-fill: green;");
        } else {
            showAlert("Error", "Delivery not found. Please check the ID.", Alert.AlertType.ERROR);
            statusLabel.setText("✗ Delivery not found.");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void handleClear(ActionEvent actionEvent) {
        clearDisplay();
    }

    @FXML
    public void handleApproveDelivery(ActionEvent actionEvent) {
        try {
            if (currentDelivery == null) {
                showAlert("Validation Error", "Please search for a delivery first.", Alert.AlertType.ERROR);
                return;
            }

            String acceptedQty = acceptedQuantityField.getText().trim();
            RadioButton selectedQuality = (RadioButton) qualityGroup.getSelectedToggle();
            String quality = selectedQuality != null ? selectedQuality.getText() : "Pass";
            String notes = notesArea.getText().trim();

            if (acceptedQty.isEmpty()) {
                showAlert("Validation Error", "Please enter accepted quantity.", Alert.AlertType.ERROR);
                return;
            }

            double accepted = Double.parseDouble(acceptedQty);
            double delivered = currentDelivery.getQuantityDelivered();

            if (accepted > delivered) {
                showAlert("Validation Error", "Accepted quantity cannot exceed delivered quantity.", Alert.AlertType.ERROR);
                return;
            }

            currentDelivery.setQuantityAccepted(accepted);
            currentDelivery.setQuantityRejected(delivered - accepted);
            currentDelivery.setQualityCheckResult(quality);
            currentDelivery.setNotes(notes);
            currentDelivery.setApprovalStatus(accepted == delivered ? "Approved" : "Partial Approved");
            currentDelivery.setApprovedBy("Procurement Officer");
            currentDelivery.setApprovalDate(LocalDate.now());
            currentDelivery.setInventoryUpdated(updateInventoryCheckBox.isSelected());

            String filename = DELIVERIES_DIR + "approval_" + currentDelivery.getApprovalID() + ".ser";
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                oos.writeObject(currentDelivery);
            }

            appendToDeliveryLog(currentDelivery);

            statusLabel.setText("✓ Delivery approved successfully! ID: " + currentDelivery.getApprovalID());
            statusLabel.setStyle("-fx-text-fill: green;");

            showAlert("Success", "Delivery approved successfully.\nApproval ID: " + currentDelivery.getApprovalID(),
                    Alert.AlertType.INFORMATION);
            clearDisplay();

        } catch (NumberFormatException e) {
            showAlert("Validation Error", "Invalid quantity format. Please enter a number.", Alert.AlertType.ERROR);
        } catch (Exception e) {
            statusLabel.setText("✗ Error: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
            showAlert("Error", "Failed to approve delivery: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void appendToDeliveryLog(DeliveryApproval approval) {
        try (FileWriter fw = new FileWriter(DELIVERY_LOG, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(approval.getApprovalID() + "," +
                    approval.getDeliveryID() + "," +
                    approval.getPurchaseOrderID() + "," +
                    approval.getApprovalStatus() + "," +
                    approval.getApprovalDate());
        } catch (IOException e) {
            System.err.println("Failed to append to delivery log: " + e.getMessage());
        }
    }

    @FXML
    public void handleRejectDelivery(ActionEvent actionEvent) {
        try {
            if (currentDelivery == null) {
                showAlert("Validation Error", "Please search for a delivery first.", Alert.AlertType.ERROR);
                return;
            }

            String notes = notesArea.getText().trim();
            if (notes.isEmpty()) {
                showAlert("Validation Error", "Please provide rejection reason in notes.", Alert.AlertType.ERROR);
                return;
            }

            currentDelivery.setApprovalStatus("Rejected");
            currentDelivery.setRejectionReason(notes);
            currentDelivery.setApprovedBy("Procurement Officer");
            currentDelivery.setApprovalDate(LocalDate.now());
            currentDelivery.setQuantityAccepted(0.0);
            currentDelivery.setQuantityRejected(currentDelivery.getQuantityDelivered());

            String filename = DELIVERIES_DIR + "rejection_" + currentDelivery.getApprovalID() + ".ser";
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                oos.writeObject(currentDelivery);
            }

            statusLabel.setText("✓ Delivery rejected. ID: " + currentDelivery.getApprovalID());
            statusLabel.setStyle("-fx-text-fill: orange;");

            showAlert("Success", "Delivery rejected.\nRejection ID: " + currentDelivery.getApprovalID(),
                    Alert.AlertType.WARNING);
            clearDisplay();

        } catch (Exception e) {
            statusLabel.setText("✗ Error: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
            showAlert("Error", "Failed to reject delivery: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleBackToDashboard(ActionEvent actionEvent) {
        Stage stage = (Stage) deliveryIdField.getScene().getWindow();
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