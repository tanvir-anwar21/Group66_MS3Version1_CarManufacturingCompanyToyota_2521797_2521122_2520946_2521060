package Sohan.ProcurementOfficerControllers;

import javafx.scene.control.*;

import java.awt.event.ActionEvent;

public class OrderRawMaterialsView_Controller
{
    @javafx.fxml.FXML
    private RadioButton standardRadio;
    @javafx.fxml.FXML
    private ComboBox unitCombo;
    @javafx.fxml.FXML
    private CheckBox trackingCheckBox;
    @javafx.fxml.FXML
    private ToggleGroup priorityGroup;
    @javafx.fxml.FXML
    private TextField costField;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private RadioButton urgentRadio;
    @javafx.fxml.FXML
    private RadioButton emergencyRadio;
    @javafx.fxml.FXML
    private DatePicker requiredDatePicker;
    @javafx.fxml.FXML
    private ComboBox materialCombo;
    @javafx.fxml.FXML
    private CheckBox insuredCheckBox;
    @javafx.fxml.FXML
    private ComboBox supplierCombo;
    @javafx.fxml.FXML
    private TextField quantityField;
    @javafx.fxml.FXML
    private CheckBox qualityCertCheckBox;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void handleClear(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handlePlaceOrder(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleBackToDashboard(ActionEvent actionEvent) {
    }
}