package com.littlesparkle.growler.raptor.driver.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lhh.ptrrv.library.PullToRefreshRecyclerView;
import com.littlesparkle.growler.raptor.driver.R;
import com.littlesparkle.growler.raptor.driver.base.RecyclerBaseAdapter;
import com.littlesparkle.growler.raptor.driver.label.LabelFunctionAdapter;
import com.littlesparkle.growler.raptor.driver.label.LabelFunctionItem;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MeFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
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
        mItems.add(new LabelFunctionItem(R.drawable.wallet_membership_grey, R.string.wallet));
        mItems.add(new LabelFunctionItem(R.drawable.message_grey, R.string.message_center));
        mItems.add(new LabelFunctionItem(R.drawable.directions_car_grey, R.string.my_car));
        mAdapter.setDataList(mItems);
        mAdapter.setOnItemClickListener(new RecyclerBaseAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, Object object) {
                showDialogWithMessage("xxxx");
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
