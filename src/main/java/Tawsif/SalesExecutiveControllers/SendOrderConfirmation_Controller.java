package Tawsif.SalesExecutiveControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

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

    @FXML
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

    @FXML
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

    @FXML
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

    @FXML
    public void handleClear(ActionEvent event) {

        orderIdField.clear();
        customerNameField.clear();
        emailField.clear();
        phoneField.clear();
        messageTextArea.clear();

        emailRadioButton.setSelected(true);

        statusLabel.setText("Ready");
    }

}