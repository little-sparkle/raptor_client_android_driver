package com.littlesparkle.growler.raptor.driver.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.AppCompatButton;

import com.amap.api.maps2d.model.LatLng;
import com.littlesparkle.growler.library.activity.BaseActivity;
import com.littlesparkle.growler.library.bean.User;
import com.littlesparkle.growler.library.http.BaseHttpSubscriber;
import com.littlesparkle.growler.library.http.DefaultResponse;
import com.littlesparkle.growler.library.log.Logger;
import com.littlesparkle.growler.library.user.driver.DriverRequest;
import com.littlesparkle.growler.raptor.driver.R;
import com.littlesparkle.growler.raptor.driver.activity.event.SignOutEvent;
import com.littlesparkle.growler.raptor.driver.fragment.CurrentFragment;
import com.littlesparkle.growler.raptor.driver.fragment.MeFragment;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {
    @BindView(R.id.view_pager)
    ViewPager mViewPager;

    @BindView(R.id.tab_host)
    SmartTabLayout mSmartTabLayout;

    @BindView(R.id.map_mode)
    AppCompatButton mMapMode;

    @OnClick(R.id.map_mode)
    public void onMapModeClick(AppCompatButton button) {
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra("passenger", new LatLng(39.979004, 116.508715));
        startActivity(intent);
    }

    @BindView(R.id.order_mode)
    AppCompatButton mOrderMode;

    @OnClick(R.id.order_mode)
    public void onOrderModeClick(AppCompatButton button) {
        new DriverRequest().online(new BaseHttpSubscriber<DefaultResponse>(this, this) {
            @Override
            public void onNext(DefaultResponse defaultResponse) {
            }
        }, mUser.user_id, mUser.token);
    }

    @BindView(R.id.shutdown_mode)
    AppCompatButton mShutdownMode;

    @OnClick(R.id.shutdown_mode)
    public void onShutDownModeClick(AppCompatButton button) {
        new DriverRequest().offline(new BaseHttpSubscriber<DefaultResponse>(this, this) {
            @Override
            public void onNext(DefaultResponse defaultResponse) {
            }
        }, mUser.user_id, mUser.token);
    }

    ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void onSignOutEvent(SignOutEvent event) {
        Logger.log("received signout event");
        startActivity(new Intent(this, LoginActivity.class));
        finish();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        ButterKnife.bind(this);

        mIsDoubleClickToQuit = true;

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.tab_item_current, CurrentFragment.class)
                .add(R.string.tab_item_me, MeFragment.class)
                .create());

        mViewPager.setAdapter(adapter);
        mSmartTabLayout.setViewPager(mViewPager);
        mSmartTabLayout.setOnPageChangeListener(mOnPageChangeListener);
    }

    private User mUser;

    @Override
    protected void initData() {
        super.initData();

        Intent it = getIntent();
        if (it != null) {
            mUser = it.getParcelableExtra("user");
            if (mUser == null) {
                mUser = new User();
                mUser.load(this);
            }
        }
    }
}
