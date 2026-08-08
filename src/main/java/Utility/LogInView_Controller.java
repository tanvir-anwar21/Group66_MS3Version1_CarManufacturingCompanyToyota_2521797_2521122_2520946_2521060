package Utility;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class LogInView_Controller {

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
    private CheckBox showPasswordCheckBox;

    @FXML
    private TextArea availableUsersTextArea;


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

        statusLabel.setText(
                "Please enter your login credentials."
        );

        statusLabel.setStyle(
                "-fx-text-fill: green; -fx-font-weight: bold;"
        );


        showPasswordCheckBox.setOnAction(event -> {

            if (showPasswordCheckBox.isSelected()) {

                statusLabel.setText(
                        "Password visibility enabled."
                );

            } else {

                statusLabel.setText(
                        "Password is hidden."
                );
            }
        });
    }


    @FXML
    public void handleLogin(ActionEvent event) throws IOException {

        String employeeId =
                employeeIdField.getText().trim();

        String password =
                passwordField.getText();

        String role =
                roleComboBox.getValue();


        // =========================
        // VALIDATION
        // =========================

        if (employeeId.isEmpty()) {

            statusLabel.setText(
                    "Please enter Employee ID."
            );

            statusLabel.setStyle(
                    "-fx-text-fill: red; -fx-font-weight: bold;"
            );

            employeeIdField.requestFocus();

            return;
        }


        if (password.isEmpty()) {

            statusLabel.setText(
                    "Please enter Password."
            );

            statusLabel.setStyle(
                    "-fx-text-fill: red; -fx-font-weight: bold;"
            );

            passwordField.requestFocus();

            return;
        }


        if (role == null) {

            statusLabel.setText(
                    "Please select a User Role."
            );

            statusLabel.setStyle(
                    "-fx-text-fill: red; -fx-font-weight: bold;"
            );

            roleComboBox.requestFocus();

            return;
        }


        // =========================
        // LOGIN VALIDATION
        // =========================

        if (employeeId.equals("admin")
                && password.equals("1234")) {


            String dashboardPath = null;


            // =========================
            // SELECT DASHBOARD
            // =========================

            switch (role) {
                case "Chief Executive Officer (CEO)":

                    dashboardPath =
                            "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tanvir/ChiefExecutiveOfficer/CEODashboard_View.fxml";

                    break;


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
                            "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tanvir/Production Manager/ProductionManager_view.fxml";

                    break;


                default:

                    statusLabel.setText(
                            "Dashboard not created yet."
                    );

                    statusLabel.setStyle(
                            "-fx-text-fill: orange; -fx-font-weight: bold;"
                    );

                    return;
            }


            // =========================
            // LOAD DASHBOARD
            // =========================

            FXMLLoader loader =
                    new FXMLLoader(
                            getClass().getResource(
                                    dashboardPath
                            )
                    );


            // Check if FXML exists

            if (loader.getLocation() == null) {

                statusLabel.setText(
                        "Dashboard FXML not found."
                );

                statusLabel.setStyle(
                        "-fx-text-fill: red; -fx-font-weight: bold;"
                );

                return;
            }


            // Load FXML

            Scene scene =
                    new Scene(loader.load());


            // Get current window

            Stage stage =
                    (Stage) loginButton
                            .getScene()
                            .getWindow();


            // Change scene

            stage.setTitle(
                    role + " Dashboard"
            );

            stage.setScene(scene);

            stage.show();


        } else {

            statusLabel.setText(
                    "Invalid Employee ID or Password."
            );

            statusLabel.setStyle(
                    "-fx-text-fill: red; -fx-font-weight: bold;"
            );
        }
    }


    // =========================
    // CLEAR BUTTON
    // =========================

    @FXML
    public void handleClear(ActionEvent event) {

        employeeIdField.clear();

        passwordField.clear();

        roleComboBox.getSelectionModel()
                .clearSelection();

        showPasswordCheckBox.setSelected(false);

        statusLabel.setText(
                "Fields cleared."
        );

        statusLabel.setStyle(
                "-fx-text-fill: green; -fx-font-weight: bold;"
        );

        employeeIdField.requestFocus();
    }
}