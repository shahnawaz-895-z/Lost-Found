package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

public class SignUpController implements Initializable {

	@FXML
	private Button button_signup;
	
	@FXML
	private Button button_log_in;
	
	@FXML 
	private TextField tf_username;
	
	@FXML 
	private TextField tf_password;
	
	@FXML
	private CheckBox rb_terms;
	
	@FXML 
	private TextField tf_confirmpassword;
	
	@Override
	public void initialize(URL Location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		button_signup.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if(!tf_username.getText().trim().isEmpty() && !tf_password.getText().trim().isEmpty()) {
					if(rb_terms.isSelected()) {
						if(tf_confirmpassword.getText().equals(tf_password.getText())) {
							DBUtils.signUpUser(event, tf_username.getText(), tf_password.getText());
						}
						else {
							Alert alert = new Alert(Alert.AlertType.ERROR);
							alert.setContentText("Passwords Doesn't Match!");
							alert.show();
						}
					}
					else {
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setContentText("Please agree to the terms!");
						alert.show();
					}
				}
				else {
					System.out.println("Please fill in all the information");
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setContentText("Please fill in all the information to sign up!");
					alert.show();
				}
			}
		});
		
		button_log_in.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DBUtils.changeScene(event, "sample.fxml", "Log in!", null,null);
			}
		});
	}
	
}
