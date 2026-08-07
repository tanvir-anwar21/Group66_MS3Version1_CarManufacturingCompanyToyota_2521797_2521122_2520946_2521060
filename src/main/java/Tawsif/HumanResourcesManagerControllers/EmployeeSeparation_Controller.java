package Tawsif.HumanResourcesManagerControllers;

import Tawsif.Models.EmployeeSeparation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class EmployeeSeparation_Controller {

    @FXML
    private TableView<EmployeeSeparation> separationTableView;

    @FXML
    private TableColumn<EmployeeSeparation, String> employeeIdColumn;

    @FXML
    private TableColumn<EmployeeSeparation, String> employeeNameColumn;

    @FXML
    private TableColumn<EmployeeSeparation, String> departmentColumn;

    @FXML
    private TableColumn<EmployeeSeparation, String> typeColumn;

    @FXML
    private TableColumn<EmployeeSeparation, LocalDate> lastDayColumn;

    @FXML
    private TableColumn<EmployeeSeparation, String> reasonColumn;

    @FXML
    private TableColumn<EmployeeSeparation, String> statusColumn;

    @FXML
    private TextField employeeIdField;

    @FXML
    private TextField employeeNameField;

    @FXML
    private TextField departmentField;

    @FXML
    private ComboBox<String> separationTypeComboBox;

    @FXML
    private DatePicker lastWorkingDayPicker;

    @FXML
    private TextArea reasonTextArea;

    @FXML
    private Label userLabel;

    @FXML
    private Label statusLabel;

    private ObservableList<EmployeeSeparation> separationList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        separationTypeComboBox.getItems().addAll(
                "Resignation",
                "Retirement",
                "Termination",
                "Contract End",
                "Transfer"
        );

        employeeIdColumn.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        employeeNameColumn.setCellValueFactory(new PropertyValueFactory<>("employeeName"));
        departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("separationType"));
        lastDayColumn.setCellValueFactory(new PropertyValueFactory<>("lastWorkingDay"));
        reasonColumn.setCellValueFactory(new PropertyValueFactory<>("reason"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        separationTableView.setItems(separationList);

        userLabel.setText("HR Manager");
        statusLabel.setText("Ready");
    }

    @FXML
    public void handleSearch(ActionEvent event) {

    }

    @FXML
    public void handleGenerateClearance(ActionEvent event) {

    }

    @FXML
    public void handleBack(ActionEvent event) {

    }

    @FXML
    public void handleClear(ActionEvent event) {

    }

    @FXML
    public void handleSave(ActionEvent event) {

    }

    @FXML
    public void handleRemove(ActionEvent event) {

    }

}