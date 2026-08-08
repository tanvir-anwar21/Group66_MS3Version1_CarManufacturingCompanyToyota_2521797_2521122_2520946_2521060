package Tanvir.CEO_Controller;

import Tanvir.Model_Class.SalesPerformance;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class SalesPerformanceView_Controller
{
    @javafx.fxml.FXML
    private TextField totalSalesField;

    @javafx.fxml.FXML
    private TextField revenueField;

    @javafx.fxml.FXML
    private TextField growthField;

    @javafx.fxml.FXML
    private TextField topModelField;

    @javafx.fxml.FXML
    private TextField dealershipField;

    @javafx.fxml.FXML
    private TableView<SalesPerformance> salesTable;

    // SalesPerformance, String
    @javafx.fxml.FXML
    private TableColumn<SalesPerformance, String> dealershipColumn;

    // SalesPerformance, String
    @javafx.fxml.FXML
    private TableColumn<SalesPerformance, String> modelColumn;

    // SalesPerformance, Integer
    @javafx.fxml.FXML
    private TableColumn<SalesPerformance, Integer> vehiclesSoldColumn;

    // SalesPerformance, Float
    @javafx.fxml.FXML
    private TableColumn<SalesPerformance, Float> revenueColumn;

    // SalesPerformance, Float
    @javafx.fxml.FXML
    private TableColumn<SalesPerformance, Float> growthColumn;

    // SalesPerformance, String
    @javafx.fxml.FXML
    private TableColumn<SalesPerformance, String> remarksColumn;

    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void exportButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backbutton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void refreshButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void loadButton(ActionEvent actionEvent) {
    }
}