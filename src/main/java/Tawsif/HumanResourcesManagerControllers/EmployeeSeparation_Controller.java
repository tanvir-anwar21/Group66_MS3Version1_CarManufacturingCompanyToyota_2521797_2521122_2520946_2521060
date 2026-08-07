package Tawsif.HumanResourcesManagerControllers;

import Tawsif.Models.EmployeeSeparation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class EmployeeSeparation_Controller {

    @FXML
    private TableView<EmployeeSeparation> separationTableView;

    @FXML
    private TableColumn<EmployeeSeparation, String> employeeIdColumn;

    @FXML
    private TableColumn<EmployeeSeparation, String> employeeNameColumn;

    @FXML
    private TableColumn<EmployeeSeparation, String> departmentColumn;

    @FXML
    private TableColumn<EmployeeSeparation, String> typeColumn;

    @FXML
    private TableColumn<EmployeeSeparation, LocalDate> lastDayColumn;

    @FXML
    private TableColumn<EmployeeSeparation, String> reasonColumn;

    @FXML
    private TableColumn<EmployeeSeparation, String> statusColumn;

    @FXML
    private TextField employeeIdField;

    @FXML
    private TextField employeeNameField;

    @FXML
    private TextField departmentField;

    @FXML
    private ComboBox<String> separationTypeComboBox;

    @FXML
    private DatePicker lastWorkingDayPicker;

    @FXML
    private TextArea reasonTextArea;

    @FXML
    private Label userLabel;

    @FXML
    private Label statusLabel;

    private final ObservableList<EmployeeSeparation> separationList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        separationTypeComboBox.getItems().addAll(
                "Resignation",
                "Retirement",
                "Termination",
                "Contract End",
                "Transfer"
        );

        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        employeeNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("separationType"));
        lastDayColumn.setCellValueFactory(new PropertyValueFactory<>("lastWorkingDay"));
        reasonColumn.setCellValueFactory(new PropertyValueFactory<>("reason"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        separationTableView.setItems(separationList);

        userLabel.setText("HR Manager");
        statusLabel.setText("Employee Separation Ready");
    }

    private void showAlert(String title, String message, Alert.AlertType type) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {

        employeeIdField.clear();
        employeeNameField.clear();
        departmentField.clear();
        separationTypeComboBox.getSelectionModel().clearSelection();
        lastWorkingDayPicker.setValue(null);
        reasonTextArea.clear();
        separationTableView.getSelectionModel().clearSelection();
    }

    @FXML
    public void handleSave(ActionEvent event) {

        if (employeeIdField.getText().isEmpty()
                || employeeNameField.getText().isEmpty()
                || departmentField.getText().isEmpty()
                || separationTypeComboBox.getValue() == null
                || lastWorkingDayPicker.getValue() == null
                || reasonTextArea.getText().isEmpty()) {

            showAlert("Validation",
                    "Please complete all fields.",
                    Alert.AlertType.WARNING);
            return;
        }
        for (EmployeeSeparation record : separationList) {

            if (record.getEmployeeId().equalsIgnoreCase(employeeIdField.getText())) {

                showAlert("Duplicate Record",
                        "Employee ID already exists.",
                        Alert.AlertType.WARNING);
                return;
            }
        }

        EmployeeSeparation separation = new EmployeeSeparation(
                employeeIdField.getText(),
                employeeNameField.getText(),
                departmentField.getText(),
                separationTypeComboBox.getValue(),
                lastWorkingDayPicker.getValue(),
                reasonTextArea.getText(),
                "Pending"
        );

        separationList.add(separation);

        separationTableView.getSelectionModel().select(separation);

        statusLabel.setText("Employee Separation Saved");

        showAlert("Success",
                "Employee separation record saved successfully.",
                Alert.AlertType.INFORMATION);

        clearFields();
    }
    @FXML
    public void handleSearch(ActionEvent event) {

        String id = employeeIdField.getText().trim();

        if (id.isEmpty()) {

            showAlert("Search",
                    "Please enter an Employee ID.",
                    Alert.AlertType.WARNING);
            return;
        }

        for (EmployeeSeparation separation : separationList) {

            if (separation.getEmployeeId().equalsIgnoreCase(id)) {

                employeeNameField.setText(separation.getEmployeeName());
                departmentField.setText(separation.getDepartment());
                separationTypeComboBox.setValue(separation.getSeparationType());
                lastWorkingDayPicker.setValue(separation.getLastWorkingDay());
                reasonTextArea.setText(separation.getReason());

                separationTableView.getSelectionModel().select(separation);

                statusLabel.setText("Employee Found");

                return;
            }
        }

        showAlert("Search",
                "Employee record not found.",
                Alert.AlertType.INFORMATION);
    }

    @FXML
    public void handleRemove(ActionEvent event) {

        EmployeeSeparation separation =
                separationTableView.getSelectionModel().getSelectedItem();

        if (separation == null) {

            showAlert("Remove",
                    "Please select a record from the table.",
                    Alert.AlertType.WARNING);
            return;
        }

        separationList.remove(separation);

        clearFields();

        statusLabel.setText("Record Removed");

        showAlert("Success",
                "Employee separation record removed successfully.",
                Alert.AlertType.INFORMATION);
    }

    @FXML
    public void handleGenerateClearance(ActionEvent event) {

        EmployeeSeparation separation =
                separationTableView.getSelectionModel().getSelectedItem();

        if (separation == null) {

            showAlert("Clearance",
                    "Please select an employee first.",
                    Alert.AlertType.WARNING);
            return;
        }

        separation.setStatus("Cleared");

        separationTableView.refresh();

        statusLabel.setText("Clearance Generated");

        showAlert("Success",
                "Employee clearance generated successfully.",
                Alert.AlertType.INFORMATION);
    }

    @FXML
    public void handleClear(ActionEvent event) {

        clearFields();

        statusLabel.setText("Fields Cleared");
    }

    @FXML
    public void handleBack(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/HumanResourcesManager/HumanResourcesManagerDashboardView.fxml"
                    )
            );

            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) statusLabel.getScene().getWindow();

            stage.setScene(scene);
            stage.setTitle("Human Resources Manager Dashboard");
            stage.show();

        } catch (IOException e) {

            showAlert("Navigation Error",
                    "Unable to open Human Resources Dashboard.",
                    Alert.AlertType.ERROR);

            e.printStackTrace();
        }
    }
}