package com.littlesparkle.growler.raptor.driver.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lhh.ptrrv.library.PullToRefreshRecyclerView;
import com.littlesparkle.growler.library.base.RecyclerBaseAdapter;
import com.littlesparkle.growler.library.webview.WebViewActivity;
import com.littlesparkle.growler.raptor.driver.R;
import com.littlesparkle.growler.raptor.driver.activity.RouteListActivity;
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
        mItems.add(new LabelFunctionItem(0, R.drawable.wallet_icon, R.string.wallet,
                "http://www.baidu.com"));
        mItems.add(new LabelFunctionItem(1, R.drawable.message_icon, R.string.message_center,
                "http://www.sina.com.cn"));
        mItems.add(new LabelFunctionItem(2, R.drawable.my_car_icon, R.string.my_car,
                "http://www.github.com"));
        mItems.add(new LabelFunctionItem(3, R.drawable.my_route_icon, R.string.my_route_label,
                "http://www.github.com"));
        mAdapter.setDataList(mItems);
        mAdapter.setOnItemClickListener(new RecyclerBaseAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object object) {
                LabelFunctionItem item = (LabelFunctionItem) object;
                Intent it;
                switch (item.id) {
                    case 0:
                    case 1:
                    case 2:
                        it = new Intent(getContext(), WebViewActivity.class);
                        it.putExtra("title", getString(item.labelName));
                        it.putExtra("url", item.url);
                        startActivity(it);
                        break;
                    case 3:
                        it = new Intent(getContext(), RouteListActivity.class);
                        it.putExtra("title", getString(item.labelName));
                        startActivity(it);
                        break;
                    default:
                        break;
                }
            }
        });

        mList.setLayoutManager(new LinearLayoutManager(getContext()));
        HorizontalDividerItemDecoration d = new HorizontalDividerItemDecoration.Builder(getContext())
                .drawable(R.drawable.divider)
                .build();
        mList.getRecyclerView().addItemDecoration(d);
        mList.setAdapter(mAdapter);
        mList.getRecyclerView().setOverScrollMode(View.OVER_SCROLL_NEVER);
    }
}
