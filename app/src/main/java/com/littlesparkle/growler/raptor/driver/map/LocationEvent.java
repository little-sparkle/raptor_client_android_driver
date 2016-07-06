package com.littlesparkle.growler.raptor.driver.map;

import com.amap.api.maps2d.model.LatLng;

public class LocationEvent {
    public final LatLng latLng;

    public LocationEvent(LatLng latLng) {
        this.latLng = latLng;
    }
}
