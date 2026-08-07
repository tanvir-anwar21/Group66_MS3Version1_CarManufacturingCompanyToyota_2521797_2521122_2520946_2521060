package Tawsif.HumanResourcesManagerControllers;

import Tawsif.Models.HRReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class HumanResourcesReports_Controller {

    @FXML
    private Label userLabel;

    @FXML
    private ComboBox<String> reportTypeComboBox;

    @FXML
    private DatePicker reportDatePicker;

    @FXML
    private TextField generatedByField;

    @FXML
    private TableView<HRReport> reportTableView;

    @FXML
    private TableColumn<HRReport, String> reportIdColumn;

    @FXML
    private TableColumn<HRReport, String> reportTypeColumn;

    @FXML
    private TableColumn<HRReport, LocalDate> reportDateColumn;

    @FXML
    private TableColumn<HRReport, String> generatedByColumn;

    @FXML
    private Label totalReportsLabel;

    @FXML
    private Label statusLabel;

    private ObservableList<HRReport> reportList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        reportTypeComboBox.getItems().addAll(
                "Attendance Report",
                "Payroll Report",
                "Leave Report",
                "Employee Directory",
                "Employee Separation"
        );

        reportIdColumn.setCellValueFactory(new PropertyValueFactory<>("reportId"));
        reportTypeColumn.setCellValueFactory(new PropertyValueFactory<>("reportType"));
        reportDateColumn.setCellValueFactory(new PropertyValueFactory<>("reportDate"));
        generatedByColumn.setCellValueFactory(new PropertyValueFactory<>("generatedBy"));

        reportTableView.setItems(reportList);

        totalReportsLabel.setText("0");
        userLabel.setText("HR Manager");
        statusLabel.setText("Ready");
    }

    @FXML
    public void handleGenerateReport(ActionEvent event) {

    }

    @FXML
    public void handleSearch(ActionEvent event) {

    }

    @FXML
    public void handleRefresh(ActionEvent event) {

    }

    @FXML
    public void handleClear(ActionEvent event) {

    }

    @FXML
    public void handleBack(ActionEvent event) {

    }

}