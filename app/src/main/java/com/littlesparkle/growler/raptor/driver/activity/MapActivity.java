package com.littlesparkle.growler.raptor.driver.activity;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.widget.CompoundButton;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.littlesparkle.growler.library.activity.BaseActivity;
import com.littlesparkle.growler.raptor.driver.R;
import com.littlesparkle.growler.raptor.driver.map.LocationEvent;
import com.littlesparkle.growler.raptor.driver.map.LocationTask;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapActivity extends BaseActivity implements AMap.OnMapLoadedListener {
    private static final int LOCATION_INTERVAL = 3000; // 3s
    private static final int LOCATE_MODE = 0;
    private static final int VIEW_MODE = 1;

    private int mZoomLevel = 18;
    private int mMode = LOCATE_MODE;

    @BindView(R.id.title_bar_back)
    AppCompatImageView mBack;

    @OnClick(R.id.title_bar_back)
    public void onCloseClick() {
        onBackPressed();
    }

    @BindView(R.id.map_view)
    MapView mMapView;

    @BindView(R.id.map_mode_switcher)
    SwitchCompat mMapModeSwitcher;

    private LocationTask mLocationTask = null;
    private Marker mMyPositionMark = null;
    private LatLng mPassengerLocation = null;
    private Marker mPassengerMark = null;
    private AMap mAMap = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        mPassengerLocation = getIntent().getParcelableExtra("passenger");

        mMapView.onCreate(savedInstanceState);
        mAMap = mMapView.getMap();
        mAMap.setOnMapLoadedListener(this);
        mLocationTask = new LocationTask(this, mAMap);

        mMapModeSwitcher.setChecked(true);
        mMapModeSwitcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // follow mode
                    mMode = LOCATE_MODE;
                    buttonView.setText(R.string.map_mode_locate);
                    if (mLocationTask.getCachedPosition() != null) {
                        moveTo(mLocationTask.getCachedPosition());
                    }
                } else {
                    // view mode
                    mMode = VIEW_MODE;
                    buttonView.setText(R.string.map_mode_view);
                }
            }
        });
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_map;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationTask.stopLocation();
        mMapView.onDestroy();
    }

    @Subscribe
    public void onLocationEvent(LocationEvent event) {
        Log.e("Raptor", "event =-  " + event.latLng.toString());
        moveTo(event.latLng);
    }

    @Override
    public void onMapLoaded() {
        MarkerOptions myMarkerOptions = new MarkerOptions();
        myMarkerOptions.position(new LatLng(0, 0));
        myMarkerOptions
                .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                        .decodeResource(getResources(),
                                R.drawable.location_on_black)));
        mMyPositionMark = mAMap.addMarker(myMarkerOptions);

        if (mPassengerLocation != null) {
            MarkerOptions passengerOptions = new MarkerOptions();
            passengerOptions.position(new LatLng(mPassengerLocation.latitude, mPassengerLocation.longitude));
            passengerOptions
                    .icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                            .decodeResource(getResources(),
                                    R.drawable.location_on_black)));
            mPassengerMark = mAMap.addMarker(passengerOptions);
        }

        mAMap.moveCamera(CameraUpdateFactory.zoomTo(mZoomLevel));
        mLocationTask.startLocation(LOCATION_INTERVAL);
    }

    private void moveTo(LatLng latLng) {
        if (mMode == LOCATE_MODE) {
            CameraUpdate cameraUpdate = CameraUpdateFactory.changeLatLng(latLng);
            mAMap.moveCamera(cameraUpdate);
        } else if (mMode == VIEW_MODE) {
            mMyPositionMark.setPositionByPixels(mMapView.getWidth() / 2, mMapView.getHeight() / 2);
        }

        mMyPositionMark.setPosition(latLng);
    }
}
