package Tawsif.SalesExecutiveControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class SendOrderConfirmation_Controller {

    @FXML
    private Button searchButton;

    @FXML
    private Button previewButton;

    @FXML
    private Button sendButton;

    @FXML
    private Button clearButton;

    @FXML
    private TextField orderIdField;

    @FXML
    private TextField customerNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private TextArea messageTextArea;

    @FXML
    private RadioButton emailRadioButton;

    @FXML
    private RadioButton smsRadioButton;

    @FXML
    private Label statusLabel;

    private ToggleGroup deliveryMethod;

    @FXML
    public void initialize() {

        deliveryMethod = new ToggleGroup();

        emailRadioButton.setToggleGroup(deliveryMethod);
        smsRadioButton.setToggleGroup(deliveryMethod);

        emailRadioButton.setSelected(true);

        customerNameField.setEditable(false);
        emailField.setEditable(false);
        phoneField.setEditable(false);

        statusLabel.setText("Ready");
    }

    @Deprecated
    public void handleSearch(ActionEvent event) {

        if (orderIdField.getText().isEmpty()) {
            statusLabel.setText("Enter an Order ID.");
            return;
        }

        customerNameField.setText("Rahim Ahmed");
        emailField.setText("rahim@gmail.com");
        phoneField.setText("01712345678");

        statusLabel.setText("Order found.");
    }

    @Deprecated
    public void handlePreview(ActionEvent event) {

        if (customerNameField.getText().isEmpty()) {
            statusLabel.setText("Search an order first.");
            return;
        }

        String message =
                "Dear " + customerNameField.getText() + ",\n\n" +
                        "Thank you for choosing Toyota.\n\n" +
                        "Your Order ID: " + orderIdField.getText() + "\n" +
                        "has been confirmed successfully.\n\n" +
                        "Our team will contact you before the delivery date.\n\n" +
                        "Thank you.\nToyota Manufacturing Company";

        messageTextArea.setText(message);

        statusLabel.setText("Confirmation preview generated.");
    }

    @Deprecated
    public void handleSend(ActionEvent event) {

        if (messageTextArea.getText().isEmpty()) {
            statusLabel.setText("Generate the preview first.");
            return;
        }

        if (emailRadioButton.isSelected()) {

            statusLabel.setText("Confirmation sent via Email.");

        } else if (smsRadioButton.isSelected()) {

            statusLabel.setText("Confirmation sent via SMS.");
        }
    }

    @Deprecated
    public void handleClear(ActionEvent event) {

        orderIdField.clear();
        customerNameField.clear();
        emailField.clear();
        phoneField.clear();
        messageTextArea.clear();

        emailRadioButton.setSelected(true);

        statusLabel.setText("Ready");
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