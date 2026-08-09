package Sohan.QualityControlManagerControllers;

import Sohan.ModelClasses.QualityControlManager.InspectionReport;
import Sohan.ModelClasses.QualityControlManager.VehicleQuality;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class CheckCarQualityView_Controller
{
    @FXML
    private Label modelDisplay;
    @FXML
    private ComboBox<String> safetyCombo;
    @FXML
    private TextArea notesArea;
    @FXML
    private ComboBox<String> brakesCombo;
    @FXML
    private ComboBox<String> electricalCombo;
    @FXML
    private Label productionDateDisplay;
    @FXML
    private ToggleGroup qualityStatusGroup;
    @FXML
    private Label vinDisplay;
    @FXML
    private ComboBox<String> bodyCombo;
    @FXML
    private Label statusLabel;
    @FXML
    private ComboBox<String> engineCombo;
    @FXML
    private Label carIdDisplay;
    @FXML
    private RadioButton passRadio;
    @FXML
    private TextField carIdField;
    @FXML
    private RadioButton failRadio;
    @FXML
    private ComboBox<String> paintCombo;
    @FXML
    private RadioButton conditionalRadio;

    private String currentVehicleId;
    private String currentVin;
    private static final String INSPECTIONS_DIR = "inspections/";
    private static final String QUALITY_LOG = INSPECTIONS_DIR + "quality_log.txt";
    private List<InspectionReport> inspectionReports = new ArrayList<>();

    @FXML
    public void initialize() {
        new File(INSPECTIONS_DIR).mkdirs();
        setupComboBoxes();
        clearDisplay();
        loadExistingInspections();
    }

    private void loadExistingInspections() {
        File dir = new File(INSPECTIONS_DIR);
        File[] files = dir.listFiles((d, name) -> name.startsWith("inspection_") && name.endsWith(".ser"));
        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    InspectionReport report = (InspectionReport) ois.readObject();
                    inspectionReports.add(report);
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Failed to load inspection: " + e.getMessage());
                }
            }
        }
    }

    private void setupComboBoxes() {
        String[] ratings = {"Excellent", "Good", "Satisfactory", "Needs Improvement", "Poor"};
        engineCombo.setItems(javafx.collections.FXCollections.observableArrayList(ratings));
        bodyCombo.setItems(javafx.collections.FXCollections.observableArrayList(ratings));
        paintCombo.setItems(javafx.collections.FXCollections.observableArrayList(ratings));
        brakesCombo.setItems(javafx.collections.FXCollections.observableArrayList(ratings));
        electricalCombo.setItems(javafx.collections.FXCollections.observableArrayList(ratings));
        safetyCombo.setItems(javafx.collections.FXCollections.observableArrayList(ratings));
    }

    private void clearDisplay() {
        modelDisplay.setText("");
        vinDisplay.setText("");
        productionDateDisplay.setText("");
        carIdDisplay.setText("");
        statusLabel.setText("");
        notesArea.clear();
        carIdField.clear();
        qualityStatusGroup.selectToggle(null);
        currentVehicleId = null;
        currentVin = null;
    }

    @FXML
    public void handleReset(ActionEvent actionEvent) {
        clearDisplay();
        setupComboBoxes();
    }

    @FXML
    public void handleClear(ActionEvent actionEvent) {
        clearDisplay();
    }

    @FXML
    public void handleSearchCar(ActionEvent actionEvent) {
        String carId = carIdField.getText().trim();
        if (carId.isEmpty()) {
            showAlert("Validation Error", "Please enter a Car ID.", Alert.AlertType.ERROR);
            return;
        }

        if (carId.matches("^VH\\d{3}$") || carId.matches("^V\\d{6}$")) {
            currentVehicleId = carId;
            currentVin = "VIN-" + System.currentTimeMillis();
            carIdDisplay.setText(carId);
            modelDisplay.setText("Sedan");
            vinDisplay.setText(currentVin);
            productionDateDisplay.setText(LocalDate.now().minusDays(30).toString());
            statusLabel.setText("✓ Vehicle found. Ready for quality check.");
            statusLabel.setStyle("-fx-text-fill: green;");
        } else {
            showAlert("Error", "Vehicle not found. Please check the ID.", Alert.AlertType.ERROR);
            statusLabel.setText("✗ Vehicle not found.");
            statusLabel.setStyle("-fx-text-fill: red;");
        }
    }

    @FXML
    public void handleSubmitQualityReport(ActionEvent actionEvent) {
        try {
            if (currentVehicleId == null || currentVehicleId.isEmpty()) {
                showAlert("Validation Error", "Please search for a vehicle first.", Alert.AlertType.ERROR);
                return;
            }

            String engine = engineCombo.getValue();
            String body = bodyCombo.getValue();
            String paint = paintCombo.getValue();
            String brakes = brakesCombo.getValue();
            String electrical = electricalCombo.getValue();
            String safety = safetyCombo.getValue();
            String notes = notesArea.getText().trim();

            if (engine == null || body == null || paint == null ||
                    brakes == null || electrical == null || safety == null) {
                showAlert("Validation Error", "Please rate all quality categories.", Alert.AlertType.ERROR);
                return;
            }

            RadioButton selectedStatus = (RadioButton) qualityStatusGroup.getSelectedToggle();
            if (selectedStatus == null) {
                showAlert("Validation Error", "Please select an overall quality status.", Alert.AlertType.ERROR);
                return;
            }

            String overallStatus = selectedStatus.getText();
            String reportId = "INSP-" + System.currentTimeMillis();

            InspectionReport report = new InspectionReport(reportId, currentVehicleId, "Sedan", "QC001", "QC Manager");
            report.setEngineStatus(engine);
            report.setBodyStatus(body);
            report.setPaintStatus(paint);
            report.setBrakesStatus(brakes);
            report.setElectricalStatus(electrical);
            report.setSafetyStatus(safety);
            report.setOverallStatus(overallStatus);
            report.setNotes(notes);
            report.setReportDate(LocalDate.now());

            boolean passing = "Pass".equals(overallStatus) || "Conditional Pass".equals(overallStatus);
            if (!passing) {
                report.setApproved(false);
            }

            String filename = INSPECTIONS_DIR + "inspection_" + reportId + ".ser";
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                oos.writeObject(report);
            }

            appendToQualityLog(report);
            inspectionReports.add(report);

            VehicleQuality vehicleQuality = new VehicleQuality(
                    "VQ-" + System.currentTimeMillis(),
                    currentVehicleId,
                    "Sedan",
                    currentVin,
                    "QC001",
                    "QC Manager"
            );
            vehicleQuality.setEngineStatus(engine);
            vehicleQuality.setBodyStatus(body);
            vehicleQuality.setPaintStatus(paint);
            vehicleQuality.setBrakesStatus(brakes);
            vehicleQuality.setElectricalStatus(electrical);
            vehicleQuality.setSafetyStatus(safety);
            vehicleQuality.setOverallStatus(overallStatus);
            vehicleQuality.setNotes(notes);
            vehicleQuality.setApprovedForDelivery(passing);
            vehicleQuality.calculateQualityScore();

            String vqFilename = INSPECTIONS_DIR + "vehicle_quality_" + currentVehicleId + ".ser";
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(vqFilename))) {
                oos.writeObject(vehicleQuality);
            }

            statusLabel.setText("✓ Quality report submitted successfully! Report ID: " + reportId);
            statusLabel.setStyle("-fx-text-fill: green;");

            showAlert("Success", "Quality report saved successfully.\nReport ID: " + reportId, Alert.AlertType.INFORMATION);
            handleClear(null);

        } catch (Exception e) {
            statusLabel.setText("✗ Error: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
            showAlert("Error", "An error occurred: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void appendToQualityLog(InspectionReport report) {
        try (FileWriter fw = new FileWriter(QUALITY_LOG, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(report.getReportID() + "," +
                    report.getVehicleID() + "," +
                    report.getOverallStatus() + "," +
                    report.getReportDate());
        } catch (IOException e) {
            System.err.println("Failed to append to quality log: " + e.getMessage());
        }
    }

    @FXML
    public void handleBackToDashboard(ActionEvent actionEvent) {
        Stage stage = (Stage) carIdField.getScene().getWindow();
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