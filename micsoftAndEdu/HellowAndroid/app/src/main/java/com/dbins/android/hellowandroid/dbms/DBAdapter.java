package com.dbins.android.hellowandroid.dbms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by SKILLSUPPORT on 2017-11-17.
 */

public class DBAdapter {
    private DBHelper mHelper;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static String[] COLUMNS = {"_id", "weather", "temp", "created"};
    public DBAdapter(Context mContext) {
        this.mContext = mContext;
        mHelper = new DBHelper(mContext);
        openDB();
    }

    private boolean openDB() {
        try {
            mDatabase = mHelper.getWritableDatabase();

        }catch (Exception e){
            mDatabase = mHelper.getReadableDatabase();
            return false;
        }
        return true;
    }
    public boolean insertWeatherData(String status, double temp){
        ContentValues cv = new ContentValues();
        cv.put(COLUMNS[1], status);
        cv.put(COLUMNS[2], temp);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datestr = sdf.format(Calendar.getInstance().getTime());
        cv.put(COLUMNS[3],datestr);

        return mDatabase.insert(DBHelper.TABLE,null, cv) > 0;
    }
    public boolean deleteData(String rowID) {
        return mDatabase.delete(DBHelper.TABLE, "_id=?", new String[]{rowID}) > 0 ;
    }

    public Cursor selectLatest(){
        return mDatabase.query(DBHelper.TABLE, COLUMNS, null, null, null, null, "_id");
    }

}
