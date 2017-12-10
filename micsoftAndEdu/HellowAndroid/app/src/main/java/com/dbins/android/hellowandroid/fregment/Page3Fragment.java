package com.dbins.android.hellowandroid.fregment;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.dbins.android.hellowandroid.MainApplication;
import com.dbins.android.hellowandroid.R;
import com.dbins.android.hellowandroid.adpater.WeatherAdpater;

/**
 * Created by SKILLSUPPORT on 2017-11-16.
 */

public class Page3Fragment extends Fragment {

    private ListView mListView;
    private WeatherAdpater mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_page3, container, false);

        mListView = view.findViewById(R.id.listview);
        initAdapter();

        return view;
    }

    private void initAdapter() {
        Cursor cursor = ((MainApplication)getActivity().getApplicationContext()).getDatabaseAdapter().selectLatest();
        mAdapter = new WeatherAdpater(getActivity(), cursor);
        mListView.setAdapter(mAdapter);

    }
}
