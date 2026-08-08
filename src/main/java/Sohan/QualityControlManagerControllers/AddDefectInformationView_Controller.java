package Sohan.QualityControlManagerControllers;

import javafx.scene.control.*;

import java.awt.event.ActionEvent;

public class AddDefectInformationView_Controller
{
    @javafx.fxml.FXML
    private RadioButton lowSeverityRadio;
    @javafx.fxml.FXML
    private ComboBox statusCombo;
    @javafx.fxml.FXML
    private ComboBox defectCategoryCombo;
    @javafx.fxml.FXML
    private DatePicker detectionDatePicker;
    @javafx.fxml.FXML
    private TextField vehicleIdField;
    @javafx.fxml.FXML
    private TextField partInfoField;
    @javafx.fxml.FXML
    private RadioButton mediumSeverityRadio;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private ToggleGroup severityGroup;
    @javafx.fxml.FXML
    private CheckBox urgentCheckBox;
    @javafx.fxml.FXML
    private TextArea defectDescriptionArea;
    @javafx.fxml.FXML
    private RadioButton criticalSeverityRadio;
    @javafx.fxml.FXML
    private TextField detectedByField;
    @javafx.fxml.FXML
    private RadioButton highSeverityRadio;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void handleClear(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleAddDefect(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleBackToDashboard(ActionEvent actionEvent) {
    }
}