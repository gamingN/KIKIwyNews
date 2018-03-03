package com.kiki.kikiwynews.injector.component;

import com.kiki.kikiwynews.injector.module.TopnewsHttpModule;
import com.kiki.kikiwynews.injector.module.TopnewsModule;
import com.kiki.kikiwynews.ui.TopnewsFragment;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;

/**
 * Created by KIKI on 2018/2/28.
 * 346409606@qq.com
 */
@Singleton
@Component(modules = {TopnewsHttpModule.class, TopnewsModule.class})
public interface TopnewsComponent {
    void injectTopnewsFragment(TopnewsFragment topnewsFragment);
}
