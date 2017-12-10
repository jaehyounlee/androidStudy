package com.dbins.android.hellowandroid.netservice;

/**
 * Created by dave on 16. 7. 2..
 */
public interface DataIOListener {

    public void onSuccess(String result) ;
    public void onFault(int code) ;

}
