package Sohan.ProcurementOfficerControllers;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;

public class ViewSupplierListView_Controller
{
    @javafx.fxml.FXML
    private Label recordCountLabel;
    @javafx.fxml.FXML
    private ComboBox filterCombo;
    @javafx.fxml.FXML
    private TextField searchField;
    @javafx.fxml.FXML
    private TableView supplierTableView;
    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void handleSearch(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleRefresh(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void handleBackToDashboard(ActionEvent actionEvent) {
    }
}