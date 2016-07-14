package com.littlesparkle.growler.raptor.driver.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.littlesparkle.growler.library.dialog.DialogHelper;
import com.littlesparkle.growler.raptor.driver.R;
import com.littlesparkle.growler.raptor.driver.activity.SettingsActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MeFragment extends BaseFragment {
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

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

    private void onViewClick(View view) {

        switch (view.getId()) {
            case R.id.wallet_container:
                DialogHelper.showDialogWithMessage(getContext(), getString(R.string.wallet));
                break;
            case R.id.message_container:
                DialogHelper.showDialogWithMessage(getContext(), getString(R.string.message_center));
                break;
            case R.id.my_car_container:
                DialogHelper.showDialogWithMessage(getContext(), getString(R.string.my_car));
                break;
            case R.id.settings_container:
                startActivity(new Intent(getContext(), SettingsActivity.class));
                break;
            default:
                break;
        }
    }
}
