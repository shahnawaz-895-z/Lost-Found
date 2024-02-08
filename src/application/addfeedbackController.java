package application;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.TextArea;

import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

public class addfeedbackController implements Initializable {
	@FXML 
	TextArea tf_feedback;
	@FXML
	Button button_add;
	@FXML
	Button button_usermenu;
	String username;
	

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	button_add.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Feedback f = new Feedback(username, tf_feedback.getText());
				try {
					insertFeedbackToDB(f);
					System.out.println("Feedback Added Successfully");
					tf_feedback.clear();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
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
        // You can perform other initialization here if needed
    }
    public void setusername(String username) {
    	this.username=username;
    }
    public void insertFeedbackToDB(Feedback feedback) throws SQLException {
    	Connection connection= null;
    	String Url = "jdbc:sqlserver://DESKTOP-QHJR3JI\\SQLEXPRESS;databaseName=SDA_project;integratedSecurity=true;encrypt=false";
        connection = DriverManager.getConnection(Url);
        try {
            String insertQuery = "INSERT INTO feedback (username, feedback_text) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            
            preparedStatement.setString(1, feedback.getUsername());
            preparedStatement.setString(2, feedback.getFeedbackText());

            // Execute the query to insert feedback into the database
            preparedStatement.executeUpdate();

            // Optionally, you can show a message confirming the feedback was added successfully
            
            // Close the PreparedStatement after use
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle any potential SQL exceptions here
        }
    }

}

