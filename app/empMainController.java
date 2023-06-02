package application;

import javafx.css.Stylesheet;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.stage.Stage;

public class empMainController {

    @FXML
    private Button addEmp;

    @FXML
    private Button editEmp;

    @FXML
    private Button removeEmp;

    @FXML
    private Button searchEmp;

    @FXML
    private Button allExp;

    @FXML
    private TextField input;

    @FXML
    private TableView<Employee> tableView;

    @FXML
    private TableColumn<Employee, String> nameColumn;

    @FXML
    private TableColumn<Employee, String> emailColumn;

    @FXML
    private TableColumn<Employee, String> cevoIssuedColumn;

    @FXML
    private TableColumn<Employee, String> dotExpColumn;

    @FXML
    private TableColumn<Employee, String> palsExpColumn;

    @FXML
    private TableColumn<Employee, String> aclsExpColumn;

    @FXML
    private TableColumn<Employee, String> emsExpColumn;

    @FXML
    private TableColumn<Employee, String> driversExpColumn;

    @FXML
    private TableColumn<Employee, String> blsExpColumn;

    @FXML
    private TableColumn<Employee, String> mvrExpColumn;

    @FXML
    private TableColumn<Employee, String> licensureLevelColumn;

    private EmpFoundController empFoundController;

    public void setEmpFoundController(EmpFoundController empFoundController) {
        this.empFoundController = empFoundController;
    }

    public void initialize() {
        initializeTableColumns();
        populateTableView();
    }

    private void initializeTableColumns() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        cevoIssuedColumn.setCellValueFactory(cellData -> cellData.getValue().cevoIssuedProperty());
        dotExpColumn.setCellValueFactory(cellData -> cellData.getValue().dotExpProperty());
        palsExpColumn.setCellValueFactory(cellData -> cellData.getValue().palsExpProperty());
        aclsExpColumn.setCellValueFactory(cellData -> cellData.getValue().aclsExpProperty());
        emsExpColumn.setCellValueFactory(cellData -> cellData.getValue().emsExpProperty());
        driversExpColumn.setCellValueFactory(cellData -> cellData.getValue().driversExpProperty());
        blsExpColumn.setCellValueFactory(cellData -> cellData.getValue().blsExpProperty());
        mvrExpColumn.setCellValueFactory(cellData -> cellData.getValue().mvrExpProperty());
        licensureLevelColumn.setCellValueFactory(cellData -> cellData.getValue().licensureLevelProperty());
    }

    private void populateTableView() {
        List<Employee> employeeList = retrieveEmployeesFromDatabase();

        tableView.getItems().setAll(employeeList);
    }

    private List<Employee> retrieveEmployeesFromDatabase() {
        List<Employee> employeeList = new ArrayList<>();

        String url = "jdbc:mysql://pemph.mysql.database.azure.com:3306/pemph?useSSL=true";
        String username = ".env####";
        String password = ".env####";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM Employee";
            try (Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String cevoIssued = resultSet.getString("CEVO_ISS");
                    String dotExp = resultSet.getString("DOT_EXP");
                    String palsExp = resultSet.getString("PALS_EXP");
                    String aclsExp = resultSet.getString("ACLS_EXP");
                    String emsExp = resultSet.getString("EMS_EXP");
                    String driversExp = resultSet.getString("DRIVERS_EXP");
                    String blsExp = resultSet.getString("BLS_EXP");
                    String mvrExp = resultSet.getString("MVR_EXP");
                    String licensureLevel = resultSet.getString("LICENSURE_LEVEL");

                    // Pass an empty string as nearestExpiration since it's not available in the database
                    Employee employee = new Employee(name, email, cevoIssued, dotExp, palsExp, aclsExp, emsExp,
                            driversExp, blsExp, mvrExp, licensureLevel, "");
                    employeeList.add(employee);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential exceptions that occurred during the database interaction
        }

        return employeeList;
    }

    @FXML
    private void addEmp() {
        // Create a grid pane to hold the input fields
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Create text fields for the input
        TextField nameField = new TextField();
        TextField emailField = new TextField();
        TextField cevoIssuedField = new TextField();
        TextField dotExpField = new TextField();
        TextField palsExpField = new TextField();
        TextField aclsExpField = new TextField();
        TextField emsExpField = new TextField();
        TextField driversExpField = new TextField();
        TextField blsExpField = new TextField();
        TextField licensureLevelField = new TextField();
        TextField mvrExpField = new TextField();

        // Add the input fields to the grid pane
        gridPane.addRow(0, new Label("Name:"), nameField);
        gridPane.addRow(1, new Label("Email:"), emailField);
        gridPane.addRow(2, new Label("CEVO Issued:"), cevoIssuedField);
        gridPane.addRow(3, new Label("DOT Exp:"), dotExpField);
        gridPane.addRow(4, new Label("PALS Exp:"), palsExpField);
        gridPane.addRow(5, new Label("ACLS Exp:"), aclsExpField);
        gridPane.addRow(6, new Label("EMS Exp:"), emsExpField);
        gridPane.addRow(7, new Label("Drivers Exp:"), driversExpField);
        gridPane.addRow(8, new Label("BLS Exp:"), blsExpField);
        gridPane.addRow(9, new Label("Licensure Level:"), licensureLevelField);
        gridPane.addRow(10, new Label("MVR Exp:"), mvrExpField);

        // Create an alert to display the entered details
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Employee Details");
        alert.setHeaderText(null);

        // Set the content of the alert to display the entered details
        alert.getDialogPane().setContent(gridPane);

        // Show the alert and wait for the user response
        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                String name = nameField.getText();
                String email = emailField.getText();
                String cevoIssued = cevoIssuedField.getText();
                String dotExp = dotExpField.getText();
                String palsExp = palsExpField.getText();
                String aclsExp = aclsExpField.getText();
                String emsExp = emsExpField.getText();
                String driversExp = driversExpField.getText();
                String blsExp = blsExpField.getText();
                String licensureLevel = licensureLevelField.getText();
                String mvrExp = mvrExpField.getText();

                // Call the method to add the employee to the database
                boolean success = addEmployeeToDatabase(name, email, cevoIssued, dotExp, palsExp, aclsExp, emsExp,
                        driversExp, blsExp, licensureLevel, mvrExp);

                if (success) {
                    // Display a success message
                    Alert successAlert = new Alert(AlertType.INFORMATION);
                    successAlert.setTitle("Success");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Employee added successfully!");
                    populateTableView();
                    successAlert.showAndWait();
                } else {
                    // Display an error message
                    Alert errorAlert = new Alert(AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Failed to add employee to the database.");
                    errorAlert.showAndWait();
                }
            }
        });
    }

    private boolean addEmployeeToDatabase(String name, String email, String cevoIssued, String dotExp, String palsExp,
            String aclsExp, String emsExp, String driversExp, String blsExp,
            String licensureLevel, String mvrExp) {
        String url = "jdbc:mysql://pemph.mysql.database.azure.com:3306/pemph?useSSL=true";
        String username = ".env##";
        String password = ".env##";

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "INSERT INTO Employee (name, email, CEVO_ISS, DOT_EXP, PALS_EXP, ACLS_EXP, EMS_EXP, " +
                    "DRIVERS_EXP, BLS_EXP, LICENSURE_LEVEL, MVR_EXP) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setString(2, email);
                statement.setString(3, cevoIssued);
                statement.setString(4, dotExp);
                statement.setString(5, palsExp);
                statement.setString(6, aclsExp);
                statement.setString(7, emsExp);
                statement.setString(8, driversExp);
                statement.setString(9, blsExp);
                statement.setString(10, licensureLevel);
                statement.setString(11, mvrExp);

                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential exceptions that occurred during the database interaction
            return false;
        }
    }

    @FXML
    private void searchEmp() throws IOException {

        String inputEmail = input.getText(); // Get the input from the email input (only input - email is SK) text field

        // Database connection parameters
        String url = "jdbc:mysql://pemph.mysql.database.azure.com:3306/pemph?useSSL=true";
        String username = ".env##.env##";
        String password = "#.env.env##y";

        // SQL query to retrieve the email from the database
        String query = "SELECT email FROM Employee WHERE email = ?";

        try {
            // Establish a database connection
            Connection connection = DriverManager.getConnection(url, username, password);

            // Create a prepared statement with the query
            PreparedStatement statement = connection.prepareStatement(query);

            // Set the parameter in the prepared statement
            statement.setString(1, inputEmail);

            // Execute the query
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Email found in the database
                String retrievedEmail = resultSet.getString("email");

                if (inputEmail.equals(retrievedEmail)) {
                    // Load the new FXML file
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("empFound.fxml"));
                    Parent empFoundRoot = loader.load();

                    // Get the controller of the new FXML file
                    EmpFoundController empFoundController = loader.getController();

                    // Pass any necessary data to the controller
                    empFoundController.searchEmp(retrievedEmail);

                    // Set the new FXML file as the content of the current scene
                    Scene scene = input.getScene();
                    scene.setRoot(empFoundRoot);
                } else {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Employee Not Found");
                    alert.setHeaderText(null);
                    alert.setContentText("Employee not found. Please try again.");
                    alert.showAndWait();
                }
            } else {
                // Perform actions when the email is not found in the database
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Employee Not Found");
                alert.setHeaderText(null);
                alert.setContentText("Employee not found. Please try again.");
                alert.showAndWait();
            }

            // Close the database resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any errors that may occur during the database connection or query execution
        }
    }

    @FXML
    private void toExpirations() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Expirations.fxml"));
            Parent root = loader.load();

            // Create an instance of the ExpirationsController
            ExpirationsController expirationsController = loader.getController();

            // Check if the TableView is already populated
            if (expirationsController.tableView.getItems().isEmpty()) {
                // Retrieve data and populate the TableView
                expirationsController.initialize();
            }

            // Create a new stage for the new scene
            Stage newStage = new Stage();
            newStage.setScene(new Scene(root));

            // Get the current stage and close it
            Stage currentStage = (Stage) allExp.getScene().getWindow();
            currentStage.close();

            // Show the new stage
            newStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void removeEmp() {
    	// handle the "Remove employee from database" button
    	Employee selectedEmployee = tableView.getSelectionModel().getSelectedItem();

        if (selectedEmployee != null) {
            String employeeEmail = selectedEmployee.emailProperty().get();

            // Database connection parameters
            String url = "jdbc:mysql://pemph.mysql.database.azure.com:3306/pemph?useSSL=true";
            String username = ".env##.env##";
            String password = ".env##.env##";

            // SQL query to remove an employee by email
            String query = "DELETE FROM employee WHERE email = ?";

            try (Connection connection = DriverManager.getConnection(url, username, password);
                 PreparedStatement statement = connection.prepareStatement(query)) {

                // Set the employee email parameter
                statement.setString(1, employeeEmail);

                // Execute the query
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    // Employee removed successfully
                    showAlert("Employee Removed", "The employee has been removed from the database.");
                    tableView.getItems().remove(selectedEmployee); // Remove the employee from the TableView
                } else {
                    // No employee found with the given email
                    showAlert("Employee Not Found", "No employee found with the given email.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Handle any errors that may occur during the database connection or query execution
            }
        } else {
            showAlert("No Selection", "Please select an employee to remove.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
