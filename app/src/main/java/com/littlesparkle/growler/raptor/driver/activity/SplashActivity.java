package com.littlesparkle.growler.raptor.driver.activity;

import android.content.Intent;
import android.os.Bundle;

import com.littlesparkle.growler.library.activity.BaseSplashActivity;
import com.littlesparkle.growler.library.log.Logger;
import com.littlesparkle.growler.raptor.driver.R;

public class SplashActivity extends BaseSplashActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onSplashEnd() {
//        startActivity(new Intent(this, MainActivity.class));
        startActivity(new Intent(this, RegisterActivity.class));
        finish();
    }
}
