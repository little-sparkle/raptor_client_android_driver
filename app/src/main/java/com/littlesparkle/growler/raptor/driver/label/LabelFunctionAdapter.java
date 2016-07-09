package com.littlesparkle.growler.raptor.driver.label;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.littlesparkle.growler.library.base.RecyclerBaseAdapter;
import com.littlesparkle.growler.raptor.driver.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LabelFunctionAdapter extends RecyclerBaseAdapter<LabelFunctionAdapter.ViewHolder, LabelFunctionItem> {
    public LabelFunctionAdapter(Context context) {

        super(context);
    }

    @Override
    protected ViewHolder onCreateViewHolderItem(ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.label_funtion_item, null);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    protected void onBindViewHolderItem(ViewHolder holder, int position) {
        if (mDataList != null) {
            LabelFunctionItem item = mDataList.get(position);
            if (item != null) {
                holder.labelIcon.setImageResource(item.labelIcon);
                holder.labelName.setText(item.labelName);
            }
            holder.itemView.setTag(item);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.label_icon)
        AppCompatImageView labelIcon;
        @BindView(R.id.label_name)
        TextView labelName;
        @BindView(R.id.label_arrow)
        AppCompatImageView labelArrow;


        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
