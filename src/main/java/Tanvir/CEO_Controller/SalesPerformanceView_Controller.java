package Tanvir.CEO_Controller;

import Tanvir.Model_Class.SalesPerformance;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

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
    public void refreshButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void loadButton(ActionEvent actionEvent) {
    }
}