package Tawsif.SalesExecutiveControllers;

import Tawsif.Models.SalesReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;

public class SalesReport_Controller {

    @FXML
    private TableColumn<SalesReport, String> orderIdColumn;

    @FXML
    private DatePicker fromDatePicker;

    @FXML
    private Label totalRevenueLabel;

    @FXML
    private TableColumn<SalesReport, String> customerColumn;

    @FXML
    private ComboBox<String> regionComboBox;

    @FXML
    private DatePicker toDatePicker;

    @FXML
    private Label totalOrdersLabel;

    @FXML
    private TableColumn<SalesReport, Double> amountColumn;

    @FXML
    private TableColumn<SalesReport, String> regionColumn;

    @FXML
    private ComboBox<String> vehicleModelComboBox;

    @FXML
    private Label statusLabel;

    @FXML
    private TableColumn<SalesReport, String> vehicleColumn;

    @FXML
    private Button exportButton;

    @FXML
    private TableColumn<SalesReport, Integer> quantityColumn;

    @FXML
    private Button clearButton;

    @FXML
    private Button refreshButton;

    @FXML
    private TableView<SalesReport> salesReportTableView;

    @FXML
    private Button generateReportButton;

    @FXML
    private TableColumn<SalesReport, LocalDate> dateColumn;

    private final ObservableList<SalesReport> reportList =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        vehicleModelComboBox.getItems().addAll(
                "Corolla",
                "Camry",
                "Yaris",
                "Hilux",
                "Land Cruiser",
                "Prius"
        );

        regionComboBox.getItems().addAll(
                "Dhaka",
                "Chattogram",
                "Sylhet",
                "Rajshahi",
                "Khulna",
                "Barishal",
                "Rangpur",
                "Mymensingh"
        );

        orderIdColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        customerColumn.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        vehicleColumn.setCellValueFactory(new PropertyValueFactory<>("vehicleModel"));
        regionColumn.setCellValueFactory(new PropertyValueFactory<>("region"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("amount"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));

        salesReportTableView.setItems(reportList);

        totalOrdersLabel.setText("0");
        totalRevenueLabel.setText("0.00");
        statusLabel.setText("Ready");
    }

    @FXML
    public void handleGenerateReport(ActionEvent event) {

        reportList.clear();

        reportList.add(new SalesReport(
                "ORD001",
                "Rahim Ahmed",
                "Corolla",
                "Dhaka",
                2,
                6400000,
                LocalDate.now()
        ));

        reportList.add(new SalesReport(
                "ORD002",
                "Karim Hasan",
                "Hilux",
                "Chattogram",
                1,
                5500000,
                LocalDate.now()
        ));

        reportList.add(new SalesReport(
                "ORD003",
                "Sadia Islam",
                "Camry",
                "Sylhet",
                1,
                4500000,
                LocalDate.now()
        ));

        int totalOrders = reportList.size();

        double revenue = 0;

        for (SalesReport report : reportList) {
            revenue += report.getAmount();
        }

        totalOrdersLabel.setText(String.valueOf(totalOrders));
        totalRevenueLabel.setText(String.format("%.2f", revenue));

        statusLabel.setText("Sales report generated successfully.");
    }

    @FXML
    public void handleRefresh(ActionEvent event) {

        salesReportTableView.refresh();

        statusLabel.setText("Sales report refreshed.");
    }

    @FXML
    public void handleExport(ActionEvent event) {

        statusLabel.setText("Sales report exported successfully.");
    }

    @FXML
    public void handleClear(ActionEvent event) {

        fromDatePicker.setValue(null);
        toDatePicker.setValue(null);

        vehicleModelComboBox.getSelectionModel().clearSelection();
        regionComboBox.getSelectionModel().clearSelection();

        reportList.clear();

        totalOrdersLabel.setText("0");
        totalRevenueLabel.setText("0.00");

        statusLabel.setText("Ready");
    }

}