package Tawsif.HumanResourcesManagerControllers;

import Tawsif.Models.Attendance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.LocalTime;

public class AttendanceManagement_Controller {

    @FXML
    private TableView<Attendance> attendanceTableView;

    @FXML
    private TableColumn<Attendance, String> employeeIdColumn;

    @FXML
    private TableColumn<Attendance, String> employeeNameColumn;

    @FXML
    private TableColumn<Attendance, String> departmentColumn;

    @FXML
    private TableColumn<Attendance, LocalDate> dateColumn;

    @FXML
    private TableColumn<Attendance, LocalTime> checkInColumn;

    @FXML
    private TableColumn<Attendance, LocalTime> checkOutColumn;

    @FXML
    private TableColumn<Attendance, Double> hoursWorkedColumn;

    @FXML
    private TableColumn<Attendance, String> statusColumn;

    @FXML
    private TextField employeeIdField;

    @FXML
    private ComboBox<String> departmentComboBox;

    @FXML
    private ComboBox<String> designationComboBox;

    @FXML
    private ComboBox<String> attendanceStatusComboBox;

    @FXML
    private DatePicker attendanceDatePicker;

    @FXML
    private Label presentCountLabel;

    @FXML
    private Label absentCountLabel;

    @FXML
    private Label lateCountLabel;

    @FXML
    private Label userLabel;

    @FXML
    private Label statusLabel;

    private ObservableList<Attendance> attendanceList =
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

        attendanceStatusComboBox.getItems().addAll(
                "Present",
                "Absent",
                "Late",
                "Leave"
        );

        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        employeeNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        checkInColumn.setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        checkOutColumn.setCellValueFactory(new PropertyValueFactory<>("checkOut"));
        hoursWorkedColumn.setCellValueFactory(new PropertyValueFactory<>("hoursWorked"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        attendanceTableView.setItems(attendanceList);

        presentCountLabel.setText("0");
        absentCountLabel.setText("0");
        lateCountLabel.setText("0");

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
    public void handleUpdate(ActionEvent event) {

    }

    @FXML
    public void handleMarkAttendance(ActionEvent event) {

    }

}