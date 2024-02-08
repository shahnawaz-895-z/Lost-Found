package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class preloginController implements Initializable{
	@FXML
	private Button button_admin;
	@FXML
	private Button button_user;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		button_admin.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DBUtils.changeScene(event,"adminlogin.fxml","Sign Up!",null,null);
			}
		});
		
		button_user.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DBUtils.changeScene(event,"Sample.fxml","Sign Up!",null,null);
			}
		});
		
	}
}
