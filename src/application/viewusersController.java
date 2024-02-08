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

public class viewusersController implements Initializable {
    @FXML
    private TableView<Accounts> table_items;

    @FXML
    private TableColumn<Accounts, Integer> col_userID;

    @FXML
    private TableColumn<Accounts, String> col_username;

    @FXML
    private TableColumn<Accounts, String> col_password;

    private static final String Url = "jdbc:sqlserver://DESKTOP-QHJR3JI\\SQLEXPRESS;databaseName=SDA_project;integratedSecurity=true;encrypt=false";

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    	col_userID.setCellValueFactory(new PropertyValueFactory<>("user_id"));
    	col_username.setCellValueFactory(new PropertyValueFactory<>("username"));
    	col_password.setCellValueFactory(new PropertyValueFactory<>("password"));


        try {
            Connection connection = DriverManager.getConnection(Url);
            String query = "SELECT * FROM users";

            ResultSet resultSet = connection.createStatement().executeQuery(query);

            ObservableList<Accounts> userList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                int userID = resultSet.getInt("user_id");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                Accounts user = new Accounts(userID, username, password);
                userList.add(user);
            }

            table_items.setItems(userList);

            resultSet.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
