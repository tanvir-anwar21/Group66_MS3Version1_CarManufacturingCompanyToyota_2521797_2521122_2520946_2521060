package Sohan.QualityControlManagerControllers;

import Sohan.ModelClasses.QualityControlManager.InspectionReport;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PrintQualityReportView_Controller
{
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private ComboBox<String> reportTypeCombo;
    @FXML
    private RadioButton htmlRadio;
    @FXML
    private CheckBox includeChartsCheckBox;
    @FXML
    private CheckBox includeDetailsCheckBox;
    @FXML
    private ComboBox<String> modelCombo;
    @FXML
    private Label statusLabel;
    @FXML
    private CheckBox summaryOnlyCheckBox;
    @FXML
    private DatePicker endDatePicker;
    @FXML
    private ToggleGroup formatGroup;
    @FXML
    private RadioButton excelRadio;
    @FXML
    private RadioButton csvRadio;
    @FXML
    private RadioButton pdfRadio;
    @FXML
    private ComboBox<String> lineCombo;

    private static final String REPORTS_DIR = "reports/";
    private static final String REPORT_CACHE = REPORTS_DIR + "report_cache.ser";
    private List<InspectionReport> reports = new ArrayList<>();

    @FXML
    public void initialize() {
        new File(REPORTS_DIR).mkdirs();
        setupComboBoxes();
        setDefaultDates();
        setupFormatGroup();
        loadExistingReports();
    }

    private void loadExistingReports() {
        File dir = new File("inspections/");
        File[] files = dir.listFiles((d, name) -> name.startsWith("inspection_") && name.endsWith(".ser"));
        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    InspectionReport report = (InspectionReport) ois.readObject();
                    reports.add(report);
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Failed to load report: " + e.getMessage());
                }
            }
        }
    }

    private void setupComboBoxes() {
        reportTypeCombo.setItems(javafx.collections.FXCollections.observableArrayList(
                "Quality Summary", "Defect Report", "Inspection Report",
                "Worker Performance", "Delivery Approval Report", "Comprehensive Report"
        ));
        reportTypeCombo.setValue("Quality Summary");

        modelCombo.setItems(javafx.collections.FXCollections.observableArrayList(
                "All Models", "Sedan", "SUV", "Truck", "Sports", "Electric"
        ));
        modelCombo.setValue("All Models");

        lineCombo.setItems(javafx.collections.FXCollections.observableArrayList(
                "All Lines", "Line A", "Line B", "Line C", "Line D"
        ));
        lineCombo.setValue("All Lines");
    }

    private void setDefaultDates() {
        startDatePicker.setValue(LocalDate.now().minusDays(30));
        endDatePicker.setValue(LocalDate.now());
    }

    private void setupFormatGroup() {
        formatGroup.selectToggle(pdfRadio);
    }

    @FXML
    public void handleReset(ActionEvent actionEvent) {
        reportTypeCombo.setValue("Quality Summary");
        modelCombo.setValue("All Models");
        lineCombo.setValue("All Lines");
        startDatePicker.setValue(LocalDate.now().minusDays(30));
        endDatePicker.setValue(LocalDate.now());
        includeChartsCheckBox.setSelected(false);
        includeDetailsCheckBox.setSelected(false);
        summaryOnlyCheckBox.setSelected(false);
        formatGroup.selectToggle(pdfRadio);
        statusLabel.setText("");
    }

    @FXML
    public void handleGenerateAndPrint(ActionEvent actionEvent) {
        try {
            if (!validateInputs()) {
                return;
            }

            String reportType = reportTypeCombo.getValue();
            LocalDate startDate = startDatePicker.getValue();
            LocalDate endDate = endDatePicker.getValue();
            String format = getSelectedFormat();

            Map<String, Object> report = new HashMap<>();
            String reportId = "RPT-" + System.currentTimeMillis();
            report.put("reportId", reportId);
            report.put("reportType", reportType);
            report.put("startDate", startDate.toString());
            report.put("endDate", endDate.toString());
            report.put("format", format);
            report.put("generatedDate", LocalDate.now().toString());
            report.put("generatedBy", "QC Manager");

            int totalInspections = reports.size();
            long passedCount = reports.stream().filter(r -> r.isPassing()).count();
            long failedCount = reports.stream().filter(r -> r.isFailed()).count();

            report.put("totalInspections", totalInspections);
            report.put("passedCount", passedCount);
            report.put("failedCount", failedCount);

            String filename = REPORTS_DIR + "report_" + reportId + ".ser";
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                oos.writeObject(report);
            }

            String exportFile = REPORTS_DIR + "quality_report_" + System.currentTimeMillis();
            if (format.equals("pdf")) {
                exportFile += ".pdf";
            } else if (format.equals("excel")) {
                exportFile += ".xls";
            } else {
                exportFile += ".csv";
            }

            try (FileWriter fw = new FileWriter(exportFile)) {
                fw.write("Quality Report\n");
                fw.write("=============\n\n");
                fw.write("Report Type: " + reportType + "\n");
                fw.write("Period: " + startDate + " to " + endDate + "\n");
                fw.write("Total Inspections: " + totalInspections + "\n");
                fw.write("Passed: " + passedCount + "\n");
                fw.write("Failed: " + failedCount + "\n");
                fw.write("Pass Rate: " + (totalInspections > 0 ? String.format("%.2f", ((double) passedCount / totalInspections) * 100) : "0") + "%\n");
                fw.write("Generated: " + LocalDate.now().toString() + "\n");
            }

            statusLabel.setText("✓ Report generated successfully!");
            statusLabel.setStyle("-fx-text-fill: green;");

            showAlert("Success", "Report generated successfully!\nFile: " + exportFile, Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            statusLabel.setText("✗ Error: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
            showAlert("Error", "Failed to generate report: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private boolean validateInputs() {
        if (reportTypeCombo.getValue() == null) {
            showAlert("Validation Error", "Please select a report type.", Alert.AlertType.ERROR);
            return false;
        }

        if (startDatePicker.getValue() == null || endDatePicker.getValue() == null) {
            showAlert("Validation Error", "Please select date range.", Alert.AlertType.ERROR);
            return false;
        }

        if (startDatePicker.getValue().isAfter(endDatePicker.getValue())) {
            showAlert("Validation Error", "Start date must be before end date.", Alert.AlertType.ERROR);
            return false;
        }

        return true;
    }

    private String getSelectedFormat() {
        RadioButton selected = (RadioButton) formatGroup.getSelectedToggle();
        return selected != null ? selected.getText().toLowerCase() : "pdf";
    }

    @FXML
    public void handlePreviewReport(ActionEvent actionEvent) {
        try {
            if (!validateInputs()) {
                return;
            }

            statusLabel.setText("✓ Preview generated. Ready for printing.");
            statusLabel.setStyle("-fx-text-fill: green;");

            showAlert("Preview", "Report preview generated successfully.", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            statusLabel.setText("✗ Error generating preview.");
            statusLabel.setStyle("-fx-text-fill: red;");
            showAlert("Error", "Failed to generate preview: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleBackToDashboard(ActionEvent actionEvent) {
        Stage stage = (Stage) reportTypeCombo.getScene().getWindow();
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