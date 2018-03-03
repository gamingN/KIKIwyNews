package com.kiki.kikiwynews.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kiki.kikiwynews.R;
import com.kiki.kikiwynews.bean.RelaxOneTimeBean;
import com.kiki.kikiwynews.utils.GlideUtils;
import com.kiki.kikiwynews.utils.TimeUtil;
import com.kiki.kikiwynews.webview.WebViewActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KIKI on 2018/3/3.
 * 346409606@qq.com
 */

public class RelaxAdapter extends BaseQuickAdapter<RelaxOneTimeBean.T1350383429665Bean,BaseViewHolder> {


    public RelaxAdapter(List<RelaxOneTimeBean.T1350383429665Bean> data) {
        super(R.layout.item_wechat,data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final RelaxOneTimeBean.T1350383429665Bean item) {
        ImageView iv_pic=helper.getView(R.id.iv_android_pic);
        if(item.getImgsrc()!=null && !TextUtils.isEmpty(item.getImgsrc())){
            iv_pic.setVisibility(View.VISIBLE);
            GlideUtils.loadMovieTopImg(iv_pic,item.getImgsrc());
        }else{
            iv_pic.setVisibility(View.GONE);
        }

        helper.setText(R.id.tv_android_des, item.getTitle());
        helper.setText(R.id.tv_android_who, item.getEname());
        helper.setText(R.id.tv_android_time, TimeUtil.getTime(item.getPtime()));
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.loadUrl(mContext,item.getUrl(),item.getTitle());
            }
        });
    }

    OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClickListener(String id, String imgUrl,View view);}
}
