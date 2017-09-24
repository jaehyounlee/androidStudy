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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class PreTestMainActivity extends AppCompatActivity{

    EditText search_view;
    Button search_btn;
    ListView listView;
    SwipeRefreshLayout mSwipeRefreshLayout;

    ArrayList<ContentsValues> releateValueList = new ArrayList<ContentsValues>();
    RelatedListAdapter mAdapter;
    View header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_test_main);

        search_view = (EditText) findViewById(R.id.search_edit);
        search_btn = (Button) findViewById(R.id.search_btn);
        listView = (ListView) findViewById(R.id.search_result_listview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.search_result_swipe_refresh_layout);

        mAdapter = new RelatedListAdapter(releateValueList);
        listView.setAdapter(mAdapter);

        header = getLayoutInflater().inflate(R.layout.listview_header,null,false);

        listView.addHeaderView(header);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = search_view.getText().toString();

                loadHeaderData(keyword);
                loadListData(keyword);

            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

            }
        });
    }

    private void loadHeaderData(String keyword) {
        ContentsLoader headerLoader = new ContentsLoader(keyword,ContentsLoader.TYPE_SUMMARY);

        headerLoader.setLoaderCallBack(new ContentsLoader.LoaderCallBack() {
            @Override
            public void onLoadFinish(ArrayList<ContentsValues> values) {
                ImageView summaryImage = (ImageView) header.findViewById(R.id.header_image);
                TextView display = (TextView) header.findViewById(R.id.display_title);
                TextView contents = (TextView) header.findViewById(R.id.header_contents);

                if(values.size() !=0) {
                    ContentsValues value = values.get(0);

                    if(value.getThumbnail() !=null) {
                        summaryImage.setImageBitmap(value.getThumbnail());
                    }
                    display.setText(value.getTitie());
                    contents.setText(value.getContents());
                }
            }
        });
        headerLoader.execute(keyword);
    }

    private void loadListData(String keyword) {
        ContentsLoader listLoader = new ContentsLoader(keyword, ContentsLoader.TYPE_RELATED);
        listLoader.setLoaderCallBack(new ContentsLoader.LoaderCallBack() {
            @Override
            public void onLoadFinish(ArrayList<ContentsValues> values) {
                releateValueList.clear();
                releateValueList.addAll(values);
                mAdapter.notifyDataSetChanged();

                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(search_view.getWindowToken(),0);
            }
        });

        listLoader.execute(keyword);
    }
}

