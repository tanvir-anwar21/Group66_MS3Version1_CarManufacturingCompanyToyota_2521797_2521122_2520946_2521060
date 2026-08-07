package Tawsif.HumanResourcesManagerControllers;

import Tawsif.Models.LeaveManagement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

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

    private ObservableList<LeaveManagement> leaveList =
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
    public void handleApprove(ActionEvent event) {

    }

    @FXML
    public void handleReject(ActionEvent event) {

    }

}