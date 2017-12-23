package com.example.cinema;

import android.graphics.Bitmap;

/**
 * Created by Gama on 11/27/17.
 */

public class Theme {
    private Bitmap bitmap;
    private String title;
    private String subhead;

    public Theme(String title, String subhead) {
        this.title = title;
        this.subhead = subhead;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public String getSubhead() {
        return subhead;
    }

    public String getTitle() {
        return title;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public void setSubhead(String subhead) {
        this.subhead = subhead;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
