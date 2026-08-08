package Sohan.QualityControlManagerControllers;

import javafx.scene.control.*;

import java.awt.event.ActionEvent;

public class ScheduleReinspectionView_Controller
{
    @javafx.fxml.FXML
    private DatePicker reInspectionDatePicker;
    @javafx.fxml.FXML
    private TextArea notesArea;
    @javafx.fxml.FXML
    private RadioButton mediumPriorityRadio;
    @javafx.fxml.FXML
    private RadioButton lowPriorityRadio;
    @javafx.fxml.FXML
    private ToggleGroup priorityGroup;
    @javafx.fxml.FXML
    private DatePicker previousInspectionDate;
    @javafx.fxml.FXML
    private ComboBox reasonCombo;
    @javafx.fxml.FXML
    private TextField vehicleIdField;
    @javafx.fxml.FXML
    private RadioButton highPriorityRadio;
    @javafx.fxml.FXML
    private ComboBox inspectorCombo;
    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void handleClear(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleScheduleReInspection(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleBackToDashboard(ActionEvent actionEvent) {
    }
}