package com.littlesparkle.growler.raptor.driver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.littlesparkle.growler.library.activity.BaseTitleBarActivity;
import com.littlesparkle.growler.library.bean.User;
import com.littlesparkle.growler.library.http.BaseHttpSubscriber;
import com.littlesparkle.growler.library.http.DefaultResponse;
import com.littlesparkle.growler.library.log.Logger;
import com.littlesparkle.growler.library.user.UserManager;
import com.littlesparkle.growler.library.user.UserRequest;
import com.littlesparkle.growler.library.webview.WebViewActivity;
import com.littlesparkle.growler.raptor.driver.R;
import com.littlesparkle.growler.raptor.driver.activity.event.SignOutEvent;
import com.littlesparkle.growler.raptor.driver.backend.Api;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends BaseTitleBarActivity {
    private User mUser;

    @OnClick(R.id.change_password_container)
    public void onChangePwdClick() {
        startActivity(new Intent(this, ResetPwdActivity.class));
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
        if (mUser == null) {
            return;
        }
        new UserRequest().signout(new BaseHttpSubscriber<DefaultResponse>(this, this) {
            @Override
            public void onNext(DefaultResponse defaultResponse) {
                UserManager.signOut(SettingsActivity.this);
                EventBus.getDefault().post(new SignOutEvent());
                finish();
            }
        }, mUser.user_id, mUser.token);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

        Intent it = getIntent();
        if (it != null) {
            mUser = it.getParcelableExtra("user");
            if (mUser == null) {
                Logger.e("setting activity: null user retrieved!");
            } else {
                Logger.log("setting activity: retrieved user:");
                mUser.dump();
            }
        }
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_settings;
    }

    private void onWebItemClick(View view) {
        Intent it = new Intent(this, WebViewActivity.class);
        switch (view.getId()) {
            case R.id.feedback_container:
                it.putExtra("title", R.string.feedback);
                it.putExtra("url", Api.FEEDBACK);
                break;
            case R.id.contact_us_container:
                it.putExtra("title", R.string.contact_us);
                it.putExtra("url", Api.CONTACT_US);
                break;
            case R.id.about_container:
                it.putExtra("title", R.string.about);
                it.putExtra("url", Api.ABOUT);
                break;
            default:
                break;
        }
        startActivity(it);
    }

    @Override
    protected int getTitleResourceId() {
        return R.string.settings;
    }
}
