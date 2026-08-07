package Tawsif.SalesExecutiveControllers;

import Tawsif.Models.Invoice;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;

public class GenerateInvoice_Controller {

    @FXML
    private Button searchButton;

    @FXML
    private Button generateButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button printButton;

    @FXML
    private Button clearButton;

    @FXML
    private TextField orderIdField;

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField vehicleField;

    @FXML
    private TextField quantityField;

    @FXML
    private TextField unitPriceField;

    @FXML
    private TextField totalPriceField;

    @FXML
    private TextArea invoiceTextArea;

    @FXML
    private Label statusLabel;

    private final ObservableList<Invoice> invoiceList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        totalPriceField.setEditable(false);
        invoiceTextArea.setEditable(false);

        statusLabel.setText("Ready");
    }

    @FXML
    public void handleSearch(ActionEvent event) {

    }

    @FXML
    public void handleGenerate(ActionEvent event) {

        try {

            double unitPrice = Double.parseDouble(unitPriceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            double amount = unitPrice * quantity;

            totalPriceField.setText(String.format("%.2f", amount));

            invoiceTextArea.setText(
                    "============= TOYOTA INVOICE =============\n\n" +
                            "Invoice ID      : INV" + (invoiceList.size() + 1) + "\n" +
                            "Order ID        : " + orderIdField.getText() + "\n" +
                            "Customer Name   : " + customerNameField.getText() + "\n" +
                            "Vehicle         : " + vehicleField.getText() + "\n" +
                            "Quantity        : " + quantity + "\n" +
                            "Unit Price      : " + unitPrice + "\n" +
                            "-----------------------------------------\n" +
                            "Total Amount    : " + amount + "\n" +
                            "Invoice Date    : " + LocalDate.now() + "\n" +
                            "Payment Status  : Pending\n\n" +
                            "Thank you for choosing Toyota!"
            );

            statusLabel.setText("Invoice generated successfully.");

        } catch (NumberFormatException e) {

            statusLabel.setText("Enter valid quantity and unit price.");

        }

    }

    @FXML
    public void handleSave(ActionEvent event) {

        try {

            Invoice invoice = new Invoice(
                    "INV" + (invoiceList.size() + 1),
                    orderIdField.getText(),
                    customerNameField.getText(),
                    Double.parseDouble(totalPriceField.getText()),
                    LocalDate.now(),
                    "Pending"
            );

            invoiceList.add(invoice);

            statusLabel.setText("Invoice saved successfully.");

        } catch (Exception e) {

            statusLabel.setText("Generate the invoice before saving.");

        }

    }

    @FXML
    public void handlePrint(ActionEvent event) {

        statusLabel.setText("Print functionality will be implemented.");

    }

    @FXML
    public void handleClear(ActionEvent event) {

        orderIdField.clear();
        customerNameField.clear();
        vehicleField.clear();
        quantityField.clear();
        unitPriceField.clear();
        totalPriceField.clear();
        invoiceTextArea.clear();

        statusLabel.setText("Fields cleared.");

    }

}