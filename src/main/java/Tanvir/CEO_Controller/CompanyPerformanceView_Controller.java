package Tanvir.CEO_Controller;

import Tanvir.Model_Class.DepartmentPerformance;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class CompanyPerformanceView_Controller
{
    @javafx.fxml.FXML
    private TextField revenueField;
    @javafx.fxml.FXML
    private TableView<DepartmentPerformance> departmentTable;
    @javafx.fxml.FXML
    private TextField profitField;
    @javafx.fxml.FXML
    private TextField growthField;
    @javafx.fxml.FXML
    private TableColumn<DepartmentPerformance, String> targetColumn;
    @javafx.fxml.FXML
    private TableColumn<DepartmentPerformance, String> statusColumn;
    @javafx.fxml.FXML
    private TextArea remarksArea;
    @javafx.fxml.FXML
    private TableColumn<DepartmentPerformance, String> departmentColumn;
    @javafx.fxml.FXML
    private TableColumn<DepartmentPerformance, String> achievementColumn;
    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void exportButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void loadButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void saveButton(ActionEvent actionEvent) {
    }
}