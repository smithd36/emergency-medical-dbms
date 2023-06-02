package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class EmpFoundController {
    @FXML
    private TableView<DetailEmployee> tableView;

    @FXML
    private TableColumn<DetailEmployee, String> nameColumn;

    @FXML
    private TableColumn<DetailEmployee, String> licensureLevelColumn;

    @FXML
    private TableColumn<DetailEmployee, String> nearestExpirationColumn;

    private empMainController empMainController;

    public void setEmpMainController(empMainController empMainController) {
        this.empMainController = empMainController;
    }

    @FXML
    public void initialize() {
        // Configure cell value factories for each column
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        licensureLevelColumn.setCellValueFactory(cellData -> cellData.getValue().licensureLevelProperty());
        nearestExpirationColumn.setCellValueFactory(cellData -> {
            StringProperty nearestExpirationProperty = cellData.getValue().nearestExpirationProperty();
            return nearestExpirationProperty;
        });
    }

    public void searchEmp(String email) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://pemph.mysql.database.azure.com:3306/pemph?useSSL=true", "serveradminROOT1015978141", "#56789#!!$$45678hhyy")) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT NAME, LICENSURE_LEVEL, LEAST(DOT_EXP, PALS_EXP, ACLS_EXP, EMS_EXP, DRIVERS_EXP, BLS_EXP, MVR_EXP) AS nearestExpiration " +
                            "FROM employee WHERE EMAIL = ?");
            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("NAME");
                String licensureLevel = resultSet.getString("LICENSURE_LEVEL");
                String nearestExpiration = resultSet.getString("nearestExpiration");

                DetailEmployee employee = new DetailEmployee(name, licensureLevel, nearestExpiration);
                tableView.getItems().add(employee);
            } else {
                // No employee found with the given email
                // Display a pop-up message
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Employee Not Found");
                alert.setHeaderText(null);
                alert.setContentText("Employee not found. Please try again.");
                alert.showAndWait();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void back() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("empMain.fxml"));
            Parent root = loader.load();

            // Get the current scene
            Scene scene = tableView.getScene();

            // Set the empMain.fxml as the content of the scene
            scene.setRoot(root);

            // Set the empMainController reference in the newly loaded empMainController
            empMainController newEmpMainController = loader.getController();
            newEmpMainController.setEmpFoundController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}