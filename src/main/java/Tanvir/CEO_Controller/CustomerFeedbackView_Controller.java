package Tanvir.CEO_Controller;

import Tanvir.Model_Class.CustomerFeedback;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class CustomerFeedbackView_Controller
{
    @javafx.fxml.FXML
    private TableColumn<CustomerFeedback, String> ratingColumn;
    @javafx.fxml.FXML
    private TableColumn<CustomerFeedback, String> feedbackColumn;
    @javafx.fxml.FXML
    private TextField totalFeedbackField;
    @javafx.fxml.FXML
    private TableColumn<CustomerFeedback, String> customerColumn;
    @javafx.fxml.FXML
    private TextField positiveField;
    @javafx.fxml.FXML
    private TextArea remarksArea;
    @javafx.fxml.FXML
    private TextField ratingField;
    @javafx.fxml.FXML
    private TextField negativeField;
    @javafx.fxml.FXML
    private TableColumn<CustomerFeedback, String> dateColumn;
    @javafx.fxml.FXML
    private TableColumn<CustomerFeedback, String> productColumn;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private TableView<CustomerFeedback> feedbackTable;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void exportButton(ActionEvent actionEvent) {
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