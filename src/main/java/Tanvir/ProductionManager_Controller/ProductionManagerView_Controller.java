package Tanvir.ProductionManager_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class ProductionManagerView_Controller
{
    @javafx.fxml.FXML
    private TextArea activityArea;
    @javafx.fxml.FXML
    private TextField targetField;
    @javafx.fxml.FXML
    private TextField productionField;
    @javafx.fxml.FXML
    private TextField machineStatusField;
    @javafx.fxml.FXML
    private TextArea taskArea;
    @javafx.fxml.FXML
    private Label statusLabel;
    @javafx.fxml.FXML
    private TextField inventoryField;

    @javafx.fxml.FXML
    public void initialize() {
    }

    @javafx.fxml.FXML
    public void planningButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void inventoryButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void refreshButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void reportButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void supplierButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void logoutButton(ActionEvent actionEvent) {
        openPage(
                "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Utility/LogInView.fxml"
        );
    }

    @javafx.fxml.FXML
    public void performanceButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void qualityButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void workforceButton(ActionEvent actionEvent) {
    }

    @javafx.fxml.FXML
    public void maintenanceButton(ActionEvent actionEvent) {
    }
    private void openPage(String fxmlFile) {

        try {

            URL resource = getClass().getResource(fxmlFile);

            if (resource == null) {

                statusLabel.setText("FXML file not found.");

                System.out.println("FXML FILE NOT FOUND: " + fxmlFile);

                return;
            }

            FXMLLoader loader = new FXMLLoader(resource);

            Parent root = loader.load();

            Stage stage = (Stage) statusLabel
                    .getScene()
                    .getWindow();

            Scene scene = new Scene(root);

            stage.setScene(scene);

            stage.show();

        } catch (IOException e) {

            statusLabel.setText("Unable to open page.");

            e.printStackTrace();
        }
    }
}