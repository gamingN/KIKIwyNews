package com.kiki.kikiwynews.injector.component;

import com.kiki.kikiwynews.injector.module.ZhihuHomeModule;
import com.kiki.kikiwynews.injector.module.ZhihuHttpModule;
import com.kiki.kikiwynews.ui.ZhiHuHomeFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by KIKI on 2018/1/9.
 * 346409606@qq.com
 */

@Singleton
@Component(modules = {ZhihuHttpModule.class, ZhihuHomeModule.class})
public interface ZhihuHomeComponent {
    void injectZhihuhome(ZhiHuHomeFragment zhiHuHomeFragment);
}
