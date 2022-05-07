package com.example.restock.objects;

public class Item {
    private String name;
    private double price;
    private int quantity;

    public Item() {
        this.name = "";
        this.price = 0;
        this.quantity = 0;
    }

    public Item(String name, Double price, int quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }
}
