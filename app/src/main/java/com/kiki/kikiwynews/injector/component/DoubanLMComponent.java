package com.kiki.kikiwynews.injector.component;

import com.kiki.kikiwynews.injector.module.DoubanHttpModule;
import com.kiki.kikiwynews.injector.module.DoubanLMModule;
import com.kiki.kikiwynews.ui.DoubanLMFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by KIKI on 2018/2/28.
 * 346409606@qq.com
 */

@Singleton
@Component(modules = {DoubanHttpModule.class, DoubanLMModule.class})
public interface DoubanLMComponent {
    void injectDoubanLMFragment(DoubanLMFragment doubanLMFragment);
}
