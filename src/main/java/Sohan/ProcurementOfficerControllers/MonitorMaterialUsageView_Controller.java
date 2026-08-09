package Sohan.ProcurementOfficerControllers;

import Sohan.ModelClasses.ProcurementOfficer.MaterialUsage;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class MonitorMaterialUsageView_Controller
{
    @FXML
    private Label shortageLabel;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private ComboBox<String> materialTypeCombo;
    @FXML
    private ComboBox<String> categoryCombo;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private Label avgConsumptionLabel;
    @FXML
    private Label totalUsedLabel;
    @FXML
    private TableView<MaterialUsage> materialUsageTableView;
    @FXML
    private Label lowStockLabel;
    @FXML
    private Label statusLabel;

    private ObservableList<MaterialUsage> usageData = FXCollections.observableArrayList();
    private static final String USAGE_DIR = "usage/";
    private static final String USAGE_CACHE = USAGE_DIR + "usage_cache.ser";

    @FXML
    public void initialize() {
        new File(USAGE_DIR).mkdirs();
        setupComboBoxes();
        setupTable();
        setDefaultDates();
        loadUsageData();
    }

    private void setupComboBoxes() {
        materialTypeCombo.setItems(FXCollections.observableArrayList(
                "All Materials", "Steel", "Aluminum", "Plastic",
                "Rubber", "Glass", "Paint", "Wood", "Electronics"
        ));
        materialTypeCombo.setValue("All Materials");

        categoryCombo.setItems(FXCollections.observableArrayList(
                "All Categories", "Raw Materials", "Packaging",
                "Parts", "Chemicals", "Electronics"
        ));
        categoryCombo.setValue("All Categories");
    }

    private void setupTable() {
        TableColumn<MaterialUsage, String> materialCol = new TableColumn<>("Material");
        materialCol.setCellValueFactory(new PropertyValueFactory<>("materialName"));

        TableColumn<MaterialUsage, String> categoryCol = new TableColumn<>("Category");
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("materialCategory"));

        TableColumn<MaterialUsage, Double> usedCol = new TableColumn<>("Used");
        usedCol.setCellValueFactory(new PropertyValueFactory<>("quantityUsed"));

        TableColumn<MaterialUsage, Double> stockCol = new TableColumn<>("In Stock");
        stockCol.setCellValueFactory(new PropertyValueFactory<>("remainingStock"));

        TableColumn<MaterialUsage, Double> avgCol = new TableColumn<>("Avg Daily");
        avgCol.setCellValueFactory(new PropertyValueFactory<>("avgConsumption"));

        TableColumn<MaterialUsage, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        materialUsageTableView.getColumns().addAll(materialCol, categoryCol, usedCol, stockCol, avgCol, statusCol);
        materialUsageTableView.setItems(usageData);
    }

    private void setDefaultDates() {
        startDatePicker.setValue(LocalDate.now().minusDays(30));
        endDatePicker.setValue(LocalDate.now());
    }

    private void loadUsageData() {
        if (loadFromCache()) {
            applyFilters();
            updateSummary();
            return;
        }

        usageData.clear();

        File dir = new File(USAGE_DIR);
        File[] files = dir.listFiles((d, name) -> name.startsWith("usage_") && name.endsWith(".ser"));
        if (files != null && files.length > 0) {
            for (File file : files) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    MaterialUsage usage = (MaterialUsage) ois.readObject();
                    usageData.add(usage);
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Failed to load usage: " + e.getMessage());
                }
            }
        }

        if (usageData.isEmpty()) {
            createSampleData();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USAGE_CACHE))) {
            oos.writeObject(new ArrayList<>(usageData));
            oos.writeLong(System.currentTimeMillis());
        } catch (IOException e) {
            System.err.println("Failed to cache usage data: " + e.getMessage());
        }

        applyFilters();
        updateSummary();
    }

    private boolean loadFromCache() {
        File cacheFile = new File(USAGE_CACHE);
        if (!cacheFile.exists()) return false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cacheFile))) {
            @SuppressWarnings("unchecked")
            List<MaterialUsage> cachedData = (List<MaterialUsage>) ois.readObject();
            long timestamp = ois.readLong();

            if (System.currentTimeMillis() - timestamp < 1800000) {
                usageData.addAll(cachedData);
                return true;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Cache load failed: " + e.getMessage());
        }
        return false;
    }

    private void createSampleData() {
        // MaterialUsage constructor: (usageID, materialID, materialName, quantityUsed, remainingStock)
        // Status is automatically calculated based on remainingStock vs minStockLevel, maxStockLevel, reorderPoint

        String[][] usage = {
                {"Steel Sheets", "Raw Materials", "4500", "2500", "150"},
                {"Aluminum", "Raw Materials", "3200", "1800", "107"},
                {"Plastic", "Raw Materials", "8000", "6000", "267"},
                {"Rubber", "Parts", "1200", "800", "40"},
                {"Glass", "Raw Materials", "600", "400", "20"},
                {"Paint", "Chemicals", "300", "150", "10"},
                {"Electronics", "Parts", "200", "120", "7"},
                {"Packaging", "Packaging", "5000", "3000", "167"}
        };

        for (String[] u : usage) {
            MaterialUsage material = new MaterialUsage(
                    "USAGE-" + System.currentTimeMillis() + (int)(Math.random() * 100),
                    "MAT-" + u[0].substring(0, 3).toUpperCase(),
                    u[0],
                    Double.parseDouble(u[1]),
                    Double.parseDouble(u[2])
            );
            material.setMaterialCategory(u[3]);
            material.setAvgConsumption(Double.parseDouble(u[4]));
            // Set minStockLevel, maxStockLevel, reorderPoint for status calculation
            material.setMinStockLevel(200.0);
            material.setMaxStockLevel(10000.0);
            material.setReorderPoint(500.0);
            material.setUsageDate(LocalDate.now().minusDays((int)(Math.random() * 30)));
            material.setUnit("kg");
            // Status will be calculated automatically based on remainingStock vs thresholds
            usageData.add(material);
        }
    }

    private void applyFilters() {
        String materialFilter = materialTypeCombo.getValue();
        String categoryFilter = categoryCombo.getValue();

        ObservableList<MaterialUsage> filtered = FXCollections.observableArrayList();

        for (MaterialUsage item : usageData) {
            boolean matches = true;

            if (!"All Materials".equals(materialFilter) && !materialFilter.equals(item.getMaterialName())) {
                matches = false;
            }

            if (!"All Categories".equals(categoryFilter) && !categoryFilter.equals(item.getMaterialCategory())) {
                matches = false;
            }

            if (matches) filtered.add(item);
        }

        materialUsageTableView.setItems(filtered);
    }

    private void updateSummary() {
        double totalUsed = 0;
        int lowStockCount = 0;
        int criticalCount = 0;

        for (MaterialUsage item : materialUsageTableView.getItems()) {
            totalUsed += item.getQuantityUsed();
            String status = item.getStatus();
            if ("Low Stock - Order Soon".equals(status)) lowStockCount++;
            if ("Critical - Reorder Required".equals(status)) criticalCount++;
        }

        totalUsedLabel.setText(String.format("%.0f", totalUsed));
        lowStockLabel.setText(String.valueOf(lowStockCount));
        shortageLabel.setText(String.valueOf(lowStockCount + criticalCount));

        double avg = materialUsageTableView.getItems().isEmpty() ? 0 :
                totalUsed / materialUsageTableView.getItems().size();
        avgConsumptionLabel.setText(String.format("%.1f", avg));
    }

    @FXML
    public void handleReset(ActionEvent actionEvent) {
        materialTypeCombo.setValue("All Materials");
        categoryCombo.setValue("All Categories");
        startDatePicker.setValue(LocalDate.now().minusDays(30));
        endDatePicker.setValue(LocalDate.now());
        applyFilters();
        updateSummary();
        statusLabel.setText("✓ Filters reset.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @FXML
    public void handleApplyFilters(ActionEvent actionEvent) {
        applyFilters();
        updateSummary();
        statusLabel.setText("✓ Filters applied.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @FXML
    public void handleExportExcel(ActionEvent actionEvent) {
        try {
            String filename = USAGE_DIR + "material_usage_" + System.currentTimeMillis() + ".xls";

            try (FileWriter fw = new FileWriter(filename)) {
                fw.write("Material Usage Report\n");
                fw.write("====================\n\n");
                fw.write("Material,Category,Used,In Stock,Avg Daily,Status\n");
                for (MaterialUsage item : materialUsageTableView.getItems()) {
                    fw.write(item.getMaterialName() + "," +
                            item.getMaterialCategory() + "," +
                            item.getQuantityUsed() + "," +
                            item.getRemainingStock() + "," +
                            item.getAvgConsumption() + "," +
                            item.getStatus() + "\n");
                }
            }

            statusLabel.setText("✓ Exported to: " + filename);
            statusLabel.setStyle("-fx-text-fill: green;");

            showAlert("Success", "Data exported successfully.", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            statusLabel.setText("✗ Error exporting.");
            statusLabel.setStyle("-fx-text-fill: red;");
            showAlert("Error", "Failed to export: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleGenerateReport(ActionEvent actionEvent) {
        try {
            Map<String, Object> report = new HashMap<>();
            report.put("reportType", "Material Usage Report");
            report.put("generatedDate", LocalDate.now().toString());
            report.put("period", startDatePicker.getValue() + " to " + endDatePicker.getValue());
            report.put("totalUsed", totalUsedLabel.getText());
            report.put("lowStock", lowStockLabel.getText());
            report.put("critical", shortageLabel.getText());
            report.put("avgConsumption", avgConsumptionLabel.getText());

            String filename = USAGE_DIR + "usage_report_" + System.currentTimeMillis() + ".ser";
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
    public void handleRefresh(ActionEvent actionEvent) {
        loadUsageData();
        statusLabel.setText("✓ Data refreshed.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @FXML
    public void handleBackToDashboard(ActionEvent actionEvent) {
        Stage stage = (Stage) totalUsedLabel.getScene().getWindow();
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