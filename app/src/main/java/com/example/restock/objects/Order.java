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

    public void addItem(Item i) { //
        if(this.getItems() == null){
           Item[][] temp = new Item[i.getCategory_id() + 1][1];
           temp[i.getCategory_id()][0] = i;
           this.setItems(temp);
        }
        else {
            Item[][] temp = new Item[this.getItems().length][this.getItems()[0].length + 1];
            for (int cat = 0; cat < this.getItems().length; cat++)
                for (int pos = 0; pos < this.getItems()[0].length; pos++)
                    temp[cat][pos] = this.getItems()[cat][pos];

            for (int cat = 0; cat < temp.length; cat++)
                if (i.getCategory_id() == cat + 1)
                    temp[cat][temp[cat].length - 1] = i;
            this.setItems(temp);
        }
    }

}
