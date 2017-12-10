package com.dbins.android.hellowandroid.netservice;

import android.util.Log;
import android.util.Pair;

import java.io.File;
import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by dave on 16. 7. 2..
 */
public class CommunicationManager {


    private static CommunicationManager instance;

    private DataIOListener dataResultListener;

    public static CommunicationManager getInstance() {
        if (instance == null) {
            instance = new CommunicationManager();
        }
        return instance;
    }


    public void postWithFiles(String url, List<FormFile> images,
                                            List<Pair<String, String>> pairs, final DataIOListener listener) throws Exception {

        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();

        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);

        if (pairs != null) {
            for (Pair<String, String> pair : pairs) {
                try {
                    if( pair != null) builder.addFormDataPart(pair.first, pair.second);
                }catch(Exception e) {
                    Log.e("CommunicationManager::" , pair.first + "::" + pair.second) ;
                }
            }
        }

        if ( images.size() > 0 ) {

            for (FormFile imageItem : images) {

                File f = new File(imageItem.getFile());

                if (f.exists()) {

                    builder.addFormDataPart(imageItem.getName(), imageItem.getFile(), RequestBody.create(imageItem.getType(), f));

                }

            }
        }

        MultipartBody requestBody = builder.build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if( listener!= null) listener.onFault(-1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (listener != null) listener.onSuccess(response.body().string());
            }
        });
    }


    public void post(String url, List<Pair<String, String>> pairs , final DataIOListener listener) throws Exception {
        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();
        Request.Builder builder = new Request.Builder().url(url);

        if (pairs != null) {
            okhttp3.FormBody.Builder postData = new okhttp3.FormBody.Builder();
            for (Pair<String, String> pair : pairs) {
                postData.add(pair.first, pair.second);
            }
            builder.post(postData.build());
        }
        Request request = builder.build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if( listener!= null) listener.onFault(-1);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    ResponseBody rbody = response.body();
                    String content = rbody.string();
                    if (listener != null) listener.onSuccess(content);
                }catch(Exception e) {
                    if( listener!= null) listener.onFault(-1);
                }
            }
        });
    }

    public  void get(String url  ,  final DataIOListener listener) throws IOException{
        okhttp3.OkHttpClient client = new okhttp3.OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(final Call call, IOException e) {
                        if( listener!= null) listener.onFault(-1);
                    }

                    @Override
                    public void onResponse(Call call, final Response response) throws IOException {
                        if (listener != null) listener.onSuccess(response.body().string());
                    }
         });
    }





}
