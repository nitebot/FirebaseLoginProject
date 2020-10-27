package com.example.firebaseloginproject;

public class UserProfile {
   // fname, lname, add, dist, state, phno, dt
    public String firstname;
    public String lastname;
    public String address;
    public String district;
    public String state;
    public String phoneno;

    public UserProfile()
    {

    }

    public UserProfile(String firstname, String lastname, String address, String district, String state, String phoneno) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.district = district;
        this.state = state;
        this.phoneno = phoneno;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}
