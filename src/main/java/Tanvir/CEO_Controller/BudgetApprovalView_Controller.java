package Tanvir.CEO_Controller;

import Tanvir.Model_Class.Budget;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class BudgetApprovalView_Controller
{
    @javafx.fxml.FXML
    private TextField totalBudgetField;
    @javafx.fxml.FXML
    private TextField pendingRequestsField;
    @javafx.fxml.FXML
    private TextField approvedBudgetField;
    @javafx.fxml.FXML
    private TextArea commentArea;
    @javafx.fxml.FXML
    private TableColumn<Budget, String> requestColumn;
    @javafx.fxml.FXML
    private TableColumn<Budget, String> statusColumn;
    @javafx.fxml.FXML
    private Button backButton;
    @javafx.fxml.FXML
    private TableColumn<Budget, String> departmentColumn;
    @javafx.fxml.FXML
    private TableColumn<Budget, String> purposeColumn;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private TableView<Budget> budgetTable;

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
    public void rejectButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void approveButton(ActionEvent actionEvent) {
    }
}