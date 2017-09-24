package com.example.jaehyoun.nhnpretest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.inputmethodservice.InputMethodService;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ConnectionConfgure;
import com.example.HttpConnector;

import org.json.JSONArray;
import org.json.JSONException;
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

    final public static String TYPE_SUMMARY = "summary/";
    final public static String TYPE_RELATED = "related/";
    final private static String REMOVE_TAGS = "<p>|</p>|<b>|</b>|<dl>|<dd>";

    final private String SUMMARY_URL = "https://en.wikipedia.org/api/rest_v1/page/";
    final private String RELATED_URL = "https://en.wikipedia.org/api/rest_v1/page/";

    private LoaderCallBack loaderCallBack;
    private String keyWord;
    private String type;

    private HttpConnector connector = new HttpConnector();
    private ConnectionConfgure conf = new ConnectionConfgure();

    ContentsLoader(String keyWord, String type) {
        this.keyWord = keyWord;
        this.type = type;

    }

    public void setLoaderCallBack(LoaderCallBack callBack){
        this.loaderCallBack = callBack;
    }

    @Override
    protected Object doInBackground(Object[] params) {

        ArrayList<ContentsValues> values = new ArrayList<>();

        conf.setTargetUrl(getBaseURL() + keyWord);
        conf.setMethod("GET");
        String response = connector.openConnenction(conf); // Json타입으로 응답값이 넘어온다
        System.out.println("datas : " + response);

        try {
            JSONObject jsonObject = new JSONObject(response);
            if(type.equals(ContentsLoader.TYPE_SUMMARY)){
                values.add(makeContentValue(jsonObject));
            }else if(type.equals(ContentsLoader.TYPE_RELATED)){
                JSONArray ja = jsonObject.getJSONArray("pages"); // related data들을 Array 로 가져온다
                for(int i = 0 ; i < ja.length(); i ++) {
                    values.add(makeContentValue(ja.getJSONObject(i)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return values;
    }

    @NonNull
    private String getBaseURL() {
        if(type.equals(ContentsLoader.TYPE_SUMMARY)) {
            return SUMMARY_URL + type;
        }else if(type.equals(ContentsLoader.TYPE_RELATED)) {
            return RELATED_URL + type;
        }
        return "";
    }

    @NonNull
    private ContentsValues makeContentValue(JSONObject json) throws JSONException {
        System.out.println(json.toString());
        String title = (String) json.get("title");
        String contents = json.getString("extract_html");

        ContentsValues value = new ContentsValues(title, removeTags(contents));

        if (json.has("thumbnail")) { // 썸네이일 있는경우 가져와서 bitmap 생성
            JSONObject thumbnail = json.getJSONObject("thumbnail");

            value.setThumbnail_URl(thumbnail.getString("source"));
            value.setThumbnail_width(Integer.parseInt(thumbnail.getString("width")));
            value.setThumbnnail_height(Integer.parseInt(thumbnail.getString("height")));
        }
        return value;
    }

    private String removeTags(String contents) {
        return contents.replaceAll(REMOVE_TAGS, "");
    }

    @Override
    protected void onPostExecute(Object o) {
        loaderCallBack.onLoadFinish((ArrayList<ContentsValues>) o);
    }
    interface LoaderCallBack {
        void onLoadFinish(ArrayList<ContentsValues> values);
    }
}