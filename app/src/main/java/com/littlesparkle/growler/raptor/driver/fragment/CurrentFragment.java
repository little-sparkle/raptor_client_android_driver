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
import com.littlesparkle.growler.library.log.Logger;
import com.littlesparkle.growler.library.user.driver.DriverInfoResponse;
import com.littlesparkle.growler.library.user.driver.DriverRequest;
import com.littlesparkle.growler.raptor.driver.R;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrentFragment extends Fragment {
    @BindView(R.id.online_hours)
    TextView mOnlineHours;

    @BindView(R.id.order_count)
    TextView mOrderCount;

    @BindView(R.id.sum)
    TextView mSum;

    @BindView(R.id.bargain_rate)
    TextView mBargainRate;

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
            public void onNext(DriverInfoResponse driverInfoResponse) {
                Logger.log("#######", driverInfoResponse.toString());
                mDriver = driverInfoResponse.data.driver;
                updateView();
            }
        }, mUser.user_id, mUser.user_id, mUser.token);
    }

    private void initData() {
        mOnlineHours.setText(String.format(getString(R.string.online_hours), new DecimalFormat(".0").format(0)));
        mOrderCount.setText(String.format(getString(R.string.order_count), 0));
        mSum.setText(String.format(getString(R.string.sum), new DecimalFormat(".0").format(0)));
        mBargainRate.setText(String.format(getString(R.string.bargain_rate), 0));
    }

    private void updateView() {
        if (mDriver != null) {
            mOrderCount.setText(String.format(getString(R.string.order_count), mDriver.order_count));
            if (mDriver.order_count != 0) {
                mBargainRate.setText(String.format(getString(R.string.bargain_rate),
                        (mDriver.order_success_count * 100) / mDriver.order_count));
            } else {
                mBargainRate.setText(String.format(getString(R.string.bargain_rate), 0));
            }
        }
    }
}
