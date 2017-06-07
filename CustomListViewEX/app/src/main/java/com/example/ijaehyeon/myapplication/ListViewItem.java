package com.example.ijaehyeon.myapplication;

import android.graphics.drawable.Drawable;

/**
 * Created by ijaehyeon on 2017. 5. 13..
 */

public class ListViewItem {
    private Drawable img;

    public void setImg(Drawable image) {
        img = image;
    }
    public Drawable getImg(){
        return img;
    }
}
