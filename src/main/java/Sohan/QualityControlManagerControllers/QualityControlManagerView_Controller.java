package Sohan.QualityControlManagerControllers;

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

public class QualityControlManagerView_Controller
{
    @FXML
    private Label userLabel;

    @FXML
    private Label totalVehiclesLabel;

    @FXML
    private Label pendingInspectionsLabel;

    @FXML
    private Label defectsFoundLabel;

    @FXML
    private Label qualityScoreLabel;

    @FXML
    private Label approvedVehiclesLabel;

    private static final String CACHE_DIR = "cache/";
    private static final String DASHBOARD_CACHE = CACHE_DIR + "dashboard_stats.ser";

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
        userLabel.setText("Welcome, Quality Control Manager");
        // Set default values if labels exist
        if (totalVehiclesLabel != null) totalVehiclesLabel.setText("Loading...");
        if (pendingInspectionsLabel != null) pendingInspectionsLabel.setText("Loading...");
        if (defectsFoundLabel != null) defectsFoundLabel.setText("Loading...");
        if (qualityScoreLabel != null) qualityScoreLabel.setText("Loading...");
        if (approvedVehiclesLabel != null) approvedVehiclesLabel.setText("Loading...");
    }

    private void loadDashboardData() {
        try {
            // Try to load from cache
            if (loadFromCache()) {
                return;
            }

            // If no cache, load default values
            setDefaultValues();

        } catch (Exception e) {
            System.err.println("Error loading dashboard data: " + e.getMessage());
            setDefaultValues();
        }
    }

    private void setDefaultValues() {
        if (totalVehiclesLabel != null) totalVehiclesLabel.setText("1,247");
        if (pendingInspectionsLabel != null) pendingInspectionsLabel.setText("89");
        if (defectsFoundLabel != null) defectsFoundLabel.setText("156");
        if (qualityScoreLabel != null) qualityScoreLabel.setText("92.5%");
        if (approvedVehiclesLabel != null) approvedVehiclesLabel.setText("1,002");
    }

    private boolean loadFromCache() {
        File cacheFile = new File(DASHBOARD_CACHE);
        if (!cacheFile.exists()) return false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cacheFile))) {
            Map<String, String> stats = (Map<String, String>) ois.readObject();
            long timestamp = ois.readLong();

            if (System.currentTimeMillis() - timestamp < 300000) {
                if (totalVehiclesLabel != null) totalVehiclesLabel.setText(stats.get("totalVehicles"));
                if (pendingInspectionsLabel != null) pendingInspectionsLabel.setText(stats.get("pendingInspections"));
                if (defectsFoundLabel != null) defectsFoundLabel.setText(stats.get("defectsFound"));
                if (qualityScoreLabel != null) qualityScoreLabel.setText(stats.get("qualityScore") + "%");
                if (approvedVehiclesLabel != null) approvedVehiclesLabel.setText(stats.get("approvedVehicles"));
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
        stats.put("totalVehicles", "1,247");
        stats.put("pendingInspections", "89");
        stats.put("defectsFound", "156");
        stats.put("qualityScore", "92.5");
        stats.put("approvedVehicles", "1,002");
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
    public void handleCheckCarQuality(ActionEvent actionEvent) {
        openView("/Sohan/QualityControlManagerFxmls/CheckCarQualityView.fxml", "Check Car Quality");
    }

    @FXML
    public void handleAddDefectInformation(ActionEvent actionEvent) {
        openView("/Sohan/QualityControlManagerFxmls/AddDefectInformationView.fxml", "Add Defect Information");
    }

    @FXML
    public void handleViewInspectionReport(ActionEvent actionEvent) {
        openView("/Sohan/QualityControlManagerFxmls/ViewInspectionReportView.fxml", "View Inspection Report");
    }

    @FXML
    public void handleScheduleReInspection(ActionEvent actionEvent) {
        openView("/Sohan/QualityControlManagerFxmls/ScheduleReinspectionView.fxml", "Schedule Re-Inspection");
    }

    @FXML
    public void handlePrintQualityReport(ActionEvent actionEvent) {
        openView("/Sohan/QualityControlManagerFxmls/PrintQualityReportView.fxml", "Print Quality Report");
    }

    @FXML
    public void handleApproveCarsForDelivery(ActionEvent actionEvent) {
        openView("/Sohan/QualityControlManagerFxmls/ApproveCarsForDeliveryView.fxml", "Approve Cars for Delivery");
    }

    @FXML
    public void handleReviewWorkerPerformance(ActionEvent actionEvent) {
        openView("/Sohan/QualityControlManagerFxmls/ReviewWorkerPerformanceView.fxml", "Review Worker Performance");
    }

    @FXML
    public void handleMonitorQualityStatistics(ActionEvent actionEvent) {
        openView("/Sohan/QualityControlManagerFxmls/MonitorQualityStatisticsView.fxml", "Monitor Quality Statistics");
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