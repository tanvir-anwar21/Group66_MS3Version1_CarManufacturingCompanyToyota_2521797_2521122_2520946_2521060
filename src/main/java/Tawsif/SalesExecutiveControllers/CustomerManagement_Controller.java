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

        statusLabel.setText("Ready");
    }

    @FXML
    public void handleSearch(ActionEvent event) {

    }

    @FXML
    public void handleClear(ActionEvent event) {

        customerIdField.clear();
        customerNameField.clear();
        addressField.clear();
        lastPurchaseField.clear();

        statusLabel.setText("Fields cleared.");
    }

    @FXML
    public void handleView(ActionEvent event) {

    }

}