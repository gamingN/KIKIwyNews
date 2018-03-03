package com.kiki.kikiwynews.adapter;

import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kiki.kikiwynews.R;
import com.kiki.kikiwynews.bean.TopnewsBean;
import com.kiki.kikiwynews.utils.GlideUtils;
import com.kiki.kikiwynews.webview.WebViewActivity;

import java.util.List;

/**
 * Created by KIKI on 2018/2/28.
 * 346409606@qq.com
 */

public class TopnewsAdapter extends BaseQuickAdapter<TopnewsBean.ResultBean.DataBean,BaseViewHolder>{
    public TopnewsAdapter(List<TopnewsBean.ResultBean.DataBean> data) {
        super(R.layout.item_news,data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final TopnewsBean.ResultBean.DataBean item) {
        GlideUtils.loadMovieTopImg((ImageView) helper.getView(R.id.iv_icon_topnews),item.getThumbnail_pic_s());
        helper.setText(R.id.tv_title_topnews,item.getTitle());
        helper.setText(R.id.tv_date_topnews,item.getDate());
        helper.setText(R.id.tv_author_topnews,item.getAuthor_name());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewActivity.loadUrl(mContext,item.getUrl(),item.getTitle());
            }
        });
    }
}
