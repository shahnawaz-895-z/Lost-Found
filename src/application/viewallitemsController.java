package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class viewallitemsController implements Initializable {

    @FXML
    private TableView<Item> table_items;

    @FXML
    private TableColumn<Item, Integer> col_itemID;

    @FXML
    private TableColumn<Item, String> col_itemName;

    @FXML
    private TableColumn<Item, String> col_itemDescription;

    @FXML
    private TableColumn<Item, String> col_itemStatus;

    private ObservableList<Item> itemList;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        itemList = FXCollections.observableArrayList();
        initializeColumns();
        loadDataFromDatabase();
    }

    private void initializeColumns() {
        col_itemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        col_itemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        col_itemDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        col_itemStatus.setCellValueFactory(new PropertyValueFactory<>("itemStatus"));
    }

    private void loadDataFromDatabase() {
        // Assuming you have a SQLite database named "your_database.db"
    	Connection connection = null;
    	String Url = "jdbc:sqlserver://DESKTOP-QHJR3JI\\SQLEXPRESS;databaseName=SDA_project;integratedSecurity=true;encrypt=false";
        try {
			connection = DriverManager.getConnection(Url);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Connection Successful");
        String query = "SELECT * FROM items";

        try (Connection conn = DriverManager.getConnection(Url);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int itemID = rs.getInt("Item_ID");
                String itemName = rs.getString("Item_Name");
                String itemDescription = rs.getString("Item_Description");
                String itemStatus = rs.getString("Item_Status");

                itemList.add(new Item(itemID, itemName, itemDescription, itemStatus));
            }

            table_items.setItems(itemList);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
