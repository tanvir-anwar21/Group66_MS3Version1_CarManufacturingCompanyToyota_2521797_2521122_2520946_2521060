package Tawsif.HumanResourcesManagerControllers;

import Tawsif.Models.Employee;
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

public class UpdateEmployeeInformation_Controller {

    @FXML
    private Label userLabel;

    @FXML
    private TextField employeeIdField;

    @FXML
    private TextField employeeNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField salaryField;

    @FXML
    private ComboBox<String> departmentComboBox;

    @FXML
    private ComboBox<String> designationComboBox;

    @FXML
    private TableView<Employee> employeeTableView;

    @FXML
    private TableColumn<Employee, String> employeeIdColumn;

    @FXML
    private TableColumn<Employee, String> employeeNameColumn;

    @FXML
    private TableColumn<Employee, String> departmentColumn;

    @FXML
    private TableColumn<Employee, String> designationColumn;

    @FXML
    private TableColumn<Employee, String> emailColumn;

    @FXML
    private TableColumn<Employee, String> phoneColumn;

    @FXML
    private TableColumn<Employee, Double> salaryColumn;

    @FXML
    private Label statusLabel;

    private final ObservableList<Employee> employeeList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        departmentComboBox.getItems().addAll(
                "Executive Office",
                "Production",
                "Sales",
                "Human Resources",
                "Engineering",
                "Quality Control",
                "Procurement"
        );

        designationComboBox.getItems().addAll(
                "Chief Executive Officer",
                "Production Manager",
                "Sales Executive",
                "Human Resources Manager",
                "Automotive Engineer",
                "Vehicle Inspector",
                "Quality Control Manager",
                "Procurement Officer"
        );

        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        employeeNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        designationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        employeeTableView.setItems(employeeList);

        userLabel.setText("HR Manager");
        statusLabel.setText("Ready");

        employeeTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldEmployee, employee) -> {

            if (employee != null) {

                employeeIdField.setText(employee.getEmployeeId());
                employeeNameField.setText(employee.getEmployeeName());
                emailField.setText(employee.getEmail());
                phoneField.setText(employee.getPhone());
                salaryField.setText(String.valueOf(employee.getSalary()));

                departmentComboBox.setValue(employee.getDepartment());
                designationComboBox.setValue(employee.getDesignation());
            }

        });
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
        emailField.clear();
        phoneField.clear();
        salaryField.clear();

        departmentComboBox.getSelectionModel().clearSelection();
        designationComboBox.getSelectionModel().clearSelection();

        employeeTableView.getSelectionModel().clearSelection();

    }
    @FXML
    public void handleSearch(ActionEvent event) {

        String id = employeeIdField.getText().trim();

        if (id.isEmpty()) {
            showAlert("Search", "Please enter Employee ID.", Alert.AlertType.WARNING);
            return;
        }

        for (Employee employee : employeeList) {

            if (employee.getEmployeeId().equalsIgnoreCase(id)) {

                employeeTableView.getSelectionModel().select(employee);

                employeeNameField.setText(employee.getEmployeeName());
                emailField.setText(employee.getEmail());
                phoneField.setText(employee.getPhone());
                salaryField.setText(String.valueOf(employee.getSalary()));

                departmentComboBox.setValue(employee.getDepartment());
                designationComboBox.setValue(employee.getDesignation());

                statusLabel.setText("Employee Found");

                return;
            }
        }

        showAlert("Search", "Employee not found.", Alert.AlertType.INFORMATION);
    }
    @FXML
    public void handleUpdate(ActionEvent event) {

        Employee employee =
                employeeTableView.getSelectionModel().getSelectedItem();

        if (employee == null) {

            showAlert("Update",
                    "Please search or select an employee first.",
                    Alert.AlertType.WARNING);
            return;
        }

        try {

            if (employeeNameField.getText().isEmpty()
                    || emailField.getText().isEmpty()
                    || phoneField.getText().isEmpty()
                    || salaryField.getText().isEmpty()
                    || departmentComboBox.getValue() == null
                    || designationComboBox.getValue() == null) {

                showAlert("Validation",
                        "Please complete all fields.",
                        Alert.AlertType.WARNING);
                return;
            }

            employee.setEmployeeName(employeeNameField.getText());
            employee.setEmail(emailField.getText());
            employee.setPhone(phoneField.getText());
            employee.setDepartment(departmentComboBox.getValue());
            employee.setDesignation(designationComboBox.getValue());
            employee.setSalary(Double.parseDouble(salaryField.getText()));

            employeeTableView.refresh();

            statusLabel.setText("Employee Updated Successfully");

            showAlert("Success",
                    "Employee information updated successfully.",
                    Alert.AlertType.INFORMATION);

        } catch (NumberFormatException e) {

            showAlert("Error",
                    "Salary must be numeric.",
                    Alert.AlertType.ERROR);
        }
    }
    @FXML
    public void handleRefresh(ActionEvent event) {

        employeeTableView.refresh();

        statusLabel.setText("Table Refreshed");
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
            stage.show();

        } catch (IOException e) {

            showAlert("Navigation Error",
                    "Unable to return to HR Dashboard.",
                    Alert.AlertType.ERROR);

            e.printStackTrace();
        }
    }
}