package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class selectmyitemController implements Initializable {
    @FXML
    Label label_searchitem;
    @FXML
    Button button_done;
    @FXML
    Button button_usermenu;
    @FXML
    TextField tf_id;
    String username;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        label_searchitem.setText("Select Item");
        // You can perform other initialization here if needed
        button_done.setOnAction(event -> {
            String itemIdText = tf_id.getText();
            if (!itemIdText.isEmpty()) {
                int itemId = Integer.parseInt(itemIdText);
                updateItemStatus(itemId);
            } else {
                System.out.println("Please enter an Item ID");
            }
        });
        button_usermenu.setOnAction(event -> {
        	String fxmlFile="logged-in.fxml";
        	Parent root = null;
    	    FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource(fxmlFile));
    	    try {
				root = loader.load();
				LoggedInController loggedInController = loader.getController();
	            loggedInController.setUserInformation(username);
	            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		        stage.setTitle("User Menu");
		        stage.setScene(new Scene(root, 600, 400));
		        stage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        });
    }

    private void updateItemStatus(int itemId) {
        String url = "jdbc:sqlserver://DESKTOP-QHJR3JI\\SQLEXPRESS;databaseName=SDA_project;integratedSecurity=true;encrypt=false";
        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "UPDATE items SET Item_Status = 'found' WHERE Item_ID = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, itemId);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Item with ID " + itemId + " has been marked as found.");
            } else {
                System.out.println("Item with ID " + itemId + " not found.");
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void setusername(String username) {
    	this.username=username;
    }
}
