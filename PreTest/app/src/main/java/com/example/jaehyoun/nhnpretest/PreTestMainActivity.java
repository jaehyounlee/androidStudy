package com.example.jaehyoun.nhnpretest;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PreTestMainActivity extends AppCompatActivity{

    EditText search_view;
    Button search_btn;
    ListView listView;
    SwipeRefreshLayout mSwipeRefreshLayout;

    ArrayList<ContentsValues> releateValueList = new ArrayList<ContentsValues>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_test_main);

        search_view = (EditText) findViewById(R.id.search_edit);
        search_btn = (Button) findViewById(R.id.search_btn);
        listView = (ListView) findViewById(R.id.search_result_listview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.search_result_swipe_refresh_layout);

        final RelatedListAdapter adapter = new RelatedListAdapter(releateValueList);
        listView.setAdapter(adapter);

        final View header = getLayoutInflater().inflate(R.layout.listview_header,null,false);

        listView.addHeaderView(header);



        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentsLoader loader = new ContentsLoader(header,releateValueList,adapter);
                String keyword = search_view.getText().toString();

                loader.execute(keyword);

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(search_view.getWindowToken(),0);

            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ContentsLoader loader = new ContentsLoader(header,releateValueList,adapter);
                String keyword = search_view.getText().toString();

                loader.execute(keyword);
                mSwipeRefreshLayout.setRefreshing(false);

            }
        });
    }

}

