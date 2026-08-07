package Tawsif.SalesExecutiveControllers;

import Tawsif.Models.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class CustomerManagement_Controller {

    @FXML
    private TextField customerIdField;

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField addressField;

    @FXML
    private TextField lastPurchaseField;

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

    @FXML
    private Button searchButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button viewButton;

    @FXML
    private Label statusLabel;

    private final ObservableList<Customer> customerList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        ordersColumn.setCellValueFactory(new PropertyValueFactory<>("totalOrders"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        customerTableView.setItems(customerList);

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

        statusLabel.setText("Ready");

        customerTableView.getSelectionModel().selectedItemProperty().addListener((obs, oldCustomer, customer) -> {

            if (customer != null) {

                customerIdField.setText(customer.getCustomerId());
                customerNameField.setText(customer.getCustomerName());
                addressField.setText(customer.getAddress());
                lastPurchaseField.setText(customer.getLastPurchase());

            }

        });

    }

    private void showAlert(String title, String message, Alert.AlertType type) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();

    }

    private void clearFields() {

        customerIdField.clear();
        customerNameField.clear();
        addressField.clear();
        lastPurchaseField.clear();

        customerTableView.getSelectionModel().clearSelection();

    }
    @FXML
    public void handleSearch(ActionEvent event) {

        String id = customerIdField.getText().trim();

        if (id.isEmpty()) {

            showAlert(
                    "Search",
                    "Please enter Customer ID.",
                    Alert.AlertType.WARNING
            );
            return;
        }

        for (Customer customer : customerList) {

            if (customer.getCustomerId().equalsIgnoreCase(id)) {

                customerTableView.getSelectionModel().select(customer);

                customerNameField.setText(customer.getCustomerName());
                addressField.setText(customer.getAddress());
                lastPurchaseField.setText(customer.getLastPurchase());

                statusLabel.setText("Customer Found");

                return;
            }
        }

        showAlert(
                "Search",
                "Customer not found.",
                Alert.AlertType.INFORMATION
        );
    }

    @FXML
    public void handleView(ActionEvent event) {

        Customer customer =
                customerTableView.getSelectionModel().getSelectedItem();

        if (customer == null) {

            showAlert(
                    "View Customer",
                    "Please select a customer first.",
                    Alert.AlertType.WARNING
            );
            return;
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Customer Details");
        alert.setHeaderText("Customer Information");

        alert.setContentText(
                "Customer ID : " + customer.getCustomerId() +
                        "\n\nCustomer Name : " + customer.getCustomerName() +
                        "\n\nPhone : " + customer.getPhone() +
                        "\n\nEmail : " + customer.getEmail() +
                        "\n\nAddress : " + customer.getAddress() +
                        "\n\nTotal Orders : " + customer.getTotalOrders() +
                        "\n\nLast Purchase : " + customer.getLastPurchase() +
                        "\n\nStatus : " + customer.getStatus()
        );

        alert.showAndWait();

        statusLabel.setText("Customer Details Displayed");
    }

    @FXML
    public void handleClear(ActionEvent event) {

        clearFields();

        statusLabel.setText("Fields Cleared");
    }

}