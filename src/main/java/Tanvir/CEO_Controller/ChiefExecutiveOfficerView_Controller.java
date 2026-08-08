package Tanvir.CEO_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ChiefExecutiveOfficerView_Controller {

    @FXML
    private TextField revenueField;

    @FXML
    private TextArea activityArea;

    @FXML
    private TextField productionField;

    @FXML
    private TextArea budgetArea;

    @FXML
    private TextField salesField;

    @FXML
    private Label statusLabel;

    @FXML
    private TextField employeeField;

    @FXML
    public void initialize() {

        loadDashboardData();

        statusLabel.setText("Dashboard loaded successfully.");
    }

    private void loadDashboardData() {

        revenueField.setText("900000");
        productionField.setText("850");
        salesField.setText("320");
        employeeField.setText("150");

        activityArea.setText(
                "• Monthly production target reviewed\n" +
                        "• Sales performance updated\n" +
                        "• Employee statistics reviewed\n" +
                        "• Customer feedback analysed"
        );

        budgetArea.setText(
                "Pending Budget Approvals:\n" +
                        "• Production Department\n" +
                        "• Sales Department"
        );
    }

    @FXML
    public void executiveReportButton(ActionEvent actionEvent) {

        openPage(
                "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tanvir/ChiefExecutiveOfficer/FinancialSummary_view.fxml"
        );
    }

    @FXML
    public void productionPerformanceButton(ActionEvent actionEvent) {

        openPage(
                "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tanvir/ChiefExecutiveOfficer/ProductionPerformance_view.fxml"
        );
    }

    @FXML
    public void customerFeedbackButton(ActionEvent actionEvent) {

        openPage(
                "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tanvir/ChiefExecutiveOfficer/CustomerFeedback_view.fxml"
        );
    }

    @FXML
    public void companyPerformanceButton(ActionEvent actionEvent) {

        openPage(
                "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tanvir/ChiefExecutiveOfficer/CompanyPerformance_view.fxml"
        );
    }

    @FXML
    public void employeeStatisticsButton(ActionEvent actionEvent) {

        openPage(
                "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tanvir/ChiefExecutiveOfficer/EmployeeStatistics_view.fxml"
        );
    }

    @FXML
    public void budgetApprovalButton(ActionEvent actionEvent) {

        openPage(
                "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tanvir/ChiefExecutiveOfficer/BudgetApproval_view.fxml"
        );
    }

    @FXML
    public void salesPerformanceButton(ActionEvent actionEvent) {

        openPage(
                "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tanvir/ChiefExecutiveOfficer/SalesPerformane_view.fxml"
        );
    }

    @FXML
    public void financialSummaryButton(ActionEvent actionEvent) {

        openPage(
                "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tanvir/ChiefExecutiveOfficer/FinancialSummary_view.fxml"
        );
    }

    @FXML
    public void refreshButton(ActionEvent actionEvent) {

        loadDashboardData();

        statusLabel.setText("Dashboard refreshed successfully.");
    }

    @FXML
    public void logoutButton(ActionEvent actionEvent) {

        openPage(
                "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Utility/LogInView.fxml"
        );
    }

    private void openPage(String fxmlFile) {

        try {

            URL resource = getClass().getResource(fxmlFile);

            if (resource == null) {

                statusLabel.setText("FXML file not found.");

                System.out.println("FXML FILE NOT FOUND: " + fxmlFile);

                return;
            }

            FXMLLoader loader = new FXMLLoader(resource);

            Parent root = loader.load();

            Stage stage = (Stage) statusLabel
                    .getScene()
                    .getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);

            stage.show();

        } catch (IOException e) {

            statusLabel.setText("Unable to open page.");

            e.printStackTrace();
        }
    }
}