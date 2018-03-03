package com.kiki.kikiwynews.ui;

import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.kiki.kikiwynews.LoadingBaseActivity;
import com.kiki.kikiwynews.R;
import com.kiki.kikiwynews.presenter.BasePresenter;

/**
 * Created by KIKI on 2018/1/17.
 * 346409606@qq.com
 */

public abstract class ZhihuDetailBaseActivity<T extends BasePresenter> extends LoadingBaseActivity<T>{

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detail_base;
    }

    @Override
    public int setFrameLayoutId() {
        return R.id.fl_base_content;
    }

    protected ImageView detailBarImage;
    protected Toolbar toolbarZhihuDetail;
    protected TextView detatilBarCopyright;

    @Override
    protected void initUI() {
        detailBarImage= (ImageView) findViewById(R.id.detail_bar_image);
        toolbarZhihuDetail= (Toolbar) findViewById(R.id.toolbar_zhihu_detail);
        detatilBarCopyright= (TextView) findViewById(R.id.detail_bar_copyright);
        setToolbar(toolbarZhihuDetail,"");
    }
}
