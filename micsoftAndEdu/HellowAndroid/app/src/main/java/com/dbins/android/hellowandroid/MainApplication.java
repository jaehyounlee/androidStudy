package com.dbins.android.hellowandroid;

import android.app.Application;
import android.util.Log;

import com.dbins.android.hellowandroid.dbms.DBAdapter;
import com.dbins.android.hellowandroid.util.PrefUtil;

/**
 * Created by SKILLSUPPORT on 2017-11-15.
 */

public class MainApplication extends Application {
    private PrefUtil mPref;
    private DBAdapter mDatabaseAdapter;

    public PrefUtil getPref() {
        return mPref;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(getPackageName(), "##### Application init ####");
        mPref = new PrefUtil(this);
        mDatabaseAdapter = new DBAdapter(this);
    }

    public DBAdapter getDatabaseAdapter() {
        return mDatabaseAdapter;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
