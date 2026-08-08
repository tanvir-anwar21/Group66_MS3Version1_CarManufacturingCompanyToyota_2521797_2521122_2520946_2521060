package Tawsif.SalesExecutiveControllers;

import Tawsif.Models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerManagement_Controller {

    // =========================
    // TEXT FIELDS
    // =========================

    @FXML
    private TextField customerIdField;

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField lastPurchaseField;


    // =========================
    // TABLE VIEW
    // =========================

    @FXML
    private TableView<Customer> customerTableView;

    @FXML
    private TableColumn<Customer, String> customerIdColumn;

    @FXML
    private TableColumn<Customer, String> nameColumn;

    @FXML
    private TableColumn<Customer, String> phoneColumn;

    @FXML
    private TableColumn<Customer, String> emailColumn;

    @FXML
    private TableColumn<Customer, Integer> ordersColumn;

    @FXML
    private TableColumn<Customer, String> statusColumn;


    // =========================
    // BUTTONS
    // =========================

    @FXML
    private Button searchButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button viewButton;


    // =========================
    // STATUS LABEL
    // =========================

    @FXML
    private Label statusLabel;


    // =========================
    // CUSTOMER LIST
    // =========================

    private final ObservableList<Customer> customerList =
            FXCollections.observableArrayList();


    // =========================
    // INITIALIZE
    // =========================

    @FXML
    public void initialize() {

        // Connect TableView columns with Customer class properties

        customerIdColumn.setCellValueFactory(
                new PropertyValueFactory<>("customerId")
        );

        nameColumn.setCellValueFactory(
                new PropertyValueFactory<>("customerName")
        );

        phoneColumn.setCellValueFactory(
                new PropertyValueFactory<>("phone")
        );

        emailColumn.setCellValueFactory(
                new PropertyValueFactory<>("email")
        );

        ordersColumn.setCellValueFactory(
                new PropertyValueFactory<>("totalOrders")
        );

        statusColumn.setCellValueFactory(
                new PropertyValueFactory<>("status")
        );


        // Put customer list into TableView

        customerTableView.setItems(customerList);


        // =========================
        // SAMPLE CUSTOMER DATA
        // =========================

        customerList.addAll(

                new Customer(
                        "C001",
                        "Rahim Ahmed",
                        "01711111111",
                        "rahim@gmail.com",
                        "Dhaka",
                        4,
                        "15-Jul-2026",
                        "Active"
                ),

                new Customer(
                        "C002",
                        "Karim Hasan",
                        "01822222222",
                        "karim@gmail.com",
                        "Chattogram",
                        2,
                        "20-Jul-2026",
                        "Active"
                ),

                new Customer(
                        "C003",
                        "Nusrat Jahan",
                        "01933333333",
                        "nusrat@gmail.com",
                        "Sylhet",
                        7,
                        "05-Aug-2026",
                        "VIP"
                )
        );


        // Initial status

        statusLabel.setText("Ready");


        // =========================
        // TABLE ROW SELECTION
        // =========================

        customerTableView
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((obs, oldCustomer, customer) -> {

                    if (customer != null) {

                        customerIdField.setText(
                                customer.getCustomerId()
                        );

                        customerNameField.setText(
                                customer.getCustomerName()
                        );

                        addressField.setText(
                                customer.getAddress()
                        );

                        lastPurchaseField.setText(
                                customer.getLastPurchase()
                        );
                    }
                });
    }


    // =========================
    // ALERT METHOD
    // =========================

    private void showAlert(
            String title,
            String message,
            Alert.AlertType type
    ) {

        Alert alert = new Alert(type);

        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }


    // =========================
    // CLEAR FIELDS
    // =========================

    private void clearFields() {

        customerIdField.clear();
        customerNameField.clear();
        addressField.clear();
        lastPurchaseField.clear();

        customerTableView
                .getSelectionModel()
                .clearSelection();
    }


    // =========================
    // SEARCH CUSTOMER
    // =========================

    @FXML
    public void handleSearchCustomer(ActionEvent event) {

        String id = customerIdField.getText().trim();


        // Check empty ID

        if (id.isEmpty()) {

            showAlert(
                    "Search",
                    "Please enter Customer ID.",
                    Alert.AlertType.WARNING
            );

            return;
        }


        // Search customer

        for (Customer customer : customerList) {

            if (customer.getCustomerId()
                    .equalsIgnoreCase(id)) {

                // Select customer in TableView

                customerTableView
                        .getSelectionModel()
                        .select(customer);


                // Display customer information

                customerNameField.setText(
                        customer.getCustomerName()
                );

                addressField.setText(
                        customer.getAddress()
                );

                lastPurchaseField.setText(
                        customer.getLastPurchase()
                );


                statusLabel.setText("Customer Found");

                return;
            }
        }


        // Customer not found

        showAlert(
                "Search",
                "Customer not found.",
                Alert.AlertType.INFORMATION
        );

        statusLabel.setText("Customer Not Found");
    }


    // =========================
    // VIEW CUSTOMER DETAILS
    // =========================

    @FXML
    public void handleCustomerViewDetails(ActionEvent event) {

        Customer customer =
                customerTableView
                        .getSelectionModel()
                        .getSelectedItem();


        // No customer selected

        if (customer == null) {

            showAlert(
                    "View Customer",
                    "Please select a customer first.",
                    Alert.AlertType.WARNING
            );

            return;
        }


        // Create information alert

        Alert alert =
                new Alert(Alert.AlertType.INFORMATION);

        alert.setTitle("Customer Details");

        alert.setHeaderText(
                "Customer Information"
        );


        alert.setContentText(

                "Customer ID : "
                        + customer.getCustomerId()

                        + "\n\nCustomer Name : "
                        + customer.getCustomerName()

                        + "\n\nPhone : "
                        + customer.getPhone()

                        + "\n\nEmail : "
                        + customer.getEmail()

                        + "\n\nAddress : "
                        + customer.getAddress()

                        + "\n\nTotal Orders : "
                        + customer.getTotalOrders()

                        + "\n\nLast Purchase : "
                        + customer.getLastPurchase()

                        + "\n\nStatus : "
                        + customer.getStatus()
        );


        alert.showAndWait();

        statusLabel.setText(
                "Customer Details Displayed"
        );
    }


    // =========================
    // CLEAR BUTTON
    // =========================

    @FXML
    public void handleCustomerClear(ActionEvent event) {

        clearFields();

        statusLabel.setText("Fields Cleared");
    }


    // =========================
    // BACK TO DASHBOARD
    // =========================

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
}