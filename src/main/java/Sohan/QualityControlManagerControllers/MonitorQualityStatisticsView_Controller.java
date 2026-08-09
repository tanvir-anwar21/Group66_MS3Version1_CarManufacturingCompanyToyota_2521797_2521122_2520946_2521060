package Sohan.QualityControlManagerControllers;

import Sohan.ModelClasses.QualityControlManager.QualityStatistics;
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

public class MonitorQualityStatisticsView_Controller
{
    @FXML
    private Label passRateLabel;
    @FXML
    private Label criticalDefectsLabel;
    @FXML
    private TableView<QualityStatistics> qualityStatsTableView;
    @FXML
    private ComboBox<String> periodCombo;
    @FXML
    private Label avgRepairTimeLabel;
    @FXML
    private Label qualityScoreLabel;
    @FXML
    private Label totalInspectionsLabel;
    @FXML
    private Label defectRateLabel;
    @FXML
    private ComboBox<String> modelCombo;
    @FXML
    private Label statusLabel;
    @FXML
    private ComboBox<String> lineCombo;

    private ObservableList<QualityStatistics> statsData = FXCollections.observableArrayList();
    private static final String STATS_DIR = "stats/";
    private static final String STATS_CACHE = STATS_DIR + "statistics_cache.ser";

    @FXML
    public void initialize() {
        new File(STATS_DIR).mkdirs();
        setupComboBoxes();
        setupTable();
        loadStatistics();
    }

    private void setupComboBoxes() {
        periodCombo.setItems(FXCollections.observableArrayList(
                "Today", "Last 7 Days", "Last 30 Days", "This Quarter", "This Year"
        ));
        periodCombo.setValue("Last 30 Days");

        modelCombo.setItems(FXCollections.observableArrayList(
                "All Models", "Sedan", "SUV", "Truck", "Sports", "Electric"
        ));
        modelCombo.setValue("All Models");

        lineCombo.setItems(FXCollections.observableArrayList(
                "All Lines", "Line A", "Line B", "Line C", "Line D"
        ));
        lineCombo.setValue("All Lines");
    }

    private void setupTable() {
        TableColumn<QualityStatistics, LocalDate> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<QualityStatistics, Integer> inspectedCol = new TableColumn<>("Inspections");
        inspectedCol.setCellValueFactory(new PropertyValueFactory<>("totalInspected"));

        TableColumn<QualityStatistics, Integer> passedCol = new TableColumn<>("Passed");
        passedCol.setCellValueFactory(new PropertyValueFactory<>("passed"));

        TableColumn<QualityStatistics, Integer> failedCol = new TableColumn<>("Failed");
        failedCol.setCellValueFactory(new PropertyValueFactory<>("failed"));

        TableColumn<QualityStatistics, Double> passRateCol = new TableColumn<>("Pass Rate");
        passRateCol.setCellValueFactory(new PropertyValueFactory<>("passRate"));

        TableColumn<QualityStatistics, String> scoreCol = new TableColumn<>("Score");
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("qualityScore"));

        qualityStatsTableView.getColumns().addAll(dateCol, inspectedCol, passedCol, failedCol, passRateCol, scoreCol);
    }

    private void loadStatistics() {
        if (loadFromCache()) {
            return;
        }
        generateStatistics();
    }

    private boolean loadFromCache() {
        File cacheFile = new File(STATS_CACHE);
        if (!cacheFile.exists()) return false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cacheFile))) {
            @SuppressWarnings("unchecked")
            List<QualityStatistics> cachedStats = (List<QualityStatistics>) ois.readObject();
            long timestamp = ois.readLong();

            if (System.currentTimeMillis() - timestamp < 3600000) {
                statsData.setAll(cachedStats);
                qualityStatsTableView.setItems(statsData);
                updateSummaryStats();
                return true;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Cache load failed: " + e.getMessage());
        }
        return false;
    }

    private void generateStatistics() {
        statsData.clear();

        String[] dates = {"2026-07-01", "2026-07-02", "2026-07-03", "2026-07-04", "2026-07-05", "2026-07-06", "2026-07-07"};

        for (int i = 0; i < dates.length; i++) {
            QualityStatistics stats = new QualityStatistics(
                    "STAT-" + System.currentTimeMillis() + i,
                    "Line A",
                    "Sedan"
            );
            stats.setDate(LocalDate.parse(dates[i]));
            stats.setTotalInspected(50 + (int)(Math.random() * 30));
            stats.setPassed(35 + (int)(Math.random() * 15));
            stats.setFailed(stats.getTotalInspected() - stats.getPassed());
            stats.setConditionalPassed((int)(Math.random() * 5));
            stats.setDefectsFound((int)(Math.random() * 10));
            stats.setCriticalDefects((int)(Math.random() * 3));
            stats.setMajorDefects((int)(Math.random() * 5));
            stats.setMinorDefects((int)(Math.random() * 8));
            stats.setAvgRepairTime(1.5 + Math.random() * 3);
            stats.setReworkCount((int)(Math.random() * 5));
            stats.setScrapCount((int)(Math.random() * 2));
            stats.calculateQualityScore();

            statsData.add(stats);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STATS_CACHE))) {
            oos.writeObject(new ArrayList<>(statsData));
            oos.writeLong(System.currentTimeMillis());
        } catch (IOException e) {
            System.err.println("Failed to cache statistics: " + e.getMessage());
        }

        qualityStatsTableView.setItems(statsData);
        updateSummaryStats();
    }

    private void updateSummaryStats() {
        if (statsData.isEmpty()) {
            totalInspectionsLabel.setText("0");
            passRateLabel.setText("0%");
            defectRateLabel.setText("0%");
            qualityScoreLabel.setText("N/A");
            criticalDefectsLabel.setText("0");
            avgRepairTimeLabel.setText("0 days");
            return;
        }

        int totalInspected = statsData.stream().mapToInt(QualityStatistics::getTotalInspected).sum();
        int totalPassed = statsData.stream().mapToInt(QualityStatistics::getPassed).sum();
        int totalFailed = statsData.stream().mapToInt(QualityStatistics::getFailed).sum();
        int totalCritical = statsData.stream().mapToInt(QualityStatistics::getCriticalDefects).sum();

        double avgPassRate = totalInspected > 0 ? ((double) totalPassed / totalInspected) * 100 : 0;
        double avgDefectRate = totalInspected > 0 ? ((double) totalFailed / totalInspected) * 100 : 0;

        double avgRepairTime = statsData.stream()
                .mapToDouble(QualityStatistics::getAvgRepairTime)
                .average().orElse(0);

        totalInspectionsLabel.setText(String.valueOf(totalInspected));
        passRateLabel.setText(String.format("%.1f%%", avgPassRate));
        defectRateLabel.setText(String.format("%.1f%%", avgDefectRate));
        criticalDefectsLabel.setText(String.valueOf(totalCritical));
        avgRepairTimeLabel.setText(String.format("%.1f days", avgRepairTime));

        if (avgPassRate >= 95) {
            qualityScoreLabel.setText("A");
        } else if (avgPassRate >= 85) {
            qualityScoreLabel.setText("B");
        } else if (avgPassRate >= 75) {
            qualityScoreLabel.setText("C");
        } else if (avgPassRate >= 60) {
            qualityScoreLabel.setText("D");
        } else {
            qualityScoreLabel.setText("F");
        }
    }

    @FXML
    public void handleReset(ActionEvent actionEvent) {
        periodCombo.setValue("Last 30 Days");
        modelCombo.setValue("All Models");
        lineCombo.setValue("All Lines");
        generateStatistics();
        statusLabel.setText("✓ Filters reset.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @FXML
    public void handleApplyFilters(ActionEvent actionEvent) {
        generateStatistics();
        statusLabel.setText("✓ Filters applied.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @FXML
    public void handleExportExcel(ActionEvent actionEvent) {
        try {
            String filename = STATS_DIR + "quality_stats_" + System.currentTimeMillis() + ".xls";

            try (FileWriter fw = new FileWriter(filename)) {
                fw.write("Date,Inspections,Passed,Failed,Pass Rate,Defects,Critical,Score\n");
                for (QualityStatistics stats : statsData) {
                    fw.write(stats.getDate() + "," +
                            stats.getTotalInspected() + "," +
                            stats.getPassed() + "," +
                            stats.getFailed() + "," +
                            String.format("%.1f", stats.getPassRate()) + "%," +
                            stats.getDefectsFound() + "," +
                            stats.getCriticalDefects() + "," +
                            stats.getQualityScore() + "\n");
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
            report.put("reportType", "Quality Statistics Report");
            report.put("generatedDate", LocalDate.now().toString());
            report.put("period", periodCombo.getValue());
            report.put("totalInspections", totalInspectionsLabel.getText());
            report.put("passRate", passRateLabel.getText());
            report.put("defectRate", defectRateLabel.getText());
            report.put("qualityScore", qualityScoreLabel.getText());

            String filename = STATS_DIR + "stats_report_" + System.currentTimeMillis() + ".ser";
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
        generateStatistics();
        statusLabel.setText("✓ Data refreshed.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @FXML
    public void handleBackToDashboard(ActionEvent actionEvent) {
        Stage stage = (Stage) totalInspectionsLabel.getScene().getWindow();
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