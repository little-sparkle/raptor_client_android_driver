package com.littlesparkle.growler.raptor.driver.map;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.UiSettings;
import com.amap.api.maps2d.model.LatLng;

import org.greenrobot.eventbus.EventBus;

public class LocationTask {
    private Context mContext = null;
    private UiSettings mUiSettings = null;
    private AMap mAMap = null;
    private AMapLocationClient mAMapLocationClient = null;
    private AMapLocationClientOption mAMapLocationClientOption = null;
    private LatLng mCurrentLocation = null;
    private AMapLocationListener mAMapLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    mCurrentLocation = new LatLng(aMapLocation.getLatitude(), aMapLocation.getLongitude());
                    EventBus.getDefault().post(new LocationEvent(mCurrentLocation));
                } else {
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }
        }
    };

    public LocationTask(Context context, AMap aMap) {
        mContext = context;
        mAMap = aMap;
        mUiSettings = aMap.getUiSettings();
        mAMapLocationClient = new AMapLocationClient(mContext.getApplicationContext());
    }

    public void startLocation(int time) {
        mUiSettings.setMyLocationButtonEnabled(true);
        mUiSettings.setScaleControlsEnabled(true);
        mUiSettings.setZoomControlsEnabled(true);

        mAMap.setMyLocationEnabled(true);
        mAMapLocationClient.setLocationListener(mAMapLocationListener);
        mAMapLocationClientOption = new AMapLocationClientOption();
        mAMapLocationClientOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        if (time == 0) {
            mAMapLocationClientOption.setOnceLocation(true);
        } else {
            mAMapLocationClientOption.setInterval(time);
        }
        mAMapLocationClient.setLocationOption(mAMapLocationClientOption);
        mAMapLocationClient.startLocation();
    }

    public LatLng getCachedPosition() {
        return mCurrentLocation;
    }

    public void stopLocation() {
        if (mAMapLocationClient.isStarted()) {
            mAMapLocationClient.stopLocation();
            mAMapLocationClient.onDestroy();
        }
    }
}
