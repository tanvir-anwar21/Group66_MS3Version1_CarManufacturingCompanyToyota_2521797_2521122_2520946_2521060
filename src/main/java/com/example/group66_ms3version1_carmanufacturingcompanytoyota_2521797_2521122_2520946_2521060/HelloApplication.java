package com.example.group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        URL url = getClass().getResource(
                "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/Utility/LogInView.fxml"
        );

        System.out.println("FXML Location: " + url);

        if (url == null) {
            throw new RuntimeException("LogInView.fxml not found. Check resources folder path.");
        }

        FXMLLoader loader = new FXMLLoader(url);
        Scene scene = new Scene(loader.load());

        stage.setTitle("Toyota Car Manufacturing Company");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            System.out.println("Closing application...");
            Platform.exit();
            System.exit(0);
        });

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}