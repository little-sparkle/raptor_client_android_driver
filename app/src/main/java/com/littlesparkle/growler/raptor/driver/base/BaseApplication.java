package com.littlesparkle.growler.raptor.driver.base;

import android.app.Application;

import org.xutils.x;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        x.Ext.init(this);
    }
}
