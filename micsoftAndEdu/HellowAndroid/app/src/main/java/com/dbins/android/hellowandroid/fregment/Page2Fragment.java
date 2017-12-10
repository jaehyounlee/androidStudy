package com.dbins.android.hellowandroid.fregment;

import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dbins.android.hellowandroid.MainApplication;
import com.dbins.android.hellowandroid.R;
import com.dbins.android.hellowandroid.dbms.DBAdapter;
import com.dbins.android.hellowandroid.location.GPSManager;
import com.dbins.android.hellowandroid.location.myLocationListener;
import com.dbins.android.hellowandroid.netservice.DataService;
import com.dbins.android.hellowandroid.netservice.DataServiceCallback;
import com.dbins.android.hellowandroid.netservice.model.BaseResponse;
import com.dbins.android.hellowandroid.netservice.model.WeatherResponse;

/**
 * Created by SKILLSUPPORT on 2017-11-16.
 */

public class Page2Fragment extends Fragment implements myLocationListener {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_page2, container, false);
        mWeatherLable = view.findViewById(R.id.temp);
        mLocationLable = view.findViewById(R.id.location);
        invokeWeatherData();
        GPSManager.getInstance().init(getActivity());
        GPSManager.getInstance().setListener(this);
        try {
            GPSManager.getInstance().startLocationService();
        } catch (GPSManager.NotInitializedException e) {
            e.printStackTrace();
        }
        return view;
    }

    private void invokeWeatherData() {
        String apiURL = getString(R.string.weatherapi);
        String key = getString(R.string.weatherkey);
        String city = getString(R.string.seoul);
        final String url2call=apiURL + "?appid="+key + "&id=" +city;
        Log.d("apiURL", url2call);
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                callWeatherAPI(url2call);
            }
        });

    }

    private synchronized void callWeatherAPI(String url) {

        new DataService.Builder(url).method(DataService.METHOD_GET)
                .resultclass(WeatherResponse.class)
                .callback(new DataServiceCallback() {
            @Override
            public void onSuccess(final BaseResponse response) {
                Log.d("DATA RESULT : ", "RESULT OK");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateWeatherUI((WeatherResponse)response);
                    }
                });
            }

            @Override
            public void onFailure(String code, String message) {

            }
        }).build().execute();
    }

    TextView mWeatherLable, mLocationLable;
    private void updateWeatherUI(WeatherResponse response) {
        Double temp = (Double) response.getMain().get("temp");
        String desc = response.getWeather().get(0).get("main").toString();
        temp -=  274.15d;
        Log.d("result msg", "SEOUL : " + desc + " : " + temp + "℃" );
        mWeatherLable.setText("SEOUL : " + desc + " : " + temp + "℃");

        DBAdapter db = ((MainApplication)getActivity().getApplicationContext()).getDatabaseAdapter();
        db.insertWeatherData(desc,temp);
    }

    @Override
    public void onLocationUpdate(Location location) {
        StringBuffer sb = new StringBuffer();
        sb.append("위도 : " + location.getLatitude() + " 경도 : " +location.getLongitude());
        Log.d("Location : " , sb.toString());
        mLocationLable.setText(sb.toString());
    }
}
