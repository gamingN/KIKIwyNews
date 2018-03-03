package com.kiki.kikiwynews.presenter.impl;

import com.kiki.kikiwynews.bean.douban.MovieDetailBean;
import com.kiki.kikiwynews.http.Callback;
import com.kiki.kikiwynews.http.service.DoubanService;
import com.kiki.kikiwynews.presenter.BasePresenter;
import com.kiki.kikiwynews.presenter.DoubanMovieDetailPresenter;

import javax.inject.Inject;

/**
 * Created by KIKI on 2018/2/28.
 * 346409606@qq.com
 */

public class DoubanMovieDetailPresenterImpl extends BasePresenter<DoubanMovieDetailPresenter.View> implements DoubanMovieDetailPresenter.Presenter{

    private DoubanService mDoubanService;

    @Inject
    public DoubanMovieDetailPresenterImpl(DoubanService mDoubanService){
        this.mDoubanService=mDoubanService;
    }

    @Override
    public void fetchMovieDetail(String id) {
        invoke(mDoubanService.fetchMovieDetail(id),new Callback<MovieDetailBean>(){});
    }
}
