package Sohan.ProcurementOfficerControllers;

import Sohan.ModelClasses.ProcurementOfficer.Supplier;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.*;
import java.util.*;

public class ViewSupplierListView_Controller
{
    @FXML
    private Label recordCountLabel;
    @FXML
    private ComboBox<String> filterCombo;
    @FXML
    private TextField searchField;
    @FXML
    private TableView<Supplier> supplierTableView;
    @FXML
    private Label statusLabel;

    private ObservableList<Supplier> supplierData = FXCollections.observableArrayList();
    private static final String SUPPLIERS_DIR = "suppliers/";
    private static final String SUPPLIER_LIST_CACHE = SUPPLIERS_DIR + "supplier_list_cache.ser";

    @FXML
    public void initialize() {
        new File(SUPPLIERS_DIR).mkdirs();
        setupTable();
        setupComboBoxes();
        loadSupplierData();
        updateRecordCount();
    }

    private void setupTable() {
        TableColumn<Supplier, String> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("supplierID"));

        TableColumn<Supplier, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Supplier, String> contactCol = new TableColumn<>("Contact Person");
        contactCol.setCellValueFactory(new PropertyValueFactory<>("contactPerson"));

        TableColumn<Supplier, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Supplier, String> typeCol = new TableColumn<>("Type");
        typeCol.setCellValueFactory(new PropertyValueFactory<>("type"));

        TableColumn<Supplier, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<Supplier, String> preferredCol = new TableColumn<>("Preferred");
        preferredCol.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().isPreferred() ? "Yes" : "No"));

        supplierTableView.getColumns().addAll(idCol, nameCol, contactCol, phoneCol, typeCol, statusCol, preferredCol);
        supplierTableView.setItems(supplierData);

        supplierTableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    if (newVal != null) {
                        statusLabel.setText("✓ Selected: " + newVal.getSupplierID() + " - " + newVal.getName());
                        statusLabel.setStyle("-fx-text-fill: green;");
                    }
                });
    }

    private void setupComboBoxes() {
        filterCombo.setItems(FXCollections.observableArrayList(
                "All Suppliers", "Active", "Inactive", "Pending", "Suspended"
        ));
        filterCombo.setValue("All Suppliers");
    }

    private void loadSupplierData() {
        if (loadFromCache()) {
            applyFilters();
            return;
        }

        supplierData.clear();

        File dir = new File(SUPPLIERS_DIR);
        File[] files = dir.listFiles((d, name) -> name.startsWith("supplier_") && name.endsWith(".ser"));
        if (files != null && files.length > 0) {
            for (File file : files) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    Supplier supplier = (Supplier) ois.readObject();
                    supplierData.add(supplier);
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Failed to load supplier: " + e.getMessage());
                }
            }
        }

        if (supplierData.isEmpty()) {
            createSampleData();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SUPPLIER_LIST_CACHE))) {
            oos.writeObject(new ArrayList<>(supplierData));
            oos.writeLong(System.currentTimeMillis());
        } catch (IOException e) {
            System.err.println("Failed to cache supplier data: " + e.getMessage());
        }

        applyFilters();
        updateRecordCount();
    }

    private boolean loadFromCache() {
        File cacheFile = new File(SUPPLIER_LIST_CACHE);
        if (!cacheFile.exists()) return false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cacheFile))) {
            @SuppressWarnings("unchecked")
            List<Supplier> cachedData = (List<Supplier>) ois.readObject();
            long timestamp = ois.readLong();

            if (System.currentTimeMillis() - timestamp < 1800000) {
                supplierData.addAll(cachedData);
                return true;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Cache load failed: " + e.getMessage());
        }
        return false;
    }

    private void createSampleData() {
        String[][] suppliers = {
                {"SUP-001", "ABC Industries", "John Smith", "+1-555-111-1111", "Raw Material", "Active", "Yes"},
                {"SUP-002", "XYZ Corp", "Jane Doe", "+1-555-222-2222", "Parts", "Active", "Yes"},
                {"SUP-003", "MNO Supplies", "Bob Johnson", "+1-555-333-3333", "Raw Material", "Active", "No"},
                {"SUP-004", "PQR Logistics", "Alice Brown", "+1-555-444-4444", "Service", "Active", "No"},
                {"SUP-005", "DEF Parts", "Charlie Wilson", "+1-555-555-5555", "Parts", "Pending", "No"}
        };

        for (String[] s : suppliers) {
            Supplier supplier = new Supplier(s[0], s[1], s[2], s[3] + "@company.com", s[4]);
            supplier.setType(s[4]);
            supplier.setStatus(s[5]);
            supplier.setPreferred("Yes".equals(s[6]));
            supplierData.add(supplier);
        }
    }

    private void applyFilters() {
        String filter = filterCombo.getValue();
        String search = searchField.getText().trim().toLowerCase();

        ObservableList<Supplier> filtered = FXCollections.observableArrayList();

        for (Supplier supplier : supplierData) {
            boolean matches = true;

            if (!"All Suppliers".equals(filter) && !filter.equals(supplier.getStatus())) {
                matches = false;
            }

            if (!search.isEmpty() && !supplier.getName().toLowerCase().contains(search)) {
                matches = false;
            }

            if (matches) filtered.add(supplier);
        }

        supplierTableView.setItems(filtered);
        updateRecordCount();
    }

    private void updateRecordCount() {
        int count = supplierTableView.getItems().size();
        recordCountLabel.setText("Suppliers: " + count);
    }

    @FXML
    public void handleSearch(ActionEvent actionEvent) {
        applyFilters();
        statusLabel.setText("✓ Search applied.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @FXML
    public void handleRefresh(ActionEvent actionEvent) {
        loadSupplierData();
        statusLabel.setText("✓ Data refreshed.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @FXML
    public void handleBackToDashboard(ActionEvent actionEvent) {
        Stage stage = (Stage) recordCountLabel.getScene().getWindow();
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