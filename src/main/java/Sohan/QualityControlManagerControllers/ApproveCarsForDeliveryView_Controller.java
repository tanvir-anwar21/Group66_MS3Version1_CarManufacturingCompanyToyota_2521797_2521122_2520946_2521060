package Sohan.QualityControlManagerControllers;

import javafx.scene.control.*;

import java.awt.event.ActionEvent;

public class ApproveCarsForDeliveryView_Controller
{
    @javafx.fxml.FXML
    private Label recordCountLabel;
    @javafx.fxml.FXML
    private RadioButton approvedRadio;
    @javafx.fxml.FXML
    private RadioButton approveRadio;
    @javafx.fxml.FXML
    private ToggleGroup approvalFilterGroup;
    @javafx.fxml.FXML
    private TextArea commentsArea;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private RadioButton allVehiclesRadio;
    @javafx.fxml.FXML
    private RadioButton pendingApprovalRadio;
    @javafx.fxml.FXML
    private RadioButton rejectRadio;
    @javafx.fxml.FXML
    private Label selectedVehicleLabel;
    @javafx.fxml.FXML
    private RadioButton holdRadio;
    @javafx.fxml.FXML
    private ComboBox modelFilterCombo;
    @javafx.fxml.FXML
    private TextField batchIdField;
    @javafx.fxml.FXML
    private TableView vehicleTableView;
    @javafx.fxml.FXML
    private ToggleGroup approvalActionGroup;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void handleSearch(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleSubmitApproval(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleBackToDashboard(ActionEvent actionEvent) {
    }
}