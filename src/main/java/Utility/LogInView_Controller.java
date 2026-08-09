package Utility;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class LogInView_Controller {

    @FXML
    private TextField employeeIdField;

    @FXML
    private Button clearButton;

    @FXML
    private Button loginButton;

    @FXML
    private ComboBox<String> roleComboBox;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label statusLabel;

    @FXML
    private CheckBox showPasswordCheckBox;

    @FXML
    private TextArea availableUsersTextArea;

    private static final String LOGIN_DIR = "login_logs/";
    private static final String LOGIN_HISTORY = LOGIN_DIR + "login_history.ser";
    private static final String LOGIN_LOG = LOGIN_DIR + "login_log.txt";
    private static final String SESSION_FILE = LOGIN_DIR + "current_session.ser";

    // =========================
    // CREDENTIALS FOR ALL USERS
    // =========================
    private final Map<String, String> validCredentials = new HashMap<>();
    private final Map<String, String> roleToId = new HashMap<>();

    @FXML
    public void initialize() {
        createDirectories();
        setupCredentials();
        setupRoleComboBox();
        setupShowPasswordCheckBox();
        loadAvailableUsers();
        loadSavedSession();
        setInitialStatus();
    }

    private void createDirectories() {
        try {
            new File(LOGIN_DIR).mkdirs();
        } catch (Exception e) {
            System.err.println("Failed to create login directory: " + e.getMessage());
        }
    }

    private void setupCredentials() {
        // Valid credentials for all users
        validCredentials.put("admin", "1234");
        validCredentials.put("pm", "1234");
        validCredentials.put("sales", "1234");
        validCredentials.put("hr", "1234");
        validCredentials.put("eng", "1234");
        validCredentials.put("insp", "1234");
        validCredentials.put("qc", "1234");
        validCredentials.put("po", "1234");

        // Role to ID mapping
        roleToId.put("Chief Executive Officer (CEO)", "admin");
        roleToId.put("Production Manager", "pm");
        roleToId.put("Sales Executive", "sales");
        roleToId.put("Human Resources Manager", "hr");
        roleToId.put("Automotive Engineer", "eng");
        roleToId.put("Vehicle Inspector", "insp");
        roleToId.put("Quality Control Manager", "qc");
        roleToId.put("Procurement Officer", "po");
    }

    private void setupRoleComboBox() {
        roleComboBox.getItems().addAll(
                "Chief Executive Officer (CEO)",
                "Production Manager",
                "Sales Executive",
                "Human Resources Manager",
                "Automotive Engineer",
                "Vehicle Inspector",
                "Quality Control Manager",
                "Procurement Officer"
        );
    }

    private void setupShowPasswordCheckBox() {
        showPasswordCheckBox.setOnAction(event -> {
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setPromptText("Password visible");
                statusLabel.setText("Password visibility enabled.");
                statusLabel.setStyle("-fx-text-fill: blue; -fx-font-weight: bold;");
            } else {
                passwordField.setPromptText("Enter Password");
                statusLabel.setText("Password is hidden.");
                statusLabel.setStyle("-fx-text-fill: blue; -fx-font-weight: bold;");
            }
        });
    }

    private void loadAvailableUsers() {
        StringBuilder users = new StringBuilder();
        users.append("=== AVAILABLE USERS ===\n\n");
        users.append("CEO: admin / 1234\n");
        users.append("Production Manager: pm / 1234\n");
        users.append("Sales Executive: sales / 1234\n");
        users.append("HR Manager: hr / 1234\n");
        users.append("Automotive Engineer: eng / 1234\n");
        users.append("Vehicle Inspector: insp / 1234\n");
        users.append("Quality Control Manager: qc / 1234\n");
        users.append("Procurement Officer: po / 1234\n\n");
        users.append("Select role and use corresponding ID.");

        availableUsersTextArea.setText(users.toString());
        availableUsersTextArea.setStyle("-fx-font-family: monospace; -fx-font-size: 11px;");
    }

    private void loadSavedSession() {
        File sessionFile = new File(SESSION_FILE);
        if (sessionFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(sessionFile))) {
                Map<String, String> session = (Map<String, String>) ois.readObject();
                long timestamp = ois.readLong();

                if (System.currentTimeMillis() - timestamp < 3600000) {
                    employeeIdField.setText(session.get("employeeId"));
                    roleComboBox.setValue(session.get("role"));
                    statusLabel.setText("✓ Session restored. Click Login to continue.");
                    statusLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                }
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Failed to load session: " + e.getMessage());
            }
        }
    }

    private void setInitialStatus() {
        statusLabel.setText("Please enter your login credentials.");
        statusLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
    }

    private void saveLoginHistory(Map<String, Object> loginRecord) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(LOGIN_HISTORY))) {
            oos.writeObject(loginRecord);
        } catch (IOException e) {
            System.err.println("Failed to save login history: " + e.getMessage());
        }
    }

    private void appendToLoginLog(Map<String, Object> loginRecord) {
        try (FileWriter fw = new FileWriter(LOGIN_LOG, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(loginRecord.get("employeeId") + "," +
                    loginRecord.get("role") + "," +
                    loginRecord.get("loginTime") + "," +
                    loginRecord.get("status"));
        } catch (IOException e) {
            System.err.println("Failed to append to login log: " + e.getMessage());
        }
    }

    private void saveSession(String employeeId, String role) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(SESSION_FILE))) {
            Map<String, String> session = new HashMap<>();
            session.put("employeeId", employeeId);
            session.put("role", role);
            oos.writeObject(session);
            oos.writeLong(System.currentTimeMillis());
        } catch (IOException e) {
            System.err.println("Failed to save session: " + e.getMessage());
        }
    }

    private String getDashboardPath(String role) {
        String basePath = "/com/example/group66_ms3version1_carmanufacturingcompanytoyota_2521797_2521122_2520946_2521060/";

        switch (role) {
            case "Chief Executive Officer (CEO)":
                return basePath + "Tanvir/ChiefExecutiveOfficer/CEODashboard_View.fxml";

            case "Production Manager":
                return basePath + "Tanvir/Production Manager/ProductionManager_view.fxml";

            case "Sales Executive":
                return basePath + "Tawsif/SalesExecutive/SalesExecutiveDashboardView.fxml";

            case "Human Resources Manager":
                return basePath + "Tawsif/HumanResourcesManager/HumanResourcesManagerDashboardView.fxml";

            case "Automotive Engineer":
                return basePath + "AutomotiveEngineer/AutomotiveEngineerDashboard.fxml";

            case "Vehicle Inspector":
                return basePath + "VehicleInspector/VehicleInspectorDashboardView.fxml";

            case "Quality Control Manager":
                return basePath + "Sohan/QualityControlManagerFxmls/QualityControlManagerView.fxml";

            case "Procurement Officer":
                return basePath + "Sohan/ProcurementOfficerFxmls/ProcurementOfficerView.fxml";

            default:
                return null;
        }
    }

    @FXML
    public void handleLogin(ActionEvent event) throws IOException {
        String employeeId = employeeIdField.getText().trim();
        String password = passwordField.getText();
        String role = roleComboBox.getValue();

        // =========================
        // VALIDATION
        // =========================

        if (employeeId.isEmpty()) {
            statusLabel.setText("Please enter Employee ID.");
            statusLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            employeeIdField.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            statusLabel.setText("Please enter Password.");
            statusLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            passwordField.requestFocus();
            return;
        }

        if (role == null) {
            statusLabel.setText("Please select a User Role.");
            statusLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            roleComboBox.requestFocus();
            return;
        }

        // =========================
        // VERIFY ROLE MATCHES ID
        // =========================
        String expectedId = roleToId.get(role);
        if (expectedId == null) {
            statusLabel.setText("Invalid role selected.");
            statusLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            return;
        }

        if (!employeeId.equals(expectedId)) {
            statusLabel.setText("Invalid Employee ID for selected role. Expected: " + expectedId);
            statusLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            return;
        }

        // =========================
        // VERIFY CREDENTIALS
        // =========================
        String expectedPassword = validCredentials.get(employeeId);
        if (expectedPassword == null || !expectedPassword.equals(password)) {
            // Log failed attempt
            Map<String, Object> failedRecord = new HashMap<>();
            failedRecord.put("employeeId", employeeId);
            failedRecord.put("role", role);
            failedRecord.put("loginTime", LocalDateTime.now().format(
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            failedRecord.put("status", "FAILED");
            appendToLoginLog(failedRecord);

            statusLabel.setText("Invalid Employee ID or Password.");
            statusLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            passwordField.clear();
            passwordField.requestFocus();
            return;
        }

        // =========================
        // LOGIN SUCCESSFUL
        // =========================
        Map<String, Object> loginRecord = new HashMap<>();
        loginRecord.put("employeeId", employeeId);
        loginRecord.put("role", role);
        loginRecord.put("loginTime", LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        loginRecord.put("status", "SUCCESS");

        saveLoginHistory(loginRecord);
        appendToLoginLog(loginRecord);
        saveSession(employeeId, role);

        String dashboardPath = getDashboardPath(role);

        if (dashboardPath == null) {
            statusLabel.setText("Dashboard not found for this role.");
            statusLabel.setStyle("-fx-text-fill: orange; -fx-font-weight: bold;");
            return;
        }

        // =========================
        // LOAD DASHBOARD
        // =========================
        try {
            System.out.println("Loading dashboard: " + dashboardPath);
            FXMLLoader loader = new FXMLLoader(getClass().getResource(dashboardPath));

            if (loader.getLocation() == null) {
                statusLabel.setText("Dashboard FXML not found: " + dashboardPath);
                statusLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                System.err.println("FXML NOT FOUND: " + dashboardPath);
                return;
            }

            System.out.println("FXML Found at: " + loader.getLocation());
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setTitle(role + " Dashboard");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            statusLabel.setText("Error loading dashboard: " + e.getMessage());
            statusLabel.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
            System.err.println("Error loading FXML: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void handleClear(ActionEvent event) {
        employeeIdField.clear();
        passwordField.clear();
        roleComboBox.getSelectionModel().clearSelection();
        showPasswordCheckBox.setSelected(false);
        passwordField.setPromptText("Enter Password");

        statusLabel.setText("Fields cleared.");
        statusLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
        employeeIdField.requestFocus();
    }

    @FXML
    public void handleViewLoginHistory(ActionEvent event) {
        File historyFile = new File(LOGIN_HISTORY);
        if (!historyFile.exists()) {
            showAlert("No History", "No login history found.", Alert.AlertType.INFORMATION);
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(historyFile))) {
            Map<String, Object> lastLogin = (Map<String, Object>) ois.readObject();

            String message = "Last Login Details:\n\n" +
                    "User: " + lastLogin.get("employeeId") + "\n" +
                    "Role: " + lastLogin.get("role") + "\n" +
                    "Time: " + lastLogin.get("loginTime") + "\n" +
                    "Status: " + lastLogin.get("status");

            showAlert("Login History", message, Alert.AlertType.INFORMATION);

        } catch (IOException | ClassNotFoundException e) {
            showAlert("Error", "Failed to read login history: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleViewLoginLog(ActionEvent event) {
        File logFile = new File(LOGIN_LOG);
        if (!logFile.exists()) {
            showAlert("No Log", "No login log found.", Alert.AlertType.INFORMATION);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(logFile))) {
            StringBuilder content = new StringBuilder();
            content.append("=== LOGIN LOG ===\n\n");
            String line;
            int count = 0;
            while ((line = br.readLine()) != null && count < 20) {
                content.append(line).append("\n");
                count++;
            }
            if (count == 20) {
                content.append("\n... (showing last 20 entries)");
            }
            showAlert("Login Log", content.toString(), Alert.AlertType.INFORMATION);
        } catch (IOException e) {
            showAlert("Error", "Failed to read login log: " + e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void handleClearLoginHistory(ActionEvent event) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Clear History");
        confirm.setHeaderText("Are you sure you want to clear login history?");
        confirm.setContentText("This action cannot be undone.");

        Optional<ButtonType> result = confirm.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            File historyFile = new File(LOGIN_HISTORY);
            if (historyFile.exists()) {
                historyFile.delete();
            }

            File logFile = new File(LOGIN_LOG);
            if (logFile.exists()) {
                logFile.delete();
            }

            File sessionFile = new File(SESSION_FILE);
            if (sessionFile.exists()) {
                sessionFile.delete();
            }

            statusLabel.setText("✓ Login history cleared.");
            statusLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
        }
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void handleEnterKeyPressed(ActionEvent event) throws IOException {
        handleLogin(event);
    }

    @FXML
    public void handleEscapeKeyPressed(ActionEvent event) {
        handleClear(event);
    }
}