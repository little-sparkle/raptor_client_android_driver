package com.littlesparkle.growler.raptor.driver.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.littlesparkle.growler.library.activity.BaseActivity;
import com.littlesparkle.growler.library.bean.Driver;
import com.littlesparkle.growler.library.bean.User;
import com.littlesparkle.growler.library.http.BaseHttpSubscriber;
import com.littlesparkle.growler.library.http.ErrorResponse;
import com.littlesparkle.growler.library.http.api.Api;
import com.littlesparkle.growler.library.log.Logger;
import com.littlesparkle.growler.library.user.UserManager;
import com.littlesparkle.growler.library.user.driver.DriverInfoResponse;
import com.littlesparkle.growler.library.user.driver.DriverRequest;
import com.littlesparkle.growler.raptor.driver.R;
import com.littlesparkle.growler.raptor.driver.activity.event.SignOutEvent;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrentFragment extends Fragment {
    @BindView(R.id.online_hours)
    TextView mOnlineHours;

    @BindView(R.id.order_count)
    TextView mOrderCount;

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

        return inflater.inflate(R.layout.fragment_current, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        initData();
        initView();
    }

    private Driver mDriver;

    private void initView() {
        Logger.log("get driver info: id = " + mUser.user_id + ", token = " + mUser.token);
        new DriverRequest().getDriverInfo(new BaseHttpSubscriber<DriverInfoResponse>(
                (BaseActivity) getActivity(), (BaseActivity) getActivity()) {
            @Override
            protected void onError(ErrorResponse error) {
                super.onError(error);

                if (Api.needReSignIn(error.data.err_no)) {
                    UserManager.signOut(getActivity());
                    EventBus.getDefault().post(new SignOutEvent());
                }
            }

            @Override
            public void onNext(DriverInfoResponse driverInfoResponse) {
                Logger.log("#######", driverInfoResponse.toString());
                mDriver = driverInfoResponse.data.driver;
                updateView();
            }
        }, mUser.user_id, mUser.user_id, mUser.token);
    }

    private void initData() {
    }

    private void updateView() {
        if (mDriver != null) {
            mOrderCount.setText(mDriver.order_count);
        }
    }
}
