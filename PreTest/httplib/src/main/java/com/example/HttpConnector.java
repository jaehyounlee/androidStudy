package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
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

        if(conf.getMethod().equals(ConnectionConfgure.METHOD_POST) && conf.getBody() !=null) {
            OutputStream os = conn.getOutputStream();
            os.write(conf.getBody().getBytes("UTF-8"));
            os.flush();
            os.close();
        }

        return conn;
    }

    public String openConnenction(ConnectionConfgure conf){
        try {
            HttpURLConnection conn = setConfigure(conf);

            int resultCode = conn.getResponseCode();
            if(resultCode == conn.HTTP_OK) {

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

                String line;
                StringBuffer sb = new StringBuffer();
                while((line = br.readLine())!=null) {
                    sb.append(line).append("\r\n");
                }
                System.out.println(sb.toString());
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
