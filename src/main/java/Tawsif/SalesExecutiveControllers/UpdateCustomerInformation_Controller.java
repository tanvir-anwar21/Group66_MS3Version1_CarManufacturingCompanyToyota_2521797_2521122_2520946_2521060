package Tawsif.SalesExecutiveControllers;

import Tawsif.Models.Customer;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class UpdateCustomerInformation_Controller {


    @FXML
    private Button searchButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button cancelButton;


    @FXML
    private TextField customerIdField;

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField addressField;


    @FXML
    private ComboBox<String> vehicleComboBox;


    @FXML
    private TableView<Customer> customerTableView;


    @FXML
    private TableColumn<Customer, String> idColumn;

    @FXML
    private TableColumn<Customer, String> nameColumn;

    @FXML
    private TableColumn<Customer, String> phoneColumn;

    @FXML
    private TableColumn<Customer, String> emailColumn;

    @FXML
    private TableColumn<Customer, String> vehicleColumn;


    @FXML
    private Label statusLabel;


    private ObservableList<Customer> customerList;



    @FXML
    public void initialize() {


        customerList = FXCollections.observableArrayList();

        customerTableView.setItems(customerList);


        idColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getCustomerId()
                )
        );


        nameColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getCustomerName()
                )
        );


        phoneColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getPhone()
                )
        );


        emailColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getEmail()
                )
        );


        vehicleColumn.setCellValueFactory(
                data -> new SimpleStringProperty(
                        "Toyota Vehicle"
                )
        );


        vehicleComboBox.getItems().addAll(
                "Toyota Corolla",
                "Toyota Axio",
                "Toyota Premio",
                "Toyota Camry"
        );


        statusLabel.setText("Ready");


    }



    @FXML
    private void searchCustomer() {


        String id = customerIdField.getText().trim();


        if(id.isEmpty()) {

            statusLabel.setText("Enter Customer ID");
            return;

        }


        // Temporary data
        Customer customer = new Customer(
                id,
                "Rahim Motors",
                "01700000000",
                "rahim@gmail.com",
                "Dhaka",
                3,
                "Toyota Corolla",
                "Active"
        );


        customerNameField.setText(customer.getCustomerName());

        phoneField.setText(customer.getPhone());

        emailField.setText(customer.getEmail());

        addressField.setText(customer.getAddress());


        customerList.clear();

        customerList.add(customer);


        statusLabel.setText("Customer Found");


    }



    @FXML
    private void updateCustomer() {


        if(customerIdField.getText().isEmpty()) {

            statusLabel.setText("Search customer first");
            return;

        }


        statusLabel.setText(
                "Customer information updated successfully"
        );


    }



    @FXML
    private void clearFields() {


        customerIdField.clear();

        customerNameField.clear();

        phoneField.clear();

        emailField.clear();

        addressField.clear();

        vehicleComboBox.setValue(null);


        statusLabel.setText("Cleared");


    }



    @FXML
    private void cancelUpdate() {


        Stage stage =
                (Stage) cancelButton.getScene().getWindow();

        stage.close();

    }

    @FXML
    public void handleBackToSalesExecutiveDashboard(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/SalesExecutive/SalesExecutiveDashboardView.fxml"
                    )
            );

            if (loader.getLocation() == null) {
                throw new IOException("SalesExecutiveDashboardView.fxml not found!");
            }

            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(scene);
            stage.setTitle("Sales Executive Dashboard");
            stage.show();

        } catch (IOException e) {

            e.printStackTrace();

            showAlert(
                    "Error",
                    "Could not open Sales Executive Dashboard.\n\n"
                            + e.getMessage(),
                    Alert.AlertType.ERROR
            );
        }
    }

    private void showAlert(String error, String s, Alert.AlertType alertType) {
    }
}