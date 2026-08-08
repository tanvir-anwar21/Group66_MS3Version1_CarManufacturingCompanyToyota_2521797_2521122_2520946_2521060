package Tanvir.CEO_Controller;

import Tanvir.Model_Class.ProductionPerformance;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

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

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tanvir/ChiefExecutiveOfficer/CEODashboard_View.fxml"
                    )
            );

            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void loadButton(ActionEvent actionEvent) {

    }

    @javafx.fxml.FXML
    public void generateReportButton(ActionEvent actionEvent) {

    }
}