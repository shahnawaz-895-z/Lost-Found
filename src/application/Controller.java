package application;

import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class Controller implements Initializable{
	@FXML 
	private Button button_login;
	
	@FXML 
	private Button button_sign_up;
	
	@FXML 
	private TextField tf_username;
	
	@FXML 
	private TextField tf_password;
	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		button_login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DBUtils.logInUser(event,tf_username.getText(),tf_password.getText());
			}
		});
		
		button_sign_up.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DBUtils.changeScene(event,"sign-up.fxml","Sign Up!",null,null);
			}
		});
		
	}
}