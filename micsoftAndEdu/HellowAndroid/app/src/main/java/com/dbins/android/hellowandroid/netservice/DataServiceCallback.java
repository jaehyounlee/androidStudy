package com.dbins.android.hellowandroid.netservice;

import com.dbins.android.hellowandroid.netservice.model.BaseResponse;

/**
 * Created by dave on 2016. 7. 30..
 */
public interface DataServiceCallback {

    public void onSuccess(BaseResponse response) ;

    public void onFailure(String code, String message) ;

}
