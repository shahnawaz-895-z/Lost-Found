package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class removeuserController implements Initializable {
    @FXML
    private Label label_searchitem;
    @FXML
    private TextField tf_userid;
    @FXML
    private Button button_remove;

    private static final String Url = "jdbc:sqlserver://DESKTOP-QHJR3JI\\SQLEXPRESS;databaseName=SDA_project;integratedSecurity=true;encrypt=false";

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        button_remove.setOnAction(event -> handleRemoveUser());
    }

    private void handleRemoveUser() {
        String userID = tf_userid.getText(); // Get the user ID from the text field

        try {
            Connection connection = DriverManager.getConnection(Url);

            String deleteQuery = "DELETE FROM users WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, userID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User with ID " + userID + " removed successfully!");
                // Update UI or provide a notification of successful removal
            } else {
                System.out.println("No user found with ID: " + userID);
                // Handle case where no user was found with the provided ID
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
