package com.example.restock.objects;

public class Profile {
    private int profileID;
    private String ownership;
    private String address;
    private String email;
    private String phone;
    private String afm;
    private String password;
    private boolean signedIn;

    public Profile() {
        this.profileID = -1;
        this.ownership = "";
        this.address = "";
        this.email = "";
        this.phone = "";
        this.afm = "";
        this.password = "";
        this.signedIn = false;
    }

    public Profile(int id, String name, String street, String email, String phone, String afm, String password, boolean signedIn) {
        this.profileID = id;
        this.ownership = name;
        this.address = street;
        this.email = email;
        this.phone = phone;
        this.afm = afm;
        this.password = password;
        this.signedIn = signedIn;
    }

    public void setProfileID(int id) {this.profileID = id;}
    public void setPhone(String phone) {this.phone = phone;}
    public void setAfm(String afm) {this.afm = afm;}
    public void setOwnership(String name) {this.ownership = name;}
    public void setAddress(String street) {this.address = street;}
    public void setEmail(String email) {this.email = email;}
    public void setPassword(String password) {this.password = password;}

    public boolean isSignedIn() {
        return signedIn;
    }

    public void setSignedIn(boolean signedIn) {
        this.signedIn = signedIn;
    }

    public int getProfileID() {return profileID;}
    public String getPhone() {return phone;}
    public String getAfm() {return afm;}
    public String getOwnership() {return ownership;}
    public String getAddress() {return address;}
    public String getEmail() {return  email;}
    public String getPassword() {return password;}

}
