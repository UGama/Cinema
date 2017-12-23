package com.example.cinema;

import android.graphics.Bitmap;

/**
 * Created by Gama on 11/17/17.
 */

public class Film {
    private Bitmap bitmap;
    private String Name;
    private String Subhead;
    private String time;
    private String country;
    private String releaseDate;
    private String type;
    private String url;

    public Film(String Name) {
        this.Name = Name;
    }
    public Film(String Name, String Subhead) {
        this.Name = Name;
        this.Subhead = Subhead;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getSubhead() {
        return Subhead;
    }

    public String getName() {
        return Name;
    }

    public String getType() {
        return type;
    }

    public String getTime() {
        return time;
    }

    public String getCountry() {
        return country;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getUrl() {
        return url;
    }

    public void setSubhead(String subhead) {
        Subhead = subhead;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
