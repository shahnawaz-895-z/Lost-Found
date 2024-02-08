package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class additemController implements Initializable {
@FXML
Label label_additem;
@FXML
Button button_additem;
@FXML
TextField tf_itemid;
@FXML
TextField tf_itemname;
@FXML
TextArea tf_itemdescription;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        label_additem.setText("Add Item");
        // You can perform other initialization here if needed
        button_additem.setOnAction(event -> {
        	int itemID = Integer.parseInt(tf_itemid.getText());
            String itemName = tf_itemname.getText();
            String itemDescription = tf_itemdescription.getText();

            // Check if the item name and description are not empty before adding
            if (!itemName.isEmpty() && !itemDescription.isEmpty()) {
                try {
                    // Establish the connection using the provided URL
                    String url = "jdbc:sqlserver://DESKTOP-QHJR3JI\\SQLEXPRESS;databaseName=SDA_project;integratedSecurity=true;encrypt=false";
                    Connection connection = DriverManager.getConnection(url);

                    // Prepare the SQL statement for insertion
                    String query = "INSERT INTO items (Item_ID, Item_Name, Item_Description) VALUES (?, ?, ?)";
                    PreparedStatement statement = connection.prepareStatement(query);

                    // Set the parameters for the prepared statement
                    statement.setInt(1, itemID);
                    statement.setString(2, itemName);
                    statement.setString(3, itemDescription);

                    // Execute the update
                    int rowsInserted = statement.executeUpdate();

                    if (rowsInserted > 0) {
                        System.out.println("Item added successfully!");
                        // Optionally, you can update the table view here to reflect the changes
                    } else {
                        System.out.println("Failed to add item!");
                    }

                    // Close the resources
                    statement.close();
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Please fill in both item name and description.");
            }
        });


    }
}


