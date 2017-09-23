package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;

public class HttpConnector {

    private String method ="";
    private String targetUrl ="";
    private int connectionTimeout = 3000;
    private int ReadTimeout = 3000;

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
        ReadTimeout = readTimeout;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public int getReadTimeout() {
        return ReadTimeout;
    }

    public String openConnenction(){
        try {
            URL url = new URL(targetUrl);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod(method);
            int resultCode = conn.getResponseCode();
            if(resultCode == conn.HTTP_OK) {

                InputStreamReader isr = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader br = new BufferedReader(isr);

                String line;
                StringBuffer sb = new StringBuffer();
                System.out.println("--------start---------\n\n\n");
                while((line = br.readLine())!=null) {
                    sb.append(line).append("\r\n");
                }
                System.out.println(sb.toString());
                isr.close();
                br.close();
                return sb.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
