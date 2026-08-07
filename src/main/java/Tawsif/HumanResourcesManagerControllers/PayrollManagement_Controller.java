package Tawsif.HumanResourcesManagerControllers;

import Tawsif.Models.Payroll;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class PayrollManagement_Controller {

    @FXML
    private Label userLabel;

    @FXML
    private TextField payrollIdField;

    @FXML
    private TextField employeeIdField;

    @FXML
    private TextField employeeNameField;

    @FXML
    private TextField basicSalaryField;

    @FXML
    private TextField bonusField;

    @FXML
    private TextField deductionField;

    @FXML
    private TextField netSalaryField;

    @FXML
    private DatePicker paymentDatePicker;

    @FXML
    private TableView<Payroll> payrollTableView;

    @FXML
    private TableColumn<Payroll, String> payrollIdColumn;

    @FXML
    private TableColumn<Payroll, String> employeeIdColumn;

    @FXML
    private TableColumn<Payroll, String> employeeNameColumn;

    @FXML
    private TableColumn<Payroll, Double> basicSalaryColumn;

    @FXML
    private TableColumn<Payroll, Double> bonusColumn;

    @FXML
    private TableColumn<Payroll, Double> deductionColumn;

    @FXML
    private TableColumn<Payroll, Double> netSalaryColumn;

    @FXML
    private TableColumn<Payroll, LocalDate> paymentDateColumn;

    @FXML
    private TableColumn<Payroll, String> statusColumn;

    @FXML
    private Label totalPayrollLabel;

    @FXML
    private Label paidEmployeesLabel;

    @FXML
    private Label statusLabel;

    private final ObservableList<Payroll> payrollList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        payrollIdColumn.setCellValueFactory(new PropertyValueFactory<>("payrollId"));
        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        employeeNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        basicSalaryColumn.setCellValueFactory(new PropertyValueFactory<>("basicSalary"));
        bonusColumn.setCellValueFactory(new PropertyValueFactory<>("bonus"));
        deductionColumn.setCellValueFactory(new PropertyValueFactory<>("deduction"));
        netSalaryColumn.setCellValueFactory(new PropertyValueFactory<>("netSalary"));
        paymentDateColumn.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        payrollTableView.setItems(payrollList);

        userLabel.setText("HR Manager");
        totalPayrollLabel.setText("0.00");
        paidEmployeesLabel.setText("0");
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

        payrollIdField.clear();
        employeeIdField.clear();
        employeeNameField.clear();
        basicSalaryField.clear();
        bonusField.clear();
        deductionField.clear();
        netSalaryField.clear();
        paymentDatePicker.setValue(null);

        statusLabel.setText("Fields cleared.");
    }

    @FXML
    public void handleCalculate(ActionEvent event) {

        try {

            double basic = Double.parseDouble(basicSalaryField.getText());
            double bonus = Double.parseDouble(bonusField.getText());
            double deduction = Double.parseDouble(deductionField.getText());

            double netSalary = basic + bonus - deduction;

            netSalaryField.setText(String.format("%.2f", netSalary));

            statusLabel.setText("Salary calculated.");

        } catch (NumberFormatException e) {

            statusLabel.setText("Please enter valid salary values.");

        }
    }

    @FXML
    public void handleSave(ActionEvent event) {

    }

}