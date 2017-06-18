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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        new HtmlContent().execute("http://www.gettyimagesgallery.com/collections/archive/slim-aarons.aspx");

        adater = new ListViewAdater();

        listview = (ListView)findViewById(R.id.lv1);
        listview.setAdapter(adater);


//        adater.addItem(ContextCompat.getDrawable(this, image_urls.get(0)));
//        adater.addItem(ContextCompat.getDrawable(this, R.drawable.ico_android));


    }
    private class HtmlContent extends AsyncTask {
        URLConnection conntion = null;

        public ArrayList getContent(String strurl) throws MalformedURLException {
            Bitmap bitmap;
            ArrayList<URL> urls = new ArrayList<URL>();

            try {
                Document doc = Jsoup.connect(strurl).get();
                int count = 0;
                for(Element images : doc.select("img")){
                    String imageStr = images.attr("src");
                    URL full_url = new URL("http://www.gettyimagesgallery.com" + images.attr("src"));
                    System.out.println(imageStr);
                    if(!imageStr.substring(0,1).equals("/"))
                        continue;
                    if(count ==10)
                        break;

                    urls.add(full_url);
                    count++;
                };

            } catch (IOException e) {
                e.printStackTrace();
            }
            return urls;
        }

        @Override
        protected Object doInBackground(Object[] params) {
            try {
                return getContent((String)params[0]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return "fail";
            }
        }

        @Override
        protected void onPostExecute(final Object urls) {
            Thread mThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(URL url : (ArrayList<URL>)urls) {
                        Bitmap bitmap ;
                        try{
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setDoInput(true);
                            conn.connect();

                            InputStream is = conn.getInputStream();
                            bitmap = null;
                            bitmap = BitmapFactory.decodeStream(is);

                            adater.addItem(bitmap);
                            adater.notifyDataSetChanged();
                        } catch(Exception e){
                            e.printStackTrace();
                        }

                    }
                }
            });

        }
    }
    private class SetImageHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {


        }
    }
}

