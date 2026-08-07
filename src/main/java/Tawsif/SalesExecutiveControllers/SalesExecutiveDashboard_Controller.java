package Tawsif.SalesExecutiveControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class SalesExecutiveDashboard_Controller {

    @FXML
    private Label userLabel;

    @FXML
    public void initialize() {
        userLabel.setText("Sales Executive");
    }

    private void openPage(String fxml) {

        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));

            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) userLabel.getScene().getWindow();

            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("FXML Error");
            alert.setHeaderText(null);
            alert.setContentText("Cannot open:\n" + fxml);
            alert.showAndWait();

            e.printStackTrace();
        }
    }

    @FXML
    public void handleNewOrder(ActionEvent event) {

        openPage("/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/SalesExecutive/NewVehicleOrderView.fxml");
    }

    @FXML
    public void handleInventory(ActionEvent event) {

        openPage("/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/SalesExecutive/VehicleInventoryView.fxml");
    }

    @FXML
    public void handleSalesReport(ActionEvent event) {

        openPage("/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/SalesExecutive/SalesReportView.fxml");
    }

    @FXML
    public void handleTrackOrder(ActionEvent event) {

        openPage("/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/SalesExecutive/TrackVehicleOrderView.fxml");
    }

    @FXML
    public void handleInvoice(ActionEvent event) {

        openPage("/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/SalesExecutive/GenerateInvoiceView.fxml");
    }

    @FXML
    public void handleUpdateCustomer(ActionEvent event) {

        openPage("/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/SalesExecutive/UpdateCustomerInformationView.fxml");
    }

    @FXML
    public void handleCustomer(ActionEvent event) {

        openPage("/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/SalesExecutive/CustomerManagementView.fxml");
    }

    @FXML
    public void handleConfirmation(ActionEvent event) {

        openPage("/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/SalesExecutive/SendOrderConfirmationView.fxml");
    }

    @FXML
    public void handleLogout(ActionEvent event) {

        openPage("/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Utility/LogInView.fxml");
    }

}