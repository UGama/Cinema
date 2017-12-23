package com.example.cinema;

/**
 * Created by Gama on 12/8/17.
 */

public class Cinema {
    private String Name;
    private String Position;
    private String Distance;
    public Cinema() {
    }

    public Cinema(String Name, String Position, String Distance) {
        this.Name = Name;
        this.Position = Position;
        this.Distance = Distance;
    }

    public String getName() {
        return Name;
    }

    public String getDistance() {
        return Distance;
    }


    public String getPosition() {
        return Position;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDistance(String distance) {
        Distance = distance;
    }

    public void setPosition(String position) {
        Position = position;
    }
}
