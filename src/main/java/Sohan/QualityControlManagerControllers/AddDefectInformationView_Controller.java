package Sohan.QualityControlManagerControllers;

import Sohan.ModelClasses.QualityControlManager.DefectRecord;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class AddDefectInformationView_Controller
{
    @FXML
    private RadioButton lowSeverityRadio;
    @FXML
    private ComboBox<String> statusCombo;
    @FXML
    private ComboBox<String> defectCategoryCombo;
    @FXML
    private DatePicker detectionDatePicker;
    @FXML
    private TextField vehicleIdField;
    @FXML
    private TextField partInfoField;
    @FXML
    private RadioButton mediumSeverityRadio;
    @FXML
    private Label statusLabel;
    @FXML
    private ToggleGroup severityGroup;
    @FXML
    private CheckBox urgentCheckBox;
    @FXML
    private TextArea defectDescriptionArea;
    @FXML
    private RadioButton criticalSeverityRadio;
    @FXML
    private TextField detectedByField;
    @FXML
    private RadioButton highSeverityRadio;

    private static final String DEFECTS_DIR = "defects/";
    private static final String DEFECT_LOG = DEFECTS_DIR + "defect_log.txt";
    private List<DefectRecord> defectRecords = new ArrayList<>();

    @FXML
    public void initialize() {
        new File(DEFECTS_DIR).mkdirs();
        setupComboBoxes();
        setDefaultValues();
        loadExistingDefects();
    }

    private void loadExistingDefects() {
        File dir = new File(DEFECTS_DIR);
        File[] files = dir.listFiles((d, name) -> name.startsWith("defect_") && name.endsWith(".ser"));
        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    DefectRecord defect = (DefectRecord) ois.readObject();
                    defectRecords.add(defect);
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Failed to load defect: " + e.getMessage());
                }
            }
        }
    }

    private void setupComboBoxes() {
        statusCombo.setItems(javafx.collections.FXCollections.observableArrayList(
                "Open", "In Progress", "Under Review", "Fixed", "Closed"
        ));
        statusCombo.setValue("Open");

        defectCategoryCombo.setItems(javafx.collections.FXCollections.observableArrayList(
                "Engine", "Transmission", "Brakes", "Electrical",
                "Suspension", "Body", "Paint", "Interior", "Safety"
        ));
        defectCategoryCombo.setValue("Engine");
    }

    private void setDefaultValues() {
        detectionDatePicker.setValue(LocalDate.now());
        severityGroup.selectToggle(lowSeverityRadio);
    }

    @FXML
    public void handleClear(ActionEvent actionEvent) {
        vehicleIdField.clear();
        partInfoField.clear();
        defectDescriptionArea.clear();
        detectedByField.clear();
        defectCategoryCombo.setValue("Engine");
        statusCombo.setValue("Open");
        detectionDatePicker.setValue(LocalDate.now());
        urgentCheckBox.setSelected(false);
        severityGroup.selectToggle(lowSeverityRadio);
        statusLabel.setText("");
    }

    @FXML
    public void handleAddDefect(ActionEvent actionEvent) {
        try {
            String vehicleId = vehicleIdField.getText().trim();
            String partInfo = partInfoField.getText().trim();
            String description = defectDescriptionArea.getText().trim();
            String detectedBy = detectedByField.getText().trim();
            String category = defectCategoryCombo.getValue();
            String status = statusCombo.getValue();
            LocalDate detectionDate = detectionDatePicker.getValue();

            if (vehicleId.isEmpty() || partInfo.isEmpty() || description.isEmpty() ||
                    detectedBy.isEmpty() || category == null || status == null || detectionDate == null) {
                showAlert("Validation Error", "Please fill in all required fields.", Alert.AlertType.ERROR);
                return;
            }

            RadioButton selectedSeverity = (RadioButton) severityGroup.getSelectedToggle();
            String severity = selectedSeverity != null ? selectedSeverity.getText() : "Low";

            String defectId = "DEF-" + System.currentTimeMillis();

            DefectRecord defect = new DefectRecord(defectId, vehicleId, "Unknown", category, partInfo, description);
            defect.setSeverity(severity);
            defect.setDetectedBy(detectedBy);
            defect.setDetectionDate(detectionDate);
            defect.setStatus(status);
            defect.setUrgent(urgentCheckBox.isSelected());
            defect.setReportedBy("QC Manager");
            defect.setProductionLine("Line A");

            if (severity.equals("Critical")) {
                defect.setPriority("High");
            } else if (severity.equals("High")) {
                defect.setPriority("High");
            } else if (severity.equals("Medium")) {
                defect.setPriority("Medium");
            } else {
                defect.setPriority("Low");
            }

            String filename = DEFECTS_DIR + "defect_" + defectId + ".ser";
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                oos.writeObject(defect);
            }

            appendToDefectLog(defect);
            defectRecords.add(defect);

            statusLabel.setText("✓ Defect added successfully! ID: " + defectId);
            statusLabel.setStyle("-fx-text-fill: green;");

            showAlert("Success", "Defect information saved successfully.\nDefect ID: " + defectId, Alert.AlertType.INFORMATION);
            handleClear(null);

        } catch (Exception e) {
            statusLabel.setText("✗ Error: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
            showAlert("Error", "An error occurred: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void appendToDefectLog(DefectRecord defect) {
        try (FileWriter fw = new FileWriter(DEFECT_LOG, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(defect.getDefectID() + "," +
                    defect.getVehicleID() + "," +
                    defect.getCategory() + "," +
                    defect.getSeverity() + "," +
                    defect.getStatus() + "," +
                    defect.getDetectionDate());
        } catch (IOException e) {
            System.err.println("Failed to append to defect log: " + e.getMessage());
        }
    }

    @FXML
    public void handleBackToDashboard(ActionEvent actionEvent) {
        Stage stage = (Stage) vehicleIdField.getScene().getWindow();
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