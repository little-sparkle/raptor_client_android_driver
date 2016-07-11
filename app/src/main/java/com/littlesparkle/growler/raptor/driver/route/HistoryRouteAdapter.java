package com.littlesparkle.growler.raptor.driver.route;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.littlesparkle.growler.library.base.RecyclerBaseAdapter;
import com.littlesparkle.growler.raptor.driver.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HistoryRouteAdapter extends RecyclerBaseAdapter<HistoryRouteAdapter.ViewHolder, HistoryRouteItem> {
    public HistoryRouteAdapter(Context context) {

        super(context);
    }

    @Override
    protected ViewHolder onCreateViewHolderItem(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.route_item, null);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolderItem(ViewHolder holder, int position) {
        if (mDataList != null) {
            HistoryRouteItem item = mDataList.get(position);
            if (item != null) {
                holder.start_time.setText(item.start_time);
                holder.route_type.setText(item.route_type);
                holder.route_status.setText(item.status_description);
                holder.route_start.setText(item.start_point);
                holder.route_end.setText(item.end_point);
            }
            holder.itemView.setTag(item);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.route_time)
        TextView start_time;
        @BindView(R.id.route_type)
        TextView route_type;
        @BindView(R.id.route_status)
        TextView route_status;
        @BindView(R.id.start_point_text)
        TextView route_start;
        @BindView(R.id.end_point_text)
        TextView route_end;

        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
