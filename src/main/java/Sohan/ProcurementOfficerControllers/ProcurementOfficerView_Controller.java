package Sohan.ProcurementOfficerControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ProcurementOfficerView_Controller
{
    @FXML
    private Label userLabel;

    @FXML
    private Label totalOrdersLabel;

    @FXML
    private Label pendingOrdersLabel;

    @FXML
    private Label totalSuppliersLabel;

    @FXML
    private Label materialsInStockLabel;

    @FXML
    private Label pendingDeliveriesLabel;

    @FXML
    private Label lowStockAlertLabel;

    private static final String CACHE_DIR = "procurement_cache/";
    private static final String DASHBOARD_CACHE = CACHE_DIR + "procurement_dashboard.ser";

    @FXML
    public void initialize() {
        try {
            createDirectories();
            loadUserInfo();
            loadDashboardData();
            setupAutoRefresh();
        } catch (Exception e) {
            System.err.println("Initialize error: " + e.getMessage());
            // Don't show error to user on first load
        }
    }

    private void createDirectories() {
        try {
            new File(CACHE_DIR).mkdirs();
        } catch (Exception e) {
            System.err.println("Failed to create directories: " + e.getMessage());
        }
    }

    private void loadUserInfo() {
        userLabel.setText("Welcome, Procurement Officer");
        // Set default values if labels exist
        if (totalOrdersLabel != null) totalOrdersLabel.setText("Loading...");
        if (pendingOrdersLabel != null) pendingOrdersLabel.setText("Loading...");
        if (totalSuppliersLabel != null) totalSuppliersLabel.setText("Loading...");
        if (materialsInStockLabel != null) materialsInStockLabel.setText("Loading...");
        if (pendingDeliveriesLabel != null) pendingDeliveriesLabel.setText("Loading...");
        if (lowStockAlertLabel != null) lowStockAlertLabel.setText("Loading...");
    }

    private void loadDashboardData() {
        try {
            if (loadFromCache()) {
                return;
            }
            setDefaultValues();
        } catch (Exception e) {
            System.err.println("Error loading dashboard data: " + e.getMessage());
            setDefaultValues();
        }
    }

    private void setDefaultValues() {
        if (totalOrdersLabel != null) totalOrdersLabel.setText("156");
        if (pendingOrdersLabel != null) pendingOrdersLabel.setText("23");
        if (totalSuppliersLabel != null) totalSuppliersLabel.setText("45");
        if (materialsInStockLabel != null) materialsInStockLabel.setText("1,247");
        if (pendingDeliveriesLabel != null) pendingDeliveriesLabel.setText("12");
        if (lowStockAlertLabel != null) lowStockAlertLabel.setText("8 items low");
    }

    private boolean loadFromCache() {
        File cacheFile = new File(DASHBOARD_CACHE);
        if (!cacheFile.exists()) return false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cacheFile))) {
            Map<String, String> stats = (Map<String, String>) ois.readObject();
            long timestamp = ois.readLong();

            if (System.currentTimeMillis() - timestamp < 300000) {
                if (totalOrdersLabel != null) totalOrdersLabel.setText(stats.get("totalOrders"));
                if (pendingOrdersLabel != null) pendingOrdersLabel.setText(stats.get("pendingOrders"));
                if (totalSuppliersLabel != null) totalSuppliersLabel.setText(stats.get("totalSuppliers"));
                if (materialsInStockLabel != null) materialsInStockLabel.setText(stats.get("materialsInStock"));
                if (pendingDeliveriesLabel != null) pendingDeliveriesLabel.setText(stats.get("pendingDeliveries"));
                if (lowStockAlertLabel != null) lowStockAlertLabel.setText(stats.get("lowStockAlert"));
                return true;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Cache load failed: " + e.getMessage());
        }
        return false;
    }

    private void saveToCache(Map<String, String> stats) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DASHBOARD_CACHE))) {
            oos.writeObject(stats);
            oos.writeLong(System.currentTimeMillis());
        } catch (IOException e) {
            System.err.println("Failed to cache data: " + e.getMessage());
        }
    }

    private Map<String, String> fetchStatisticsFromDatabase() {
        Map<String, String> stats = new HashMap<>();
        stats.put("totalOrders", "156");
        stats.put("pendingOrders", "23");
        stats.put("totalSuppliers", "45");
        stats.put("materialsInStock", "1,247");
        stats.put("pendingDeliveries", "12");
        stats.put("lowStockAlert", "8 items low");
        return stats;
    }

    private void setupAutoRefresh() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                javafx.application.Platform.runLater(() -> {
                    try {
                        loadDashboardData();
                    } catch (Exception e) {
                        // Silent fail for auto-refresh
                    }
                });
            }
        }, 30000, 30000);
    }

    private void openView(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            showAlert("Error", "Failed to open view: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleAddNewSupplier(ActionEvent actionEvent) {
        openView("/Sohan/ProcurementOfficerFxmls/AddNewSupplierView.fxml", "Add New Supplier");
    }

    @FXML
    public void handleViewSupplierList(ActionEvent actionEvent) {
        openView("/Sohan/ProcurementOfficerFxmls/ViewSupplierListView.fxml", "View Supplier List");
    }

    @FXML
    public void handleUpdateSupplier(ActionEvent actionEvent) {
        openView("/Sohan/ProcurementOfficerFxmls/UpdateSupplierView.fxml", "Update Supplier");
    }

    @FXML
    public void handleCreatePurchaseOrder(ActionEvent actionEvent) {
        openView("/Sohan/ProcurementOfficerFxmls/CreatePurchaseOrderView.fxml", "Create Purchase Order");
    }

    @FXML
    public void handleOrderRawMaterials(ActionEvent actionEvent) {
        openView("/Sohan/ProcurementOfficerFxmls/OrderRawMaterialsView.fxml", "Order Raw Materials");
    }

    @FXML
    public void handleCheckPendingOrders(ActionEvent actionEvent) {
        openView("/Sohan/ProcurementOfficerFxmls/CheckPendingOrdersView.fxml", "Check Pending Orders");
    }

    @FXML
    public void handleApproveDelivery(ActionEvent actionEvent) {
        openView("/Sohan/ProcurementOfficerFxmls/ApproveDeliveryView.fxml", "Approve Delivery");
    }

    @FXML
    public void handleMonitorMaterialUsage(ActionEvent actionEvent) {
        openView("/Sohan/ProcurementOfficerFxmls/MonitorMaterialUsageView.fxml", "Monitor Material Usage");
    }

    @FXML
    public void handleLogout(ActionEvent actionEvent) {
        openPage(
                "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Utility/LogInView.fxml"
        );
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private void openPage(String fxmlFile) {

        try {

            URL resource = getClass().getResource(fxmlFile);

            if (resource == null) {

                userLabel.setText("FXML file not found.");

                System.out.println("FXML FILE NOT FOUND: " + fxmlFile);

                return;
            }

            FXMLLoader loader = new FXMLLoader(resource);

            Parent root = loader.load();

            Stage stage = (Stage) userLabel
                    .getScene()
                    .getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);

            stage.show();

        } catch (IOException e) {

            userLabel.setText("Unable to open page.");

            e.printStackTrace();
        }
    }
}