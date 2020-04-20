package com.example.entrexit_v3;

import java.util.HashMap;

public class Username_Accounts {

    String firstName, lastName, email, dob, passOne, passTwo, cardName;
    String cardNum, expiry, cvv;
   // private HashMap<String, Object> users;

    public Username_Accounts(String firstName, String lastName, String email, String dob, String passOne, String passTwo, String cardName, String cardNum, String expiry, String cvv){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.dob = dob;
        this.passOne = passOne;
        this.passTwo = passTwo;
        this.cardName = cardName;
        this.cardNum = cardNum;
        this.expiry = expiry;
        this.cvv = cvv;
    }

    public Username_Accounts(){
        }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassOne() {
        return passOne;
    }

    public void setPassOne(String passOne) {
        this.passOne = passOne;
    }

    public String getPassTwo() {
        return passTwo;
    }

    public void setPassTwo(String passTwo) {
        this.passTwo = passTwo;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

}
