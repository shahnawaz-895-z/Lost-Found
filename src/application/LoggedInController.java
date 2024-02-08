package application;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
public class LoggedInController implements Initializable{
	@FXML
	private Button button_logout;
	@FXML
	private Label label_welcome;
	@FXML
	private Button button_additem;
	@FXML
	private Button button_searchitem;
	@FXML
	private Button button_viewallitems;
	@FXML
	private Button button_viewmyitems;
	@FXML
	private Button button_addfeedback;
	@FXML
	private Button button_selectmyitem;
	String username;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		button_logout.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				DBUtils.changeScene(event, "sample.fxml", "Log in!", null,null);
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
		button_viewmyitems.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				DBUtils.changeScene(event, "viewmyitems.fxml", "View My Items!", null,null);
			}
			
		});
		button_addfeedback.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				DBUtils.changeScene(event, "addfeedback.fxml", "Add Feedback!", username,null);
			}
			
		});
		button_selectmyitem.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Parent root = null;
			    FXMLLoader loader = new FXMLLoader(DBUtils.class.getResource("selectmyitem.fxml"));
				selectmyitemController smic = loader.getController();
				DBUtils.changeScene(event, "selectmyitem.fxml", "select my item", username,null);
			}
			
		});
	}
	public void setUserInformation(String username) {
		label_welcome.setText("Welcome "+username+ "!");
		this.username=username;
	}
}
