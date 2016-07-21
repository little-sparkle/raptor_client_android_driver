package com.littlesparkle.growler.raptor.driver.activity;

import android.os.Bundle;
import android.view.View;

import com.littlesparkle.growler.library.activity.BaseTitleBarActivity;
import com.littlesparkle.growler.raptor.driver.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends BaseTitleBarActivity {

    @OnClick(R.id.change_password_container)
    public void onChangePwdClick() {
    }

    @OnClick(R.id.update_container)
    public void onUpdateClick() {

    }

    @OnClick(R.id.feedback_container)
    public void onFeedbackClick(View v) {
        onWebItemClick(v);
    }

    @OnClick(R.id.contact_us_container)
    public void onContactUsClick(View v) {
        onWebItemClick(v);
    }

    @OnClick(R.id.about_container)
    public void onAboutClick(View v) {
        onWebItemClick(v);
    }

    @OnClick(R.id.quit_container)
    public void onQuitClick(View v) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_settings;
    }

    private void onWebItemClick(View view) {
        switch (view.getId()) {
            case R.id.wallet_container:
                break;
            default:
                break;
        }
    }

    @Override
    protected int getTitleResourceId() {
        return R.string.settings;
    }
}
