package com.example.cinema;

import java.util.List;

/**
 * Created by Gama on 11/27/17.
 */

public class Theme {
    private int imageId;
    private String title;
    private String subhead;
    private List<String> filmsNameList;

    public Theme(int imageId, String title, String subhead, List<String> filmsNameList) {
        this.imageId = imageId;
        this.title = title;
        this.subhead = subhead;
        this.filmsNameList = filmsNameList;
    }

    public int getImageId() {
        return imageId;
    }

    public String getSubhead() {
        return subhead;
    }

    public List<String> getFilmsNameList() {
        return filmsNameList;
    }

    public String getTitle() {
        return title;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public void setSubhead(String subhead) {
        this.subhead = subhead;
    }

    public void setFilmsNameList(List<String> filmsNameList) {
        this.filmsNameList = filmsNameList;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
