package com.example.restock.objects;

public class Order {
    private int orderNumber;
    private String date;
    private double totalPrice;
    private Item[][] items;
    private String documentPath;
    private boolean completed;

    public Order(){
        this.orderNumber = 0;
        this.date = "";
        this.totalPrice = 0;
        this.items = null;
        this.documentPath = "";
        this.completed = false;
    }

    public Order(int orderNumber, String date, double totalPrice, Item[][] items, String documentPath, boolean completed) {
        this.orderNumber = orderNumber;
        this.date = date;
        this.totalPrice = totalPrice;
        this.items = items;
        this.documentPath = documentPath;
        this.completed = false;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Item[][] getItems() {
        return items;
    }

    public void setItems(Item[][] items) {
        this.items = items;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOrderNumber() {return orderNumber; }
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDocumentPath() {
        return documentPath;
    }

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }

}
