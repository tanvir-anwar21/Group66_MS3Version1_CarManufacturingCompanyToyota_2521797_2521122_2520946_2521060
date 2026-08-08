package Tawsif.SalesExecutiveControllers;

import Tawsif.Models.SalesReport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
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

    private void showAlert(String title, String message, Alert.AlertType type) {

        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void updateSummary() {

        totalOrdersLabel.setText(String.valueOf(reportList.size()));

        double revenue = 0;

        for (SalesReport report : reportList) {
            revenue += report.getAmount();
        }

        totalRevenueLabel.setText(String.format("%.2f", revenue));
    }
    @Deprecated
    public void handleGenerateReport(ActionEvent event) {

        reportList.clear();

        ObservableList<SalesReport> tempList = FXCollections.observableArrayList();

        tempList.add(new SalesReport(
                "ORD001",
                "Rahim Ahmed",
                "Corolla",
                "Dhaka",
                2,
                6400000,
                LocalDate.now().minusDays(2)
        ));

        tempList.add(new SalesReport(
                "ORD002",
                "Karim Hasan",
                "Hilux",
                "Chattogram",
                1,
                5500000,
                LocalDate.now().minusDays(5)
        ));

        tempList.add(new SalesReport(
                "ORD003",
                "Sadia Islam",
                "Camry",
                "Sylhet",
                1,
                4500000,
                LocalDate.now().minusDays(10)
        ));

        LocalDate fromDate = fromDatePicker.getValue();
        LocalDate toDate = toDatePicker.getValue();
        String vehicle = vehicleModelComboBox.getValue();
        String region = regionComboBox.getValue();

        for (SalesReport report : tempList) {

            boolean match = true;

            if (fromDate != null &&
                    report.getOrderDate().isBefore(fromDate)) {
                match = false;
            }

            if (toDate != null &&
                    report.getOrderDate().isAfter(toDate)) {
                match = false;
            }

            if (vehicle != null &&
                    !vehicle.equals(report.getVehicleModel())) {
                match = false;
            }

            if (region != null &&
                    !region.equals(report.getRegion())) {
                match = false;
            }

            if (match) {
                reportList.add(report);
            }
        }

        updateSummary();

        if (reportList.isEmpty()) {

            statusLabel.setText("No matching records found.");

            showAlert(
                    "No Records",
                    "No sales report matches the selected filters.",
                    Alert.AlertType.INFORMATION
            );

        } else {

            statusLabel.setText("Sales report generated successfully.");

            showAlert(
                    "Success",
                    "Sales report generated successfully.",
                    Alert.AlertType.INFORMATION
            );
        }
    }

    @Deprecated
    public void handleRefresh(ActionEvent event) {

        salesReportTableView.refresh();

        updateSummary();

        statusLabel.setText("Sales report refreshed.");

        showAlert(
                "Refresh",
                "Sales report refreshed successfully.",
                Alert.AlertType.INFORMATION
        );
    }
    @Deprecated
    public void handleExport(ActionEvent event) {

        if (reportList.isEmpty()) {

            showAlert(
                    "Export",
                    "No report available to export.",
                    Alert.AlertType.WARNING
            );

            statusLabel.setText("Nothing to export.");
            return;
        }

        showAlert(
                "Export Successful",
                "Sales report exported successfully.",
                Alert.AlertType.INFORMATION
        );

        statusLabel.setText("Sales report exported.");
    }

    @Deprecated
    public void handleClear(ActionEvent event) {

        fromDatePicker.setValue(null);
        toDatePicker.setValue(null);

        vehicleModelComboBox.getSelectionModel().clearSelection();
        regionComboBox.getSelectionModel().clearSelection();

        reportList.clear();

        totalOrdersLabel.setText("0");
        totalRevenueLabel.setText("0.00");

        salesReportTableView.refresh();

        statusLabel.setText("Ready");

        showAlert(
                "Cleared",
                "All filters and report data have been cleared.",
                Alert.AlertType.INFORMATION
        );
    }

    @FXML
    public void handleBackToSalesExecutiveDashboard(ActionEvent event) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tawsif/SalesExecutive/SalesExecutiveDashboardView.fxml"
                    )
            );

            if (loader.getLocation() == null) {
                throw new IOException("SalesExecutiveDashboardView.fxml not found!");
            }

            Scene scene = new Scene(loader.load());

            Stage stage = (Stage) ((javafx.scene.Node) event.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(scene);
            stage.setTitle("Sales Executive Dashboard");
            stage.show();

        } catch (IOException e) {

            e.printStackTrace();

            showAlert(
                    "Error",
                    "Could not open Sales Executive Dashboard.\n\n"
                            + e.getMessage(),
                    Alert.AlertType.ERROR
            );
        }
    }
}
