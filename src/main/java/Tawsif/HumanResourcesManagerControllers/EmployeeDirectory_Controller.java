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
import java.time.LocalDate;

public class EmployeeDirectory_Controller {

    @FXML
    private TableView<Employee> employeeDirectoryTableView;

    @FXML
    private TableColumn<Employee, String> employeeIdColumn;

    @FXML
    private TableColumn<Employee, String> employeeNameColumn;

    @FXML
    private TableColumn<Employee, String> departmentColumn;

    @FXML
    private TableColumn<Employee, String> designationColumn;

    @FXML
    private TableColumn<Employee, String> phoneColumn;

    @FXML
    private TableColumn<Employee, String> emailColumn;

    @FXML
    private TableColumn<Employee, LocalDate> joiningDateColumn;

    @FXML
    private TextField employeeIdField;

    @FXML
    private TextField employeeNameField;

    @FXML
    private ComboBox<String> departmentComboBox;

    @FXML
    private ComboBox<String> designationComboBox;

    @FXML
    private Label totalEmployeesLabel;

    @FXML
    private Label departmentCountLabel;

    @FXML
    private Label userLabel;

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
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        joiningDateColumn.setCellValueFactory(new PropertyValueFactory<>("joiningDate"));

        employeeDirectoryTableView.setItems(employeeList);

        userLabel.setText("HR Manager");
        statusLabel.setText("Employee Directory Ready");

        updateStatistics();
    }

    private void updateStatistics() {

        totalEmployeesLabel.setText(String.valueOf(employeeList.size()));

        long count = employeeList.stream()
                .map(Employee::getDepartment)
                .distinct()
                .count();

        departmentCountLabel.setText(String.valueOf(count));
    }

    private void clearFields() {

        employeeIdField.clear();
        employeeNameField.clear();

        departmentComboBox.getSelectionModel().clearSelection();
        designationComboBox.getSelectionModel().clearSelection();

        employeeDirectoryTableView.getSelectionModel().clearSelection();
    }

    private void showAlert(String title, String message, Alert.AlertType type) {

        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
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

                employeeNameField.setText(employee.getEmployeeName());
                departmentComboBox.setValue(employee.getDepartment());
                designationComboBox.setValue(employee.getDesignation());

                employeeDirectoryTableView.getSelectionModel().select(employee);

                statusLabel.setText("Employee Found");

                return;
            }
        }

        showAlert("Search", "Employee not found.", Alert.AlertType.INFORMATION);
        statusLabel.setText("Employee Not Found");
    }

    @FXML
    public void handleViewDetails(ActionEvent event) {

        Employee employee = employeeDirectoryTableView.getSelectionModel().getSelectedItem();

        if (employee == null) {

            showAlert("Employee Details",
                    "Please select an employee from the table.",
                    Alert.AlertType.WARNING);

            return;
        }

        String details =
                "Employee ID : " + employee.getEmployeeId() +
                        "\nName : " + employee.getEmployeeName() +
                        "\nDepartment : " + employee.getDepartment() +
                        "\nDesignation : " + employee.getDesignation() +
                        "\nPhone : " + employee.getPhone() +
                        "\nEmail : " + employee.getEmail() +
                        "\nJoining Date : " + employee.getJoiningDate();

        showAlert("Employee Details", details, Alert.AlertType.INFORMATION);
    }

    @FXML
    public void handleRefresh(ActionEvent event) {

        employeeDirectoryTableView.refresh();

        updateStatistics();

        statusLabel.setText("Directory Refreshed");
    }

    @FXML
    public void handleClear(ActionEvent event) {

        clearFields();

        statusLabel.setText("Fields Cleared");
    }

    @FXML
    public void handleBack(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/HumanResourcesManager/HumanResourcesManagerDashboardView.fxml"
            ));

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