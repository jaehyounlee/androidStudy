package com.example.jaehyoun.nhnpretest;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.HttpConnector;

public class PreTestMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_test_main);

        AsyncTask task = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                HttpConnector connector = new HttpConnector();
                connector.setTargetUrl("http://www.google.co.kr");
                connector.setMethod("GET");
                connector.openConnenction();

                return "";
            }
        };

        task.execute();
    }
}
