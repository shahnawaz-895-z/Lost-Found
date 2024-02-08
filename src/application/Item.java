package application;

public class Item {
    private int itemID;
    private String itemName;
    private String itemDescription;
    private String itemStatus;

    public Item(int itemID, String itemName, String itemDescription, String itemStatus) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemStatus = itemStatus;
    }
    public Item(int itemID, String itemName, String itemDescription) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        
    }

    // Getter methods for properties
    public int getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getItemStatus() {
        return itemStatus;
    }
}
