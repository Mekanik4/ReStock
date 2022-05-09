package com.example.restock.objects;

public class Profile {
    private int profileID;
    private String ownership;
    private String address;
    private String email;
    private int phone;
    private int afm;
    private String password;

    public Profile() {
        this.profileID = -1;
        this.ownership = "";
        this.address = "";
        this.email = "";
        this.phone = 0;
        this.afm = 0;
        this.password = "";
    }

    public Profile(int id, String name, String street, String email, int phone, int afm, String password) {
        this.profileID = id;
        this.ownership = name;
        this.address = street;
        this.email = email;
        this.phone = phone;
        this.afm = afm;
        this.password = password;
    }

    public void setProfileID(int id) {this.profileID = id;}
    public void setPhone(int phone) {this.phone = phone;}
    public void setAfm(int afm) {this.afm = afm;}
    public void setOwnership(String name) {this.ownership = name;}
    public void setAddress(String street) {this.address = street;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}

    public int getProfileID() {return profileID;}
    public int getPhone() {return phone;}
    public int getAfm() {return afm;}
    public String getOwnership() {return ownership;}
    public String getAddress() {return address;}
    public String getEmail() {return  email;}
    public String getPassword() {return password;}
}
