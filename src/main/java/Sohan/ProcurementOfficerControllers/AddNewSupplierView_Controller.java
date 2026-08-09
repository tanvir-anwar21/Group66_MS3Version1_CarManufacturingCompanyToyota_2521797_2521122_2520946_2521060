package Sohan.ProcurementOfficerControllers;

import Sohan.ModelClasses.ProcurementOfficer.Supplier;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class AddNewSupplierView_Controller
{
    @FXML
    private TextField contactPersonField;
    @FXML
    private CheckBox preferredCheckBox;
    @FXML
    private ComboBox<String> paymentTermsCombo;
    @FXML
    private CheckBox taxExemptCheckBox;
    @FXML
    private ComboBox<String> statusCombo;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private ComboBox<String> typeCombo;
    @FXML
    private TextArea addressArea;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField phoneField;

    private static final String SUPPLIERS_DIR = "suppliers/";
    private static final String SUPPLIER_LOG = SUPPLIERS_DIR + "supplier_log.txt";

    @FXML
    public void initialize() {
        new File(SUPPLIERS_DIR).mkdirs();
        setupComboBoxes();
    }

    private void setupComboBoxes() {
        paymentTermsCombo.setItems(javafx.collections.FXCollections.observableArrayList(
                "Net 30", "Net 60", "Net 90", "COD", "Advance Payment", "Letter of Credit"
        ));
        paymentTermsCombo.setValue("Net 30");

        statusCombo.setItems(javafx.collections.FXCollections.observableArrayList(
                "Active", "Inactive", "Pending", "Suspended"
        ));
        statusCombo.setValue("Active");

        typeCombo.setItems(javafx.collections.FXCollections.observableArrayList(
                "Raw Material Supplier", "Parts Supplier", "Equipment Supplier",
                "Logistics Provider", "Service Provider", "Consultant"
        ));
        typeCombo.setValue("Raw Material Supplier");
    }

    @FXML
    public void handleReset(ActionEvent actionEvent) {
        nameField.clear();
        contactPersonField.clear();
        emailField.clear();
        phoneField.clear();
        addressArea.clear();
        preferredCheckBox.setSelected(false);
        taxExemptCheckBox.setSelected(false);
        paymentTermsCombo.setValue("Net 30");
        statusCombo.setValue("Active");
        typeCombo.setValue("Raw Material Supplier");
        statusLabel.setText("");
    }

    @FXML
    public void handleAddSupplier(ActionEvent actionEvent) {
        try {
            String name = nameField.getText().trim();
            String contactPerson = contactPersonField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String address = addressArea.getText().trim();
            String paymentTerms = paymentTermsCombo.getValue();
            String status = statusCombo.getValue();
            String type = typeCombo.getValue();

            if (name.isEmpty() || contactPerson.isEmpty() || email.isEmpty() ||
                    phone.isEmpty() || address.isEmpty()) {
                showAlert("Validation Error", "Please fill in all required fields.", Alert.AlertType.ERROR);
                return;
            }

            if (!isValidEmail(email)) {
                showAlert("Validation Error", "Invalid email format.", Alert.AlertType.ERROR);
                return;
            }

            String supplierId = "SUP-" + System.currentTimeMillis();

            Supplier supplier = new Supplier(supplierId, name, contactPerson, email, phone);
            supplier.setAddress(address);
            supplier.setType(type);
            supplier.setStatus(status);
            supplier.setPaymentTerms(paymentTerms);
            supplier.setPreferred(preferredCheckBox.isSelected());
            supplier.setTaxExempt(taxExemptCheckBox.isSelected());
            supplier.setRegistrationDate(LocalDate.now());

            String filename = SUPPLIERS_DIR + "supplier_" + supplierId + ".ser";
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                oos.writeObject(supplier);
            }

            appendToSupplierLog(supplier);

            statusLabel.setText("✓ Supplier added successfully! ID: " + supplierId);
            statusLabel.setStyle("-fx-text-fill: green;");

            showAlert("Success", "Supplier added successfully.\nSupplier ID: " + supplierId,
                    Alert.AlertType.INFORMATION);
            handleReset(null);

        } catch (Exception e) {
            statusLabel.setText("✗ Error: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
            showAlert("Error", "Failed to add supplier: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void appendToSupplierLog(Supplier supplier) {
        try (FileWriter fw = new FileWriter(SUPPLIER_LOG, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(supplier.getSupplierID() + "," +
                    supplier.getName() + "," +
                    supplier.getType() + "," +
                    supplier.getStatus() + "," +
                    supplier.getRegistrationDate());
        } catch (IOException e) {
            System.err.println("Failed to append to supplier log: " + e.getMessage());
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    @FXML
    public void handleBackToDashboard(ActionEvent actionEvent) {
        Stage stage = (Stage) nameField.getScene().getWindow();
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