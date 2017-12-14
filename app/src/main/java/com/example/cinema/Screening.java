package com.example.cinema;

/**
 * Created by Gama on 12/14/17.
 */

public class Screening {
    private String startTime;
    private String endTime;
    private String price;
    private String type;

    public Screening(String startTime, String endTime, String price, String type) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String getPrice() {
        return price;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
