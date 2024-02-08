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

public class removeitemController implements Initializable {
    @FXML
    private Label label_searchitem;
    @FXML
    private TextField tf_itemid;
    @FXML
    private Button button_remove;

    private static final String Url = "jdbc:sqlserver://DESKTOP-QHJR3JI\\SQLEXPRESS;databaseName=SDA_project;integratedSecurity=true;encrypt=false";

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        button_remove.setOnAction(event -> handleRemoveItem());
    }

    private void handleRemoveItem() {
        String itemID = tf_itemid.getText(); // Get the item ID from the text field

        try {
            Connection connection = DriverManager.getConnection(Url);

            String deleteQuery = "DELETE FROM items WHERE item_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery);
            preparedStatement.setString(1, itemID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Item with ID " + itemID + " removed successfully!");
                // Update UI or provide a notification of successful removal
            } else {
                System.out.println("No item found with ID: " + itemID);
                // Handle case where no item was found with the provided ID
            }

            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
