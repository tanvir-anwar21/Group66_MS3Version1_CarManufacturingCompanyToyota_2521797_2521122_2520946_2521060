package Tanvir.ProductionManager_Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class InventoryManagementView_Controller
{
    @javafx.fxml.FXML
    private TextField lowStockField;
    @javafx.fxml.FXML
    private ComboBox typeComboBox;
    @javafx.fxml.FXML
    private TextField rawMaterialField;
    @javafx.fxml.FXML
    private TextField capacityField;
    @javafx.fxml.FXML
    private TableColumn itemColumn;
    @javafx.fxml.FXML
    private TableColumn typeColumn;
    @javafx.fxml.FXML
    private TextField finishedProductField;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private TableView inventoryTable;
    @javafx.fxml.FXML
    private TableColumn quantityColumn;
    @javafx.fxml.FXML
    private ComboBox statusComboBox;
    @javafx.fxml.FXML
    private TableColumn statusColumn;
    @javafx.fxml.FXML
    private TableColumn supplierColumn;
    @javafx.fxml.FXML
    private TextField itemField;
    @javafx.fxml.FXML
    private TextField quantityField;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void deleteButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void refreshButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void updateButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void addButton(ActionEvent actionEvent) {
    }

    public static class ProductionplannerView_Controller
    {
        @javafx.fxml.FXML
        private TextField todayPlanField;
        @javafx.fxml.FXML
        private TableColumn startDateColumn;
        @javafx.fxml.FXML
        private TextField monthlyTargetField;
        @javafx.fxml.FXML
        private DatePicker startDatePicker;
        @javafx.fxml.FXML
        private TableView planningTable;
        @javafx.fxml.FXML
        private Button addButton;
        @javafx.fxml.FXML
        private Label statusLabel;
        @javafx.fxml.FXML
        private TableColumn endDateColumn;
        @javafx.fxml.FXML
        private TableColumn quantityColumn;
        @javafx.fxml.FXML
        private DatePicker endDatePicker;
        @javafx.fxml.FXML
        private TableColumn statusColumn;
        @javafx.fxml.FXML
        private TextField completedOrdersField;
        @javafx.fxml.FXML
        private TextField productField;
        @javafx.fxml.FXML
        private TableColumn productColumn;
        @javafx.fxml.FXML
        private TextField quantityField;
        @javafx.fxml.FXML
        private TextField pendingOrdersField;

        @javafx.fxml.FXML
        public void initialize() {
        }

        @javafx.fxml.FXML
        public void deleteButton(ActionEvent actionEvent) {
        }

        @javafx.fxml.FXML
        public void refreshButton(ActionEvent actionEvent) {
        }

        @javafx.fxml.FXML
        public void backButton(ActionEvent actionEvent) {
        }

        @javafx.fxml.FXML
        public void updateButton(ActionEvent actionEvent) {
        }

        @javafx.fxml.FXML
        public void addButton(ActionEvent actionEvent) {
        }
    }
}