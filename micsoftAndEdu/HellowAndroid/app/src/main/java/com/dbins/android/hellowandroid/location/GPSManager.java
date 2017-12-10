package com.dbins.android.hellowandroid.location;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

/**
 * Created by SKILLSUPPORT on 2017-11-17.
 */

public class GPSManager implements android.location.LocationListener {
    private static final GPSManager ourInstance = new GPSManager();

    private Context mContext;

    private myLocationListener mListener ;

    public static GPSManager getInstance() {
        return ourInstance;
    }

    public void init(Context context) {
        this.mContext = context;
    }

    public void startLocationService() throws NotInitializedException {
        if (mContext == null)
            throw new NotInitializedException("GPSManager.getInstance().init(Context) should be called");
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationManager mgr = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        Location location = mgr.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if( location != null) {
            if(mListener != null) {
                mListener.onLocationUpdate(location);
            }
        }
        mgr.requestLocationUpdates(LocationManager.GPS_PROVIDER , 10000 , 0 , this);
    }

    @Override
    public void onLocationChanged(Location location) {
        if(mListener != null) {
            mListener.onLocationUpdate(location);
        }
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public static class NotInitializedException extends  Exception {
        public NotInitializedException(String message) {
            super(message);
        }
    }

    public void setListener(myLocationListener listener)  {
        this.mListener = listener ;
    }

}
