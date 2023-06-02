package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class accessController {
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Perform user authentication logic
        boolean isAuthenticated = authenticateUser(username, password);

        if (isAuthenticated) {
            // Navigate to the main application page
            navigateToMainPage();
        } else {
            // Show an error message to the user
            showErrorAlert("Invalid credentials. Please try again.");
        }
    }

    private boolean authenticateUser(String username, String password) {
        // Database connection details
        String url = "jdbc:sqlite:Lockbox.db";

        try {
            // Establish a connection to the database
            Connection connection = DriverManager.getConnection(url);

            // Create a prepared statement to execute the query
            String query = "SELECT COUNT(*) FROM pick WHERE user = ? AND pass = ?";
            PreparedStatement statement = connection.prepareStatement(query);

            // Set the parameters in the prepared statement
            statement.setString(1, username);
            statement.setString(2, password);

            // Execute the query and retrieve the result
            ResultSet resultSet = statement.executeQuery();

            // Check if a row with the provided username and password exists
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;  // Return true if a match is found, false otherwise
            }

            // Close the database resources
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;  // Return false if an error occurs or no match is found
    }

    private void navigateToMainPage() {
        try {
            // Load the FXML file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("empMain.fxml"));
            Parent root = loader.load();

            // Create a new scene with the loaded FXML root
            Scene scene = new Scene(root);

            // Get the current stage
            Stage currentStage = (Stage) usernameField.getScene().getWindow();

            // Set the scene on the stage
            currentStage.setScene(scene);

            // Show the stage
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}