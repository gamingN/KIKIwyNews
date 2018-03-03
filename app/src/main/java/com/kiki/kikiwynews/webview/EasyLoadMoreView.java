package com.kiki.kikiwynews.webview;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.kiki.kikiwynews.R;

/**
 * Created by KIKI on 2018/1/2.
 * 346409606@qq.com
 */

public class EasyLoadMoreView extends LoadMoreView {
    @Override
    public int getLayoutId() {
        return R.layout.base_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return R.id.load_more_loading_view;
    }

    @Override
    protected int getLoadFailViewId() {
        return R.id.load_more_load_fail_view;
    }

    @Override
    protected int getLoadEndViewId() {
        return R.id.load_more_load_end_view;
    }
}
