package Sohan.ProcurementOfficerControllers;

import Sohan.ModelClasses.ProcurementOfficer.Supplier;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class UpdateSupplierView_Controller
{
    @FXML
    private TextField contactPersonField;
    @FXML
    private RadioButton preferredRadio;
    @FXML
    private TextField searchIdField;
    @FXML
    private RadioButton standardRadio;
    @FXML
    private ComboBox<String> statusCombo;
    @FXML
    private TextField nameField;
    @FXML
    private TextField emailField;
    @FXML
    private Label supplierIdLabel;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField phoneField;
    @FXML
    private TextField searchNameField;
    @FXML
    private ToggleGroup preferredGroup;
    @FXML
    private TextArea addressArea;

    private static final String SUPPLIERS_DIR = "suppliers/";
    private static final String UPDATE_LOG = SUPPLIERS_DIR + "update_log.txt";
    private Supplier currentSupplier;

    @FXML
    public void initialize() {
        new File(SUPPLIERS_DIR).mkdirs();
        setupComboBoxes();
        setDefaultValues();
        clearForm();
    }

    private void setupComboBoxes() {
        statusCombo.setItems(javafx.collections.FXCollections.observableArrayList(
                "Active", "Inactive", "Pending", "Suspended"
        ));
        statusCombo.setValue("Active");
    }

    private void setDefaultValues() {
        preferredGroup.selectToggle(standardRadio);
    }

    private void clearForm() {
        searchIdField.clear();
        searchNameField.clear();
        supplierIdLabel.setText("");
        nameField.clear();
        contactPersonField.clear();
        emailField.clear();
        phoneField.clear();
        addressArea.clear();
        statusCombo.setValue("Active");
        preferredGroup.selectToggle(standardRadio);
        currentSupplier = null;
        statusLabel.setText("");
        nameField.setDisable(true);
        contactPersonField.setDisable(true);
        emailField.setDisable(true);
        phoneField.setDisable(true);
        addressArea.setDisable(true);
        statusCombo.setDisable(true);
        preferredRadio.setDisable(true);
        standardRadio.setDisable(true);
    }

    private void enableForm(boolean enable) {
        nameField.setDisable(!enable);
        contactPersonField.setDisable(!enable);
        emailField.setDisable(!enable);
        phoneField.setDisable(!enable);
        addressArea.setDisable(!enable);
        statusCombo.setDisable(!enable);
        preferredRadio.setDisable(!enable);
        standardRadio.setDisable(!enable);
    }

    @FXML
    public void handleFindByName(ActionEvent actionEvent) {
        String name = searchNameField.getText().trim();
        if (name.isEmpty()) {
            showAlert("Validation Error", "Please enter supplier name.", Alert.AlertType.ERROR);
            return;
        }

        File dir = new File(SUPPLIERS_DIR);
        File[] files = dir.listFiles((d, f) -> f.startsWith("supplier_") && f.endsWith(".ser"));
        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    Supplier supplier = (Supplier) ois.readObject();
                    if (supplier.getName().toLowerCase().contains(name.toLowerCase())) {
                        loadSupplier(supplier);
                        return;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Failed to load supplier: " + e.getMessage());
                }
            }
        }

        showAlert("Not Found", "Supplier not found. Please check the name.", Alert.AlertType.WARNING);
        statusLabel.setText("✗ Supplier not found.");
        statusLabel.setStyle("-fx-text-fill: red;");
    }

    @FXML
    public void handleClear(ActionEvent actionEvent) {
        clearForm();
        statusLabel.setText("✓ Form cleared.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @FXML
    public void handleLoadSupplier(ActionEvent actionEvent) {
        String supplierId = searchIdField.getText().trim();
        if (supplierId.isEmpty()) {
            showAlert("Validation Error", "Please enter Supplier ID.", Alert.AlertType.ERROR);
            return;
        }

        String filename = SUPPLIERS_DIR + "supplier_" + supplierId + ".ser";
        File file = new File(filename);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                Supplier supplier = (Supplier) ois.readObject();
                loadSupplier(supplier);
            } catch (IOException | ClassNotFoundException e) {
                showAlert("Error", "Failed to load supplier: " + e.getMessage(), Alert.AlertType.ERROR);
            }
        } else {
            showAlert("Not Found", "Supplier not found. Please check the ID.", Alert.AlertType.WARNING);
            statusLabel.setText("✗ Supplier not found.");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }

    private void loadSupplier(Supplier supplier) {
        currentSupplier = supplier;
        supplierIdLabel.setText(supplier.getSupplierID());
        nameField.setText(supplier.getName());
        contactPersonField.setText(supplier.getContactPerson());
        emailField.setText(supplier.getEmail());
        phoneField.setText(supplier.getPhone());
        addressArea.setText(supplier.getAddress());
        statusCombo.setValue(supplier.getStatus());
        preferredGroup.selectToggle(supplier.isPreferred() ? preferredRadio : standardRadio);

        enableForm(true);
        statusLabel.setText("✓ Supplier loaded. Ready for update.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @FXML
    public void handleUpdateSupplier(ActionEvent actionEvent) {
        try {
            if (currentSupplier == null) {
                showAlert("Validation Error", "Please load a supplier first.", Alert.AlertType.ERROR);
                return;
            }

            String name = nameField.getText().trim();
            String contactPerson = contactPersonField.getText().trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            String address = addressArea.getText().trim();
            String status = statusCombo.getValue();

            if (name.isEmpty() || contactPerson.isEmpty() || email.isEmpty() ||
                    phone.isEmpty() || address.isEmpty()) {
                showAlert("Validation Error", "Please fill in all fields.", Alert.AlertType.ERROR);
                return;
            }

            if (!isValidEmail(email)) {
                showAlert("Validation Error", "Invalid email format.", Alert.AlertType.ERROR);
                return;
            }

            currentSupplier.setName(name);
            currentSupplier.setContactPerson(contactPerson);
            currentSupplier.setEmail(email);
            currentSupplier.setPhone(phone);
            currentSupplier.setAddress(address);
            currentSupplier.setStatus(status);
            currentSupplier.setPreferred(preferredRadio.isSelected());

            String filename = SUPPLIERS_DIR + "supplier_" + currentSupplier.getSupplierID() + ".ser";
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                oos.writeObject(currentSupplier);
            }

            appendToUpdateLog(currentSupplier);

            statusLabel.setText("✓ Supplier updated successfully! ID: " + currentSupplier.getSupplierID());
            statusLabel.setStyle("-fx-text-fill: green;");

            showAlert("Success", "Supplier information updated successfully.", Alert.AlertType.INFORMATION);
            clearForm();

        } catch (Exception e) {
            statusLabel.setText("✗ Error: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
            showAlert("Error", "Failed to update supplier: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void appendToUpdateLog(Supplier supplier) {
        try (FileWriter fw = new FileWriter(UPDATE_LOG, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(supplier.getSupplierID() + "," +
                    supplier.getName() + "," +
                    supplier.getStatus() + "," +
                    LocalDate.now().toString());
        } catch (IOException e) {
            System.err.println("Failed to append to update log: " + e.getMessage());
        }
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    @FXML
    public void handleBackToDashboard(ActionEvent actionEvent) {
        Stage stage = (Stage) searchIdField.getScene().getWindow();
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