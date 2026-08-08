package Tanvir.CEO_Controller;

import Tanvir.Model_Class.FinancialSummary;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;


public class FinancialSummaryView_Controller {

    @javafx.fxml.FXML
    private TextField revenueField;

    // FinancialSummary, Float
    @javafx.fxml.FXML
    private TableColumn<FinancialSummary, Float> incomeColumn;

    @javafx.fxml.FXML
    private TextField expensesField;

    // FinancialSummary, String
    @javafx.fxml.FXML
    private TableColumn<FinancialSummary, String> remarksColumn;

    @javafx.fxml.FXML
    private TextField profitField;

    @javafx.fxml.FXML
    private TextArea notesArea;

    // FinancialSummary, Float
    @javafx.fxml.FXML
    private TableColumn<FinancialSummary, Float> expenseColumn;

    @javafx.fxml.FXML
    private TextField marginField;

    @javafx.fxml.FXML
    private TableView<FinancialSummary> financialTable;

    // FinancialSummary, String
    @javafx.fxml.FXML
    private TableColumn<FinancialSummary, String> categoryColumn;

    // FinancialSummary, Float
    @javafx.fxml.FXML
    private TableColumn<FinancialSummary, Float> balanceColumn;

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
}