package com.littlesparkle.growler.raptor.driver.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lhh.ptrrv.library.PullToRefreshRecyclerView;
import com.littlesparkle.growler.raptor.driver.R;
import com.littlesparkle.growler.raptor.driver.activity.WebViewActivity;
import com.littlesparkle.growler.raptor.driver.base.RecyclerBaseAdapter;
import com.littlesparkle.growler.raptor.driver.label.LabelFunctionAdapter;
import com.littlesparkle.growler.raptor.driver.label.LabelFunctionItem;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeFragment extends BaseFragment {
    @BindView(R.id.function_list)
    PullToRefreshRecyclerView mList;

    private LabelFunctionAdapter mAdapter;
    private List<LabelFunctionItem> mItems = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        mAdapter = new LabelFunctionAdapter(getContext());
        mItems.add(new LabelFunctionItem(0, R.drawable.wallet_membership_grey, R.string.wallet,
                "http://www.baidu.com"));
        mItems.add(new LabelFunctionItem(1, R.drawable.message_grey, R.string.message_center,
                "http://www.sina.com.cn"));
        mItems.add(new LabelFunctionItem(2, R.drawable.directions_car_grey, R.string.my_car,
                "http://www.github.com"));
        mAdapter.setDataList(mItems);
        mAdapter.setOnItemClickListener(new RecyclerBaseAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object object) {
                LabelFunctionItem item = (LabelFunctionItem) object;
                Intent it = new Intent(getContext(), WebViewActivity.class);
                it.putExtra("url", item.url);
                startActivity(it);
            }
        });

        mList.setLayoutManager(new LinearLayoutManager(getContext()));
        HorizontalDividerItemDecoration d = new HorizontalDividerItemDecoration.Builder(getContext())
                .drawable(R.drawable.divider)
                .build();
        mList.getRecyclerView().addItemDecoration(d);
        mList.setAdapter(mAdapter);
    }
}
