package com.dbins.android.hellowandroid.dbms;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by SKILLSUPPORT on 2017-11-17.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String NAME = "WEATHERAPP";
    private static final int VERSION =1 ;
    public static final String TABLE = "WEATHERLOG";
    public DBHelper(Context context) {
        super(context,NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String drop_sql = "drop table if exists " + TABLE;
        String create_sql = "create table " + TABLE
                + "(_id Integer primary key autoincrement, " +
                "weather text , temp real, created text)";

        db.execSQL(drop_sql);
        db.execSQL(create_sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldversion, int newversion) {
        if(oldversion < newversion) {
            onCreate(sqLiteDatabase);
        }
    }
}
