package com.example.ijaehyeon.myapplication;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listview;
        ListViewAdater adater;

        adater = new ListViewAdater();

        listview = (ListView)findViewById(R.id.lv1);
        listview.setAdapter(adater);
        adater.addItem(ContextCompat.getDrawable(this, R.drawable.android_logo));
        adater.addItem(ContextCompat.getDrawable(this, R.drawable.ico_android));


    }
}
