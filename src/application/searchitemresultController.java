package application;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class searchitemresultController implements Initializable {

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
    // Add other columns if there are more fields in the 'items' table

    private String itemname;

    public void setitemname(String name) {
        itemname = name;
        displayItemInfo();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // Initialize columns
    	col_itemID.setCellValueFactory(cellData -> null); // Clear cell value factory
    	col_itemName.setCellValueFactory(cellData -> null); // Clear cell value factory
    	col_itemDescription.setCellValueFactory(cellData -> null); // Clear cell value factory
    	col_itemStatus.setCellValueFactory(null);
        // Set cell value factory manually
    	col_itemID.setCellValueFactory(cellData -> {
            // Here, cellData.getValue() will be an 'Item' object
            return new ReadOnlyObjectWrapper<>(cellData.getValue().getItemID());
        });

    	col_itemName.setCellValueFactory(cellData -> {
            // Here, cellData.getValue() will be an 'Item' object
            return new SimpleStringProperty(cellData.getValue().getItemName());
        });

    	col_itemDescription.setCellValueFactory(cellData -> {
            // Here, cellData.getValue() will be an 'Item' object
            return new SimpleStringProperty(cellData.getValue().getItemDescription());
        });
        // Initialize other columns if needed
    	col_itemStatus.setCellValueFactory(cellData -> {
            // Here, cellData.getValue() will be an 'Item' object
            return new SimpleStringProperty(cellData.getValue().getItemStatus());
        });
    }

    private void displayItemInfo() {
        String url = "jdbc:sqlserver://DESKTOP-QHJR3JI\\SQLEXPRESS;databaseName=SDA_project;integratedSecurity=true;encrypt=false";

        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "SELECT * FROM items WHERE Item_Name = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, itemname);

            ResultSet resultSet = statement.executeQuery();
            ObservableList<Item> itemList = FXCollections.observableArrayList();

            while (resultSet.next()) {
                // Assuming 'Item' class with appropriate constructor; adjust as needed
                Item item = new Item(
                        resultSet.getInt("Item_ID"),
                        resultSet.getString("Item_Name"),
                        resultSet.getString("Item_Description"),
                        resultSet.getString("Item_Status")
                        
                );
                itemList.add(item);
            }

            table_items.setItems(itemList);

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
