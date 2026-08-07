package Tawsif.SalesExecutiveControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class SalesExecutiveDashboard_Controller {

    @FXML
    private Label userLabel;

    @FXML
    public void initialize() {
        userLabel.setText("Sales Executive");
    }

    @FXML
    public void handleNewOrder(ActionEvent event) {
        System.out.println("Opening New Vehicle Order...");
    }

    @FXML
    public void handleInventory(ActionEvent event) {
        System.out.println("Opening Vehicle Inventory...");
    }

    @FXML
    public void handleSalesReport(ActionEvent event) {
        System.out.println("Opening Sales Report...");
    }

    @FXML
    public void handleTrackOrder(ActionEvent event) {
        System.out.println("Opening Track Order...");
    }

    @FXML
    public void handleInvoice(ActionEvent event) {
        System.out.println("Opening Generate Invoice...");
    }

    @FXML
    public void handleUpdateCustomer(ActionEvent event) {
        System.out.println("Opening Update Customer...");
    }

    @FXML
    public void handleCustomer(ActionEvent event) {
        System.out.println("Opening Customer Management...");
    }

    @FXML
    public void handleConfirmation(ActionEvent event) {
        System.out.println("Opening Send Confirmation...");
    }

    @FXML
    public void handleLogout(ActionEvent event) {
        System.out.println("Logging out...");
    }
}