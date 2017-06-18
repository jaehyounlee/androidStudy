package com.example.ijaehyeon.myapplication;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

/**
 * Created by ijaehyeon on 2017. 5. 13..
 */

public class ListViewItem {
    private Bitmap img;

    public void setImg(Bitmap image) {
        img = image;
    }
    public Bitmap getImg(){
        return img;
    }
}
