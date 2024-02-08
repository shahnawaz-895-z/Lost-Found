package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class viewallfeedbacksController implements Initializable {
    @FXML
    private TableView<Feedback> table_items;

    @FXML
    private TableColumn<Feedback, String> col_username;

    @FXML
    private TableColumn<Feedback, String> col_feedback;

    private static final String Url = "jdbc:sqlserver://DESKTOP-QHJR3JI\\SQLEXPRESS;databaseName=SDA_project;integratedSecurity=true;encrypt=false";

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
    	col_feedback.setCellValueFactory(new PropertyValueFactory<>("feedbackText"));


        try {
            Connection connection = DriverManager.getConnection(Url);
            String query = "SELECT username, feedback_text FROM feedback";


            ResultSet resultSet = connection.createStatement().executeQuery(query);

            ObservableList<Feedback> feedbacksList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String feedback = resultSet.getString("feedback_text");

                Feedback feedbackItem = new Feedback(username, feedback);
                feedbacksList.add(feedbackItem);
            }

            table_items.setItems(feedbacksList);

            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
