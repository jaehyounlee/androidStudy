package com.example.jaehyoun.nhnpretest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.inputmethodservice.InputMethodService;
import android.os.AsyncTask;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ConnectionConfgure;
import com.example.HttpConnector;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by ijaehyeon on 2017. 6. 18..
 * Thumbnail, title, contents를 받아서 생성해주는 로더
 */

class ContentsLoader extends AsyncTask{


    final private String SUMMARY_URL = "https://en.wikipedia.org/api/rest_v1/page/summary/";
    final private String RELATED_URL = "https://en.wikipedia.org/api/rest_v1/page/related/";

    private View mView;
    private ArrayList<ContentsValues> releatedValueList;
    RelatedListAdapter mAdapter;

    ContentsLoader(View header, ArrayList<ContentsValues> releateValueList, RelatedListAdapter adapter) {
        this.mView = header;
        this.releatedValueList = releateValueList;
        this.mAdapter = adapter;
    }


    @Override
    protected Object doInBackground(Object[] params) {

        String keyWord = params[0].toString(); // 키워드가 넘어온다.
        HttpConnector connector = new HttpConnector();
        ConnectionConfgure conf = new ConnectionConfgure();
        try {
            conf.setTargetUrl(SUMMARY_URL + keyWord);
            conf.setMethod("GET");
            String response = connector.openConnenction(conf); // Json형식으로 응답값이 넘어온다 (default-json)
            System.out.println("datas : " + response);
            ContentsValues value = null;
            JSONObject jsonObject = null;

            if(conf.getResponseDataType().equals(conf.JSON)) {
                jsonObject = new JSONObject(response);

                String title = (String) jsonObject.get("title");
                String contents = jsonObject.getString("extract_html");
                contents = contents.replaceAll("<p>|</p>|<b>|</b>","");

                value = new ContentsValues(title, contents);
            }else if(conf.getMethod().equals(conf.XML)){

            }

            JSONObject thumbnail;
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
                Bitmap bitmap = BitmapFactory.decodeStream(is, null, null);

                value.setThumbnail(bitmap);
                value.setThumbnail_width(width);
                value.setThumbnnail_height(height);


                System.out.println(thumbnail.toString());
            }

            /* -------------- List Header ----------------- */

            conf.setTargetUrl(RELATED_URL + keyWord);
            conf.setMethod("GET");
            response = connector.openConnenction(conf); // Json타입으로 응답값이 넘어온다
            System.out.println("datas : " + response);
            ContentsValues relatedValue;

            if(conf.getResponseDataType().equals(conf.JSON)) {
                jsonObject = new JSONObject(response);
                JSONArray ja = jsonObject.getJSONArray("pages"); // related data들을 Array 로 가져온다

                for(int i = 0 ; i < ja.length(); i ++) {
                    JSONObject json = ja.getJSONObject(i);
                    System.out.println("Json from JsonArray [" + i + "] : " + json);


                    String  title = (String) json.get("title");
                    String  contents = json.getString("extract_html");
                    contents = contents.replaceAll("<p>|</p>|<b>|</b>","");

                    relatedValue = new ContentsValues(title, contents);

                    thumbnail = null;
                    if (!json.isNull("thumbnail")) { // 썸네이일 있는경우 가져와서 bitmap 생성
                        thumbnail = json.getJSONObject("thumbnail");
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

                        relatedValue.setThumbnail(bitmap);
                        relatedValue.setThumbnail_width(width);
                        relatedValue.setThumbnnail_height(height);

                        System.out.println(thumbnail.toString());

                    }
                    releatedValueList.add(relatedValue);
                }
            } else if(conf.getResponseDataType().equals(conf.XML)) {

            }

            return value;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        ContentsValues value = (ContentsValues) o;
        ImageView summaryImage = (ImageView) mView.findViewById(R.id.header_image);
        TextView display = (TextView) mView.findViewById(R.id.display_title);
        TextView contents = (TextView) mView.findViewById(R.id.header_contents);

        if(value.getThumbnail() !=null) {
            summaryImage.setImageBitmap(value.getThumbnail());
        }
        display.setText(value.getTitie());
        contents.setText(value.getContents());
        //--------------list Header------

        mAdapter.notifyDataSetChanged();


    }
}
