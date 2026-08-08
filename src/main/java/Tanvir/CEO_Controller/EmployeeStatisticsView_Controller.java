package Tanvir.CEO_Controller;

import Tanvir.Model_Class.EmployeeStatistics;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeStatisticsView_Controller
{
    @javafx.fxml.FXML
    private TextField performanceField;
    @javafx.fxml.FXML
    private TableColumn<EmployeeStatistics, Float> performanceColumn;
    @javafx.fxml.FXML
    private TableColumn<EmployeeStatistics, String> remarksColumn;
    @javafx.fxml.FXML
    private TextArea notesArea;
    @javafx.fxml.FXML
    private TableView<EmployeeStatistics> employeeTable;
    @javafx.fxml.FXML
    private TableColumn<EmployeeStatistics, Integer> employeeCountColumn;
    @javafx.fxml.FXML
    private TextField totalEmployeesField;
    @javafx.fxml.FXML
    private TableColumn<EmployeeStatistics, Float> attendanceColumn;
    @javafx.fxml.FXML
    private TableColumn<EmployeeStatistics, String> departmentColumn;
    @javafx.fxml.FXML
    private TextField attendanceField;
    @javafx.fxml.FXML
    private TextField departmentCountField;
    @javafx.fxml.FXML
    private Label statusLabel;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void refreshButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void backButton(ActionEvent actionEvent) {

        try {

            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource(
                            "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Tanvir/ChiefExecutiveOfficer/CEODashboard_View.fxml"
                    )
            );

            Parent root = loader.load();

            Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource())
                    .getScene()
                    .getWindow();

            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    @javafx.fxml.FXML
    public void loadButton(ActionEvent actionEvent) {
    }
}