package com.example.ijaehyeon.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listview;
    ListViewAdater adater;
    final String  PAGRURL = "http://www.gettyimagesgallery.com/collections/archive/slim-aarons.aspx";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView)findViewById(R.id.lv1);
        adater = new ListViewAdater();
        listview.setAdapter(adater);

        ImageLoader loader = new ImageLoader(PAGRURL, listview, adater);
        loader.execute();




//        adater.addItem(ContextCompat.getDrawable(this, image_urls.get(0)));
//        adater.addItem(ContextCompat.getDrawable(this, R.drawable.ico_android));


    }
}

