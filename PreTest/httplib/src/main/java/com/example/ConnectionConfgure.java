package com.example;

import java.util.HashMap;

/**
 * Created by jaehyoun on 2017-09-24.
 */

public class ConnectionConfgure {

    public static final String JSON = "application/json";
    public static final String XML = "application/xml";
    public static final String TEXT_HTML = "text/html";


    private String method ="";
    private String targetUrl ="";
    private int connectionTimeout = 3000;
    private int readTimeout = 3000;
    private String responseDataType = "application/json"; //default json
    private String requestBodyType = "text/html"; // default text/html

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setResponseDataType(String type) {
        this.responseDataType = type;
    }
    public void setRequestBodyType(String type) {
        this.requestBodyType = type;
    }
    public String getResponseDataType(){
        return responseDataType;
    }
    public String getRequestBodyType(){
        return requestBodyType;
    }
}
