package Sohan.ProcurementOfficerControllers;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.awt.event.ActionEvent;

public class MonitorMaterialUsageView_Controller
{
    @javafx.fxml.FXML
    private Label shortageLabel;
    @javafx.fxml.FXML
    private DatePicker endDatePicker;
    @javafx.fxml.FXML
    private ComboBox materialTypeCombo;
    @javafx.fxml.FXML
    private ComboBox categoryCombo;
    @javafx.fxml.FXML
    private DatePicker startDatePicker;
    @javafx.fxml.FXML
    private Label avgConsumptionLabel;
    @javafx.fxml.FXML
    private Label totalUsedLabel;
    @javafx.fxml.FXML
    private TableView materialUsageTableView;
    @javafx.fxml.FXML
    private Label lowStockLabel;
    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void handleReset(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleApplyFilters(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleExportExcel(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleGenerateReport(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleRefresh(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleBackToDashboard(ActionEvent actionEvent) {
    }
}