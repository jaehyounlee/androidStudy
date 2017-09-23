package com.example.jaehyoun.nhnpretest;

import android.graphics.Bitmap;

/**
 * Created by ijaehyeon on 2017. 9. 23..
 */

public class ContentsValues {
    String titie;
    String contents;

    Bitmap thumbnail;

    int thumbnail_width;
    int thumbnnail_height;

    public ContentsValues(String titie, String contents) {
        this.titie = titie;
        this.contents = contents;
    }

    public void setThumbnail_width(int thumbnail_width) {
        this.thumbnail_width = thumbnail_width;
    }

    public void setThumbnnail_height(int thumbnnail_height) {
        this.thumbnnail_height = thumbnnail_height;
    }

    public int getThumbnail_width() {
        return thumbnail_width;
    }

    public int getThumbnnail_height() {
        return thumbnnail_height;
    }

    public String getTitie() {
        return titie;
    }

    public String getContents() {
        return contents;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public void setTitie(String titie) {
        this.titie = titie;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setThumbnail(Bitmap thumbnail) {
        this.thumbnail = thumbnail;
    }
}
