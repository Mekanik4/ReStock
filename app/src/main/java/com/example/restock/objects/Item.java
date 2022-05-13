package com.example.restock.objects;

public class Item {
    private int id;
    private String name;
    private double price;
    private int category_id;
    private int quantity;

    public Item() {
        this.id = -1;
        this.name = "";
        this.price = 0;
        this.category_id = 0;
        this.quantity = 0;
    }

    public Item(String name, Double price, int quantity, int id, int category_id){
        this.id = id;
        this.name = name;
        this.price = price;
        this.category_id = category_id;
        this.quantity = quantity;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
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
