package Sohan.ProcurementOfficerControllers;

import Sohan.ModelClasses.ProcurementOfficer.PurchaseOrder;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class CheckPendingOrdersView_Controller
{
    @FXML
    private Label recordCountLabel;
    @FXML
    private ComboBox<String> supplierFilterCombo;
    @FXML
    private RadioButton allRadio;
    @FXML
    private ToggleGroup filterGroup;
    @FXML
    private RadioButton inTransitRadio;
    @FXML
    private RadioButton deliveredRadio;
    @FXML
    private TextField orderIdField;
    @FXML
    private Label selectedOrderLabel;
    @FXML
    private RadioButton pendingRadio;
    @FXML
    private TableView<PurchaseOrder> pendingOrderTableView;
    @FXML
    private Label statusLabel;

    private ObservableList<PurchaseOrder> orderData = FXCollections.observableArrayList();
    private PurchaseOrder selectedOrder;
    private static final String ORDERS_DIR = "orders/";
    private static final String ORDERS_CACHE = ORDERS_DIR + "orders_cache.ser";

    @FXML
    public void initialize() {
        new File(ORDERS_DIR).mkdirs();
        setupTable();
        setupComboBoxes();
        setupToggleGroups();
        loadOrderData();
        updateRecordCount();
    }

    private void setupTable() {
        TableColumn<PurchaseOrder, String> idCol = new TableColumn<>("Order ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("orderID"));

        TableColumn<PurchaseOrder, String> supplierCol = new TableColumn<>("Supplier");
        supplierCol.setCellValueFactory(new PropertyValueFactory<>("supplierName"));

        TableColumn<PurchaseOrder, String> materialCol = new TableColumn<>("Material");
        materialCol.setCellValueFactory(new PropertyValueFactory<>("materialName"));

        TableColumn<PurchaseOrder, Double> quantityCol = new TableColumn<>("Quantity");
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        TableColumn<PurchaseOrder, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        TableColumn<PurchaseOrder, LocalDate> dateCol = new TableColumn<>("Order Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        pendingOrderTableView.getColumns().addAll(idCol, supplierCol, materialCol, quantityCol, statusCol, dateCol);
        pendingOrderTableView.setItems(orderData);

        pendingOrderTableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    selectedOrder = newVal;
                    if (newVal != null) {
                        selectedOrderLabel.setText("Selected: " + newVal.getOrderID() + " - " + newVal.getSupplierName());
                    }
                });
    }

    private void setupComboBoxes() {
        supplierFilterCombo.setItems(FXCollections.observableArrayList(
                "All Suppliers", "ABC Industries", "XYZ Corp", "MNO Supplies",
                "PQR Logistics", "DEF Parts"
        ));
        supplierFilterCombo.setValue("All Suppliers");
    }

    private void setupToggleGroups() {
        filterGroup.selectToggle(allRadio);
    }

    private void loadOrderData() {
        if (loadFromCache()) {
            applyFilters();
            return;
        }

        orderData.clear();

        File dir = new File("purchase_orders/");
        File[] files = dir.listFiles((d, name) -> name.startsWith("po_") && name.endsWith(".ser"));
        if (files != null && files.length > 0) {
            for (File file : files) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    PurchaseOrder po = (PurchaseOrder) ois.readObject();
                    orderData.add(po);
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Failed to load order: " + e.getMessage());
                }
            }
        }

        if (orderData.isEmpty()) {
            createSampleData();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ORDERS_CACHE))) {
            oos.writeObject(new ArrayList<>(orderData));
            oos.writeLong(System.currentTimeMillis());
        } catch (IOException e) {
            System.err.println("Failed to cache order data: " + e.getMessage());
        }

        applyFilters();
        updateRecordCount();
    }

    private boolean loadFromCache() {
        File cacheFile = new File(ORDERS_CACHE);
        if (!cacheFile.exists()) return false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cacheFile))) {
            @SuppressWarnings("unchecked")
            List<PurchaseOrder> cachedData = (List<PurchaseOrder>) ois.readObject();
            long timestamp = ois.readLong();

            if (System.currentTimeMillis() - timestamp < 1800000) {
                orderData.addAll(cachedData);
                return true;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Cache load failed: " + e.getMessage());
        }
        return false;
    }

    private void createSampleData() {
        String[][] orders = {
                {"PO-2026-001", "ABC Industries", "Steel Sheets", "500", "Pending", "2026-07-01"},
                {"PO-2026-002", "XYZ Corp", "Aluminum", "300", "In Transit", "2026-07-02"},
                {"PO-2026-003", "MNO Supplies", "Plastic", "1000", "Pending", "2026-07-03"},
                {"PO-2026-004", "PQR Logistics", "Packaging", "200", "Delivered", "2026-07-04"},
                {"PO-2026-005", "ABC Industries", "Steel Sheets", "250", "In Transit", "2026-07-05"}
        };

        for (String[] o : orders) {
            PurchaseOrder po = new PurchaseOrder(o[0], "SUP-001", o[1], o[2], Double.parseDouble(o[3]), 0.0);
            po.setStatus(o[4]);
            po.setOrderDate(LocalDate.parse(o[5]));
            orderData.add(po);
        }
    }

    private void applyFilters() {
        String filter = ((RadioButton) filterGroup.getSelectedToggle()).getText();
        String supplierFilter = supplierFilterCombo.getValue();
        String orderId = orderIdField.getText().trim();

        ObservableList<PurchaseOrder> filtered = FXCollections.observableArrayList();

        for (PurchaseOrder order : orderData) {
            boolean matches = true;

            if (!"All Suppliers".equals(supplierFilter) && !supplierFilter.equals(order.getSupplierName())) {
                matches = false;
            }

            if (!filter.equals("All") && !filter.equals(order.getStatus())) {
                matches = false;
            }

            if (!orderId.isEmpty() && !order.getOrderID().contains(orderId)) {
                matches = false;
            }

            if (matches) filtered.add(order);
        }

        pendingOrderTableView.setItems(filtered);
        updateRecordCount();
    }

    private void updateRecordCount() {
        int count = pendingOrderTableView.getItems().size();
        recordCountLabel.setText("Orders: " + count);
    }

    @FXML
    public void handleCheckStatus(ActionEvent actionEvent) {
        if (selectedOrder == null) {
            showAlert("Selection Error", "Please select an order from the table.", Alert.AlertType.WARNING);
            return;
        }

        String status = selectedOrder.getStatus();
        String details = "Order: " + selectedOrder.getOrderID() +
                "\nSupplier: " + selectedOrder.getSupplierName() +
                "\nMaterial: " + selectedOrder.getMaterialName() +
                "\nQuantity: " + selectedOrder.getQuantity() +
                "\nStatus: " + status +
                "\nOrder Date: " + selectedOrder.getOrderDate();

        showAlert("Order Status", details, Alert.AlertType.INFORMATION);
        statusLabel.setText("✓ Status checked for order: " + selectedOrder.getOrderID());
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @FXML
    public void handleGenerateReport(ActionEvent actionEvent) {
        try {
            String filename = ORDERS_DIR + "pending_orders_report_" + System.currentTimeMillis() + ".ser";

            Map<String, Object> report = new HashMap<>();
            report.put("reportDate", LocalDate.now().toString());
            report.put("totalOrders", orderData.size());
            report.put("orders", new ArrayList<>(pendingOrderTableView.getItems()));

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                oos.writeObject(report);
            }

            statusLabel.setText("✓ Report generated: " + filename);
            statusLabel.setStyle("-fx-text-fill: green;");

            showAlert("Success", "Report generated successfully.", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            statusLabel.setText("✗ Error generating report.");
            statusLabel.setStyle("-fx-text-fill: red;");
            showAlert("Error", "Failed to generate report: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleAllPending(ActionEvent actionEvent) {
        filterGroup.selectToggle(pendingRadio);
        applyFilters();
        statusLabel.setText("✓ Showing pending orders only.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @FXML
    public void handleViewDetails(ActionEvent actionEvent) {
        if (selectedOrder == null) {
            showAlert("Selection Error", "Please select an order from the table.", Alert.AlertType.WARNING);
            return;
        }

        String details = "Order ID: " + selectedOrder.getOrderID() + "\n" +
                "Supplier: " + selectedOrder.getSupplierName() + "\n" +
                "Material: " + selectedOrder.getMaterialName() + "\n" +
                "Quantity: " + selectedOrder.getQuantity() + "\n" +
                "Status: " + selectedOrder.getStatus() + "\n" +
                "Order Date: " + selectedOrder.getOrderDate() + "\n" +
                "Priority: " + selectedOrder.getPriority();

        showAlert("Order Details", details, Alert.AlertType.INFORMATION);
        statusLabel.setText("✓ Details displayed for order: " + selectedOrder.getOrderID());
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