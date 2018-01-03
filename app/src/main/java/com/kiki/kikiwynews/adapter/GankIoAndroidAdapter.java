package com.kiki.kikiwynews.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kiki.kikiwynews.R;
import com.kiki.kikiwynews.webview.WebViewActivity;
import com.kiki.kikiwynews.bean.GankIoDataBean;
import com.kiki.kikiwynews.utils.GlideUtils;
import com.kiki.kikiwynews.utils.TimeUti;

import java.util.ArrayList;

/**
 * Created by KIKI on 2017/12/26.
 * 346409606@qq.com
 */

public class GankIoAndroidAdapter extends BaseQuickAdapter<GankIoDataBean.ResultBean,BaseViewHolder> {
    public GankIoAndroidAdapter(ArrayList<GankIoDataBean.ResultBean> data) {
        super(R.layout.item_left_fragment,data);
    }

    /**
     *
     * @param helper BaseViewHolder
     * @param item 集合中的单个对象
     */
    @Override
    protected void convert(BaseViewHolder helper, final GankIoDataBean.ResultBean item) {
        ImageView iv_item_pre=helper.getView(R.id.iv_item_pre);
        if(item.getImages()!=null && item.getImages().size()>0 && !TextUtils.isEmpty(item.getImages().get(0))){//是否存在数据
            iv_item_pre.setVisibility(View.VISIBLE);
            GlideUtils.loadMovieTopImg(iv_item_pre,item.getImages().get(0));
        }else{
            iv_item_pre.setVisibility(View.GONE);
        }
        helper.setText(R.id.tv_item_pre,item.getDesc());
        helper.setText(R.id.tv_android_who,item.getWho());
        helper.setText(R.id.tv_android_time, TimeUti.getTranslateTime(item.getPublishedAt()));

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.loadUrl(mContext,item.getUrl(),item.getSource());
            }
        });
    }


}
