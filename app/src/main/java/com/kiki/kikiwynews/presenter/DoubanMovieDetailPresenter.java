package com.kiki.kikiwynews.presenter;

import com.kiki.kikiwynews.bean.douban.MovieDetailBean;

import dagger.Module;

/**
 * Created by KIKI on 2018/2/28.
 * 346409606@qq.com
 */

public interface DoubanMovieDetailPresenter {
    interface View extends BaseView<MovieDetailBean>{

    }

    interface Presenter{
        void fetchMovieDetail(String id);
    }
}
