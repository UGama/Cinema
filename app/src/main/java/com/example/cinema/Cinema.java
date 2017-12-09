package com.example.cinema;

/**
 * Created by Gama on 12/8/17.
 */

public class Cinema {
    private String Name;
    private String Position;
    private String Pic;
    private String Distance;
    public Cinema() {
    }

    public Cinema(String Name, String Position, String Pic, String Distance) {
        this.Name = Name;
        this.Position = Position;
        this.Pic = Pic;
        this.Distance = Distance;
    }

    public String getName() {
        return Name;
    }

    public String getDistance() {
        return Distance;
    }

    public String getPic() {
        return Pic;
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

    public void setPic(String pic) {
        Pic = pic;
    }

    public void setPosition(String position) {
        Position = position;
    }
}
