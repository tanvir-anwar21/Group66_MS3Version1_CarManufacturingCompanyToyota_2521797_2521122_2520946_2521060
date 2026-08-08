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

public class EmployeeRegistration_Controller {

    @FXML
    private Label userLabel;

    @FXML
    private Label statusLabel;

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
    private DatePicker joiningDatePicker;

    @FXML
    private ComboBox<String> departmentComboBox;

    @FXML
    private ComboBox<String> designationComboBox;

    @FXML
    private Button registerButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button deleteButton;

    @FXML
    private Button clearButton;

    @FXML
    private TableView<Employee> employeeTableView;

    @FXML
    private TableColumn<Employee, String> idColumn;

    @FXML
    private TableColumn<Employee, String> nameColumn;

    @FXML
    private TableColumn<Employee, String> departmentColumn;

    @FXML
    private TableColumn<Employee, String> designationColumn;

    @FXML
    private TableColumn<Employee, String> phoneColumn;

    @FXML
    private TableColumn<Employee, Double> salaryColumn;

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

        idColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        designationColumn.setCellValueFactory(new PropertyValueFactory<>("designation"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salary"));

        employeeTableView.setItems(employeeList);

        userLabel.setText("HR Manager");
        statusLabel.setText("Employee Registration Ready");
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

        joiningDatePicker.setValue(null);

        departmentComboBox.getSelectionModel().clearSelection();
        designationComboBox.getSelectionModel().clearSelection();

        employeeTableView.getSelectionModel().clearSelection();
    }

    @FXML
    public void handleRegister(ActionEvent event) {

        try {

            if (employeeIdField.getText().isEmpty()
                    || employeeNameField.getText().isEmpty()
                    || emailField.getText().isEmpty()
                    || phoneField.getText().isEmpty()
                    || salaryField.getText().isEmpty()
                    || joiningDatePicker.getValue() == null
                    || departmentComboBox.getValue() == null
                    || designationComboBox.getValue() == null) {

                showAlert("Validation",
                        "Please complete all fields.",
                        Alert.AlertType.WARNING);
                return;
            }

            double salary = Double.parseDouble(salaryField.getText());

            Employee employee = new Employee(
                    employeeIdField.getText(),
                    employeeNameField.getText(),
                    departmentComboBox.getValue(),
                    designationComboBox.getValue(),
                    phoneField.getText(),
                    emailField.getText(),
                    Double.parseDouble(salaryField.getText()),
                    joiningDatePicker.getValue()
            );

            employeeList.add(employee);

            employeeTableView.refresh();

            statusLabel.setText("Employee Registered Successfully");

            showAlert("Success",
                    "Employee registered successfully.",
                    Alert.AlertType.INFORMATION);

            clearFields();

        } catch (NumberFormatException e) {

            showAlert("Error",
                    "Salary must be numeric.",
                    Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleSearch(ActionEvent event) {

        String id = employeeIdField.getText().trim();

        if (id.isEmpty()) {

            showAlert("Search",
                    "Enter Employee ID.",
                    Alert.AlertType.WARNING);
            return;
        }

        for (Employee employee : employeeList) {

            if (employee.getEmployeeId().equalsIgnoreCase(id)) {

                employeeNameField.setText(employee.getEmployeeName());
                emailField.setText(employee.getEmail());
                phoneField.setText(employee.getPhone());
                salaryField.setText(String.valueOf(employee.getSalary()));
                joiningDatePicker.setValue(employee.getJoiningDate());
                departmentComboBox.setValue(employee.getDepartment());
                designationComboBox.setValue(employee.getDesignation());

                employeeTableView.getSelectionModel().select(employee);

                statusLabel.setText("Employee Found");

                return;
            }
        }

        showAlert("Search",
                "Employee not found.",
                Alert.AlertType.INFORMATION);
    }

    @FXML
    public void handleDelete(ActionEvent event) {

        Employee employee =
                employeeTableView.getSelectionModel().getSelectedItem();

        if (employee == null) {

            showAlert("Delete",
                    "Select an employee first.",
                    Alert.AlertType.WARNING);
            return;
        }

        employeeList.remove(employee);

        employeeTableView.refresh();

        clearFields();

        statusLabel.setText("Employee Deleted");

        showAlert("Success",
                "Employee deleted successfully.",
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
            stage.show();

        } catch (IOException e) {

            showAlert("Navigation Error",
                    "Unable to return to HR Dashboard.",
                    Alert.AlertType.ERROR);

            e.printStackTrace();
        }
    }
}