package Sohan.QualityControlManagerControllers;

import Sohan.ModelClasses.QualityControlManager.WorkerPerformance;
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

public class ReviewWorkerPerformanceView_Controller
{
    @FXML
    private Label recordCountLabel;
    @FXML
    private RadioButton topPerformersRadio;
    @FXML
    private RadioButton needsImprovementRadio;
    @FXML
    private TableView<WorkerPerformance> performanceTableView;
    @FXML
    private TextField workerIdField;
    @FXML
    private Label performanceRatingLabel;
    @FXML
    private TextField workerNameField;
    @FXML
    private RadioButton allWorkersRadio;
    @FXML
    private Label selectedWorkerLabel;
    @FXML
    private TextArea feedbackArea;
    @FXML
    private Label statusLabel;
    @FXML
    private ToggleGroup workerFilterGroup;

    private ObservableList<WorkerPerformance> performanceData = FXCollections.observableArrayList();
    private WorkerPerformance selectedWorker;
    private static final String PERFORMANCE_DIR = "performance/";
    private static final String FEEDBACK_LOG = PERFORMANCE_DIR + "feedback_log.txt";

    @FXML
    public void initialize() {
        new File(PERFORMANCE_DIR).mkdirs();
        setupTable();
        setupToggleGroups();
        loadPerformanceData();
        updateRecordCount();
    }

    private void setupTable() {
        TableColumn<WorkerPerformance, String> idCol = new TableColumn<>("Worker ID");
        idCol.setCellValueFactory(new PropertyValueFactory<>("workerID"));

        TableColumn<WorkerPerformance, String> nameCol = new TableColumn<>("Name");
        nameCol.setCellValueFactory(new PropertyValueFactory<>("workerName"));

        TableColumn<WorkerPerformance, String> deptCol = new TableColumn<>("Department");
        deptCol.setCellValueFactory(new PropertyValueFactory<>("department"));

        TableColumn<WorkerPerformance, Integer> defectsCol = new TableColumn<>("Defects");
        defectsCol.setCellValueFactory(new PropertyValueFactory<>("defectsFound"));

        TableColumn<WorkerPerformance, Integer> scoreCol = new TableColumn<>("Quality Score");
        scoreCol.setCellValueFactory(new PropertyValueFactory<>("qualityScore"));

        TableColumn<WorkerPerformance, String> ratingCol = new TableColumn<>("Rating");
        ratingCol.setCellValueFactory(new PropertyValueFactory<>("performanceRating"));

        performanceTableView.getColumns().addAll(idCol, nameCol, deptCol, defectsCol, scoreCol, ratingCol);
        performanceTableView.setItems(performanceData);

        performanceTableView.getSelectionModel().selectedItemProperty().addListener(
                (obs, oldVal, newVal) -> {
                    selectedWorker = newVal;
                    if (newVal != null) {
                        selectedWorkerLabel.setText("Selected: " + newVal.getWorkerID() + " - " + newVal.getWorkerName());
                        workerNameField.setText(newVal.getWorkerName());
                        performanceRatingLabel.setText("Rating: " + newVal.getPerformanceRating());
                    }
                });
    }

    private void setupToggleGroups() {
        workerFilterGroup.selectToggle(allWorkersRadio);
    }

    private void loadPerformanceData() {
        performanceData.clear();

        File dir = new File(PERFORMANCE_DIR);
        File[] files = dir.listFiles((d, name) -> name.startsWith("performance_") && name.endsWith(".ser"));
        if (files != null && files.length > 0) {
            for (File file : files) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    WorkerPerformance wp = (WorkerPerformance) ois.readObject();
                    performanceData.add(wp);
                } catch (IOException | ClassNotFoundException e) {
                    System.err.println("Failed to load performance: " + e.getMessage());
                }
            }
            applyFilter();
            return;
        }

        createSampleData();
        applyFilter();
    }

    private void createSampleData() {
        String[][] workers = {
                {"W001", "John Smith", "Assembly", "Assembly Worker", "2", "95", "Excellent"},
                {"W002", "Sarah Johnson", "Paint Shop", "Painter", "5", "88", "Good"},
                {"W003", "Mike Brown", "Engine Plant", "Engineer", "8", "82", "Good"},
                {"W004", "Emily Davis", "Quality Control", "Inspector", "1", "97", "Excellent"},
                {"W005", "David Wilson", "Body Shop", "Welder", "12", "72", "Average"},
                {"W006", "Lisa Anderson", "Maintenance", "Technician", "3", "92", "Excellent"},
                {"W007", "Robert Taylor", "Assembly", "Supervisor", "15", "65", "Needs Improvement"},
                {"W008", "Maria Garcia", "Paint Shop", "Painter", "9", "78", "Average"},
                {"W009", "James Martinez", "Logistics", "Coordinator", "6", "85", "Good"},
                {"W010", "Patricia Lee", "Quality Control", "Inspector", "4", "90", "Excellent"}
        };

        for (String[] w : workers) {
            WorkerPerformance wp = new WorkerPerformance(w[0], w[1], w[2], w[3]);
            wp.setDefectsFound(Integer.parseInt(w[4]));
            wp.setQualityScore(Integer.parseInt(w[5]));
            wp.setPerformanceRating(w[6]);
            wp.setProductsChecked(100 + (int)(Math.random() * 50));
            wp.setReviewDate(LocalDate.now().minusDays((int)(Math.random() * 30)));
            wp.setAccuracyRate(80 + Math.random() * 19);
            wp.setEfficiencyRate(75 + Math.random() * 24);
            performanceData.add(wp);

            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(PERFORMANCE_DIR + "performance_" + w[0] + ".ser"))) {
                oos.writeObject(wp);
            } catch (IOException e) {
                System.err.println("Failed to save performance: " + e.getMessage());
            }
        }
    }

    private void applyFilter() {
        String filter = ((RadioButton) workerFilterGroup.getSelectedToggle()).getText();
        ObservableList<WorkerPerformance> filtered = FXCollections.observableArrayList();

        for (WorkerPerformance wp : performanceData) {
            boolean matches = true;

            if ("Top Performers".equals(filter) && !wp.isTopPerformer()) {
                matches = false;
            } else if ("Needs Improvement".equals(filter) && !wp.needsImprovement()) {
                matches = false;
            }

            String workerId = workerIdField.getText().trim();
            if (!workerId.isEmpty() && !wp.getWorkerID().contains(workerId)) {
                matches = false;
            }

            if (matches) filtered.add(wp);
        }

        performanceTableView.setItems(filtered);
        updateRecordCount();
    }

    private void updateRecordCount() {
        int count = performanceTableView.getItems().size();
        recordCountLabel.setText("Workers: " + count);
    }

    @FXML
    public void handleSearch(ActionEvent actionEvent) {
        applyFilter();
        statusLabel.setText("✓ Search applied.");
        statusLabel.setStyle("-fx-text-fill: green;");
    }

    @FXML
    public void handleViewFullReport(ActionEvent actionEvent) {
        try {
            if (selectedWorker == null) {
                showAlert("Selection Error", "Please select a worker from the table.", Alert.AlertType.WARNING);
                return;
            }

            String filename = PERFORMANCE_DIR + "full_report_" + selectedWorker.getWorkerID() + "_" +
                    System.currentTimeMillis() + ".ser";
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
                oos.writeObject(selectedWorker);
            }

            statusLabel.setText("✓ Full report generated for " + selectedWorker.getWorkerName());
            statusLabel.setStyle("-fx-text-fill: green;");

            showAlert("Success", "Full performance report generated for " +
                    selectedWorker.getWorkerName(), Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            statusLabel.setText("✗ Error: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
            showAlert("Error", "Failed to generate report: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleAddFeedback(ActionEvent actionEvent) {
        try {
            if (selectedWorker == null) {
                showAlert("Selection Error", "Please select a worker from the table.", Alert.AlertType.WARNING);
                return;
            }

            String feedback = feedbackArea.getText().trim();
            if (feedback.isEmpty()) {
                showAlert("Validation Error", "Please enter feedback.", Alert.AlertType.ERROR);
                return;
            }

            selectedWorker.addFeedbackComment(feedback);

            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(PERFORMANCE_DIR + "performance_" + selectedWorker.getWorkerID() + ".ser"))) {
                oos.writeObject(selectedWorker);
            }

            appendToFeedbackLog(selectedWorker, feedback);

            statusLabel.setText("✓ Feedback added for " + selectedWorker.getWorkerName());
            statusLabel.setStyle("-fx-text-fill: green;");
            feedbackArea.clear();

            showAlert("Success", "Feedback added successfully for " +
                    selectedWorker.getWorkerName(), Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            statusLabel.setText("✗ Error: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
            showAlert("Error", "Failed to add feedback: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void appendToFeedbackLog(WorkerPerformance worker, String feedback) {
        try (FileWriter fw = new FileWriter(FEEDBACK_LOG, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(worker.getWorkerID() + "," +
                    worker.getWorkerName() + "," +
                    feedback.replace(",", ";") + "," +
                    LocalDate.now().toString());
        } catch (IOException e) {
            System.err.println("Failed to append to feedback log: " + e.getMessage());
        }
    }

    @FXML
    public void handlePrintReport(ActionEvent actionEvent) {
        try {
            if (selectedWorker == null) {
                showAlert("Selection Error", "Please select a worker from the table.", Alert.AlertType.WARNING);
                return;
            }

            String filename = PERFORMANCE_DIR + "performance_report_" +
                    selectedWorker.getWorkerID() + "_" + System.currentTimeMillis() + ".txt";

            try (FileWriter fw = new FileWriter(filename)) {
                fw.write(selectedWorker.getPerformanceSummary());
            }

            statusLabel.setText("✓ Report printed: " + filename);
            statusLabel.setStyle("-fx-text-fill: green;");

            showAlert("Success", "Performance report printed successfully.", Alert.AlertType.INFORMATION);

        } catch (Exception e) {
            statusLabel.setText("✗ Error: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red;");
            showAlert("Error", "Failed to print report: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleSaveFeedback(ActionEvent actionEvent) {
        handleAddFeedback(actionEvent);
    }

    @FXML
    public void handleViewAll(ActionEvent actionEvent) {
        workerIdField.clear();
        allWorkersRadio.setSelected(true);
        applyFilter();
        statusLabel.setText("✓ Showing all workers.");
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