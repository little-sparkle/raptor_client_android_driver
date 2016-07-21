package com.littlesparkle.growler.raptor.driver.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.littlesparkle.growler.library.bean.User;
import com.littlesparkle.growler.library.log.Logger;
import com.littlesparkle.growler.library.webview.WebViewActivity;
import com.littlesparkle.growler.raptor.driver.R;
import com.littlesparkle.growler.raptor.driver.activity.SettingsActivity;
import com.littlesparkle.growler.raptor.driver.backend.Api;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeFragment extends Fragment {
    @OnClick(R.id.wallet_container)
    public void onWalletClick(View view) {
        onViewClick(view);
    }

    @OnClick(R.id.message_container)
    public void onMessageClick(View view) {
        onViewClick(view);
    }

    @OnClick(R.id.my_car_container)
    public void onMyCarClick(View view) {
        onViewClick(view);
    }

    @OnClick(R.id.settings_container)
    public void onSettingsClick(View view) {
        onViewClick(view);
    }

    private User mUser;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Intent it = getActivity().getIntent();
        if (it != null) {
            mUser = it.getParcelableExtra("user");
            if (mUser == null) {
                Logger.e("null user retrieved!");
                return null;
            } else {
                Logger.log("retrieved user:");
                mUser.dump();
            }
        }
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    private void onViewClick(View view) {
        Intent it = null;
        switch (view.getId()) {
            case R.id.wallet_container:
                it = new Intent(getContext(), WebViewActivity.class);
                it.putExtra("title", getString(R.string.wallet));
                it.putExtra("url", Api.WALLET);
                if (mUser != null) {
                    it.putExtra("user_id", mUser.user_id);
                    it.putExtra("token", mUser.token);
                }
                break;
            case R.id.message_container:
                it = new Intent(getContext(), WebViewActivity.class);
                it.putExtra("title", getString(R.string.message_center));
                it.putExtra("url", Api.MESSAGE_CENTER);
                if (mUser != null) {
                    it.putExtra("user_id", mUser.user_id);
                    it.putExtra("token", mUser.token);
                }
                break;
            case R.id.my_car_container:
                it = new Intent(getContext(), WebViewActivity.class);
                it.putExtra("title", getString(R.string.my_car));
                it.putExtra("url", Api.MY_CAR);
                if (mUser != null) {
                    it.putExtra("user_id", mUser.user_id);
                    it.putExtra("token", mUser.token);
                }
                break;
            case R.id.settings_container:
                it = new Intent(getContext(), SettingsActivity.class);
                it.putExtra("user", mUser);
                break;
            default:
                break;
        }
        if (it != null) {
            startActivity(it);
        }
    }
}
