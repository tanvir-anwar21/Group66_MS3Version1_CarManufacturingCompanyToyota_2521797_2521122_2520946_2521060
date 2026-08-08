package Tanvir.CEO_Controller;

import Tanvir.Model_Class.ProductionPerformance;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class ProductionPerformanceView_Controller {

    @javafx.fxml.FXML
    private TextField completedField;

    @javafx.fxml.FXML
    private TextField pendingField;

    @javafx.fxml.FXML
    private TextField defectiveField;

    @javafx.fxml.FXML
    private TextField efficiencyField;

    @javafx.fxml.FXML
    private TextField targetField;

    @javafx.fxml.FXML
    private TextField lineStatusField;

    @javafx.fxml.FXML
    private TableView<ProductionPerformance> productionTable;

    // ProductionPerformance, String
    @javafx.fxml.FXML
    private TableColumn<ProductionPerformance, String> modelColumn;

    // ProductionPerformance, String
    @javafx.fxml.FXML
    private TableColumn<ProductionPerformance, String> assemblyLineColumn;

    // ProductionPerformance, Integer
    @javafx.fxml.FXML
    private TableColumn<ProductionPerformance, Integer> completedColumn;

    // ProductionPerformance, Integer
    @javafx.fxml.FXML
    private TableColumn<ProductionPerformance, Integer> pendingColumn;

    // ProductionPerformance, Integer
    @javafx.fxml.FXML
    private TableColumn<ProductionPerformance, Integer> defectiveColumn;

    // ProductionPerformance, Float
    @javafx.fxml.FXML
    private TableColumn<ProductionPerformance, Float> efficiencyColumn;

    // ProductionPerformance, String
    @javafx.fxml.FXML
    private TableColumn<ProductionPerformance, String> statusColumn;

    @javafx.fxml.FXML
    private Label statusLabel;


    @javafx.fxml.FXML
    public void initialize() {

    }

    @javafx.fxml.FXML
    public void refreshButton(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void backButton(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void loadButton(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void generateReportButton(ActionEvent actionEvent) {

    }
}