package Sohan.QualityControlManagerControllers;

import Sohan.ModelClasses.QualityControlManager.InspectionReport;
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

public class ViewInspectionReportView_Controller
{
    @FXML
    private Label recordCountLabel;
    @FXML
    private DatePicker reportDatePicker;
    @FXML
    private TableView<InspectionReport> inspectionReportTableView;
    @FXML
    private Label selectedReportLabel;
    @FXML
    private ComboBox<String> modelCombo;
    @FXML
    private ComboBox<String> statusFilterCombo;
    @FXML
    private Label statusLabel;

    private ObservableList<InspectionReport> reportData = FXCollections.observableArrayList();
    private InspectionReport selectedReport;
    private static final String REPORTS_DIR = "reports/";
    private static final String INSPECTION_CACHE = REPORTS_DIR + "inspection_cache.ser";

    @FXML
    public void initialize() {
        new File(REPORTS_DIR).mkdirs();
        setupComboBoxes();
        setupTable();
        setDefaultDate();
        loadInspectionReport();
    }

    private void setupComboBoxes() {
        modelCombo.setItems(FXCollections.observableArrayList(
                "All Models", "Sedan", "SUV", "Truck", "Sports", "Electric"
        ));
        modelCombo.setValue("All Models");

        statusFilterCombo.setItems(FXCollections.observableArrayList(
                "All Status", "Pass", "Failed", "Conditional Pass", "Pending"
        ));
        statusFilterCombo.setValue("All Status");
    }

    private void setupTable() {
        TableColumn<InspectionReport, String> idCol = new TableColumn<>("Report ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("reportID"));

        TableColumn<InspectionReport, String> vehicleCol = new TableColumn<>("Vehicle ID");
        vehicleCol.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));

        TableColumn<InspectionReport, String> modelCol = new TableColumn<>("Model");
        modelCol.setCellValueFactory(new PropertyValueFactory<>("vehicleModel"));

        TableColumn<InspectionReport, LocalDate> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("reportDate"));

        TableColumn<InspectionReport, String> inspectorCol = new TableColumn<>("Inspector");
        inspectorCol.setCellValueFactory(new PropertyValueFactory<>("inspectorName"));

        TableColumn<InspectionReport, String> resultCol = new TableColumn<>("Result");
        resultCol.setCellValueFactory(new PropertyValueFactory<>("overallStatus"));

        inspectionReportTableView.getColumns().addAll(idCol, vehicleCol, modelCol, dateCol, inspectorCol, resultCol);
        inspectionReportTableView.setItems(reportData);

        inspectionReportTableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    selectedReport = newVal;
                    if (newVal != null) {
                        selectedReportLabel.setText("Selected: " + newVal.getReportID() + " - " + newVal.getVehicleID());
                    }
                });
    }

    private void setDefaultDate() {
        reportDatePicker.setValue(LocalDate.now());
    }

    private void loadInspectionReport() {
        if (loadFromCache()) {
            return;
        }
        generateReportData();
    }

    private boolean loadFromCache() {
        File cacheFile = new File(INSPECTION_CACHE);
        if (!cacheFile.exists()) return false;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(cacheFile))) {
            @SuppressWarnings("unchecked")
            List<InspectionReport> cachedData = (List<InspectionReport>) ois.readObject();
            long timestamp = ois.readLong();

            if (System.currentTimeMillis() - timestamp < 1800000) {
                reportData.addAll(cachedData);
                applyFilters();
                updateRecordCount();
                return true;
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Cache load failed: " + e.getMessage());
        }
        return false;
    }

    private void generateReportData() {
        reportData.clear();

        File dir = new File("inspections/");
        File[] files = dir.listFiles((d, name) -> name.startsWith("inspection_") && name.endsWith(".ser"));
        if (files != null && files.length > 0) {
            for (File file : files) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    InspectionReport report = (InspectionReport) ois.readObject();
                    reportData.add(report);
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Failed to load report: " + e.getMessage());
                }
            }
        }

        if (reportData.isEmpty()) {
            createSampleData();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(INSPECTION_CACHE))) {
            oos.writeObject(new ArrayList<>(reportData));
            oos.writeLong(System.currentTimeMillis());
        } catch (IOException e) {
            System.err.println("Failed to cache report data: " + e.getMessage());
        }

        applyFilters();
        updateRecordCount();
    }

    private void createSampleData() {
        String[][] reports = {
                {"R001", "VH001", "Sedan", "2026-07-01", "John Smith", "Pass"},
                {"R002", "VH002", "SUV", "2026-07-01", "Sarah Johnson", "Failed"},
                {"R003", "VH003", "Truck", "2026-07-02", "Mike Brown", "Pass"},
                {"R004", "VH004", "Sports", "2026-07-02", "Emily Davis", "Conditional Pass"},
                {"R005", "VH005", "Electric", "2026-07-03", "David Wilson", "Pass"}
        };

        for (String[] r : reports) {
            InspectionReport report = new InspectionReport(r[0], r[1], r[2], "INSP001", r[4]);
            report.setOverallStatus(r[5]);
            report.setReportDate(LocalDate.parse(r[3]));
            reportData.add(report);
        }
    }

    private void applyFilters() {
        String modelFilter = modelCombo.getValue();
        String statusFilter = statusFilterCombo.getValue();
        LocalDate dateFilter = reportDatePicker.getValue();

        ObservableList<InspectionReport> filtered = FXCollections.observableArrayList();

        for (InspectionReport report : reportData) {
            boolean matches = true;

            if (!"All Models".equals(modelFilter) && !modelFilter.equals(report.getVehicleModel())) {
                matches = false;
            }

            if (!"All Status".equals(statusFilter) && !statusFilter.equals(report.getOverallStatus())) {
                matches = false;
            }

            if (dateFilter != null && !report.getReportDate().equals(dateFilter)) {
                matches = false;
            }

            if (matches) filtered.add(report);
        }

        inspectionReportTableView.setItems(filtered);
        updateRecordCount();
    }

    private void updateRecordCount() {
        int count = inspectionReportTableView.getItems().size();
        recordCountLabel.setText("Reports: " + count);
    }

    @FXML
    public void handleExportPDF(ActionEvent actionEvent) {
        try {
            if (selectedReport == null) {
                showAlert("Selection Error", "Please select a report to export.", Alert.AlertType.WARNING);
                return;
            }

            String filename = REPORTS_DIR + "report_" + selectedReport.getReportID() + "_" +
                    System.currentTimeMillis() + ".pdf";

            try (FileWriter fw = new FileWriter(filename)) {
                fw.write("INSPECTION REPORT - PDF\n");
                fw.write("=======================\n\n");
                fw.write("Report ID: " + selectedReport.getReportID() + "\n");
                fw.write("Vehicle ID: " + selectedReport.getVehicleID() + "\n");
                fw.write("Model: " + selectedReport.getVehicleModel() + "\n");
                fw.write("Date: " + selectedReport.getReportDate() + "\n");
                fw.write("Inspector: " + selectedReport.getInspectorName() + "\n");
                fw.write("Result: " + selectedReport.getOverallStatus() + "\n");
                fw.write("Defects Found: " + selectedReport.getDefectsCount() + "\n");
                fw.write("Pass Rate: " + String.format("%.2f", selectedReport.getPassRate()) + "%\n");
                fw.write("Exported: " + LocalDate.now().toString() + "\n");
            }

            statusLabel.setText("✓ Report exported to PDF: " + filename);
            statusLabel.setStyle("-fx-text-fill: green;");

            showAlert("Success", "Report exported to PDF successfully.", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            statusLabel.setText("✗ Error: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
            showAlert("Error", "Failed to export PDF: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleViewFullReport(ActionEvent actionEvent) {
        try {
            if (selectedReport == null) {
                showAlert("Selection Error", "Please select a report to view.", Alert.AlertType.WARNING);
                return;
            }

            String filename = REPORTS_DIR + "full_report_" + selectedReport.getReportID() + ".ser";
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                oos.writeObject(selectedReport);
            }

            statusLabel.setText("✓ Full report loaded for " + selectedReport.getReportID());
            statusLabel.setStyle("-fx-text-fill: green;");

            showAlert("Full Report", "Detailed inspection report loaded.\n" +
                            "Report ID: " + selectedReport.getReportID() + "\n" +
                            "Vehicle: " + selectedReport.getVehicleID() + "\n" +
                            "Status: " + selectedReport.getOverallStatus() + "\n" +
                            "Defects: " + selectedReport.getDefectsCount() + "\n" +
                            "Pass Rate: " + String.format("%.2f", selectedReport.getPassRate()) + "%",
                    Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            statusLabel.setText("✗ Error: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
            showAlert("Error", "Failed to view full report: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleViewReport(ActionEvent actionEvent) {
        loadInspectionReport();
        statusLabel.setText("✓ Report loaded for " + reportDatePicker.getValue().toString());
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @FXML
    public void handlePrintReport(ActionEvent actionEvent) {
        try {
            if (selectedReport == null) {
                showAlert("Selection Error", "Please select a report to print.", Alert.AlertType.WARNING);
                return;
            }

            String filename = REPORTS_DIR + "print_report_" + selectedReport.getReportID() + ".txt";
            try (FileWriter fw = new FileWriter(filename)) {
                fw.write("PRINT - INSPECTION REPORT\n");
                fw.write("=========================\n\n");
                fw.write("Report ID: " + selectedReport.getReportID() + "\n");
                fw.write("Vehicle: " + selectedReport.getVehicleID() + "\n");
                fw.write("Result: " + selectedReport.getOverallStatus() + "\n");
                fw.write("Date: " + LocalDate.now().toString() + "\n");
            }

            statusLabel.setText("✓ Report sent to printer: " + filename);
            statusLabel.setStyle("-fx-text-fill: green;");

            showAlert("Success", "Report sent to printer successfully.", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            statusLabel.setText("✗ Error: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
            showAlert("Error", "Failed to print report: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleRefresh(ActionEvent actionEvent) {
        loadInspectionReport();
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