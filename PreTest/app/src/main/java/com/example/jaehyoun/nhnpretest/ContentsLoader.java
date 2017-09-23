package com.example.jaehyoun.nhnpretest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.HttpConnector;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ijaehyeon on 2017. 6. 18..
 */

public class ContentsLoader extends AsyncTask{

    public static String CONTENTS_SUMMARY = "summary";
    public static String CONTENTS_RELATED = "related";

    final private String SUMMARY_URL = "https://en.wikipedia.org/api/rest_v1/page/summary/";
    final private String RELATED_URL = "https://en.wikipedia.org/api/rest_v1/page/related/";

    String value_gb;
    View mView;
    ListView mListView;

    public ContentsLoader(View headerView, ListView listView) {
        this.mView = headerView;
        this.mListView = listView;
    }


    @Override
    protected Object doInBackground(Object[] params) {

        String keyWord = params[0].toString(); // 키워드가 넘어온다.

        HttpConnector connector = new HttpConnector();
        ArrayList<ContentsValues> values = new ArrayList<ContentsValues>();
        try {
            connector.setTargetUrl(SUMMARY_URL + keyWord);
            connector.setMethod("GET");
            String response = connector.openConnenction(); // Json타입으로 응답값이 넘어온다
            System.out.println("datas : " + response);

            JSONObject jsonObject = new JSONObject(response);

            String title = (String) jsonObject.get("title");
            String contents = jsonObject.getString("extract_html");

            ContentsValues value = new ContentsValues(title, contents);

            JSONObject thumbnail = null;
            if (!jsonObject.isNull("thumbnail")) { // 썸네이링 있는경우 가져와서 bitmap 생성
                thumbnail = jsonObject.getJSONObject("thumbnail");
                int width = Integer.parseInt(thumbnail.getString("width"));
                int height = Integer.parseInt(thumbnail.getString("height"));

                String thumbnail_src = thumbnail.getString("source");
                URL thumbnail_URL = new URL(thumbnail_src);
                HttpURLConnection conn = (HttpURLConnection) thumbnail_URL.openConnection();
                conn.setDoInput(true);
                conn.connect();

                InputStream is = conn.getInputStream();
                Bitmap bitmap = null;
                bitmap = BitmapFactory.decodeStream(is, null, null);

                value.setThumbnail(bitmap);
                value.setThumbnail_width(width);
                value.setThumbnnail_height(height);


                System.out.println(thumbnail.toString());
            }
            values.add(value);

            connector.setTargetUrl(RELATED_URL + keyWord);
            connector.setMethod("GET");
            response = connector.openConnenction(); // Json타입으로 응답값이 넘어온다
            System.out.println("datas : " + response);

            jsonObject = new JSONObject(response);
            JSONArray ja = jsonObject.getJSONArray("pages");


            title = (String) jsonObject.get("title");
            contents = jsonObject.getString("extract_html");

            value = new ContentsValues(title, contents);

            thumbnail = null;
            if (!jsonObject.isNull("thumbnail")) { // 썸네이일 있는경우 가져와서 bitmap 생성
                thumbnail = jsonObject.getJSONObject("thumbnail");
                int width = Integer.parseInt(thumbnail.getString("width"));
                int height = Integer.parseInt(thumbnail.getString("height"));

                String thumbnail_src = thumbnail.getString("source");
                URL thumbnail_URL = new URL(thumbnail_src);
                HttpURLConnection conn = (HttpURLConnection) thumbnail_URL.openConnection();
                conn.setDoInput(true);
                conn.connect();

                InputStream is = conn.getInputStream();
                Bitmap bitmap = null;
                bitmap = BitmapFactory.decodeStream(is, null, null);

                value.setThumbnail(bitmap);
                value.setThumbnail_width(width);
                value.setThumbnnail_height(height);


                System.out.println(thumbnail.toString());
            }



            return values;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        ContentsValues value = (ContentsValues)o;
        ImageView summaryImage = (ImageView) mView.findViewById(R.id.header_image);
        TextView display = (TextView) mView.findViewById(R.id.display_title);
        TextView contents = (TextView) mView.findViewById(R.id.header_contents);

        if(value.getThumbnail() !=null) {
            summaryImage.setImageBitmap(value.getThumbnail());
        }
        display.setText(value.getTitie());
        contents.setText(value.getContents());

    }
}
