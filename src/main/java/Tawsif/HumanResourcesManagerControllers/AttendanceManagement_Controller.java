package Tawsif.HumanResourcesManagerControllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
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

    private final ObservableList<Attendance> attendanceList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        System.out.println("Initialize is running");

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

        userLabel.setText("HR Manager");
        attendanceDatePicker.setValue(LocalDate.now());

        updateStatistics();

        statusLabel.setText("Attendance system ready.");
    }
    private void updateStatistics() {

        int present = 0;
        int absent = 0;
        int late = 0;

        for (Attendance attendance : attendanceList) {

            if (attendance.getStatus().equalsIgnoreCase("Present")) {
                present++;
            }

            else if (attendance.getStatus().equalsIgnoreCase("Absent")) {
                absent++;
            }

            else if (attendance.getStatus().equalsIgnoreCase("Late")) {
                late++;
            }

        }

        presentCountLabel.setText(String.valueOf(present));
        absentCountLabel.setText(String.valueOf(absent));
        lateCountLabel.setText(String.valueOf(late));
    }

    private void clearFields() {

        employeeIdField.clear();

        departmentComboBox.getSelectionModel().clearSelection();

        designationComboBox.getSelectionModel().clearSelection();

        attendanceStatusComboBox.getSelectionModel().clearSelection();

        attendanceDatePicker.setValue(LocalDate.now());

    }

    private void showAlert(String title,
                           String message,
                           Alert.AlertType type) {

        Alert alert = new Alert(type);

        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }
        @FXML
        public void handleSearch(ActionEvent event) {

            try {

                String employeeId = employeeIdField.getText().trim();

                if (employeeId.isEmpty()) {
                    showAlert("Validation Error",
                            "Please enter an Employee ID.",
                            Alert.AlertType.WARNING);
                    return;
                }

                Attendance found = null;

                for (Attendance attendance : attendanceList) {

                    if (attendance.getEmployeeId().equalsIgnoreCase(employeeId)) {
                        found = attendance;
                        break;
                    }

                }

                if (found == null) {

                    statusLabel.setText("Employee not found.");

                    showAlert(
                            "Search",
                            "No attendance record found.",
                            Alert.AlertType.INFORMATION
                    );

                    return;

                }

                employeeIdField.setText(found.getEmployeeId());

                departmentComboBox.setValue(found.getDepartment());

                attendanceDatePicker.setValue(found.getDate());

                attendanceStatusComboBox.setValue(found.getStatus());

                statusLabel.setText("Attendance record found.");

            }

            catch (Exception e) {

                showAlert(
                        "Error",
                        "Unable to search attendance.",
                        Alert.AlertType.ERROR
                );

            }

        }

        @FXML
        public void handleMarkAttendance(ActionEvent event) {

            try {

                if (employeeIdField.getText().trim().isEmpty()
                        || departmentComboBox.getValue() == null
                        || attendanceStatusComboBox.getValue() == null
                        || attendanceDatePicker.getValue() == null) {

                    showAlert(
                            "Validation",
                            "Please complete all required fields.",
                            Alert.AlertType.WARNING
                    );

                    return;

                }

                String employeeId = employeeIdField.getText().trim();

                String employeeName = "Employee " + employeeId;

                String department = departmentComboBox.getValue();

                LocalDate date = attendanceDatePicker.getValue();

                String status = attendanceStatusComboBox.getValue();

                LocalTime checkIn = null;
                LocalTime checkOut = null;
                double hoursWorked = 0;

                if (status.equalsIgnoreCase("Present")) {

                    checkIn = LocalTime.of(9, 0);
                    checkOut = LocalTime.of(17, 0);
                    hoursWorked = 8;

                }

                else if (status.equalsIgnoreCase("Late")) {

                    checkIn = LocalTime.of(10, 0);
                    checkOut = LocalTime.of(17, 0);
                    hoursWorked = 7;

                }

                Attendance attendance = new Attendance(

                        "ATT-" + (attendanceList.size() + 1),

                        employeeId,

                        employeeName,

                        department,

                        date,

                        checkIn,

                        checkOut,

                        hoursWorked,

                        status

                );

                attendanceList.add(attendance);

                attendanceTableView.refresh();

                updateStatistics();

                statusLabel.setText("Attendance marked successfully.");

                showAlert(
                        "Success",
                        "Attendance has been recorded.",
                        Alert.AlertType.INFORMATION
                );

                clearFields();

            }

            catch (Exception e) {

                showAlert(
                        "Error",
                        "Unable to mark attendance.",
                        Alert.AlertType.ERROR
                );

            }

        }
        @FXML
        public void handleUpdate(ActionEvent event) {

            try {

                Attendance selectedAttendance =
                        attendanceTableView.getSelectionModel().getSelectedItem();

                if (selectedAttendance == null) {

                    showAlert(
                            "Update",
                            "Please select an attendance record first.",
                            Alert.AlertType.WARNING
                    );

                    return;
                }

                if (attendanceStatusComboBox.getValue() == null
                        || attendanceDatePicker.getValue() == null
                        || departmentComboBox.getValue() == null) {

                    showAlert(
                            "Validation",
                            "Complete all required fields.",
                            Alert.AlertType.WARNING
                    );

                    return;
                }

                selectedAttendance.setDepartment(
                        departmentComboBox.getValue());

                selectedAttendance.setDate(
                        attendanceDatePicker.getValue());

                selectedAttendance.setStatus(
                        attendanceStatusComboBox.getValue());

                if (attendanceStatusComboBox.getValue().equalsIgnoreCase("Present")) {

                    selectedAttendance.setCheckIn(LocalTime.of(9, 0));
                    selectedAttendance.setCheckOut(LocalTime.of(17, 0));
                    selectedAttendance.setHoursWorked(8);

                }

                else if (attendanceStatusComboBox.getValue().equalsIgnoreCase("Late")) {

                    selectedAttendance.setCheckIn(LocalTime.of(10, 0));
                    selectedAttendance.setCheckOut(LocalTime.of(17, 0));
                    selectedAttendance.setHoursWorked(7);

                }

                else {

                    selectedAttendance.setCheckIn(null);
                    selectedAttendance.setCheckOut(null);
                    selectedAttendance.setHoursWorked(0);

                }

                attendanceTableView.refresh();

                updateStatistics();

                statusLabel.setText("Attendance updated successfully.");

                showAlert(
                        "Success",
                        "Attendance record updated successfully.",
                        Alert.AlertType.INFORMATION
                );

                clearFields();

            }

            catch (Exception e) {

                showAlert(
                        "Error",
                        "Unable to update attendance.",
                        Alert.AlertType.ERROR
                );

            }

        }

        @FXML
        public void handleClear(ActionEvent event) {

            clearFields();

            attendanceTableView.getSelectionModel().clearSelection();

            statusLabel.setText("Form cleared.");

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

            }
            catch (IOException e) {
            showAlert(
                    "Navigation Error",
                    "Unable to return to HR Dashboard.",
                    Alert.AlertType.ERROR
            );

            e.printStackTrace();
        }
    }



    }
