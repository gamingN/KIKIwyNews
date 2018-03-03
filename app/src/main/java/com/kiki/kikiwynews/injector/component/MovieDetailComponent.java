package com.kiki.kikiwynews.injector.component;

import com.kiki.kikiwynews.injector.module.DoubanHttpModule;
import com.kiki.kikiwynews.ui.MovieDetailActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by KIKI on 2018/3/2.
 * 346409606@qq.com
 */
@Singleton
@Component(modules = DoubanHttpModule.class)
public interface MovieDetailComponent {
    void injectMovieDetail(MovieDetailActivity movieDetailActivity);
}
