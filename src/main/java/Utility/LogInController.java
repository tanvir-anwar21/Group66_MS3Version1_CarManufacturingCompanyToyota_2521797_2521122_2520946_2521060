package Utility;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInController {

    @FXML
    private TextField employeeIdField;

    @FXML
    private Button clearButton;

    @FXML
    private Button loginButton;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;


    @FXML
    public void initialize() {

        roleComboBox.getItems().addAll(
                "Chief Executive Officer (CEO)",
                "Production Manager",
                "Sales Executive",
                "Human Resources Manager",
                "Automotive Engineer",
                "Vehicle Inspector",
                "Quality Control Manager",
                "Procurement Officer"
        );

        statusLabel.setText("Please enter your login credentials.");
    }


    @FXML
    public void handleLogin(ActionEvent actionEvent) throws IOException {

        String employeeId = employeeIdField.getText();
        String password = passwordField.getText();
        String role = roleComboBox.getValue();


        if (employeeId.isEmpty() || password.isEmpty() || role == null) {

            statusLabel.setText("Please fill all fields.");
            statusLabel.setStyle("-fx-text-fill:red;");
            return;
        }


        // Temporary login validation
        if (employeeId.equals("admin") && password.equals("1234")) {


            String dashboardPath = null;


            switch (role) {

                case "Sales Executive":

                    dashboardPath =
                            "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/SalesExecutive/SalesExecutiveDashboardView.fxml";

                    break;


                case "Human Resources Manager":

                    dashboardPath =
                            "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/HumanResourcesManager/HumanResourcesManagerDashboardView.fxml";

                    break;


                case "Automotive Engineer":
                    dashboardPath =
                            "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/AutomotiveEngineer/AutomotiveEngineerDashboard.fxml";
                    break;


                case "Production Manager":
                    dashboardPath =
                            "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/ProductionManager/ProductionManagerDashboard.fxml";
                    break;


                default:
                    statusLabel.setText("Dashboard not created yet.");
                    statusLabel.setStyle("-fx-text-fill:orange;");
                    return;
            }


            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(dashboardPath)
            );


            if (loader.getLocation() == null) {
                statusLabel.setText("Dashboard FXML not found.");
                statusLabel.setStyle("-fx-text-fill:red;");
                return;
            }


            Scene scene = new Scene(loader.load());


            Stage stage = (Stage) loginButton.getScene().getWindow();

            stage.setTitle(role + " Dashboard");
            stage.setScene(scene);
            stage.show();


        } else {

            statusLabel.setText("Invalid Employee ID or Password.");
            statusLabel.setStyle("-fx-text-fill:red;");
        }
    }


    @FXML
    public void handleClear(ActionEvent actionEvent) {

        employeeIdField.clear();

        passwordField.clear();

        roleComboBox.getSelectionModel().clearSelection();

        statusLabel.setText("Fields cleared.");
        statusLabel.setStyle("-fx-text-fill:green;");
    }
}