package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Iterator;

public class HttpConnector {

    private HttpURLConnection setConfigure(ConnectionConfgure conf) throws Exception{
        URL url = new URL(conf.getTargetUrl());
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod(conf.getMethod());
        conn.setRequestProperty("Accept", conf.getResponseDataType());
        conn.setRequestProperty("Content-Type", conf.getRequestBodyType());
        conn.setConnectTimeout(conf.getConnectionTimeout());
        conn.setReadTimeout(conf.getReadTimeout());

        return conn;
    }

    public String openConnenction(ConnectionConfgure conf){
        try {
            HttpURLConnection conn = setConfigure(conf);

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
