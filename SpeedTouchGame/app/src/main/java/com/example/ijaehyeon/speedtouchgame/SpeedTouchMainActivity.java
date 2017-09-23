package com.example.ijaehyeon.speedtouchgame;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class SpeedTouchMainActivity extends AppCompatActivity {

    Activity act = this;
    ArrayList<Drawable> imgData = new ArrayList<Drawable>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed_touch);
        setIntImge();

        ImageGridAdapter gvAdapter = new ImageGridAdapter(this.getApplicationContext(), imgData );

        GridView gv = (GridView) findViewById(R.id.grdView);
        gv.setAdapter(gvAdapter);
    }

    private void setIntImge(){

        for(int i = 0 ; i < 20 ; i ++) {
            Bitmap bitmap = null;
            if(i%2 == 0) {
                bitmap = Bitmap.createBitmap(240, 240, Bitmap.Config.ARGB_8888);
                bitmap.eraseColor(Color.GRAY);
            } else {
                bitmap = Bitmap.createBitmap(240, 240, Bitmap.Config.ARGB_8888);
                bitmap.eraseColor(Color.WHITE);
            }
            Drawable img = new BitmapDrawable(getResources(), bitmap);
            imgData.add(img);
        }
    }

}
