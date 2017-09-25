package com.example;

import junit.framework.TestCase;

import org.junit.Test;

import java.net.HttpURLConnection;
import java.util.ArrayList;

public class HttpConnectorTest extends TestCase {

    private ArrayList<String> validHttpsURL = new ArrayList<>();
    {
        validHttpsURL.add("https://www.google.co.kr");
        validHttpsURL.add("https://www.naver.com");
    }
    private ArrayList<String> inValidURL = new ArrayList<>();
    {
        inValidURL.add("http://asdasd");
    }
    private ArrayList<String> validHttpURL = new ArrayList<>();
    {
        validHttpURL.add("http://www.google.co.kr");
    }


    @Test
    public void testHttpConnection() throws Exception {
        System.out.println("Start HTTP TEST");
        ConnectionConfgure conf = new ConnectionConfgure();
        HttpConnector connector = new HttpConnector();
        conf.setMethod(ConnectionConfgure.METHOD_GET);
        for(int i = 0 ; i < validHttpURL.size() ; i++){
            String url = validHttpURL.get(i);
            System.out.println("[URL]" + url);
            conf.setTargetUrl(url);
            String response = connector.openConnenction(conf);

            assertTrue(!"".equals(response));
       }
    }

    @Test
    public void testHttpsConnection() throws Exception {
        System.out.println("Start HTTPS TEST");
        ConnectionConfgure conf = new ConnectionConfgure();
        HttpConnector connector = new HttpConnector();
        conf.setMethod(ConnectionConfgure.METHOD_GET);
        for(int i = 0 ; i < validHttpsURL.size() ; i++){
            String url = validHttpsURL.get(i);
            System.out.println("[URL]" + url);
            conf.setTargetUrl(url);
            conf.setSSL(true);
            String response = connector.openConnenction(conf);

            assertTrue(!"".equals(response));
        }
    }
}