package Sohan.QualityControlManagerControllers;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

import java.awt.event.ActionEvent;

public class MonitorQualityStatisticsView_Controller
{
    @javafx.fxml.FXML
    private Label passRateLabel;
    @javafx.fxml.FXML
    private Label criticalDefectsLabel;
    @javafx.fxml.FXML
    private TableView qualityStatsTableView;
    @javafx.fxml.FXML
    private ComboBox periodCombo;
    @javafx.fxml.FXML
    private Label avgRepairTimeLabel;
    @javafx.fxml.FXML
    private Label qualityScoreLabel;
    @javafx.fxml.FXML
    private Label totalInspectionsLabel;
    @javafx.fxml.FXML
    private Label defectRateLabel;
    @javafx.fxml.FXML
    private ComboBox modelCombo;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private ComboBox lineCombo;

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