package com.example.ijaehyeon.myapplication;

import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview;
        ListViewAdater adater;

        new HtmlContent().execute("https://www.google.co.kr");

        adater = new ListViewAdater();

        listview = (ListView)findViewById(R.id.lv1);
        listview.setAdapter(adater);
        adater.addItem(ContextCompat.getDrawable(this, R.drawable.android_logo));
        adater.addItem(ContextCompat.getDrawable(this, R.drawable.ico_android));


    }
    private class HtmlContent extends AsyncTask {
        URLConnection conntion = null;

        public String getContent(String strurl) throws MalformedURLException {
            StringBuilder sb = new StringBuilder();

            try {
                BufferedInputStream bis = null;
                URL url = new URL(strurl);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                int responseCode;

                responseCode = conn.getResponseCode();
                if(responseCode == 200) {
                    bis = new BufferedInputStream(conn.getInputStream());
                    BufferedReader reader = new BufferedReader(new InputStreamReader(bis, "UTF-8"));
                    String line =null;

                    while((line = reader.readLine()) != null){
                        sb.append(line);
                    }
                    bis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return sb.toString();
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
        protected void onPostExecute(Object result) {
            System.out.println("result : " + result);
        }
    }
}

