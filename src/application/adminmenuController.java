package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import javafx.scene.control.Label;

public class adminmenuController implements Initializable{
	@FXML
	private Button button_additem;
	@FXML
	private Button button_searchitem;
	@FXML
	private Button button_viewallitems;
	@FXML
	private Button button_logout;
	@FXML
	private Label label_welcome;
	@FXML
	private Button button_viewfeedbacks;
	@FXML
	private Button button_removeitem;
	@FXML
	private Button button_removeuser;
	@FXML
	private Button button_viewusers;
	String username;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		button_logout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				DBUtils.changeScene(event, "prelogin.fxml", "Select User!", null,null);
			}
			
		});
		button_additem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				DBUtils.changeScene(event, "additem.fxml", "Add Item!", null,null);
			}
			
		});
		button_searchitem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				DBUtils.changeScene(event, "searchitem.fxml", "Search Item!", null,null);
			}
			
		});
		button_viewallitems.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				DBUtils.changeScene(event, "viewallitems.fxml", "View All items!", null,null);
			}
			
		});
		button_removeitem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				DBUtils.changeScene(event, "removeitem.fxml", "Remove items!", null,null);
			}
			
		});
		button_removeuser.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				DBUtils.changeScene(event, "removeuser.fxml", "Remove Users!", null,null);
			}
			
		});
		button_viewfeedbacks.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				DBUtils.changeScene(event, "viewallfeedbacks.fxml", "Feedbacks!", null,null);
			}
			
		});
		button_viewusers.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				DBUtils.changeScene(event, "viewusers.fxml", "Users!", null,null);
			}
			
		});
		
	}
	public void setusername(String username) {
		// TODO Auto-generated method stub
		this.username=username;
		
	}

}
