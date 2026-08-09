package Sohan.QualityControlManagerControllers;

import Sohan.ModelClasses.QualityControlManager.VehicleQuality;
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

public class ApproveCarsForDeliveryView_Controller
{
    @FXML
    private Label recordCountLabel;
    @FXML
    private RadioButton approvedRadio;
    @FXML
    private RadioButton approveRadio;
    @FXML
    private ToggleGroup approvalFilterGroup;
    @FXML
    private TextArea commentsArea;
    @FXML
    private Label statusLabel;
    @FXML
    private RadioButton allVehiclesRadio;
    @FXML
    private RadioButton pendingApprovalRadio;
    @FXML
    private RadioButton rejectRadio;
    @FXML
    private Label selectedVehicleLabel;
    @FXML
    private RadioButton holdRadio;
    @FXML
    private ComboBox<String> modelFilterCombo;
    @FXML
    private TextField batchIdField;
    @FXML
    private TableView<VehicleQuality> vehicleTableView;
    @FXML
    private ToggleGroup approvalActionGroup;

    private ObservableList<VehicleQuality> vehicleData = FXCollections.observableArrayList();
    private VehicleQuality selectedVehicle;
    private static final String APPROVALS_DIR = "approvals/";
    private static final String APPROVAL_LOG = APPROVALS_DIR + "approval_log.txt";

    @FXML
    public void initialize() {
        new File(APPROVALS_DIR).mkdirs();
        setupTable();
        setupComboBoxes();
        setupToggleGroups();
        loadVehicleData();
        updateRecordCount();
    }

    private void setupTable() {
        TableColumn<VehicleQuality, String> idCol = new TableColumn<>("Vehicle ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("vehicleID"));

        TableColumn<VehicleQuality, String> modelCol = new TableColumn<>("Model");
        modelCol.setCellValueFactory(new PropertyValueFactory<>("vehicleModel"));

        TableColumn<VehicleQuality, String> statusCol = new TableColumn<>("Status");
        statusCol.setCellValueFactory(new PropertyValueFactory<>("overallStatus"));

        TableColumn<VehicleQuality, String> qualityCol = new TableColumn<>("Quality Status");
        qualityCol.setCellValueFactory(new PropertyValueFactory<>("approvalStatus"));

        TableColumn<VehicleQuality, LocalDate> dateCol = new TableColumn<>("Inspection Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("inspectionDate"));

        TableColumn<VehicleQuality, String> scoreCol = new TableColumn<>("Score");
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("qualityScore"));

        vehicleTableView.getColumns().addAll(idCol, modelCol, statusCol, qualityCol, dateCol, scoreCol);
        vehicleTableView.setItems(vehicleData);

        vehicleTableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    selectedVehicle = newVal;
                    if (newVal != null) {
                        selectedVehicleLabel.setText("Selected: " + newVal.getVehicleID() + " - " + newVal.getVehicleModel());
                    }
                });
    }

    private void setupComboBoxes() {
        modelFilterCombo.setItems(FXCollections.observableArrayList(
                "All Models", "Sedan", "SUV", "Truck", "Sports", "Electric"
        ));
        modelFilterCombo.setValue("All Models");
    }

    private void setupToggleGroups() {
        approvalFilterGroup.selectToggle(allVehiclesRadio);
        approvalActionGroup.selectToggle(approveRadio);
    }

    private void loadVehicleData() {
        vehicleData.clear();

        File dir = new File("inspections/");
        File[] files = dir.listFiles((d, name) -> name.startsWith("vehicle_quality_") && name.endsWith(".ser"));
        if (files != null && files.length > 0) {
            for (File file : files) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    VehicleQuality vq = (VehicleQuality) ois.readObject();
                    vehicleData.add(vq);
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Failed to load vehicle: " + e.getMessage());
                }
            }
            applyFilter();
            return;
        }

        createSampleData();
        applyFilter();
    }

    private void createSampleData() {
        String[][] vehicles = {
                {"VH001", "Sedan", "Pass", "Pending", "2026-07-01", "A"},
                {"VH002", "SUV", "Pass", "Pending", "2026-07-02", "B"},
                {"VH003", "Truck", "Pass", "Approved", "2026-07-03", "A"},
                {"VH004", "Sports", "Conditional Pass", "Pending", "2026-07-04", "C"},
                {"VH005", "Electric", "Fail", "Rejected", "2026-07-05", "F"},
                {"VH006", "Sedan", "Pass", "Pending", "2026-07-06", "B"},
                {"VH007", "SUV", "Pass", "Approved", "2026-07-07", "A"}
        };

        for (String[] v : vehicles) {
            VehicleQuality vq = new VehicleQuality(
                    "VQ-" + System.currentTimeMillis() + (int)(Math.random() * 100),
                    v[0],
                    v[1],
                    "VIN-" + v[0],
                    "QC001",
                    "QC Manager"
            );
            vq.setOverallStatus(v[2]);
            vq.setApprovalStatus(v[3]);
            vq.setInspectionDate(LocalDate.parse(v[4]));
            vq.setQualityScore(v[5]);
            vq.setApprovedForDelivery("Approved".equals(v[3]));
            vehicleData.add(vq);
        }

        for (VehicleQuality vq : vehicleData) {
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream("inspections/vehicle_quality_" + vq.getVehicleID() + ".ser"))) {
                oos.writeObject(vq);
            } catch (IOException e) {
                System.err.println("Failed to save vehicle: " + e.getMessage());
            }
        }
    }

    private void applyFilter() {
        String filter = ((RadioButton) approvalFilterGroup.getSelectedToggle()).getText();
        String modelFilter = modelFilterCombo.getValue();

        ObservableList<VehicleQuality> filtered = FXCollections.observableArrayList();

        for (VehicleQuality vq : vehicleData) {
            boolean matches = true;

            if ("Approved".equals(filter) && !vq.isApprovedForDelivery()) {
                matches = false;
            } else if ("Pending".equals(filter) && !"Pending".equals(vq.getApprovalStatus())) {
                matches = false;
            }

            if (!"All Models".equals(modelFilter) && !modelFilter.equals(vq.getVehicleModel())) {
                matches = false;
            }

            if (matches) filtered.add(vq);
        }

        vehicleTableView.setItems(filtered);
        updateRecordCount();
    }

    private void updateRecordCount() {
        int count = vehicleTableView.getItems().size();
        recordCountLabel.setText("Vehicles: " + count);
    }

    @FXML
    public void handleSearch(ActionEvent actionEvent) {
        applyFilter();
        statusLabel.setText("✓ Filter applied.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @FXML
    public void handleSubmitApproval(ActionEvent actionEvent) {
        try {
            if (selectedVehicle == null) {
                showAlert("Selection Error", "Please select a vehicle from the table.", Alert.AlertType.WARNING);
                return;
            }

            String action = ((RadioButton) approvalActionGroup.getSelectedToggle()).getText();
            String comments = commentsArea.getText().trim();

            if (comments.isEmpty()) {
                showAlert("Validation Error", "Please enter comments for the approval decision.", Alert.AlertType.ERROR);
                return;
            }

            boolean approved = "Approve".equals(action);
            selectedVehicle.setApprovedForDelivery(approved);
            selectedVehicle.setApprovalStatus(approved ? "Approved" : action);
            selectedVehicle.setNotes(comments);

            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream("inspections/vehicle_quality_" + selectedVehicle.getVehicleID() + ".ser"))) {
                oos.writeObject(selectedVehicle);
            }

            Map<String, Object> approval = new HashMap<>();
            String approvalId = "APP-" + System.currentTimeMillis();
            approval.put("approvalId", approvalId);
            approval.put("vehicleId", selectedVehicle.getVehicleID());
            approval.put("vehicleModel", selectedVehicle.getVehicleModel());
            approval.put("action", action);
            approval.put("comments", comments);
            approval.put("approvedBy", "QC Manager");
            approval.put("approvalDate", LocalDate.now().toString());

            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(APPROVALS_DIR + "approval_" + approvalId + ".ser"))) {
                oos.writeObject(approval);
            }

            appendToApprovalLog(approval);

            vehicleTableView.refresh();
            applyFilter();

            statusLabel.setText("✓ Vehicle " + selectedVehicle.getVehicleID() + " " + action + " successfully!");
            statusLabel.setStyle("-fx-text-fill: green;");
            commentsArea.clear();

            showAlert("Success", "Vehicle " + selectedVehicle.getVehicleID() + " has been " + action + ".",
                    Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            statusLabel.setText("✗ Error: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
            showAlert("Error", "An error occurred: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void appendToApprovalLog(Map<String, Object> approval) {
        try (FileWriter fw = new FileWriter(APPROVAL_LOG, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(approval.get("approvalId") + "," +
                    approval.get("vehicleId") + "," +
                    approval.get("action") + "," +
                    approval.get("approvalDate"));
        } catch (IOException e) {
            System.err.println("Failed to append to approval log: " + e.getMessage());
        }
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