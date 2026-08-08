package Sohan.ProcurementOfficerControllers;

import javafx.scene.control.*;

import java.awt.event.ActionEvent;

public class CreatePurchaseOrderView_Controller
{
    @javafx.fxml.FXML
    private DatePicker deliveryDatePicker;
    @javafx.fxml.FXML
    private TextField orderRefField;
    @javafx.fxml.FXML
    private RadioButton normalPriorityRadio;
    @javafx.fxml.FXML
    private RadioButton urgentPriorityRadio;
    @javafx.fxml.FXML
    private ComboBox materialCombo;
    @javafx.fxml.FXML
    private ToggleGroup priorityGroup;
    @javafx.fxml.FXML
    private ComboBox supplierCombo;
    @javafx.fxml.FXML
    private TextField quantityField;
    @javafx.fxml.FXML
    private RadioButton criticalPriorityRadio;
    @javafx.fxml.FXML
    private CheckBox insuranceCheckBox;
    @javafx.fxml.FXML
    private CheckBox expressDeliveryCheckBox;
    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void handleClear(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleCreatePurchaseOrder(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleBackToDashboard(ActionEvent actionEvent) {
    }
}