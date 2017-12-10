package com.dbins.android.hellowandroid.adpater;

import android.content.Context;
import android.database.Cursor;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.dbins.android.hellowandroid.R;
import com.dbins.android.hellowandroid.dbms.DBAdapter;

/**
 * Created by SKILLSUPPORT on 2017-11-17.
 */

public class WeatherAdpater extends CursorAdapter {
    public WeatherAdpater(Context context, Cursor c) {
        super(context, c, true);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        //LayoutInflater inflater = (LayoutInflater)(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE));
        LayoutInflater inflater = LayoutInflater.from( context );
        View view = inflater.inflate(R.layout.weather_item, viewGroup, false);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String status = cursor.getString(cursor.getColumnIndex(DBAdapter.COLUMNS[1]));
        Float temp = cursor.getFloat(cursor.getColumnIndex(DBAdapter.COLUMNS[2]));
        String createdTime = cursor.getString(cursor.getColumnIndex(DBAdapter.COLUMNS[3]));
        ((TextView)view.findViewById(R.id.createdtime)).setText(createdTime);
        ((TextView)view.findViewById(R.id.status)).setText(status);
        ((TextView)view.findViewById(R.id.temper)).setText(temp + "℃");
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
