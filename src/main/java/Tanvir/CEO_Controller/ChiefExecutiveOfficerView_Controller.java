package Tanvir.CEO_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ChiefExecutiveOfficerView_Controller {

    @javafx.fxml.FXML
    private TextField revenueField;

    @javafx.fxml.FXML
    private TextArea activityArea;

    @javafx.fxml.FXML
    private TextField productionField;

    @javafx.fxml.FXML
    private TextArea budgetArea;

    @javafx.fxml.FXML
    private TextField salesField;

    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    private TextField employeeField;


    @javafx.fxml.FXML
    public void initialize() {

        // Dashboard summary values
        revenueField.setText("900000");
        productionField.setText("850");
        salesField.setText("320");
        employeeField.setText("150");

        // Recent company activities
        activityArea.setText(
                "• Monthly production target reviewed\n" +
                        "• Sales performance updated\n" +
                        "• Employee statistics reviewed\n" +
                        "• Customer feedback analysed"
        );

        // Pending budget information
        budgetArea.setText(
                "Pending Budget Approvals:\n" +
                        "• Production Department\n" +
                        "• Sales Department"
        );

        statusLabel.setText("Dashboard loaded successfully.");
    }


    @javafx.fxml.FXML
    public void executiveReportButton(ActionEvent actionEvent) {
        openPage("/Tanvir/ExecutiveReportsView.fxml");
    }


    @javafx.fxml.FXML
    public void productionPerformanceButton(ActionEvent actionEvent) {
        openPage("/Tanvir/ProductionPerformanceView.fxml");
    }


    @javafx.fxml.FXML
    public void customerFeedbackButton(ActionEvent actionEvent) {
        openPage("/Tanvir/CustomerFeedbackView.fxml");
    }


    @javafx.fxml.FXML
    public void companyPerformanceButton(ActionEvent actionEvent) {
        openPage("/Tanvir/CompanyPerformanceView.fxml");
    }


    @javafx.fxml.FXML
    public void refreshButton(ActionEvent actionEvent) {

        initialize();

        statusLabel.setText("Dashboard refreshed successfully.");
    }


    @javafx.fxml.FXML
    public void employeeStatisticsButton(ActionEvent actionEvent) {
        openPage("/Tanvir/EmployeeStatisticsView.fxml");
    }


    @javafx.fxml.FXML
    public void budgetApprovalButton(ActionEvent actionEvent) {
        openPage("/Tanvir/BudgetApprovalView.fxml");
    }


    @javafx.fxml.FXML
    public void salesPerformanceButton(ActionEvent actionEvent) {
        openPage("/Tanvir/SalesPerformanceView.fxml");
    }


    @javafx.fxml.FXML
    public void logoutButton(ActionEvent actionEvent) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource("/Tanvir/Login.fxml"));

            Parent root = loader.load();

            Stage stage = (Stage) statusLabel.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();

        } catch (IOException e) {

            statusLabel.setText("Unable to logout.");
            e.printStackTrace();
        }
    }


    @javafx.fxml.FXML
    public void financialSummaryButton(ActionEvent actionEvent) {
        openPage("/Tanvir/FinancialSummaryView.fxml");
    }


    // Common method for opening CEO pages

    private void openPage(String fxmlFile) {

        try {

            FXMLLoader loader =
                    new FXMLLoader(getClass().getResource(fxmlFile));

            Parent root = loader.load();

            Stage stage = (Stage) statusLabel.getScene().getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {

            statusLabel.setText("Unable to open page.");
            e.printStackTrace();
        }
    }
}