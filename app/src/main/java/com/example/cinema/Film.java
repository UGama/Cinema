package com.example.cinema;

/**
 * Created by Gama on 11/17/17.
 */

public class Film {
    private int SourceId;
    private String Name;
    private String Subhead;

    public Film(int SourceId, String Name, String Subhead) {
        this.SourceId = SourceId;
        this.Name = Name;
        this.Subhead = Subhead;
    }

    public int getSourceId() {
        return SourceId;
    }

    public String getSubhead() {
        return Subhead;
    }

    public String getName() {
        return Name;
    }

    public void setSubhead(String subhead) {
        Subhead = subhead;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setSourceId(int sourceId) {
        SourceId = sourceId;
    }
}
