package com.example.jaehyoun.nhnpretest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageLoader extends AsyncTask {

    private String imageUrl;
    private ImageView imgView;



    ImageLoader(String imageUrl, ImageView imgView) {
        this.imageUrl = imageUrl;
        this.imgView = imgView;

        imgView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {
                System.out.println("Attach ");
            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                System.out.println("Detach ");
                ImageLoader.this.imageUrl = null;
                ImageLoader.this.imgView = null;
            }
        });
    }

    @Override
    protected Object doInBackground(Object[] params) {
        if(imageUrl == null) return null;
        try{
            URL thumbnail_URL = new URL(imageUrl);
            HttpURLConnection conn = (HttpURLConnection) thumbnail_URL.openConnection();
            conn.setDoInput(true);
            conn.connect();

            InputStream is = conn.getInputStream();
            return BitmapFactory.decodeStream(is, null, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object bitmap) {
        if(imgView == null) return ;
        if(bitmap == null){
            imgView.setImageDrawable(ContextCompat.getDrawable(imgView.getContext(), R.drawable.ic_launcher));
        } else {
            imgView.setImageBitmap((Bitmap)bitmap);
        }

    }
}
