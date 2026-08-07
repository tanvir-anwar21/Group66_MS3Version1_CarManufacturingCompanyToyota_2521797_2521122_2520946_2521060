package Tawsif.HumanResourcesManagerControllers;

import Tawsif.Models.Employee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

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

    private ObservableList<Employee> employeeList =
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
        statusLabel.setText("Ready");
    }

    @FXML
    public void handleSearch(ActionEvent event) {

    }

    @FXML
    public void handleBack(ActionEvent event) {

    }

    @FXML
    public void handleClear(ActionEvent event) {

    }

    @FXML
    public void handleRegister(ActionEvent event) {

    }

    @FXML
    public void handleDelete(ActionEvent event) {

    }

}