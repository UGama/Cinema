package com.example.cinema;

/**
 * Created by Gama on 12/10/17.
 */

public class Schedule {
    private String Time;
    private String Price;
    private String Type;

    public Schedule(String Time, String Price, String Type) {
        this.Time = Time;
        this.Price = Price;
        this.Type = Type;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getPrice() {
        return Price;
    }

    public String getTime() {
        return Time;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getType() {
        return Type;
    }
}
