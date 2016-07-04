package com.littlesparkle.growler.raptor.driver.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.littlesparkle.growler.raptor.driver.R;

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CurrentFragment extends BaseFragment {
    @BindView(R.id.online_hours)
    TextView mOnlineHours;

    @BindView(R.id.order_count)
    TextView mOrderCount;

    @BindView(R.id.sum)
    TextView mSum;

    @BindView(R.id.bargain_rate)
    TextView mBargainRate;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_current, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        fakeData();
    }

    private void fakeData() {
        float hours = 1.5f;
        mOnlineHours.setText(String.format(getString(R.string.online_hours), new DecimalFormat(".0").format(hours)));
        mOrderCount.setText(String.format(getString(R.string.order_count), 9));
        float sum = 100.5f;
        mSum.setText(String.format(getString(R.string.sum), new DecimalFormat(".0").format(sum)));
        mBargainRate.setText(String.format(getString(R.string.bargain_rate), 95));
    }
}
