package com.example.ijaehyeon.myapplication;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.widget.ListView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ijaehyeon on 2017. 6. 18..
 */

public class ImageLoader extends AsyncTask{


    String url;
    ListView mListview;
    ListViewAdater mAdater;
    ArrayList<URL> urls;
    Bitmap bitmap;

    Handler handler = new Handler();

    public ImageLoader(String strUrl, ListView listview, ListViewAdater adater ) {
        this.url = strUrl;
        mListview = listview;
        mAdater = adater;
    }

    private void getImageUrls() {
        urls = new ArrayList<URL>();

        try {
            Document doc = Jsoup.connect(url).get();
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
    }

    private void setImageToListView(){
        Thread bmlThread = new Thread(new BitmapLoaderThread());
        bmlThread.start();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        getImageUrls();
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        Thread bmlThread = new Thread(new BitmapLoaderThread());
        bmlThread.start();

    }

    private class BitmapLoaderThread implements Runnable {


        @Override
        public void run() {

            for(URL url : urls) {
                try{

                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.connect();

                    InputStream is = conn.getInputStream();
                    BitmapFactory.Options option = new BitmapFactory.Options();
                    option.inSampleSize = 4;
                    bitmap = null;
                    bitmap = BitmapFactory.decodeStream(is, null, option);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mAdater.addItem(bitmap);
                            mAdater.notifyDataSetChanged();
                        }
                    });
                } catch(Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
