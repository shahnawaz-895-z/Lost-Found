package application;

import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


import javafx.scene.control.PasswordField;

public class adminloginController implements Initializable{
	@FXML
	private PasswordField tf_password;
	@FXML
	private TextField tf_username;
	@FXML
	private Button button_login;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		button_login.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DBUtils.logInAdmin(event,tf_username.getText(),tf_password.getText());
			}
		});
		
		
		
	}
}