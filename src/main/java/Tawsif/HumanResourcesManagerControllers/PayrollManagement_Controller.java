package Tawsif.HumanResourcesManagerControllers;

import Tawsif.Models.Payroll;
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
        statusLabel.setText("Payroll Management Ready");
    }

    @FXML
    public void handleSearch(ActionEvent event) {

        String payrollId = payrollIdField.getText().trim();

        if (payrollId.isEmpty()) {
            statusLabel.setText("Enter Payroll ID.");
            return;
        }

        for (Payroll payroll : payrollList) {

            if (payroll.getPayrollId().equalsIgnoreCase(payrollId)) {

                employeeIdField.setText(payroll.getEmployeeId());
                employeeNameField.setText(payroll.getEmployeeName());
                basicSalaryField.setText(String.valueOf(payroll.getBasicSalary()));
                bonusField.setText(String.valueOf(payroll.getBonus()));
                deductionField.setText(String.valueOf(payroll.getDeduction()));
                netSalaryField.setText(String.valueOf(payroll.getNetSalary()));
                paymentDatePicker.setValue(payroll.getPaymentDate());

                payrollTableView.getSelectionModel().select(payroll);

                statusLabel.setText("Payroll Record Found.");
                return;
            }
        }

        statusLabel.setText("Payroll Record Not Found.");
    }

    @FXML
    public void handleCalculate(ActionEvent event) {

        try {

            double basic = Double.parseDouble(basicSalaryField.getText());
            double bonus = Double.parseDouble(bonusField.getText());
            double deduction = Double.parseDouble(deductionField.getText());

            double netSalary = basic + bonus - deduction;

            netSalaryField.setText(String.format("%.2f", netSalary));

            statusLabel.setText("Salary Calculated.");

        } catch (NumberFormatException e) {

            statusLabel.setText("Please enter valid salary values.");
        }
    }

    @FXML
    public void handleSave(ActionEvent event) {

        try {

            if (payrollIdField.getText().isEmpty()
                    || employeeIdField.getText().isEmpty()
                    || employeeNameField.getText().isEmpty()
                    || basicSalaryField.getText().isEmpty()
                    || bonusField.getText().isEmpty()
                    || deductionField.getText().isEmpty()
                    || netSalaryField.getText().isEmpty()
                    || paymentDatePicker.getValue() == null) {

                statusLabel.setText("Please complete all fields.");
                return;
            }

            Payroll payroll = new Payroll(
                    payrollIdField.getText(),
                    employeeIdField.getText(),
                    employeeNameField.getText(),
                    Double.parseDouble(basicSalaryField.getText()),
                    Double.parseDouble(bonusField.getText()),
                    Double.parseDouble(deductionField.getText()),
                    Double.parseDouble(netSalaryField.getText()),
                    paymentDatePicker.getValue(),
                    "Paid"
            );

            payrollList.add(payroll);

            payrollTableView.refresh();

            double total = 0;

            for (Payroll p : payrollList) {
                total += p.getNetSalary();
            }

            totalPayrollLabel.setText(String.format("%.2f", total));
            paidEmployeesLabel.setText(String.valueOf(payrollList.size()));

            statusLabel.setText("Payroll Saved Successfully.");

            handleClear(null);

        } catch (NumberFormatException e) {

            statusLabel.setText("Invalid numeric values.");
        }
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

        payrollTableView.getSelectionModel().clearSelection();

        statusLabel.setText("Fields Cleared.");
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
            stage.setTitle("Human Resources Dashboard");
            stage.show();

        } catch (IOException e) {

            statusLabel.setText("Unable to open Dashboard.");
            e.printStackTrace();
        }
    }
}