package com.example.jaehyoun.nhnpretest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

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
        search_view.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                //Enter key Action
                if ((event.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    String keyword = search_view.getText().toString();
                    if (TextUtils.isEmpty(keyword)) {
                        return true;
                    }
                    loadHeaderData(keyword);
                    loadListData(keyword);
                    return true;
                }
                return false;
            }
        });

        search_btn = (Button) findViewById(R.id.search_btn);
        listView = (ListView) findViewById(R.id.search_result_listview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.search_result_swipe_refresh_layout);

        mAdapter = new RelatedListAdapter(releateValueList);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                ContentsValues item = (ContentsValues)listView.getItemAtPosition(position);
                String keyword = item.getTitie();
                loadHeaderData(keyword);
                loadListData(keyword);
                search_view.setText(keyword);
            }
        });

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
                String keyword = search_view.getText().toString();

                loadHeaderData(keyword);
                loadListData(keyword);
            }
        });

        header.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PreTestMainActivity.this,DetailViewActivity.class);
                i.putExtra("keyword",(String)v.getTag());
                startActivity(i);
            }
        });
    }

    private void loadHeaderData(final String keyword) {
        ContentsLoader headerLoader = new ContentsLoader(keyword,ContentsLoader.TYPE_SUMMARY);

        headerLoader.setLoaderCallBack(new ContentsLoader.LoaderCallBack() {
            @Override
            public void onLoadFinish(ArrayList<ContentsValues> values) {
                ImageView summaryImage = (ImageView) header.findViewById(R.id.header_image);
                TextView display = (TextView) header.findViewById(R.id.display_title);
                TextView contents = (TextView) header.findViewById(R.id.header_contents);

                if(values.size() !=0) {
                    ContentsValues value = values.get(0);
                    new ImageLoader(value.getThumbnail_URl(), summaryImage).execute();
                    display.setText(value.getTitie());
                    contents.setText(value.getContents());
                }
                header.setTag(keyword);
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
                listView.setSelection(0);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        listLoader.execute(keyword);
    }
}

