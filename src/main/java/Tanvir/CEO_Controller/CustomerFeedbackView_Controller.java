package Tanvir.CEO_Controller;

import Tanvir.Model_Class.CustomerFeedback;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

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
    }

    @javafx.fxml.FXML
    public void loadButton(ActionEvent actionEvent) {
    }
}