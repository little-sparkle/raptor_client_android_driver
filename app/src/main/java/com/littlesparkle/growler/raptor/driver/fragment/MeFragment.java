package com.littlesparkle.growler.raptor.driver.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.littlesparkle.growler.library.bean.User;
import com.littlesparkle.growler.library.log.Logger;
import com.littlesparkle.growler.library.webview.WebViewActivity;
import com.littlesparkle.growler.raptor.driver.R;
import com.littlesparkle.growler.raptor.driver.activity.SettingsActivity;
import com.littlesparkle.growler.raptor.driver.backend.Api;

import butterknife.BindView;
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

    @OnClick(R.id.performance_review)
    public void onPerformanceClick(View view) {
        onViewClick(view);
    }

    @OnClick(R.id.avatar)
    public void onAvatarClick(View view) {
        onViewClick(view);
    }

    @BindView(R.id.name)
    TextView mName;

    @BindView(R.id.rating)
    TextView mRating;

    @OnClick(R.id.name)
    public void onNameClick(View view) {
        onViewClick(view);
    }

    @OnClick(R.id.route_group)
    public void onMyRouteClick(View view) {
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

        initView();
    }

    private void onViewClick(View view) {
        Intent it = null;
        switch (view.getId()) {
            case R.id.wallet_container:
                it = createWebViewIntent(getString(R.string.wallet),
                        Api.WALLET);
                break;
            case R.id.message_container:
                it = createWebViewIntent(getString(R.string.message_center),
                        Api.MESSAGE_CENTER);
                break;
            case R.id.my_car_container:
                it = createWebViewIntent(getString(R.string.my_car),
                        Api.MY_CAR);
                break;
            case R.id.performance_review:
                it = createWebViewIntent(getString(R.string.performance_review),
                        Api.PERFORMANCE_REVIEW);
                break;
            case R.id.settings_container:
                it = new Intent(getContext(), SettingsActivity.class);
                it.putExtra("user", mUser);
                break;
            case R.id.avatar:
            case R.id.name:
                it = createWebViewIntent(getString(R.string.personal_information),
                        Api.PERSONAL_INFORMATION);
                break;
            case R.id.route_group:
                it = createWebViewIntent(getString(R.string.my_route_label),
                        Api.MY_ROUTE);
                break;
            default:
                break;
        }
        if (it != null) {
            startActivity(it);
        }
    }

    private Intent createWebViewIntent(@NonNull String title, @NonNull String url) {
        Intent it = new Intent(getContext(), WebViewActivity.class);
        it.putExtra("title", title);
        it.putExtra("url", url);
        if (mUser != null) {
            it.putExtra("user_id", mUser.user_id);
            it.putExtra("token", mUser.token);
        }
        return it;
    }

    private void initView() {
        if (mUser != null) {
            if (!"".equals(mUser.nickname)) {
                mName.setText(mUser.nickname);
            }
            mRating.setText(String.valueOf(mUser.rate));
        }
    }
}
