package com.example.restock.objects;

public class Supplier {
    private int supplierId;
    private String name, VAT_number, phone_number, address, email;

    public Supplier(int supplierId, String name, String VAT_number, String phone_number, String address, String email) {
        this.supplierId = supplierId;
        this.name = name;
        this.VAT_number = VAT_number;
        this.phone_number = phone_number;
        this.address = address;
        this.email = email;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVAT_number() {
        return VAT_number;
    }

    public void setVAT_number(String VAT_number) {
        this.VAT_number = VAT_number;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
