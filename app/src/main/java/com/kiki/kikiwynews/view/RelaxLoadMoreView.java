package com.kiki.kikiwynews.view;

import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.kiki.kikiwynews.R;

/**
 * Created by KIKI on 2018/3/3.
 * 346409606@qq.com
 */

public class RelaxLoadMoreView extends LoadMoreView{
    @Override
    public int getLayoutId() {
        return R.layout.base_load_more;
    }

    @Override
    protected int getLoadingViewId() {
        return 0;
    }

    @Override
    protected int getLoadFailViewId() {
        return 0;
    }

    @Override
    protected int getLoadEndViewId() {
        return 0;
    }
}
