package Sohan.QualityControlManagerControllers;

import javafx.scene.control.*;

import java.awt.event.ActionEvent;

public class PrintQualityReportView_Controller
{
    @javafx.fxml.FXML
    private DatePicker startDatePicker;
    @javafx.fxml.FXML
    private ComboBox reportTypeCombo;
    @javafx.fxml.FXML
    private RadioButton htmlRadio;
    @javafx.fxml.FXML
    private CheckBox includeChartsCheckBox;
    @javafx.fxml.FXML
    private CheckBox includeDetailsCheckBox;
    @javafx.fxml.FXML
    private ComboBox modelCombo;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private CheckBox summaryOnlyCheckBox;
    @javafx.fxml.FXML
    private DatePicker endDatePicker;
    @javafx.fxml.FXML
    private ToggleGroup formatGroup;
    @javafx.fxml.FXML
    private RadioButton excelRadio;
    @javafx.fxml.FXML
    private RadioButton csvRadio;
    @javafx.fxml.FXML
    private RadioButton pdfRadio;
    @javafx.fxml.FXML
    private ComboBox lineCombo;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void handleReset(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleGenerateAndPrint(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handlePreviewReport(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleBackToDashboard(ActionEvent actionEvent) {
    }
}