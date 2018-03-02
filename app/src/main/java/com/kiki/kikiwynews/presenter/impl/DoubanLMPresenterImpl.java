package com.kiki.kikiwynews.presenter.impl;

import com.kiki.kikiwynews.bean.douban.HotMovieBean;
import com.kiki.kikiwynews.http.Callback;
import com.kiki.kikiwynews.http.service.DoubanService;
import com.kiki.kikiwynews.presenter.BasePresenter;
import com.kiki.kikiwynews.presenter.DoubanLMPresenter;

import javax.inject.Inject;

/**
 * Created by KIKI on 2018/2/28.
 * 346409606@qq.com
 */

public class DoubanLMPresenterImpl extends BasePresenter<DoubanLMPresenter.View> implements  DoubanLMPresenter.Presenter {
    private DoubanService mDoubanService;

    @Inject
    public DoubanLMPresenterImpl(DoubanService mDoubanService) {
        this.mDoubanService = mDoubanService;
    }
    @Override
    public void fetchHotMovie() {
        invoke(mDoubanService.fetchHotMovie(),new Callback<HotMovieBean>(){});
    }
}
