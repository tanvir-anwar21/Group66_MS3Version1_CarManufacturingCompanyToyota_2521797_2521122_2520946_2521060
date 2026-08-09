package Sohan.QualityControlManagerControllers;

import Sohan.ModelClasses.QualityControlManager.ReInspectionSchedule;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ScheduleReinspectionView_Controller
{
    @FXML
    private DatePicker reInspectionDatePicker;
    @FXML
    private TextArea notesArea;
    @FXML
    private RadioButton mediumPriorityRadio;
    @FXML
    private RadioButton lowPriorityRadio;
    @FXML
    private ToggleGroup priorityGroup;
    @FXML
    private DatePicker previousInspectionDate;
    @FXML
    private ComboBox<String> reasonCombo;
    @FXML
    private TextField vehicleIdField;
    @FXML
    private RadioButton highPriorityRadio;
    @FXML
    private ComboBox<String> inspectorCombo;
    @FXML
    private Label statusLabel;

    private static final String REINSPECTIONS_DIR = "reinspections/";
    private static final String REINSPECTION_LOG = REINSPECTIONS_DIR + "reinspection_log.txt";
    private List<ReInspectionSchedule> schedules = new ArrayList<>();

    @FXML
    public void initialize() {
        new File(REINSPECTIONS_DIR).mkdirs();
        setupComboBoxes();
        setDefaultValues();
        loadExistingSchedules();
    }

    private void loadExistingSchedules() {
        File dir = new File(REINSPECTIONS_DIR);
        File[] files = dir.listFiles((d, name) -> name.startsWith("reinspection_") && name.endsWith(".ser"));
        if (files != null) {
            for (File file : files) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    ReInspectionSchedule schedule = (ReInspectionSchedule) ois.readObject();
                    schedules.add(schedule);
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Failed to load schedule: " + e.getMessage());
                }
            }
        }
    }

    private void setupComboBoxes() {
        reasonCombo.setItems(javafx.collections.FXCollections.observableArrayList(
                "Failed Initial Inspection", "Customer Complaint",
                "Quality Audit", "Random Check", "Maintenance Verification", "Recall Notice"
        ));
        reasonCombo.setValue("Failed Initial Inspection");

        inspectorCombo.setItems(javafx.collections.FXCollections.observableArrayList(
                "John Smith (Level 3)", "Sarah Johnson (Level 2)",
                "Mike Brown (Level 3)", "Emily Davis (Level 1)", "David Wilson (Level 2)"
        ));
        inspectorCombo.setValue("John Smith (Level 3)");
    }

    private void setDefaultValues() {
        reInspectionDatePicker.setValue(LocalDate.now().plusDays(3));
        previousInspectionDate.setValue(LocalDate.now().minusDays(7));
        priorityGroup.selectToggle(mediumPriorityRadio);
    }

    @FXML
    public void handleClear(ActionEvent actionEvent) {
        vehicleIdField.clear();
        notesArea.clear();
        reasonCombo.setValue("Failed Initial Inspection");
        inspectorCombo.setValue("John Smith (Level 3)");
        reInspectionDatePicker.setValue(LocalDate.now().plusDays(3));
        previousInspectionDate.setValue(LocalDate.now().minusDays(7));
        priorityGroup.selectToggle(mediumPriorityRadio);
        statusLabel.setText("");
    }

    @FXML
    public void handleScheduleReInspection(ActionEvent actionEvent) {
        try {
            String vehicleId = vehicleIdField.getText().trim();
            LocalDate reInspectionDate = reInspectionDatePicker.getValue();
            String reason = reasonCombo.getValue();
            String inspector = inspectorCombo.getValue();
            String notes = notesArea.getText().trim();

            if (vehicleId.isEmpty() || reInspectionDate == null ||
                    reason == null || inspector == null) {
                showAlert("Validation Error", "Please fill in all required fields.", Alert.AlertType.ERROR);
                return;
            }

            if (!vehicleId.matches("^VH\\d{3}$") && !vehicleId.matches("^V\\d{6}$")) {
                showAlert("Validation Error", "Invalid Vehicle ID format. Use VH### or V######.", Alert.AlertType.ERROR);
                return;
            }

            if (reInspectionDate.isBefore(LocalDate.now())) {
                showAlert("Validation Error", "Re-inspection date must be today or in the future.", Alert.AlertType.ERROR);
                return;
            }

            RadioButton selectedPriority = (RadioButton) priorityGroup.getSelectedToggle();
            String priority = selectedPriority != null ? selectedPriority.getText() : "Medium";

            String scheduleId = "RS-" + System.currentTimeMillis();

            ReInspectionSchedule schedule = new ReInspectionSchedule(
                    scheduleId, vehicleId, "Unknown", reInspectionDate, reason, "INSP001"
            );
            schedule.setPriority(priority);
            schedule.setNotes(notes);
            schedule.setAssignedInspectorName(inspector);
            schedule.setPreviousInspectionDate(previousInspectionDate.getValue());
            schedule.setStatus("Scheduled");

            String filename = REINSPECTIONS_DIR + "reinspection_" + scheduleId + ".ser";
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                oos.writeObject(schedule);
            }

            appendToReinspectionLog(schedule);
            schedules.add(schedule);

            statusLabel.setText("✓ Re-inspection scheduled successfully! ID: " + scheduleId);
            statusLabel.setStyle("-fx-text-fill: green;");

            showAlert("Success", "Re-inspection scheduled successfully.\n" +
                    "Vehicle: " + vehicleId + "\n" +
                    "Date: " + reInspectionDate.format(DateTimeFormatter.ofPattern("MMM d, yyyy")) + "\n" +
                    "Inspector: " + inspector, Alert.AlertType.INFORMATION);

            handleClear(null);

        } catch (Exception e) {
            statusLabel.setText("✗ Error: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
            showAlert("Error", "Failed to schedule re-inspection: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void appendToReinspectionLog(ReInspectionSchedule schedule) {
        try (FileWriter fw = new FileWriter(REINSPECTION_LOG, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(schedule.getScheduleID() + "," +
                    schedule.getVehicleID() + "," +
                    schedule.getReInspectionDate() + "," +
                    schedule.getStatus() + "," +
                    schedule.getScheduledDate());
        } catch (IOException e) {
            System.err.println("Failed to append to re-inspection log: " + e.getMessage());
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