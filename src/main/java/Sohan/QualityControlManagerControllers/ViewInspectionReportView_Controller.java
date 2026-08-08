package Sohan.QualityControlManagerControllers;

import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.awt.event.ActionEvent;

public class ViewInspectionReportView_Controller
{
    @javafx.fxml.FXML
    private Label recordCountLabel;
    @javafx.fxml.FXML
    private DatePicker reportDatePicker;
    @javafx.fxml.FXML
    private TableView inspectionReportTableView;
    @javafx.fxml.FXML
    private Label selectedReportLabel;
    @javafx.fxml.FXML
    private ComboBox modelCombo;
    @javafx.fxml.FXML
    private ComboBox statusFilterCombo;
    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void handleExportPDF(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleViewFullReport(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleViewReport(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handlePrintReport(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleRefresh(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleBackToDashboard(ActionEvent actionEvent) {
    }
}