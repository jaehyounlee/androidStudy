package com.example;

import java.util.HashMap;

/**
 * Created by jaehyoun on 2017-09-24.
 */

public class ConnectionConfgure {

    public static final String JSON = "application/json";
    public static final String XML = "application/xml";
    public static final String TEXT_HTML = "text/html";

    public static final String METHOD_GET = "GET";
    public static final String METHOD_POST ="POST";
    public static final String METHOD_DELETE = "DELETE";
    public static final String METHOD_PUT = "PUT";


    private String method =null;
    private String targetUrl =null;
    private int connectionTimeout = 3000;
    private int readTimeout = 3000;
    private String responseDataType = "application/json"; //default json
    private String requestBodyType = "text/html"; // default text/html
    private String body = null;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;

    }

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
