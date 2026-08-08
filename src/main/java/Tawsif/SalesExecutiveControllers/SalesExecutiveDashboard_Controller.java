package Tawsif.SalesExecutiveControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class SalesExecutiveDashboard_Controller {

    @FXML
    private Label userLabel;


    @FXML
    public void initialize() {

        userLabel.setText("Sales Executive");

    }


    private void openPage(String fxml) {

        try {

            URL url = getClass().getResource(fxml);

            // Check whether FXML exists
            if (url == null) {

                showAlert(
                        "FXML Not Found",
                        "The following FXML file could not be found:\n\n"
                                + fxml
                );

                System.out.println(
                        "FXML NOT FOUND: " + fxml
                );

                return;
            }

            System.out.println(
                    "Opening FXML: " + url
            );


            FXMLLoader loader =
                    new FXMLLoader(url);


            Scene scene =
                    new Scene(loader.load());


            Stage stage =
                    (Stage) userLabel
                            .getScene()
                            .getWindow();


            stage.setScene(scene);

            stage.show();


        } catch (IOException e) {

            showAlert(
                    "FXML Error",
                    "Could not open:\n\n"
                            + fxml
                            + "\n\nError:\n"
                            + e.getMessage()
            );

            e.printStackTrace();
        }
    }


    private void showAlert(
            String title,
            String message
    ) {

        Alert alert =
                new Alert(Alert.AlertType.ERROR);

        alert.setTitle(title);

        alert.setHeaderText(null);

        alert.setContentText(message);

        alert.showAndWait();
    }


    // =========================================
    // NEW VEHICLE ORDER
    // =========================================

    @FXML
    public void handleNewOrder(ActionEvent event) {

        openPage(
                "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/SalesExecutive/NewVehicleOrderView.fxml"
        );
    }


    // =========================================
    // VEHICLE INVENTORY
    // =========================================

    @FXML
    public void handleInventory(ActionEvent event) {

        openPage(
                "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/SalesExecutive/VehicleInventoryView.fxml"
        );
    }


    // =========================================
    // SALES REPORT
    // =========================================

    @FXML
    public void handleSalesReport(ActionEvent event) {

        openPage(
                "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/SalesExecutive/SalesReportView.fxml"
        );
    }


    // =========================================
    // TRACK ORDER
    // =========================================

    @FXML
    public void handleTrackOrder(ActionEvent event) {

        openPage(
                "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/SalesExecutive/TrackVehicleOrderView.fxml"
        );
    }


    // =========================================
    // INVOICE
    // =========================================

    @FXML
    public void handleInvoice(ActionEvent event) {

        openPage(
                "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/SalesExecutive/GenerateInvoiceView.fxml"
        );
    }


    // =========================================
    // UPDATE CUSTOMER
    // =========================================

    @FXML
    public void handleUpdateCustomer(ActionEvent event) {

        openPage(
                "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/SalesExecutive/UpdateCustomerInformationView.fxml"
        );
    }


    // =========================================
    // CUSTOMER MANAGEMENT
    // =========================================

    @FXML
    public void handleCustomer(ActionEvent event) {

        openPage(
                "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/SalesExecutive/CustomerManagementView.fxml"
        );
    }


    // =========================================
    // ORDER CONFIRMATION
    // =========================================

    @FXML
    public void handleConfirmation(ActionEvent event) {

        openPage(
                "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/SalesExecutive/SendOrderConfirmationView.fxml"
        );
    }


    // =========================================
    // LOGOUT
    // =========================================

    @FXML
    public void handleLogout(ActionEvent event) {

        openPage(
                "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Utility/LogInView.fxml"
        );
    }
}