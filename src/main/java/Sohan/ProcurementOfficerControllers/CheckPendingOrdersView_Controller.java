package Sohan.ProcurementOfficerControllers;

import javafx.scene.control.*;

import java.awt.event.ActionEvent;

public class CheckPendingOrdersView_Controller
{
    @javafx.fxml.FXML
    private Label recordCountLabel;
    @javafx.fxml.FXML
    private ComboBox supplierFilterCombo;
    @javafx.fxml.FXML
    private RadioButton allRadio;
    @javafx.fxml.FXML
    private ToggleGroup filterGroup;
    @javafx.fxml.FXML
    private RadioButton inTransitRadio;
    @javafx.fxml.FXML
    private RadioButton deliveredRadio;
    @javafx.fxml.FXML
    private TextField orderIdField;
    @javafx.fxml.FXML
    private Label selectedOrderLabel;
    @javafx.fxml.FXML
    private RadioButton pendingRadio;
    @javafx.fxml.FXML
    private TableView pendingOrderTableView;
    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void handleCheckStatus(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleGenerateReport(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleAllPending(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleViewDetails(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleBackToDashboard(ActionEvent actionEvent) {
    }
}