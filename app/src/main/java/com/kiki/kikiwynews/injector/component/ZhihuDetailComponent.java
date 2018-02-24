package com.kiki.kikiwynews.injector.component;

import com.kiki.kikiwynews.injector.module.ZhihuHttpModule;
import com.kiki.kikiwynews.ui.ZhiHuDetailActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by KIKI on 2018/1/18.
 * 346409606@qq.com
 */
@Singleton
@Component(modules = ZhihuHttpModule.class)
public interface ZhihuDetailComponent {
    void injectZhiHuDetail(ZhiHuDetailActivity zhiHuDetailActivity);
}
