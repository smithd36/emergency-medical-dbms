package application;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class ExpirationsController {
    @FXML
    public TableView<DetailEmployee> tableView;
    @FXML
    private TableColumn<DetailEmployee, String> nameColumn;
    @FXML
    private TableColumn<DetailEmployee, String> licensureLevelColumn;
    @FXML
    private TableColumn<DetailEmployee, String> nearestExpirationColumn;

    @FXML
    public void initialize() {
        // Set up the column cell value factories
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        licensureLevelColumn.setCellValueFactory(new PropertyValueFactory<>("licensureLevel"));
        nearestExpirationColumn.setCellValueFactory(new PropertyValueFactory<>("nearestExpiration"));

        // Retrieve data from the database and populate the TableView
        try {
            // Establish a connection to the MySQL database
            Connection connection = DriverManager.getConnection("jdbc:mysql://pemph.mysql.database.azure.com:3306/pemph?useSSL=true", "serveradminROOT1015978141", "#56789#!!$$45678hhyy");
            Statement statement = connection.createStatement();
            String query = "SELECT NAME, LICENSURE_LEVEL, LEAST(DOT_EXP, PALS_EXP, ACLS_EXP, EMS_EXP, DRIVERS_EXP, BLS_EXP, MVR_EXP) AS NEAREST_EXPIRATION " +
                    "FROM employee";
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String name = resultSet.getString("NAME");
                String licensureLevel = resultSet.getString("LICENSURE_LEVEL");
                String nearestExpiration = resultSet.getString("NEAREST_EXPIRATION");

                DetailEmployee employee = new DetailEmployee(name, licensureLevel, nearestExpiration);
                tableView.getItems().add(employee);
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}