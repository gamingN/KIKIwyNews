package com.kiki.kikiwynews.injector.component;

import com.kiki.kikiwynews.injector.module.ZhihuHttpModule;
import com.kiki.kikiwynews.injector.module.ZhihuThemeModule;
import com.kiki.kikiwynews.ui.ZhihuThemeActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by KIKI on 2018/2/23.
 * 346409606@qq.com
 */

@Singleton
@Component(modules = {ZhihuHttpModule.class, ZhihuThemeModule.class})
public interface ZhihuThemeComponent {
    void injectZhiHuTheme(ZhihuThemeActivity zhihuThemeActivity);
}
