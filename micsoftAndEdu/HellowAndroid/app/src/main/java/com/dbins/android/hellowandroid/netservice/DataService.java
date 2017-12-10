package com.dbins.android.hellowandroid.netservice;

import android.util.Log;
import android.util.Pair;

import com.dbins.android.hellowandroid.netservice.model.BaseResponse;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dave on 2016. 7. 30..
 */
public class DataService implements DataIOListener{


    public static final String METHOD_GET = "get" ;
    public static final String METHOD_POST = "post" ;
    public static final String METHOD_POST_MULTI = "multipart" ;

    private List<Pair<String,String>> params ;
    private Class resultClass  = BaseResponse.class ;
    private DataServiceCallback callback ;
    private String apiURL ;
    private String method = METHOD_GET ;
    private List<FormFile> files ;

    //Callback for Data Result
    @Override
    public void onSuccess(String result) {
        Log.d(getClass().getName() , result) ;
        Gson gson = new Gson() ;
        try {
            BaseResponse model = (BaseResponse) gson.fromJson(result, resultClass);
            if( callback != null) {
                callback.onSuccess(model);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFault(int code) {
        if( callback != null) {
            callback.onFailure("9999" , "네트워크 오류입니다. 잠시후 다시 실행해 주십 시오");
        }
    }


    public static class Builder {

        private List<Pair<String,String>> params ;
        private Class resultClass ;
        private DataServiceCallback callback ;
        private String apiURL ;
        private String method ;
        private List<FormFile> files ;

        public Builder(String apiURL) {
            this.apiURL = apiURL ;
        }

        public Builder resultclass(Class clazz) {
            this.resultClass = clazz ;
            return this ;
        }

        public Builder parameters(String ... params) {
            this.params = new ArrayList<Pair<String, String>>();
            for(int i = 0 ; i < params.length ; i+=2) {
                int keyIdx = i  ;
                int valueIdx = keyIdx + 1 ;
                String key = params[keyIdx] ;
                String value = "" ;
                if( valueIdx < params.length) value = params[valueIdx] ;
                this.params.add(new Pair<String, String>(key , value)) ;
            }
            return this ;
        }

        public Builder callback(DataServiceCallback callback) {
            this.callback = callback ;
            return this ;
        }

        public Builder method(String value){
            this.method = value ;
            return this ;
        }

        public Builder files(List<FormFile> items) {
            this.files = items ;
            return this ;
        }

        public DataService build() {
            return new DataService(this) ;
        }


    }

    private DataService(Builder builder) {
        this.apiURL = builder.apiURL ;
        this.resultClass = builder.resultClass ;
        this.params = builder.params ;
        this.callback = builder.callback ;
        this.method =  builder.method ;
        this.files = builder.files ;
        if(this.method == null) this.method = METHOD_POST ;
        if( this.resultClass == null) resultClass = BaseResponse.class ;
    }

    public void execute() {

        if( params == null) params = new ArrayList<>();

        try {
            if (method.equals(METHOD_GET)) {
                CommunicationManager.getInstance().get(apiURL , this);
            } else if (method.equals(METHOD_POST)) {
                CommunicationManager.getInstance().post(apiURL , params,  this);
            } else if (method.equals(METHOD_POST_MULTI)) {
                CommunicationManager.getInstance().postWithFiles(apiURL, files, params, this);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }

    }



}
