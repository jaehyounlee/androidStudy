package com.dbins.android.hellowandroid.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by SKILLSUPPORT on 2017-11-16.
 */

public class PrefUtil {

    public static final String PREF_KEY = "APP_PREF";
    private Context mContext;
    public PrefUtil(Context context) {
        mContext = context;
    }

    public void saveString(String key, String value){
        SharedPreferences pref = mContext.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        SharedPreferences.Editor prefEditor = pref.edit();
        prefEditor.putString(key, value);
        prefEditor.commit();
    }
    public String getPrefString(String key){
        SharedPreferences pref = mContext.getSharedPreferences(PREF_KEY, Context.MODE_PRIVATE);
        return pref.getString(key,null);
    }
}
