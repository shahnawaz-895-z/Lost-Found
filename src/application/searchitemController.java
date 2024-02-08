package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;



public class searchitemController implements Initializable {
	@FXML
	Label label_searchitem;
	@FXML
	Button button_search;
	@FXML
	TextField tf_itemname;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
       label_searchitem.setText("Search Item");
        // You can perform other initialization here if needed
       button_search.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				DBUtils.changeScene(event, "searchitemresult.fxml", "Search Item Result",null, tf_itemname.getText());
			}
			
		});
    }
}


