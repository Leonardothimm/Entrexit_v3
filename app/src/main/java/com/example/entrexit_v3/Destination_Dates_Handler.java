package com.example.entrexit_v3;

public class Destination_Dates_Handler {

    String place;
    String date1, date2;


    public Destination_Dates_Handler() {
    }

    public Destination_Dates_Handler(String place, String date1, String date2) {
        this.place = place;
        this.date1 = date1;
        this.date2 = date2;
    }


    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }
}
