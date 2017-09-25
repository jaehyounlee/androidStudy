package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.Buffer;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

public class HttpConnector {

    private BufferedReader br;
    private HttpURLConnection conn;

    private void setConfigure(ConnectionConfgure conf, HttpURLConnection conn) throws Exception{
        conn.setRequestMethod(conf.getMethod());
        conn.setRequestProperty("Accept", conf.getResponseDataType());
        conn.setRequestProperty("Content-Type", conf.getRequestBodyType());
        conn.setConnectTimeout(conf.getConnectionTimeout());
        conn.setReadTimeout(conf.getReadTimeout());

        if(conf.getMethod().equals(ConnectionConfgure.METHOD_POST)
                && (conf.getBody() != null && !"".equals(conf.getBody()))) {
            OutputStream os = conn.getOutputStream();
            os.write(conf.getBody().getBytes("UTF-8"));
            os.flush();
            os.close();
        }
    }

    public String openConnenction(ConnectionConfgure conf) {
        try {
            URL url = new URL(conf.getTargetUrl());

            if(conf.isSSL()) {
                conn = getHttpsURLConnection(url);
            } else {
                conn = (HttpURLConnection)url.openConnection();
            }
            setConfigure(conf, conn);

            int resultCode = conn.getResponseCode();
            if (resultCode == HttpURLConnection.HTTP_OK) {
                br  = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

                String line;
                StringBuffer sb = new StringBuffer();
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\r\n");
                }
                br.close();
                return sb.toString();
            } else {
                // no Action
                System.out.println("Result Code is not OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }
        return "";
    }

    private HttpsURLConnection getHttpsURLConnection(URL url) throws IOException, NoSuchAlgorithmException, KeyManagementException {
        HttpsURLConnection httpsConn = (HttpsURLConnection) url.openConnection();
        httpsConn.setHostnameVerifier(new HostnameVerifier() {

            @Override
            public boolean verify(String hostname, SSLSession session) {
                System.out.print("Hostname : " + hostname + " sslSession : " + session);
                return true;
            }
        });
        // SSL setting
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(null, new TrustManager[]{
            new javax.net.ssl.X509TrustManager() {

                @Override
                public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }
        }, null);
        httpsConn.setSSLSocketFactory(context.getSocketFactory());
        return httpsConn;
    }
    private void disconnect() {
        if (conn == null) {
            return;
        }

        conn.disconnect();
    }
}
