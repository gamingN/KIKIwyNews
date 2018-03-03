package com.kiki.kikiwynews.adapter;

import android.support.v7.widget.RecyclerView;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kiki.kikiwynews.R;

import java.util.List;

/**
 * Created by KIKI on 2018/1/24.
 * 346409606@qq.com
 */

public class AdjustmentAdapter extends BaseItemDraggableAdapter<String,BaseViewHolder>{
    public AdjustmentAdapter(List<String> data) {
        super(R.layout.item_adjustment,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tv_adjustment_name,item);
    }

}
