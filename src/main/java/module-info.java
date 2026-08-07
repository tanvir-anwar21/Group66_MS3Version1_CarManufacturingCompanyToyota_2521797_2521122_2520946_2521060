module com.example.group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060 {

    requires javafx.controls;
    requires javafx.fxml;

    // Main Application
    exports com.example.group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060;
    opens com.example.group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060 to javafx.fxml;


    // Login Controller
    exports Utility;
    opens Utility to javafx.fxml;


    // Models
    exports Tawsif.Models;
    opens Tawsif.Models to javafx.base, javafx.fxml;


    // User 3 - Human Resources Manager
    exports Tawsif.HumanResourcesManagerControllers;
    opens Tawsif.HumanResourcesManagerControllers to javafx.fxml;


    // User 4 - Sales Executive
    exports Tawsif.SalesExecutiveControllers;
    opens Tawsif.SalesExecutiveControllers to javafx.fxml;
}