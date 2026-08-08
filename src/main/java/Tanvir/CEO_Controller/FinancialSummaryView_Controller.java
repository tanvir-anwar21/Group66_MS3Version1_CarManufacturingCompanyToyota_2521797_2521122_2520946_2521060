package Tanvir.CEO_Controller;

import Tanvir.Model_Class.FinancialSummary;
import javafx.event.ActionEvent;
import javafx.scene.control.*;


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

    }

    @javafx.fxml.FXML
    public void loadButton(ActionEvent actionEvent) {

    }
}