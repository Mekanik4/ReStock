package com.example.restock.objects;

public class Item {
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Item() {
        this.id = -1;
        this.name = "";
        this.price = 0;
        this.quantity = 0;
    }

    public Item(String name, Double price, int quantity, int id){
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
