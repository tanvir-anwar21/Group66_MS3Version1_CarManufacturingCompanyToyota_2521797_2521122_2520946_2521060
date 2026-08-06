package Tawsif.SalesExecutiveControllers;

import javafx.scene.control.*;

public class SalesReport_Controller {
    @javafx.fxml.FXML
    private TableColumn orderIdColumn;
    @javafx.fxml.FXML
    private DatePicker fromDatePicker;
    @javafx.fxml.FXML
    private Label totalRevenueLabel;
    @javafx.fxml.FXML
    private TableColumn customerColumn;
    @javafx.fxml.FXML
    private ComboBox regionComboBox;
    @javafx.fxml.FXML
    private DatePicker toDatePicker;
    @javafx.fxml.FXML
    private Label totalOrdersLabel;
    @javafx.fxml.FXML
    private TableColumn amountColumn;
    @javafx.fxml.FXML
    private TableColumn regionColumn;
    @javafx.fxml.FXML
    private ComboBox vehicleModelComboBox;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private TableColumn vehicleColumn;
    @javafx.fxml.FXML
    private Button exportButton;
    @javafx.fxml.FXML
    private TableColumn quantityColumn;
    @javafx.fxml.FXML
    private Button clearButton;
    @javafx.fxml.FXML
    private Button refreshButton;
    @javafx.fxml.FXML
    private TableView salesReportTableView;
    @javafx.fxml.FXML
    private Button generateReportButton;
    @javafx.fxml.FXML
    private TableColumn dateColumn;
}
