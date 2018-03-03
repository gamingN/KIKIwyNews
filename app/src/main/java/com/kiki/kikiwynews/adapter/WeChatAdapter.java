package com.kiki.kikiwynews.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kiki.kikiwynews.R;
import com.kiki.kikiwynews.bean.wx.WXItemBean;
import com.kiki.kikiwynews.utils.GlideUtils;
import com.kiki.kikiwynews.webview.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KIKI on 2018/2/25.
 * 346409606@qq.com
 */

public class WeChatAdapter extends BaseQuickAdapter<WXItemBean,BaseViewHolder> {
    public WeChatAdapter(List<WXItemBean> arrayList) {
        super(R.layout.item_wechat,arrayList);
    }

    @Override
    protected void convert(BaseViewHolder helper, final WXItemBean item) {
        GlideUtils.loadMovieTopImg((ImageView) helper.getView(R.id.iv_android_pic),item.getPicUrl());
        helper.setText(R.id.tv_android_des,item.getTitle());
        helper.setText(R.id.tv_android_who,item.getDescription());
        helper.setText(R.id.tv_android_time,item.getCtime());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.loadUrl(mContext,item.getUrl(),item.getTitle());
            }
        });
    }


}
