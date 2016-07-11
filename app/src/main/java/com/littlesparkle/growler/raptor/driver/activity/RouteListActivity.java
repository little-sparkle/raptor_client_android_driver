package com.littlesparkle.growler.raptor.driver.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.lhh.ptrrv.library.PullToRefreshRecyclerView;
import com.littlesparkle.growler.library.activity.BaseActivity;
import com.littlesparkle.growler.library.base.RecyclerBaseAdapter;
import com.littlesparkle.growler.raptor.driver.R;
import com.littlesparkle.growler.raptor.driver.route.HistoryRouteAdapter;
import com.littlesparkle.growler.raptor.driver.route.HistoryRouteItem;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RouteListActivity extends BaseActivity {
    @BindView(R.id.title_bar_close)
    AppCompatImageView mBack;

    @OnClick(R.id.title_bar_close)
    public void onCloseClick() {
        onBackPressed();
    }

    @BindView(R.id.title_bar_text)
    TextView mTitleText;

    @BindView(R.id.history_route_list)
    PullToRefreshRecyclerView mList;

    private HistoryRouteAdapter mAdapter;
    private List<HistoryRouteItem> mItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);

        String title = getIntent().getStringExtra("title");
        mTitleText.setText(title);

        mAdapter = new HistoryRouteAdapter(this);
        mItems.add(new HistoryRouteItem(0, "已完成", "华贸城10号院", "酒仙桥东路冠捷", "2016.07.11", "2016.07.11", "快车"));
        mItems.add(new HistoryRouteItem(0, "已完成", "华贸城10号院", "酒仙桥东路冠捷", "2016.07.11", "2016.07.11", "快车"));
        mItems.add(new HistoryRouteItem(0, "已完成", "华贸城10号院", "酒仙桥东路冠捷", "2016.07.11", "2016.07.11", "快车"));
        mItems.add(new HistoryRouteItem(0, "已完成", "华贸城10号院", "酒仙桥东路冠捷", "2016.07.11", "2016.07.11", "快车"));
        mAdapter.setDataList(mItems);
        mAdapter.setOnItemClickListener(new RecyclerBaseAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object object) {
            }
        });

        mList.setLayoutManager(new LinearLayoutManager(this));
        mList.getRecyclerView().addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .drawable(R.drawable.divider)
                .build());
        mList.setAdapter(mAdapter);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_route_list;
    }
}
