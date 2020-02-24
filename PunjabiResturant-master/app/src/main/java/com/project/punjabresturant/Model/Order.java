package com.project.punjabresturant.Model;

public class Order {
    private String ItemId, ItemName, Quantity,Price;

    public Order(String itemId, String itemName, String quantity, String price) {
        ItemId = itemId;
        ItemName = itemName;
        Quantity = quantity;
        Price = price;
    }

    public Order() {
    }

    public String getItemId() {
        return ItemId;
    }

    public String getItemName() {
        return ItemName;
    }

    public String getQuantity() {
        return Quantity;
    }

    public String getPrice() {
        return Price;
    }

    public void setItemId(String itemId) {
        ItemId = itemId;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
