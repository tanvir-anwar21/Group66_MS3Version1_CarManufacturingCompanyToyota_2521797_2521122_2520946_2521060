package Tawsif.HumanResourcesManagerControllers;

import Tawsif.Models.LeaveManagement;
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
import java.time.temporal.ChronoUnit;

public class LeaveManagement_Controller {

    @FXML
    private Label userLabel;

    @FXML
    private TextField employeeIdField;

    @FXML
    private TextField employeeNameField;

    @FXML
    private ComboBox<String> leaveTypeComboBox;

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private DatePicker toDatePicker;

    @FXML
    private TextField reasonField;

    @FXML
    private TableView<LeaveManagement> leaveTableView;

    @FXML
    private TableColumn<LeaveManagement, String> employeeIdColumn;

    @FXML
    private TableColumn<LeaveManagement, String> employeeNameColumn;

    @FXML
    private TableColumn<LeaveManagement, String> leaveTypeColumn;

    @FXML
    private TableColumn<LeaveManagement, LocalDate> fromDateColumn;

    @FXML
    private TableColumn<LeaveManagement, LocalDate> toDateColumn;

    @FXML
    private TableColumn<LeaveManagement, Integer> daysColumn;

    @FXML
    private TableColumn<LeaveManagement, String> reasonColumn;

    @FXML
    private TableColumn<LeaveManagement, String> statusColumn;

    @FXML
    private Label statusLabel;

    private final ObservableList<LeaveManagement> leaveList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        leaveTypeComboBox.getItems().addAll(
                "Casual Leave",
                "Sick Leave",
                "Annual Leave",
                "Maternity Leave",
                "Emergency Leave",
                "Unpaid Leave"
        );

        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        employeeNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        leaveTypeColumn.setCellValueFactory(new PropertyValueFactory<>("leaveType"));
        fromDateColumn.setCellValueFactory(new PropertyValueFactory<>("fromDate"));
        toDateColumn.setCellValueFactory(new PropertyValueFactory<>("toDate"));
        daysColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfDays"));
        reasonColumn.setCellValueFactory(new PropertyValueFactory<>("reason"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        leaveTableView.setItems(leaveList);

        userLabel.setText("HR Manager");
        statusLabel.setText("Leave Management Ready");
    }

    private void clearFields() {

        employeeIdField.clear();
        employeeNameField.clear();
        reasonField.clear();

        leaveTypeComboBox.getSelectionModel().clearSelection();

        fromDatePicker.setValue(null);
        toDatePicker.setValue(null);

        leaveTableView.getSelectionModel().clearSelection();
    }

    @FXML
    public void handleSearch(ActionEvent event) {

        String id = employeeIdField.getText().trim();

        if (id.isEmpty()) {
            statusLabel.setText("Enter Employee ID.");
            return;
        }

        for (LeaveManagement leave : leaveList) {

            if (leave.getEmployeeId().equalsIgnoreCase(id)) {

                employeeNameField.setText(leave.getEmployeeName());
                leaveTypeComboBox.setValue(leave.getLeaveType());
                fromDatePicker.setValue(leave.getFromDate());
                toDatePicker.setValue(leave.getToDate());
                reasonField.setText(leave.getReason());

                leaveTableView.getSelectionModel().select(leave);

                statusLabel.setText("Leave Record Found.");
                return;
            }
        }

        statusLabel.setText("Leave Record Not Found.");
    }

    @FXML
    public void handleApprove(ActionEvent event) {

        LeaveManagement leave =
                leaveTableView.getSelectionModel().getSelectedItem();

        if (leave == null) {
            statusLabel.setText("Select a leave request.");
            return;
        }

        leave.setStatus("Approved");

        leaveTableView.refresh();

        statusLabel.setText("Leave Approved.");
    }

    @FXML
    public void handleReject(ActionEvent event) {

        LeaveManagement leave =
                leaveTableView.getSelectionModel().getSelectedItem();

        if (leave == null) {
            statusLabel.setText("Select a leave request.");
            return;
        }

        leave.setStatus("Rejected");

        leaveTableView.refresh();

        statusLabel.setText("Leave Rejected.");
    }

    @FXML
    public void handleClear(ActionEvent event) {

        clearFields();

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

    /*
        Sample method to add leave requests.
        You can call this method later from another screen.
    */
    public void addLeaveRequest(String employeeId,
                                String employeeName,
                                String leaveType,
                                LocalDate fromDate,
                                LocalDate toDate,
                                String reason) {

        int days = (int) ChronoUnit.DAYS.between(fromDate, toDate) + 1;

        LeaveManagement leave = new LeaveManagement(
                "L" + (leaveList.size() + 1),
                employeeId,
                employeeName,
                leaveType,
                fromDate,
                toDate,
                days,
                reason,
                "Pending"
        );

        leaveList.add(leave);

        leaveTableView.refresh();
    }
}